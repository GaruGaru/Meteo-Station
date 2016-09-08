package modularity.Networking.http;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Tommaso Garuglieri on 10/07/2016.
 */
public class HttpRequest {

    private String url;
    private Map<String, String> params;

    public HttpRequest(String url, Map<String, String> params) {
        this.url = url;
        this.params = params;
    }

    public HttpRequest(String url) {
        this(url, new LinkedHashMap<>());
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getUrl() {
        return url;
    }

}
