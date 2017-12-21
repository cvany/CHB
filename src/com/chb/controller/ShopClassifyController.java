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
		return getShopList(list);
	}
	
	/**
	 * 根据商店名称或商品关键字模糊查询商店
	 * @param shop
	 * @return
	 */
	@RequestMapping("findShopByKeyWords")
	@ResponseBody
	public List<ShopAndGoodsVO> findShopByKeyWords(Shop shop){
		List<Shop> list = shopClassifyService.findShopByKeyWords(shop);	//根据商店名称模糊查询到的商店信息
		List<Shop> list2 = shopClassifyService.findShopIdByGoodsName(shop);	//根据商品名称模糊查询商店id集合
		//将list集合和list3集合进行去重，剔除相同商店的信息
		for(int i=0;i<list.size();i++){
			for(int j=0;j<list2.size();j++){
				if(list2.get(j).getId()==list.get(i).getId()){
					list2.remove(j);
					j--;
				}
			}
		}
		List<Shop> list3 = shopClassifyService.findShopByShopIdList(list2);	//根据商店id查询到商店信息
		List<ShopAndGoodsVO> shopList = getShopList(list);
		List<ShopAndGoodsVO> shopList2 = getShopList(list3);
		shopList.addAll(shopList2);
		return shopList;
	}
	
	/**
	 * 根据商店分类id查找商店信息
	 * @param sc
	 * @return
	 */
	@RequestMapping("findShopByShopClassifyId")
	@ResponseBody
	public List<ShopAndGoodsVO> findShopByShopClassifyId(ShopClassify sc){
		List<Shop> list = shopClassifyService.findShopByShopClassifyId(sc);
		return getShopList(list);
	}
	
	
	/**
	 * 根据商店列表封装每一个商家展示信息
	 * @param list 
	 * @return
	 */
	private List<ShopAndGoodsVO> getShopList(List<Shop> list){
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
