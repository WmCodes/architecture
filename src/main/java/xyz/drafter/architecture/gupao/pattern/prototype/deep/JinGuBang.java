package xyz.drafter.architecture.gupao.pattern.prototype.deep;

import java.io.Serializable;

/**
 * @author wangmeng
 * @date 2019/8/24
 * @desciption
 */
public class JinGuBang implements Serializable {

    private float h = 100;
    private float d = 10;

    public void big(){
        this.d *= 2;
        this.h *= 2;
    }

    public void small(){
        this.d /= 2;
        this.h /= 2;

    }
}
