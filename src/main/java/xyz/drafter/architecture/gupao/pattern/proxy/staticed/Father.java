package xyz.drafter.architecture.gupao.pattern.proxy.staticed;

/**
 * @author wangmeng
 * @date 2019/8/24
 * @desciption
 */
public class Father {

    private Son son;

    public Father(Son son){
        this.son = son;
    }

    public void findLove(){
        System.out.println("物色");
        this.son.findLove();

    }
}
