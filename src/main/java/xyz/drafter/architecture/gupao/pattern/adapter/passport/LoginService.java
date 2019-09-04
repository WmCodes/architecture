package xyz.drafter.architecture.gupao.pattern.adapter.passport;

import xyz.drafter.architecture.gupao.pattern.adapter.Member;
import xyz.drafter.architecture.gupao.pattern.adapter.ResultMsg;

/**
 * @author wangmeng
 * @date 2019/8/26
 * @desciption
 */
public class LoginService {


    public ResultMsg regist(String username,String password){
        return new ResultMsg("200", "注册成功", new Member());
    }

    public ResultMsg login(String username,String password){
        return null;
    }
}
