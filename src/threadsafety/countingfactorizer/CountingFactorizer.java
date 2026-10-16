package threadsafety.countingfactorizer;

import net.jcip.annotations.ThreadSafe;
import threadsafety.Servlet;
import threadsafety.ServletRequest;
import threadsafety.ServletResponse;

import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

import static threadsafety.Utilities.*;

@ThreadSafe
public class CountingFactorizer implements Servlet {
    private final AtomicLong count = new AtomicLong();

    public long getCount() {
        return count.get();
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(resp, factors);
    }
}
