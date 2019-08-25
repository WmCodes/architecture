package xyz.drafter.architecture.gupao.pattern.strategy.pay;

import xyz.drafter.architecture.gupao.pattern.strategy.pay.payport.PayType;

/**
 * @author wangmeng
 * @date 2019/8/25
 * @desciption
 */
public class PayStrategyTest {

    public static void main(String[] args) {

        Order order = new Order("1","201908251537",3333.33);

        // 支付
        System.out.println(order.pay(PayType.ALI_PAY));
    }
}
