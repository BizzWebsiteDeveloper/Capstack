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
import alap.com.capstack.model.ModelMember;
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

public class UserlistAdapter extends RecyclerView.Adapter<UserlistAdapter.Viewholder> {
    Context mContext;
    ArrayList<ModelMember> model = new ArrayList<>();

    public UserlistAdapter(Context mContext, ArrayList<ModelMember> modelSoftwares) {
        this.mContext = mContext;
        model = modelSoftwares;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_table, parent, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, final int position) {
        holder.name.setText(model.get(position).getName());
        holder.country.setText("");
        holder.date.setText(model.get(position).getDate());
        holder.ststus.setText(model.get(position).getStatus());

    }



    @Override
    public int getItemCount() {
        return model.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        TextView name, country, date,ststus;


        public Viewholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            country = (TextView) itemView.findViewById(R.id.country);
            date = (TextView) itemView.findViewById(R.id.date);
            ststus = (TextView) itemView.findViewById(R.id.status);
        }
    }
}
