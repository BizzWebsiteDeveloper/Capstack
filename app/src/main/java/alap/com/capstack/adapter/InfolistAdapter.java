package alap.com.capstack.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import alap.com.capstack.R;
import alap.com.capstack.activity.LoginActivity;
import alap.com.capstack.activity.SignupActivity;

/**
 * Created by Android-2 on 27-03-2018.
 */

public class InfolistAdapter extends RecyclerView.Adapter<InfolistAdapter.Viewholder> {
    Context mContext;

    public InfolistAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_info_list,parent,false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.seekbar.setEnabled(false);
        holder.btn_signin_listinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mContext, LoginActivity.class);
                mContext.startActivity(i);
            }
        });
        holder.btn_signup_listinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mContext, SignupActivity.class);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        SeekBar seekbar;
        Button btn_signin_listinfo,btn_signup_listinfo;
        public Viewholder(View itemView) {
            super(itemView);
            seekbar=(SeekBar)itemView.findViewById(R.id.seekbar);
            btn_signup_listinfo=(Button)itemView.findViewById(R.id.btn_signup_listinfo);
            btn_signin_listinfo=(Button)itemView.findViewById(R.id.btn_signin_listinfo);
        }
    }
}
