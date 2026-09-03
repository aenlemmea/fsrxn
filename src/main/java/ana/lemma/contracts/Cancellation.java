package ana.lemma.contracts;

import java.util.function.BooleanSupplier;

public interface Cancellation<T> {
    void unsubscribe();
    boolean isUnsubscribed();
}
