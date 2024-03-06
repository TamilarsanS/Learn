package optimum.concurrent;

import java.util.concurrent.Semaphore;

import com.google.common.util.concurrent.RateLimiter;

public class SemaphoreRateLimiter {

    private final Semaphore semaphore;
    private final RateLimiter guavaRateLimiter;


    public SemaphoreRateLimiter(int permits) {
        this.semaphore = new Semaphore(permits);
        this.guavaRateLimiter = RateLimiter.create(permits);
    }

    public void throttle() {
        if (semaphore.tryAcquire()) {
            // Perform the throttled operation
            System.out.println("Throttled operation executed. Permits left: " + semaphore.availablePermits());
        } else {
            System.out.println("Request throttled. Try again later.");
        }
    }


    public void throttledOperation() {
        if (guavaRateLimiter.tryAcquire()) {
            // Perform the throttled operation
            System.out.println("Guava - Throttled operation executed.");
        } else {
            System.out.println("Guava - Request throttled. Try again later.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreRateLimiter rateLimiter = new SemaphoreRateLimiter(5);
        for (int i = 0; i < 10; i++) {
            rateLimiter.throttle();
        }
        for (int i = 0; i < 10; i++) {
            rateLimiter.throttledOperation();
            if (i % 2 == 0) {
                Thread.sleep(250);
            }
        }
    }
}