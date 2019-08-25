package xyz.drafter.architecture.gupao.pattern.strategy.pay;

import xyz.drafter.architecture.gupao.pattern.strategy.pay.payport.PayType;

/**
 * @author wangmeng
 * @date 2019/8/25
 * @desciption 策略模式
 */
public class Order {

    private String uid;
    private String orderId;
    private double amount;

    public Order(String uid, String orderId, double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    public PayState pay(PayType payType){
       // return payment.pay(this.uid, this.amount);
        return payType.getPayment().pay(this.uid, this.amount);

    }
}
