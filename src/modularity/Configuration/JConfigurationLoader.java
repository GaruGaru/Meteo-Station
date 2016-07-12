package modularity.Configuration;

import modularity.Utils.Json;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Tommaso Garuglieri on 25/06/2016.
 */
public abstract class JConfigurationLoader<T> implements IConfigurationLoader<T> {

    protected String configurationFile;
    protected Class<T> configClass;


    public JConfigurationLoader(Class<T> configClass, String configurationFile) {
        this.configurationFile = configurationFile;
        this.configClass = configClass;
    }

    public JConfigurationLoader(Class<T> configClass) {
        this(configClass, "configuration.json");
    }


    @Override
    public T load() {
        String content = getConfigurationContent();
        if (content == null)
            return null;
        return Json.get().fromJson(content, configClass);
    }

    protected abstract String getConfigurationContent();


    protected String streamToString(InputStream resourceAsStream) {
        String content = "";
        Scanner scanner = new Scanner(resourceAsStream);
        while (scanner.hasNext()) {
            content += scanner.next();
        }
        return content;
    }

}
