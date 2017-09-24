package com.java.juc;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier初始化时规定一个数目，然后计算调用了CyclicBarrier.await()进入等待的线程数。当线程数达到了这个数目时，所有进入等待状态的线程被唤醒并继续。
 * CyclicBarrier就象它名字的意思一样，可看成是个障碍，所有的线程必须到齐后才能一起通过这个障碍。在这之后，如果再次调用 await() 方法，计数就又会变成 N-1，新一轮重新开始，这便是 Cyclic 的含义所在。
 * CyclicBarrier初始时还可带一个Runnable的参数，此Runnable任务在CyclicBarrier的数目达到后，所有其它线程被唤醒前被执行。
 *
 * @author wangjiayin
 */
public class TestCyclicBarrier {

    private static final int THREAD_NUM = 5;

    public static class WorkerThread implements Runnable {

        CyclicBarrier barrier;

        public WorkerThread(CyclicBarrier b) {
            this.barrier = b;
        }

        @Override
        public void run() {
            try {
                System.out.println("Worker's waiting");
                //线程在这里等待，直到所有线程都到达barrier。
                barrier.await();
                System.out.println("ID:" + Thread.currentThread().getId() + " Working");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {

        //当所有线程到达barrier时执行
        CyclicBarrier cb = new CyclicBarrier(THREAD_NUM, () -> {

            System.out.println("Inside Barrier");

        });

        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(new WorkerThread(cb)).start();
        }
    }

}