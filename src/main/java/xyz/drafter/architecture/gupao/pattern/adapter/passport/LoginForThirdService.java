package xyz.drafter.architecture.gupao.pattern.adapter.passport;

import xyz.drafter.architecture.gupao.pattern.adapter.ResultMsg;

/**
 * @author wangmeng
 * @date 2019/8/26
 * @desciption
 */
public class LoginForThirdService extends LoginService {


    public ResultMsg loginForQQ(String id){

        //注册(在原有系统里面创建一个用户)
        ResultMsg msg =  super.regist(id, null);
        return null;
    }

    public ResultMsg loginForWechat(String id){
        return null;
    }


}
