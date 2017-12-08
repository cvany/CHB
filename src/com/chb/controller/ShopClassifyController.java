package com.chb.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chb.entity.Goods;
import com.chb.entity.Page;
import com.chb.entity.Shop;
import com.chb.entity.ShopClassify;
import com.chb.service.ShopClassifyService;
/**
 * 商店分类控制类
 * @author 崔文元
 * 2017年12月7日
 *
 */
import com.chb.vo.ShopAndGoodsVO;
@Controller
public class ShopClassifyController {
	@Autowired
	private ShopClassifyService shopClassifyService;
	
	/**
	 * 查找所有商店分类
	 * @return
	 */
	@RequestMapping("findAllShopClassify")
	@ResponseBody
	public List<ShopClassify> findAllShopClassify(){
		return shopClassifyService.findAllShopClassify();
	}
	
	/**
	 * 返回每个商家展示信息
	 * @param page
	 * @return
	 */
	@RequestMapping("findShopAndGoodsByPage")
	@ResponseBody
	public List<ShopAndGoodsVO> findShopAndGoodsByPage(Page page){
		List<Shop> list = shopClassifyService.findShopByPage(page);
		List<ShopAndGoodsVO> sgList =new ArrayList<>();
		ShopAndGoodsVO sg =null;
		for(int i=0;i<list.size();i++){
			sg =new ShopAndGoodsVO(list.get(i).getId(), list.get(i).getShopPhoto(),
					list.get(i).getShopName(), list.get(i).getMonthSales(), 
					list.get(i).getStartPrice(), list.get(i).getShopNotice());
			List<Goods> goodsList = shopClassifyService.findGoodsByShopId(list.get(i));
			for(int j=0;j<goodsList.size();j++){
				switch (j) {
				case 0:
					sg.goodsName1 =goodsList.get(j).getGoodsName();
					sg.goodsPhoto1 =goodsList.get(j).getGoodsPhoto();
					sg.price1 =goodsList.get(j).getGoodsPrice();
					break;
				case 1:
					sg.goodsName2 =goodsList.get(j).getGoodsName();
					sg.goodsPhoto2 =goodsList.get(j).getGoodsPhoto();
					sg.price2 =goodsList.get(j).getGoodsPrice();
					break;
				case 2:
					sg.goodsName3 =goodsList.get(j).getGoodsName();
					sg.goodsPhoto3 =goodsList.get(j).getGoodsPhoto();
					sg.price3 =goodsList.get(j).getGoodsPrice();
					break;
				}
			}
			sgList.add(sg);
		}
		return sgList;
	}
	
	

}
