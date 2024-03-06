package optimum.concurrent;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFutureExample completableFutureExample = new CompletableFutureExample();
        try {
            completableFutureExample.basicFunction();
            completableFutureExample.combineFunctions();
            completableFutureExample.handleException();
            completableFutureExample.processArrayOfNumbers();
            completableFutureExample.timeoutFunction();
            completableFutureExample.handleWithFallbackFunction();
            completableFutureExample.executorExample();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void basicFunction() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });
        System.out.println(completableFuture.get());
    }

    public void combineFunctions() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            return "Hello";
        });
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            return "World";
        });
        CompletableFuture<String> combineCompletableFuture = completableFuture1.thenCombine(completableFuture2, (m1, m2) -> m1 + " " + m2);
        System.out.println(combineCompletableFuture.get());
    }

    public void handleException() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> exceptionally = CompletableFuture.supplyAsync(() -> 10 / 0).exceptionally(ex -> 0);
        System.out.println(exceptionally.get());
    }

    public void processArrayOfNumbers() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<CompletableFuture<Integer>> futures = list.stream().map(num -> CompletableFuture.supplyAsync(() -> processNumber(num))).toList();
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        CompletableFuture<List<Integer>> futureResult = allOf.thenApplyAsync(result -> futures.stream().map(CompletableFuture::join).toList());
        System.out.println(futureResult.join());
    }

    private Integer processNumber(Integer num) {
        return num * 2;
    }

    public void timeoutFunction() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(this::performLengthyOperation);
        CompletableFuture<String> result = future.completeOnTimeout("Default Result", 1, TimeUnit.SECONDS);
        System.out.println(result.join());
    }

    private String performLengthyOperation() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Normal Result";
    }

    public void handleWithFallbackFunction() {
        CompletableFuture<Integer> result = CompletableFuture.supplyAsync(() -> {
            double random = Math.random();
            System.out.println(random);
            if (random > 0.2) {
                throw new RuntimeException("Simulated exception");
            }
            return 42;
        }).exceptionally(ex -> {
            System.out.println("Exception occurred: " + ex.getMessage());
            return 0;
        });
        System.out.println(result.join());
    }

    public void executorExample() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        // Task 1: Simulate fetching data from a remote service
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 1 started on thread: " + Thread.currentThread().getName());
            sleep(2000);
            return "Result from Task 1";
        }, executor);

        // Task 2: Simulate processing data locally
        CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task 2 started on thread: " + Thread.currentThread().getName());
            sleep(1000);
            return "Result from Task 2";
        }, executor);

        // Combine the results of Task 1 and Task 2 when both are completed
        CompletableFuture<String> combinedResult = task1.thenCombine(task2, (result1, result2) -> {
            System.out.println("Combining results on thread: " + Thread.currentThread().getName());
            return result1 + " and " + result2;
        });

        // Handle the combined result
        combinedResult.thenAccept(result -> {
            System.out.println("Combined Result: " + result + " on thread: " + Thread.currentThread().getName());
        });

        // Main thread continues doing other work
        System.out.println("Main thread continues...");

        // Sleep to allow async tasks to complete
        sleep(4000);

        // Shutdown the executor
        executor.shutdown();
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}