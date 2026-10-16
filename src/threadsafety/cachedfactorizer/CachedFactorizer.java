package threadsafety.cachedfactorizer;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import threadsafety.Servlet;
import threadsafety.ServletRequest;
import threadsafety.ServletResponse;

import java.math.BigInteger;

import static threadsafety.Utilities.*;

@ThreadSafe
public class CachedFactorizer implements Servlet {
    @GuardedBy("this") private long hits = 0;
    @GuardedBy("this") private long cacheHits = 0;
    @GuardedBy("this") private BigInteger lastNumber = null;
    @GuardedBy("this") private BigInteger[] lastFactors = null;

    public synchronized long getHit() {
        return hits;
    }

    public synchronized long getCacheHit() {
        return cacheHits;
    }

    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = null;

        synchronized (this) {
            hits++;
            if (i.equals(lastNumber)) {
                cacheHits++;
                factors = lastFactors.clone();
            }
        }

        if (factors == null) {
            factors = factor(i);
            synchronized (this) {
                lastNumber = i;
                lastFactors = factors;
            }
        }

        encodeIntoResponse(resp, factors);
    }
}
