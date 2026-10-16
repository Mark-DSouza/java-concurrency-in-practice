package threadsafety;

import java.math.BigInteger;

public class ServletRequest {
    private BigInteger number;

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }
}
