package dev.fvames.multithread.threadState;

import java.util.concurrent.TimeUnit;

/**
 * js -> pid
 * jstack pid
 * Created by James on 2018/10/21.
 */
public class ThreadStatus {

    public static void main(String[] args) {

        //TIME_WAITING
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "timewaiting").start();

        //WAITING，线程在 ThreadStatus 类锁上通过 wait 进行等待

        new Thread(() -> {
            while (true) {
                synchronized (ThreadStatus.class) {
                    try {
                        ThreadStatus.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Waiting").start();

        //线程在 ThreadStatus 加锁后，不会释放锁

        new Thread(new BlockedDemo(), "BlockDemo-01").start();

        new Thread(new BlockedDemo(), "BlockDemo-02").start();

    }

    static class BlockedDemo extends Thread {

        public void run() {
            synchronized (BlockedDemo.class) {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}