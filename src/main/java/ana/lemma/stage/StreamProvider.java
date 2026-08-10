package ana.lemma.stage;

import ana.lemma.contracts.Language;
import ana.lemma.contracts.Recipe;
import ana.lemma.guard.SafeConsumer;

public class StreamProvider<T> {
    private final Recipe<T> recipe;

    public StreamProvider(Recipe<T> recipe) {
        this.recipe = recipe;
    }

    // Cold Stream
    public void subscribe(Language<T> consumer) {
        SafeConsumer<T> safeConsumer = new SafeConsumer<>(consumer);
        try {
            recipe.run(safeConsumer);
        } catch (final Exception e) {
            safeConsumer.error(e);
        }
    }
}
