package alap.com.capstack.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.ArrayList;

import alap.com.capstack.R;
import alap.com.capstack.activity.LoginActivity;
import alap.com.capstack.activity.SignupActivity;
import alap.com.capstack.model.ModelSoftware;
import alap.com.capstack.netUtills.RequestInterface;
import alap.com.capstack.netUtills.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android-2 on 27-03-2018.
 */

public class InfolistAdapter extends RecyclerView.Adapter<InfolistAdapter.Viewholder> {
    Context mContext;
    ArrayList<ModelSoftware> model = new ArrayList<>();

    public InfolistAdapter(Context mContext, ArrayList<ModelSoftware> modelSoftwares) {
        this.mContext = mContext;
        model = modelSoftwares;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_info_list, parent, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, final int position) {
        holder.softwarename.setText(model.get(position).getName());
        holder.desc.setText(model.get(position).getName());
        holder.seekbar.setEnabled(false);
        String id = model.get(position).getId();
//        getCount(holder,  id,model.get(position).getPrsn());
        RequestInterface r = RetrofitClient.getApiService();
        Call<ResponseBody> call = r.GetSoftwareCount("getcount", "soft='" + id + "'");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String responsest = response.body().string();
                    JSONObject json = new JSONObject(responsest);
                    int count = json.getInt("count");
                    holder.seekbar.setMax(Integer.parseInt(model.get(position).getPrsn()));
                    holder.seekbar.setProgress(count);
                    holder.Count.setText(count + "/" + model.get(position).getPrsn());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
        holder.btn_signin_listinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, LoginActivity.class);
                i.putExtra("soft",model.get(position).getId());
                mContext.startActivity(i);
                ((Activity)mContext).finish();
            }
        });
        holder.btn_signup_listinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, SignupActivity.class);
                i.putExtra("soft",model.get(position).getId());
                mContext.startActivity(i);
                ((Activity)mContext).finish();
            }
        });
    }



    @Override
    public int getItemCount() {
        return model.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        SeekBar seekbar;
        TextView softwarename, Count, desc;
        Button btn_signin_listinfo, btn_signup_listinfo;

        public Viewholder(View itemView) {
            super(itemView);
            seekbar = (SeekBar) itemView.findViewById(R.id.seekbar);
            btn_signup_listinfo = (Button) itemView.findViewById(R.id.btn_signup_listinfo);
            btn_signin_listinfo = (Button) itemView.findViewById(R.id.btn_signin_listinfo);
            softwarename = (TextView) itemView.findViewById(R.id.softwarename);
            Count = (TextView) itemView.findViewById(R.id.Count);
            desc = (TextView) itemView.findViewById(R.id.desc);
        }
    }
}
