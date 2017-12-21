package com.chb.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.chb.constant.ResultCode;
import com.chb.dao.GoodsDao;
import com.chb.entity.Goods;
import com.chb.entity.Page;
import com.chb.entity.PageInfo;
import com.chb.entity.ResultMessage;
import com.chb.entity.Shop;
import com.chb.service.GoodsService;
import com.chb.utils.Base64Util;

/**
 * 菜品服务实现类
 * 
 * @author shilim
 *
 */

@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsDao goodsDao;

	@Override
	public Goods findByGoodsName(String goodsName){
//		return goodsDao.findByGoodsName(goodsName);
		return null;
	}
	
	@Override
	public Goods findByShopId(int shopId){
//		return goodsDao.findByShopId(shopId);
		return null;
	}

	public ResultMessage getGoodsByPage(Page page, HttpSession session) {
		page.coutStartColum();
		Goods goods = new Goods();
		goods.setShopId(Integer.parseInt(session.getAttribute("businessmanId").toString()));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", page);
		map.put("goods", goods);
		Long total = goodsDao.selectCountByPage(map);
		List<Goods> list = goodsDao.selectGoodsByPage(map);
		PageInfo<Goods> pageInfo = new PageInfo<>(page, total, list);
		return new ResultMessage(true, ResultCode.SUCCESS, "获取成功", pageInfo);
	}

	@Override
	public ResultMessage getGoodsById(Goods goods) {
		return new ResultMessage(true, ResultCode.SUCCESS, "获取成功", goodsDao.selectByPrimaryKey(goods.getId()));
	}

	@Override
	public ResultMessage addGoods(Goods goods, HttpSession session, HttpServletRequest request) {
		goods.setShopId(Integer.parseInt(session.getAttribute("businessmanId").toString()));
		String goodsPhotoUrl = "CHB_upload/shop/" + goods.getShopId() + "/goods/" + UUID.randomUUID() + ".jpeg";
		String saveUrl = request.getServletContext().getRealPath("/") + "../" + goodsPhotoUrl;
		boolean saveResult = Base64Util.GenerateImage(goods.getGoodsPhoto(), saveUrl);
		ResultMessage rsMessage = null;
		if (saveResult) {
			goods.setGoodsPhoto(goodsPhotoUrl);
			goodsDao.addGoods(goods);
			rsMessage = new ResultMessage(true, ResultCode.SUCCESS, "添加成功", null);
		} else {
			rsMessage = new ResultMessage(true, ResultCode.FAIL, "图片上传失败", null);
		}
		return rsMessage;
	}

	@Override
	public ResultMessage updateGoods(Goods goods, HttpServletRequest request) {
		ResultMessage rsMessage = null;
		if (goods.getGoodsPhoto().indexOf("CHB_upload") < 0) {
			Goods mGoods = goodsDao.selectByPrimaryKey(goods.getId());
			String goodsPhotoUrl = mGoods.getGoodsPhoto();
			String saveUrl = request.getServletContext().getRealPath("/") + "../" + goodsPhotoUrl;
			boolean saveResult = Base64Util.GenerateImage(goods.getGoodsPhoto(), saveUrl);
			if (saveResult) {
				goods.setGoodsPhoto(goodsPhotoUrl);
				goodsDao.updateGoods(goods);
				rsMessage = new ResultMessage(true, ResultCode.SUCCESS, "修改成功", null);
			} else {
				rsMessage = new ResultMessage(true, ResultCode.FAIL, "图片上传失败", null);
			}
		} else {
			goodsDao.updateGoods(goods);
			rsMessage =new ResultMessage(true, ResultCode.SUCCESS, "修改成功", null);
		}
		return rsMessage;
	}

	@Override
	public ResultMessage deleteGoods(List<Goods> goods) {
		goodsDao.deleteGoods(goods);
		return new ResultMessage(true, ResultCode.SUCCESS, "删除成功", null);
	}
	
	public List<Goods> findAllGoodsByShopId(Shop shop) {
		return goodsDao.findAllGoodsByShopId(shop);
	}

	public List<Goods> findGoodsByKeywords(Goods goods) {
		return goodsDao.findGoodsByKeywords(goods);
	}

}
