package threadsafety.cachedfactorizer;

import threadsafety.ServletRequest;
import threadsafety.ServletResponse;
import threadsafety.synchronizedfactorizer.SynchronizedFactorizer;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class Main {
    static void main(String[] args) throws InterruptedException {
        CachedFactorizer servlet = new CachedFactorizer();
        Thread threadA = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 100000; i++) {
                int number = 53;
                ServletRequest req = new ServletRequest();
                req.setNumber(BigInteger.valueOf(number));
                ServletResponse resp = new ServletResponse();
                servlet.service(req, resp);
                if (!resp.getFactors()[1].equals(req.getNumber())) {
                    System.out.printf("[Thread A, Iteration %d]: The number was %s but the response was %s\n", i, req.getNumber(), Arrays.toString(resp.getFactors()));
                }
            }
        });
        Thread threadB = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 100000; i++) {
                int number = 19;
                ServletRequest req = new ServletRequest();
                req.setNumber(BigInteger.valueOf(number));
                ServletResponse resp = new ServletResponse();
                servlet.service(req, resp);
                if (!resp.getFactors()[1].equals(req.getNumber())) {
                    System.out.printf("[Thread A, Iteration %d]: The number was %s but the response was %s\n", i, req.getNumber(), Arrays.toString(resp.getFactors()));
                }
            }
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("Hits: " + servlet.getHit() + ", Cache Hits: " + servlet.getCacheHit());
    }
}
