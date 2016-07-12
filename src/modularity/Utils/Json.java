package modularity.Utils;

import com.google.gson.Gson;

/**
 * Created by Tommaso Garuglieri on 25/06/2016.
 */
public class Json {
    private static Gson instance = null;

    public static Gson get() {
        if (instance == null)
            instance = new Gson();
        return instance;
    }
}
