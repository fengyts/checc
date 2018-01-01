package com.checc.ao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import ng.bayue.common.CommonResultCode;
import ng.bayue.common.CommonResultMessage;
import ng.bayue.common.model.TokenModel;
import ng.bayue.enums.RedisModelStatusEnum;
import ng.bayue.service.RedisCacheService;
import ng.bayue.service.TokenService;
import ng.bayue.util.crypto.AESUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.alibaba.druid.util.StringUtils;
import com.checc.domain.DepositConfigDO;
import com.checc.dto.DepositDTO;
import com.checc.service.DepositConfigService;

import constants.TokenTypeConstant;

@Service
public class DepositAO {

	private static final Logger logger = LoggerFactory.getLogger(DepositAO.class);

	/** 立即支付操作安全检查 用户锁，同一用户同一时间一次只能操作一次 */
	private static final String LOCK_PREPAY_SECURITY_CHECK = "LOCK_PREPAY_SECURITY_CHECK_";

	private static AESUtils aes = new AESUtils();

	@Resource(name = "redisCacheService1")
	private RedisCacheService redisCacheService;
	@Autowired
	private DepositConfigService depositConfigService;
	@Autowired
	private TokenService tokenService;

	public List<DepositConfigDO> getDepositList(Model model, Long userId) {
		if (null == userId) {
			return new ArrayList<DepositConfigDO>();
		}
		List<DepositConfigDO> listAll = depositConfigService.selectDynamic(new DepositConfigDO());
		TokenModel tm = new TokenModel(userId.toString(),
				TokenTypeConstant.DepositTokenTypeEnum.DEPOSIT.desc);
		tokenService.create(tm);

		// AESUtils aes = new AESUtils();
		model.addAttribute("depositTk", aes.encrypt(tm.getToken()));

		return listAll;
	}

	public CommonResultMessage depositSecurityAct(DepositDTO dto, Long userId) {
		String securityLock = LOCK_PREPAY_SECURITY_CHECK + userId;
		try {
			if (redisCacheService.lock(securityLock)) {
				String depositTk = dto.getDepositTk();
				Long discountId = dto.getDiscountId();
				Integer depositAmt = dto.getDepositAmt();
				Double discount = dto.getDiscount();

				if (StringUtils.isEmpty(depositTk)) {
					return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
							CommonResultCode.SystemError.REQ_ERROR.desc);
				}
				if (null == discountId || discountId.longValue() < 0l || null == discount
						|| discount.doubleValue() <= 0d || discount.doubleValue() > 1d
						|| null == depositAmt || depositAmt < 0) {
					return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
							CommonResultCode.SystemError.REQ_ERROR.desc);
				}

				TokenModel tm = new TokenModel(userId.toString(),
						TokenTypeConstant.DepositTokenTypeEnum.DEPOSIT.desc);
				depositTk = aes.decrypt(depositTk);
				tm.setToken(depositTk);
				RedisModelStatusEnum tkCheckStatus = tokenService.check(tm);
				if (RedisModelStatusEnum.CORRECT != tkCheckStatus) {
					return new CommonResultMessage(CommonResultCode.SystemError.REQ_ERROR.code,
							CommonResultCode.SystemError.REQ_ERROR.desc);
				}

				return CommonResultMessage.success();
			} else {
				return CommonResultMessage
						.failure(CommonResultCode.BusinessError.ONCE_EVERY_TIME.desc);
			}

		} catch (Exception e) {
			logger.error("redis exception: 用户立即支付安全检查异常：{}", e);
			return CommonResultMessage.failure(CommonResultCode.SystemError.REQ_ERROR.desc);
		} finally {
			try {
				redisCacheService.unLock(securityLock);
			} catch (Exception e) {
				logger.error("redis exception: 用户立即支付安全检查异常：{}", e);
			}
		}
	}
}
