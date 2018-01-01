package com.checc.ao;

import java.util.ArrayList;
import java.util.List;

import ng.bayue.common.Page;
import ng.bayue.fastdfs.ImageUrlUtil;
import ng.bayue.util.StringUtils;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.checc.domain.AuctionRecordDO;
import com.checc.domain.CheccUserDO;
import com.checc.domain.ItemPictureDO;
import com.checc.domain.TopicDO;
import com.checc.domain.TopicItemDO;
import com.checc.domain.UserCurrencyDO;
import com.checc.enums.AuctionRecordTypeEnum;
import com.checc.enums.TopicStatusEnum;
import com.checc.service.AuctionRecordService;
import com.checc.service.ItemPictureService;
import com.checc.service.TopicItemService;
import com.checc.service.TopicService;
import com.checc.service.UserCurrencyService;
import com.checc.vo.UCAuctionListVO;
import com.checc.vo.front.CurrencyRecordVO;
import com.checc.vo.front.UserCenterVO;

@Service
public class UserCenterAO {

	private Logger logger = LoggerFactory.getLogger(UserCenterAO.class);

	@Autowired
	private UserCurrencyService userCurrencyService;
	@Autowired
	private AuctionRecordService auctionRecordService;
	@Autowired
	private TopicItemService topicItemService;
	@Autowired
	private TopicService topicService;
	@Autowired
	private ItemPictureService pictureService;
	@Autowired
	private ImageUrlUtil imageUrlUtils;

	public void userCurrencyInfo(Model model, CheccUserDO userDO) {
		Long userId = userDO.getId();
		UserCenterVO vo = new UserCenterVO();
		UserCurrencyDO uc = userCurrencyService.selectByUserId(userId);
		if (null != uc) {
			vo.setTotalCurrency(uc.getTotalCurrency());
			vo.setRefund(uc.getRefund());
			vo.setUserId(userId);
			vo.setMobile(StringUtils.securityMobile(userDO.getMobile()));
		}

		AuctionRecordDO ar = new AuctionRecordDO();
		ar.setUserId(userDO.getId());
		List<AuctionRecordDO> list = auctionRecordService.selectDynamic(ar);
		vo.setCurrencyTradeNum(list.size());

		model.addAttribute("userDetailVO", vo);
	}

	public void ucAuctionList(Model model, CheccUserDO userDO, String recordType, Integer pageNo) {
		Page<AuctionRecordDO> page = auctionRecordService.queryPageListUCAuction(userDO.getId(),
				recordType, pageNo, 10);

		Page<UCAuctionListVO> pageRes = new Page<UCAuctionListVO>();
		pageRes.setPageNo(page.getPageNo());
		pageRes.setPageSize(page.getPageSize());
		pageRes.setTotalCount(page.getTotalCount());

		List<AuctionRecordDO> listDb = page.getList();
		List<UCAuctionListVO> listResult = new ArrayList<UCAuctionListVO>();
		if (CollectionUtils.isNotEmpty(listDb)) {
			List<Long> tpIds = new ArrayList<Long>();
			for (AuctionRecordDO ar : listDb) {
				Long tpId = ar.getTopicItemId();
				if(!tpIds.contains(tpId)){
					tpIds.add(tpId);
				}
			}
			List<TopicItemDO> tiList = topicItemService.selectByIds(tpIds);
			List<Long> itemIds = new ArrayList<Long>();
			for (TopicItemDO ti : tiList) {
				Long itemId = ti.getItemId();
				if(!itemIds.contains(itemId)){
					itemIds.add(itemId);
				}

				// 获取商品进度状态
				TopicDO topicDO = topicService.selectById(ti.getTopicId());
				String topicStatus = AuctionCommonAO.getTopicStatus(topicDO.getStartTime(),
						topicDO.getEndTime());

				UCAuctionListVO vo = new UCAuctionListVO();
				long tpId = ti.getId();
				vo.setTpId(tpId);
				vo.setItemId(itemId);
				vo.setItemTitle(ti.getItemTitle());
				vo.setItemStatus(topicStatus);
				for (AuctionRecordDO ar : listDb) {
					if (tpId == ar.getTopicItemId().longValue()) {
						vo.setCurrenctAuctPrice(ar.getCurrentAuctPrice() != null ? ar
								.getCurrentAuctPrice().doubleValue() : 0);
						if (TopicStatusEnum.End.getCode().equals(topicStatus)) {
							vo.setIsWinner(true);
						}
						break;
					}
				}

				listResult.add(vo);
			}

			List<ItemPictureDO> pictures = pictureService.selectByItemIds(itemIds);
			for (UCAuctionListVO al : listResult) {
				long tpId = al.getTpId();
				long itemId = al.getItemId();
				for (AuctionRecordDO ar : listDb) {
					if (tpId == ar.getTopicItemId().longValue()) {
						for (ItemPictureDO pic : pictures) {
							if (itemId == pic.getItemId().longValue()) {
								al.setPicture(imageUrlUtils.getFileFullUrl(pic.getPicture()));
								break;
							}
						}
						break;
					}
				}
			}

		}
		pageRes.setList(listResult);

		model.addAttribute("page", pageRes);
	}

