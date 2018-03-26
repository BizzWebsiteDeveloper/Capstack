package alap.com.capstack.netUtills;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by CRAFT BOX on 5/1/2017.
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;
//    public static String service_url = "http://24.24.24.213/rw/service/";
   public static String service_url = "http://bulkbox.in/rw/service/";
//  public static String service_url = "http://bulkbox.in/charging_wala/service/";
    public static Retrofit getClient() {
        if (retrofit==null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(240, TimeUnit.SECONDS)
                    .connectTimeout(240, TimeUnit.SECONDS)
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(service_url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static RequestInterface getApiService() {
        return getClient().create(RequestInterface.class);
    }
}
