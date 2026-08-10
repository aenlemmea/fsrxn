package ana.lemma.contracts;

@FunctionalInterface
public interface Recipe<T> {
    void run(Language<T> language);
}
