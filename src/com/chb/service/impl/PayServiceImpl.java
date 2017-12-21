package com.chb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chb.constant.ResultCode;
import com.chb.dao.OrderDao;
import com.chb.entity.Order;
import com.chb.entity.ResultMessage;
import com.chb.service.PayService;
import com.chb.utils.PaymentUtil;

@Service
public class PayServiceImpl implements PayService {
	@Autowired
	OrderDao orderDao;

	// 获取支付地址
	@Override
	public ResultMessage getPayUrl(Order order) {
		Order rOrder = orderDao.selectOrderById(order.getId());
		ResultMessage resultMessage = null;
		if (rOrder == null) {
			resultMessage = new ResultMessage(true, ResultCode.FAIL, "订单不存在", null);
		} else {
			String p0_Cmd = "Buy", // 业务类型
					p1_MerId = "10001126856", // 商户编号
					p2_Order = rOrder.getOrderNo() + "", // 订单号
					p3_Amt = "0.01", // 支付金额
					p4_Cur = "CNY", // 交易币种
					p5_Pid = rOrder.getOrderNo() + "", // 商品名称
					p6_Pcat = "", // 商品种类
					p7_Pdesc = "", // 商品描述
					p8_Url = "http://172.16.15.193:8080/CHB/user/payCallback.do", // 支付成功回调
					p9_SAF = "", // 收货地址
					pa_MP = "", // 商户扩展信息
					pd_FrpId = "", // 银行编码
					pr_NeedResponse = "1", // 应答机制
					keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 商户密钥
			String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt, p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc,
					p8_Url, p9_SAF, pa_MP, pd_FrpId, pr_NeedResponse, keyValue);
			String url = "https://www.yeepay.com/app-merchant-proxy/node?" + "&p0_Cmd=" + p0_Cmd + "&p1_MerId="
					+ p1_MerId + "&p2_Order=" + p2_Order + "&p3_Amt=" + p3_Amt + "&p4_Cur=" + p4_Cur + "&p5_Pid="
					+ p5_Pid + "&p6_Pcat=" + p6_Pcat + "&p7_Pdesc=" + p7_Pdesc + "&p8_Url=" + p8_Url + "&p9_SAF="
					+ p9_SAF + "&pa_MP=" + pa_MP + "&pd_FrpId=" + pd_FrpId + "&pr_NeedResponse=" + pr_NeedResponse
					+ "&hmac=" + hmac;
			resultMessage = new ResultMessage(true, ResultCode.SUCCESS, "获取成功", url);
		}
		return resultMessage;
	}

}
