package network;

import android.support.annotation.NonNull;
import android.support.graphics.drawable.BuildConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Farhaan on 28-06-2016.
 */
public class RetrofitProvider {
    private static RetrofitProvider retrofitProvider;
    private MyRestAPI mMyRestApi;
    private String mBaseUrl = "http://192.168.2.28:8080";
    private static final int TIMEOUT = 20;
    private RetrofitProvider() {
        buildRestApi();
    }
    public static RetrofitProvider getInstance() {
        if (retrofitProvider == null) {
            synchronized (RetrofitProvider.class) {
                if (retrofitProvider == null) {
                    retrofitProvider = new RetrofitProvider();
                }
            }
        }
        return retrofitProvider;
    }
    public MyRestAPI provideApi() {
        return mMyRestApi;
    }
    private void buildRestApi() {
        OkHttpClient.Builder okHttpClient = getOkHttpBuilder();
        okHttpClient.readTimeout(TIMEOUT, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(TIMEOUT, TimeUnit.SECONDS);
        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient.build());
        mMyRestApi = builder.build().create(MyRestAPI.class);
    }
    @NonNull
    private OkHttpClient.Builder getOkHttpBuilder() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient.addInterceptor(logging);
        }
        return okHttpClient;
    }
}
