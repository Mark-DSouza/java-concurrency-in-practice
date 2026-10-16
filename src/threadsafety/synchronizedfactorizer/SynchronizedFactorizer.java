package threadsafety.synchronizedfactorizer;

import net.jcip.annotations.ThreadSafe;
import threadsafety.Servlet;
import threadsafety.ServletRequest;
import threadsafety.ServletResponse;

import java.math.BigInteger;

import static threadsafety.Utilities.*;

@ThreadSafe
public class SynchronizedFactorizer implements Servlet {
    private BigInteger lastNumber;
    private BigInteger[] lastFactors;

    @Override
    public synchronized void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        if (i.equals(lastNumber)) {
            encodeIntoResponse(resp, lastFactors);
        } else {
            BigInteger[] factors = factor(i);
            lastNumber = i;
            lastFactors = factors;
            encodeIntoResponse(resp, factors);
        }
    }
}
