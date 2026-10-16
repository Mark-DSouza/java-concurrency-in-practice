package threadsafety.lazyinitrace;

import java.util.Random;

public class ExpensiveObject {
    public ExpensiveObject() {
        Random random = new Random();
        for (int i = 0; i < random.nextInt(); i++);
    }
}
