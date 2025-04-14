package concurrent.print;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockBased extends PrintParent{

    public static void main(String[] args) {
        wrong_demo();
    }

    /**
     * use two condition control threads
     * 错误纠正
     * 1、信号丢失（Lost Signal）风险
     * 当 odd 线程执行 evenCondition.signal() 后，若 even 线程尚未进入 await() 状态，信号会被永久丢失，导致线程永久阻塞。
     * 同理适用于 even 线程向 odd 线程发送信号的情况。
     * 2、虚假唤醒
     * 未使用循环检查条件
     * 代码仅在 if 中判断 COUNTER % 2，未遵循 Condition 的标准使用模式（应在 while 循环中检查条件），虚假唤醒可能导致逻辑错误。
     * 3、未处理中断异常
     * InterruptedException 被简单封装为 RuntimeException，不符合线程中断的最佳实践。
     * 4、不安全的线程终止
     * 当 COUNTER > MAX 时，线程直接退出，可能跳过 unlock() 操作，导致锁未被释放（违反锁释放规范）。
     * 若一方线程提前终止，另一方将永久阻塞。
     * 5、tryLock() 的不当使用
     * 当前代码使用 tryLock() 非阻塞尝试获取锁，若获取失败会直接跳过本次循环，可能导致线程饥饿或效率低下。
     * 在这种严格交替执行的场景中，更适合使用 lock() 阻塞式获取锁。频繁的 tryLock() 调用可能导致 CPU 空转，尤其是在高竞争场景下。
     * 6、Condition的等待方式是await()
     */
    public static void wrong_demo(){
        ReentrantLock lock = new ReentrantLock();
        Condition oddCondition = lock.newCondition();
        Condition evenCondition = lock.newCondition();
        Thread odd = new Thread(() -> {
            while (COUNTER <= MAX){
                boolean lockSuccess = lock.tryLock();
                if (lockSuccess){
                    try {
                        if (COUNTER % 2 == 1){
                            System.out.println("奇数线程：" + COUNTER);
                            COUNTER++;
                            try {
                                oddCondition.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException("奇数线程wait异常");
                            }
                        }else {
                            // 唤醒偶数线程
                            evenCondition.signal();
                        }
                    }finally {
                        lock.unlock();
                    }
                }
            }
        });
        Thread even = new Thread(() -> {
            while (COUNTER <= MAX){
                boolean lockSuccess = lock.tryLock();
                if (lockSuccess){
                    try{
                        if (COUNTER % 2 == 0){
                            System.out.println("偶数线程：" + COUNTER);
                            COUNTER++;
                            try {
                                evenCondition.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException("偶数线程wait异常");
                            }
                        }else {
                            // 唤醒奇数线程
                            oddCondition.signal();
                        }
                    }finally {
                        lock.unlock();
                    }
                }
            }
        });

        odd.start();
        even.start();
    }

   /**
    *  improved base on wrong_demo
     * use ReentrantLock control threads
     */
    public static void demo2() {
        ReentrantLock lock = new ReentrantLock();
        Condition oddCondition = lock.newCondition();
        Condition evenCondition = lock.newCondition();

        Thread odd = new Thread(() -> {
            while (COUNTER <= MAX) {
                lock.lock();
                try {
                    while (COUNTER % 2 == 0) {
                        oddCondition.await();
                    }
                    if (COUNTER <= MAX){
                        System.out.println("奇数线程：" + COUNTER++);
                        evenCondition.signal();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        });
        Thread even = new Thread(() -> {
            while (COUNTER <= MAX) {
                lock.lock();
                try {
                    while (COUNTER % 2 == 1) {
                        evenCondition.await();
                    }
                    System.out.println("偶数线程：" + COUNTER++);
                    oddCondition.signal();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            }
        });
        odd.start();
        even.start();
    }
}
