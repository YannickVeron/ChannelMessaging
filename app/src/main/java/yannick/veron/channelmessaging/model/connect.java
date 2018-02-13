package yannick.veron.channelmessaging.model;

/**
 * Created by verony on 22/01/2018.
 */
public class connect {
    private String response;
    private int code;
    private String accesstoken;

    public connect(String response, int code, String accesstoken) {
    }

    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public String getAccesstoken() {
        return accesstoken;
    }
    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }
}
