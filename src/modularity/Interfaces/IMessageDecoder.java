package modularity.Interfaces;

/**
 * Created by Tommaso Garuglieri on 12/06/2016.
 */
public interface IMessageDecoder<S, R> {
    R decode(S message);
}
