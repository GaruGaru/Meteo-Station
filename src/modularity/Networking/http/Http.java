package modularity.Networking.http;

/**
 * Created by Tommaso Garuglieri on 10/07/2016.
 */
public class Http {

    private static IHttp instanace;

    public static IHttp get() {
        if (instanace == null)
            instanace = new HTTPExecutor();

        return instanace;
    }

}
