package threadsafety.unsafecountingfactorizer;

import threadsafety.Servlet;
import threadsafety.ServletRequest;
import threadsafety.ServletResponse;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

class Main {
    private static final int MAX_NUMBER = 101;

    static void main(String[] args) throws InterruptedException {
        UnsafeCountingFactorizer servlet = new UnsafeCountingFactorizer();
        Thread threadA = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                int number = random.nextInt(1, MAX_NUMBER);
                ServletRequest req = new ServletRequest();
                req.setNumber(BigInteger.valueOf(number));
                ServletResponse resp = new ServletResponse();
                servlet.service(req, resp);
                System.out.println("ThreadA: The factors of " + req.getNumber().toString() + " are " + Arrays.toString(resp.getFactors()));
            }
        });
        Thread threadB = new Thread(() -> {
            Random random = new Random();
            for (int i = 0; i < 1000; i++) {
                int number = random.nextInt(1, MAX_NUMBER);
                ServletRequest req = new ServletRequest();
                req.setNumber(BigInteger.valueOf(number));
                ServletResponse resp = new ServletResponse();
                servlet.service(req, resp);
                System.out.println("ThreadB: The factors of " + req.getNumber().toString() + " are " + Arrays.toString(resp.getFactors()));
            }
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("The number of requests should be 2000 but was " + servlet.getCount());
    }
}
