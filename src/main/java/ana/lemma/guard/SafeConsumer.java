package ana.lemma.guard;

import ana.lemma.contracts.Cancellation;
import ana.lemma.contracts.Language;
import ana.lemma.contracts.TeardownRoutine;

public class SafeConsumer<T> implements Language<T>, Cancellation<T> {
    private final Language<T> downstream;
    private boolean isTerminated = false;
    private TeardownRoutine teardownRoutine;

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
            this.dispose();
        }
    }

    @Override
    public void error(Throwable throwable) {
        if (!isTerminated) {
            isTerminated = true;
            downstream.error(throwable);
            this.dispose();
        }
    }

    @Override
    public void unsubscribe() {
        if (!isTerminated) {
            this.dispose();
        }
    }

    private void dispose() {
        isTerminated = true;
        if (teardownRoutine != null) {
            teardownRoutine.cleanup();
            teardownRoutine = null;
        }
    }

    @Override
    public boolean isUnsubscribed() {
        return isTerminated;
    }

    public void setTeardownRoutine(TeardownRoutine teardownRoutine) {
        this.teardownRoutine = teardownRoutine;
    }
}
