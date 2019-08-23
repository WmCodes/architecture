package xyz.drafter.architecture.gupao.pattern.singleton.test;

import xyz.drafter.architecture.gupao.pattern.singleton.lazy.LazyThree;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangmeng
 * @date 2019/8/23
 * @desciption
 */
public class ThreadSafeTest {

    public static void main(String[] args) throws InterruptedException {

        int count = 100;
        final CountDownLatch countDownLatch = new CountDownLatch(100);



        for (int i = 0; i<count;i++){
            new Thread(){
                @Override
                public void run() {
                    Object o = null;
                    try {
                        countDownLatch.await();
                         o = LazyThree.getInstance();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis()+":"+o);


                }
            }.start();
            countDownLatch.countDown();
        }

        Thread.sleep(1000);
        System.out.println("***********");

    }
}
