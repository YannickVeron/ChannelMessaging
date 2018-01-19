package yannick.veron.channelmessaging;

/**
 * Created by verony on 19/01/2018.
 */
public interface OnDownloadListener {

    void onDownloadComplete(String downloadedContent);
    void onDownloadError(String error);

}
