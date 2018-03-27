package alap.com.capstack.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import alap.com.capstack.R;
import alap.com.capstack.activity.InformationWindow2;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeScreenFragment2 extends Fragment {


    Button btn_login;

    public WelcomeScreenFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_welcome_screen_fragment2, container, false);
        btn_login=(Button)v.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getContext(), InformationWindow2.class);
                startActivity(i);

            }
        });
        return v;
    }

}
