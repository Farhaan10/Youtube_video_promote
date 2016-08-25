package network.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Farhaan on 28-06-2016.
 */
public class Status {

    public List<VideoResponse> getResults() {
        return results;
    }

    public void setResults(List<VideoResponse> results) {
        this.results = results;
    }

    @SerializedName("result")
    @Expose
    private List<VideoResponse> results;

}
