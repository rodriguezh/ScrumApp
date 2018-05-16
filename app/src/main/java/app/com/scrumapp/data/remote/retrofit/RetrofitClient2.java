package app.com.scrumapp.data.remote.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient2 {

    private static Retrofit retrofit2 = null;

    public static Retrofit getClient2(String baseUrl) {
        if (retrofit2==null) {
            retrofit2 = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit2;
    }
}