import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.util.CharArrayBuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request {

    private final String method;
    private final String path;
    private final Map<String, String> headers;
    private final InputStream body;
    private List<NameValuePair> nameValuePairs;

    public Request(String method, String path, Map<String, String> headers, InputStream body) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.body = body;
        nameValuePairs = URLEncodedUtils.parse(getQuery(), StandardCharsets.UTF_8);
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public InputStream getBody() {
        return body;
    }

    public List<NameValuePair> getQueryParams() {
        return nameValuePairs;
    }

    private String getQuery() {
        var i = path.indexOf("?");
        return path.substring(i + 1);
    }

    public String getQueryParam(String name) {
        //nameValuePairs = URLEncodedUtils.parse(getQuery(), StandardCharsets.UTF_8);
        for (NameValuePair param : nameValuePairs) {
            if (name.equals(param.getName())) return param.getValue();
        }
        return "parameter: " + name + " not found";
    }

    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}