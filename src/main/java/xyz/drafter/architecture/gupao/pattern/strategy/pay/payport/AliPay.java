package xyz.drafter.architecture.gupao.pattern.strategy.pay.payport;

import xyz.drafter.architecture.gupao.pattern.strategy.pay.PayState;

/**
 * @author wangmeng
 * @date 2019/8/25
 * @desciption
 */
public class AliPay implements Payment {


    @Override
    public PayState pay(String uid, double amount) {
        System.out.println("欢迎使用支付宝");
        return new PayState(200, amount, "支付成功");
    }
}
