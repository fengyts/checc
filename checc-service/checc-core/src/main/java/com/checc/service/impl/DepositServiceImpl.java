package com.checc.service.impl;

import java.util.Date;

import ng.bayue.exception.CommonServiceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.checc.domain.AuctionRecordDO;
import com.checc.domain.CheccUserDO;
import com.checc.dto.DepositInsertDTO;
import com.checc.enums.AuctionRecordTypeEnum;
import com.checc.service.AuctionRecordService;
import com.checc.service.CheccUserService;
import com.checc.service.DepositService;
import com.checc.service.UserCurrencyService;

@Service
public class DepositServiceImpl implements DepositService {

	private static Logger logger = LoggerFactory.getLogger(DepositServiceImpl.class);

	@Autowired
	private AuctionRecordService auctionRecordService;
	@Autowired
	private UserCurrencyService userCurrencyService;
	@Autowired
	private CheccUserService checcUserService;

	@Override
	@Transactional
	public int deposit(DepositInsertDTO dto) throws CommonServiceException {
		logger.info("deposit currency start, params: depositOrderId-" + dto.getDepositId()
				+ ";userId-" + dto.getUserId());
		Long userId = dto.getUserId();
		if (null == userId || userId.longValue() < 0l) {
			logger.info("用户充值失败,userId 参数为空");
			return -1;
		}
		CheccUserDO userDO = checcUserService.selectById(userId);
		if (null == userDO) {
			logger.info("用户充值失败,用户不存在");
			return -1;
		}
		Integer depositAmount = dto.getDepositAmount();

		AuctionRecordDO ar = new AuctionRecordDO();
		ar.setUserId(userDO.getId());
		ar.setNickname(userDO.getNickname());
		ar.setMobile(userDO.getMobile());
		ar.setDepositId(dto.getDepositId());
		ar.setDepositType(dto.getDepositType());
		ar.setDepositAmount(depositAmount);
		ar.setDiscountId(dto.getDiscountId());
		ar.setDiscount(dto.getDiscount());

		ar.setRecordType(AuctionRecordTypeEnum.DEPOSIT.code);
		ar.setCreateTime(new Date());
		
		long res = 0;
		// 校验用户西币表是否存在该用户信息
		/*UserCurrencyDO uc = userCurrencyService.selectByUserId(userId);
		if(null == uc){
			uc = new UserCurrencyDO();
			uc.setUserId(userId);
			uc.setTotalCurrency(depositAmount);
			uc.setFreeze(0);
			uc.setRefund(0);
			uc.setModifyTime(new Date());
			uc.setCreateTime(new Date());
			res = userCurrencyService.insert(uc);
		} else {
			res = userCurrencyService.increaseTotalCurrency(userDO.getId(), dto.getDepositAmount());
		}*/
		res = userCurrencyService.increaseTotalCurrency(userDO.getId(), depositAmount);
		if (res <= 0l) {
			logger.info("user deposit failure: insert deposit record exception");
			throw new CommonServiceException("用户充值异常：用户增加西币值失败");
		}
		
		res = auctionRecordService.insert(ar);
		if (res <= 0) {
			logger.info("user deposit failure: insert deposit record exception");
			throw new CommonServiceException("用户充值异常：插入充值记录失败");
		}

		return Long.valueOf(res).intValue();
	}

}
