package alap.com.capstack.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import alap.com.capstack.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeScreenFragment2 extends Fragment {


    public WelcomeScreenFragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_welcome_screen_fragment2, container, false);
        // Inflate the layout for this fragment
        return v;
    }

}
