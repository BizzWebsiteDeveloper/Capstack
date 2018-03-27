package alap.com.capstack.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alap.com.capstack.R;

/**
 * Created by Android-2 on 26-03-2018.
 */

public class WelcomeScreenFragment1 extends Fragment {
    TextView nextButton;

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public WelcomeScreenFragment1() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_welcome_screen, container, false);
        nextButton=(TextView)rootView.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NextClicked n=(NextClicked)getContext();
                n.nextcliked();
            }
        });
        return rootView;
    }
    public interface NextClicked{
        public void nextcliked();
    }
}