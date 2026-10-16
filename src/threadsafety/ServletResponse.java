package threadsafety;

import java.math.BigInteger;

public class ServletResponse {
    private BigInteger[] factors;

    public BigInteger[] getFactors() {
        return factors;
    }

    public void setFactors(BigInteger[] factors) {
        this.factors = factors;
    }
}
