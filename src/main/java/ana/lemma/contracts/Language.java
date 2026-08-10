package ana.lemma.contracts;

public interface Language<T> {
    public void next(T parameter);
    public void complete();
    public void  error(Throwable throwable);
}