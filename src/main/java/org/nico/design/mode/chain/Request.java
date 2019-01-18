package org.nico.design.mode.chain;

import java.util.List;
import java.util.Map;

public class Request {

    private String url;
    
    private String type;
    
    private List<Map<String, String>> headers;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Map<String, String>> getHeaders() {
        return headers;
    }

    public void setHeaders(List<Map<String, String>> headers) {
        this.headers = headers;
    }

    @Override
    public String toString() {
        return "Request [url=" + url + ", type=" + type + ", headers=" + headers + "]";
    }
    
}
