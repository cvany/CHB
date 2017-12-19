package com.chb.dao;


/**
 * 管理员服务接口实现类
 * @author 李卓宏
 *2017年12月16日
 */
public interface OrderDao {
	//根据订单价格查询数量
	long selectCountByPrice(long i);
	//根据支付方式查看订单数量
	long selectCountByPayMode(long i);

	
}
