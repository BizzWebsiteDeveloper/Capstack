package alap.com.capstack.netUtills;

import java.util.ArrayList;

import alap.com.capstack.model.ModelMember;
import alap.com.capstack.model.ModelSoftware;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RequestInterface {


    //		http://24.24.24.213/rw/service/service_end_user.php?key=1226&s=39&mobile_no=7383220934
    // todo Login
    @FormUrlEncoded
    @POST("api.php")
    Call<ResponseBody> LoginUser(@Field("type") String type,@Field("email")String email,@Field("password")String password);

    @FormUrlEncoded
    @POST("api.php")
    Call<ArrayList<ModelSoftware>>GetSoftware(@Field("type")String type);

    @FormUrlEncoded
    @POST("api.php")
    Call<ResponseBody>GetSoftwareCount(@Field("type")String type,@Field("cond")String count);
    @FormUrlEncoded
    @POST("api.php")
    Call<ResponseBody>GetSoftwareDetail(@Field("type")String type,@Field("cond")String count);
    @FormUrlEncoded
    @POST("api.php")
    Call<ArrayList<ModelMember>>GetUserDetail(@Field("type")String type, @Field("cond")String count);




}
