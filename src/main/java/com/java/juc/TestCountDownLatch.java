package com.java.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 当startSignal.await()会阻塞线程，
 * 当startSignal.countDown()被调用之后，所有Worker线程开始执行doWork()方法，
 * 当Worker.doWork()执行完毕后，调用doneSignal.countDown()，在所有Worker线程执行完毕之后，主线程继续执行。
 *
 * @author wangjiayin
 */
public class TestCountDownLatch {

    /**
     * 这个例子可以理解为F1赛车的维修过程，
     * 只有startSignal命令下达之后，维修工才开始干活，
     * 只有等所有工人完成工作之后，赛车才能继续。
     */
    public static void main(String[] args) throws InterruptedException {

        int n = 5;
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(n);

        for (int i = 0; i < n; ++i) {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }

        // doSomethingElse();
        startSignal.countDown();      // let all threads proceed
        // doSomethingElse();
        doneSignal.await();           // wait for all to finish
    }

}

class Worker implements Runnable {

    private final CountDownLatch startSignal;
    private final CountDownLatch doneSignal;

    Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
        this.startSignal = startSignal;
        this.doneSignal = doneSignal;
    }

    public void run() {
        try {
            startSignal.await();
            doWork();
            doneSignal.countDown();
        } catch (InterruptedException ignored) {
        }
    }

    void doWork() {
        // doSomethingElse();
    }
}