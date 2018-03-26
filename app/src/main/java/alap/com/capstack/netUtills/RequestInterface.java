package alap.com.capstack.netUtills;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestInterface {


    //		http://24.24.24.213/rw/service/service_end_user.php?key=1226&s=39&mobile_no=7383220934
    // todo Login
    @POST("service_end_user.php?")
    Call<ResponseBody> LoginUser(@Query("mobile_no") String mobileno
    );





}
