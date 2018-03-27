package alap.com.capstack.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import alap.com.capstack.R;

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
        return v;
    }

}
