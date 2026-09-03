package ana.lemma.contracts;

@FunctionalInterface
public interface Recipe<T> {
    TeardownRoutine run(Language<T> language);
}
