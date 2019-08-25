package xyz.drafter.architecture.gupao.pattern.strategy.pay.payport;

import xyz.drafter.architecture.gupao.pattern.strategy.pay.PayState;

/**
 * @author wangmeng
 * @date 2019/8/25
 * @desciption
 */
public interface Payment {



    public PayState pay(String uid, double amount);

}
