package modularity.Serial;

/**
 * Created by Tommaso Garuglieri on 15/06/2016.
 */
public interface ISerial {

    void initialize();

    void beginCommunication();

    void endCommunication();

    void addListener(ISerialListener... listeners);

    void removeListener(ISerialListener... listeners);

}
