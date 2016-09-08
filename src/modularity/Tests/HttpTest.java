package modularity.Tests;

import modularity.Networking.http.HttpRequest;
import modularity.Networking.http.HttpResponse;
import modularity.Networking.http.IHttp;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tommaso Garuglieri on 08/09/2016.
 */
public class HttpTest {

    @Test
    public void testGET() {

        HttpRequest testReq = new HttpRequest("Test");

        IHttp http = new IHttp() {
            @Override
            public HttpResponse POST(HttpRequest request) {
                fail();
                return null;
            }

            @Override
            public HttpResponse GET(HttpRequest request) {
                assertEquals(request.getUrl(), testReq.getUrl());
                assertTrue(request.getParams().isEmpty());
                return null;
            }
        };

        http.GET(testReq);
    }

    @Test
    public void testPOST() {

        HttpRequest testReq = new HttpRequest("Test");

        IHttp http = new IHttp() {
            @Override
            public HttpResponse POST(HttpRequest request) {
                assertEquals(request.getUrl(), testReq.getUrl());
                assertTrue(request.getParams().isEmpty());
                return null;
            }

            @Override
            public HttpResponse GET(HttpRequest request) {
                fail();
                return null;
            }
        };

        http.POST(testReq);
    }


    @Test
    public void testGetResponse() {
        HttpRequest testReq = new HttpRequest("Test");

        IHttp http = new IHttp() {
            @Override
            public HttpResponse POST(HttpRequest request) {
                fail();
                return null;
            }

            @Override
            public HttpResponse GET(HttpRequest request) {
                assertEquals(request.getUrl(), testReq.getUrl());
                assertTrue(request.getParams().isEmpty());
                return new HttpResponse(200, "Done");
            }
        };

        HttpResponse get = http.GET(testReq);
        assertTrue(get.isSuccess());
        assertNotNull(get.getBody());
        assertEquals(get.getStatusCode(), 200);
    }

    @Test
    public void testResponse() {
        for (int i = 0; i < 600; i++) {
            assertEquals(new HttpResponse(i, "").isSuccess(), i >= 200 && i < 400);
        }
    }


}
