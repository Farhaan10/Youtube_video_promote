package com.example.farhaan.youtube_app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Handler;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.io.InputStream;

import network.Response.Status;
import network.Response.VideoResponse;
import network.RetrofitProvider;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Farhaan on 26-06-2016.
 */
public class YoutubePlayer extends Fragment implements YouTubePlayer.OnInitializedListener{

    int i = 0;
    List<String> videos = new ArrayList<String>();
    private TextView name, title, channel, views;
    YouTubePlayer mYouTubePlayer;
    long vTime;
    private ProgressBar progressBar;
    ImageView icon;

    YouTubePlayerSupportFragment myYouTubePlayerFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_player, container, false);
        name = (TextView) view.findViewById(R.id.Name);
        title = (TextView) view.findViewById(R.id.Title);
        channel = (TextView) view.findViewById(R.id.channel);
        views = (TextView) view.findViewById(R.id.views);
        icon = (ImageView) view.findViewById(R.id.icon);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        myYouTubePlayerFragment = new YouTubePlayerSupportFragment();
        progressBar.setMax(31);
        progressBar.setProgress(20);
        name.setText("Hi " + Values.name);

        Observable<Long> observable = Observable.interval(1000, TimeUnit.MILLISECONDS);
        Subscription subscription = observable
                .subscribe(new Subscriber<Long>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Log.d("YouTubePlayer", aLong + "");
                        if (mYouTubePlayer != null) {
                            vTime = mYouTubePlayer.getCurrentTimeMillis();
                            System.out.println(String.valueOf(vTime / 1000));
                            if ((vTime / 1000) > 30) {
                                i++;
                                mYouTubePlayer.loadVideo(videos.get(i));
                                loadTitle();
                            }
                        }
                    }
                });

        for(int z=0;z<Values.video_array.length;z++) {
            videos.add(Values.video_array[z]);
            System.out.println(videos.get(z));
        }
        System.out.println("Got the videos");
        initializeYouTubePlayer();

        /*RetrofitProvider.getInstance().provideApi().getVideo().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Status>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Status status) {
                        List<VideoResponse> list = status.getResults();
                            for (int z=0;z<list.size();z++){
                                videos.add(list.get(z).getData());
                                System.out.println(videos.get(z));
                            }
                        System.out.println("Got the videos");
                        initializeYouTubePlayer();
                    }
                });*/

        return view;
    }

    Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            progressBar.setProgress((int)(vTime/1000));
            handler.postDelayed(runnable, 1000);
        }
    };

    public void loadTitle(){

        String url = "https://www.googleapis.com/youtube/v3/videos?id=" + videos.get(i) + "&key=AIzaSyCEj6SdoUvVQ6E4aBZrqcYXpDHb9s4BO-k&part=snippet,contentDetails,statistics,status";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                try {
                    title.setText(response.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").getString("title"));
                    channel.setText(response.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").getString("channelTitle"));
                    views.setText(response.getJSONArray("items").getJSONObject(0).getJSONObject("statistics").getString("viewCount"));
                    new DownloadImageTask(icon).execute(response.getJSONArray("items").getJSONObject(0).getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("default").getString("url"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.append(error.getMessage());
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

    public void initializeYouTubePlayer(){
        myYouTubePlayerFragment.initialize("AIzaSyCEj6SdoUvVQ6E4aBZrqcYXpDHb9s4BO-k", this);
        getFragmentManager().beginTransaction().replace(R.id.fragment_youtube_player, myYouTubePlayerFragment).commit();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        mYouTubePlayer = youTubePlayer;
        mYouTubePlayer.loadVideo(videos.get(i));
        loadTitle();
        handler.postDelayed(runnable, 1000);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        if (result.isUserRecoverableError()) {
            result.getErrorDialog(getActivity(),1).show();
        } else {
            Toast.makeText(getActivity(),
                    "YouTubePlayer.onInitializationFailure(): " + result.toString(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
