package modularity.Interfaces;

/**
 * Created by Tommaso Garuglieri on 12/06/2016.
 */
public interface Validator<T> {
    boolean isValid(T object);
}
