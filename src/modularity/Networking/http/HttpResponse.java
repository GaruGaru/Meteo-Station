package modularity.Networking.http;

/**
 * Created by Tommaso Garuglieri on 10/07/2016.
 */
public class HttpResponse {

    private int statusCode;
    private String body;
    private boolean success;

    public HttpResponse(int statusCode, String body, boolean success) {
        this.statusCode = statusCode;
        this.body = body;
        this.success = success;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getBody() {
        return body;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return "Response: " + statusCode + " Content: " + body;
    }
}
