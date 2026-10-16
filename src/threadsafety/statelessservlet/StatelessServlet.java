package threadsafety.statelessservlet;

import net.jcip.annotations.ThreadSafe;
import threadsafety.Servlet;
import threadsafety.ServletRequest;
import threadsafety.ServletResponse;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static threadsafety.Utilities.*;

@ThreadSafe
class StatelessServlet implements Servlet {
    @Override
    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = factor(i);
        encodeIntoResponse(resp, factors);
    }


}
