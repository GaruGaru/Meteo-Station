package modularity.Networking.http;

/**
 * Created by Tommaso Garuglieri on 10/07/2016.
 */
public class Http {

    private static IHttp instance;

    public static IHttp get() {
        if (instance == null)
            instance = new HttpExecutor();

        return instance;
    }

}
