package modularity.Networking.http;

/**
 * Created by Tommaso Garuglieri on 10/07/2016.
 */
public interface IHttp {

    HttpResponse POST(HttpRequest request);

    HttpResponse GET(HttpRequest request);

}
