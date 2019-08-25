package xyz.drafter.architecture.gupao.pattern.strategy.pay.payport;

/**
 * @author wangmeng
 * @date 2019/8/25
 * @desciption
 */
public enum PayType {

    ALI_PAY(new AliPay()),WECHAT_PAY(new WechatPay()),UNION_PAY(new UnionPay());

    private Payment payment;

    PayType(Payment payment){
        this.payment = payment;
    }


    public Payment getPayment(){
        return this.payment;
    }

}
