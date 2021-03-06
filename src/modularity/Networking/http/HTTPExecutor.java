package modularity.Networking.http;

import modularity.Utils.TextUtils;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Tommaso Garuglieri on 10/07/2016.
 */
public class HttpExecutor implements IHttp {

    private HttpResponse executePost(HttpRequest request) {

        try {
            URL url = new URL(request.getUrl());

            Map<String, String> params = request.getParams();

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }

            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            String content = TextUtils.streamToString(conn.getInputStream());
            int responseCode = conn.getResponseCode();

            return new HttpResponse(responseCode, content, responseCode >= 200 && responseCode < 300);
        } catch (Exception e) {

            return HttpResponse.create(e);
        }
    }

    @Override
    public HttpResponse POST(HttpRequest request) {
        return executePost(request);
    }

    @Override
    public HttpResponse GET(HttpRequest request) {
        throw new RuntimeException("Not implemented yet"); // TODO Implement
    }
}
