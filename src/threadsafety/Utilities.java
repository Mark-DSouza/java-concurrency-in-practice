package threadsafety;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static BigInteger extractFromRequest(ServletRequest req) {
        return req.getNumber();
    }

    public static void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
        resp.setFactors(factors);
    }

    public static BigInteger[] factor(BigInteger number) {
        List<BigInteger> result = new ArrayList<>();
        for (BigInteger i = BigInteger.ONE; i.compareTo(number) <= 0; i = i.add(BigInteger.ONE)) {
            if (number.mod(i).equals(BigInteger.ZERO)) {
                result.add(i);
            }
        }
        return result.toArray(BigInteger[]::new);
    }
}
