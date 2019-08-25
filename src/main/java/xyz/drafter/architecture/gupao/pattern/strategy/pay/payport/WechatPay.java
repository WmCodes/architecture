package xyz.drafter.architecture.gupao.pattern.strategy.pay.payport;

import xyz.drafter.architecture.gupao.pattern.strategy.pay.PayState;

/**
 * @author wangmeng
 * @date 2019/8/25
 * @desciption
 */
public class WechatPay implements Payment {
    @Override
    public PayState pay(String uid, double amount) {
        System.out.println("微信支付");

        return new PayState(200, amount, "支付成功");
    }
}
