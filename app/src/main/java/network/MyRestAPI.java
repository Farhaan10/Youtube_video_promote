package network;

import network.Response.Status;
import network.Response.VideoResponse;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Farhaan on 28-06-2016.
 */
public interface MyRestAPI {

    @GET("/getVideos.php")
    public Observable<Status> getVideo();
}
