package ana.lemma.guard;

import ana.lemma.contracts.Language;

public class SafeConsumer<T> implements Language<T> {
    private final Language<T> downstream;
    private boolean isTerminated = false;

    public SafeConsumer(Language<T> downstream) {
        this.downstream = downstream;
    }

    @Override
    public void next(T parameter) {
        if (!isTerminated) {
            downstream.next(parameter);
        }
    }

    @Override
    public void complete() {
        if (!isTerminated) {
            isTerminated = true;
            downstream.complete();
        }
    }

    @Override
    public void error(Throwable throwable) {
        if (!isTerminated) {
            isTerminated = true;
            downstream.error(throwable);
        }
    }
}
