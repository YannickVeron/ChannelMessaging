package yannick.veron.channelmessaging;

import java.util.HashMap;

/**
 * Created by verony on 19/01/2018.
 */
public class PostRequest {
    private String url;
    private HashMap<String,String> postParam;

    public PostRequest(String url, HashMap<String, String> postParam) {
        this.url = url;
        this.postParam = postParam;
    }

    public String getUrl() {
        return url;
    }

    public HashMap<String, String> getPostParam() {
        return postParam;
    }
}