	public void currencyRecList(Model model, Long userId, Integer pageNo, Integer pageSize) {
		AuctionRecordDO t = new AuctionRecordDO();
		t.setUserId(userId);
		Page<AuctionRecordDO> page = auctionRecordService.queryPageListDynamicAndStartPageSize(t,
				pageNo, pageSize);

		Page<CurrencyRecordVO> pageRes = new Page<CurrencyRecordVO>();
		pageRes.setPageNo(page.getPageNo());
		pageRes.setPageSize(page.getPageSize());
		pageRes.setTotalCount(page.getTotalCount());

		List<CurrencyRecordVO> listRes = new ArrayList<CurrencyRecordVO>();
		List<AuctionRecordDO> listDb = page.getList();
		if (CollectionUtils.isEmpty(listDb)) {
			pageRes.setList(listRes);
			model.addAttribute("page", pageRes);
			return;
		}

		List<Long> tpIds = new ArrayList<Long>();
		for (AuctionRecordDO ar : listDb) {
			Long tpId = ar.getTopicItemId();
			if (tpId != null && !tpIds.contains(tpId)) {
				tpIds.add(tpId);
			}

			CurrencyRecordVO vo = new CurrencyRecordVO();
			vo.setId(ar.getId());
			vo.setTpId(tpId);
			vo.setBidTime(ar.getCreateTime());
			vo.setRecordType(ar.getRecordType());
			vo.setTotalCurrency(ar.getTotalCurrency());

			String recordType = ar.getRecordType();
			if (AuctionRecordTypeEnum.AUCTION.code.equals(recordType)) {
				vo.setAuctNum(ar.getBidNum());
			} else if (AuctionRecordTypeEnum.EXCHANGE.code.equals(recordType)) {
				vo.setAuctNum(ar.getExchangeCount());
			} else {
				vo.setAuctNum(ar.getRefundNum());
			}

			listRes.add(vo);
		}

		List<TopicItemDO> listTi = topicItemService.selectByIds(tpIds);
		for (CurrencyRecordVO cvo : listRes) {
			if (AuctionRecordTypeEnum.DEPOSIT.code.equals(cvo.getRecordType())) {
				continue;
			}
			long tpId = cvo.getTpId();
			for (TopicItemDO ti : listTi) {
				if (tpId == ti.getId().longValue()) {
					cvo.setItemTitle(ti.getItemTitle());
					break;
				}
			}
		}

		pageRes.setList(listRes);
		model.addAttribute("page", pageRes);
		model.addAttribute("arcTypes", AuctionRecordTypeEnum.values());

	}
}
