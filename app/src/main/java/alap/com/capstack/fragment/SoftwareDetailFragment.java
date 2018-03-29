package alap.com.capstack.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alap.com.capstack.R;
import alap.com.capstack.activity.SoftwareDetail;

/**
 * Created by Android-2 on 28-03-2018.
 */

public class SoftwareDetailFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public SoftwareDetailFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
//    public static SoftwareDetailFragment newInstance(int sectionNumber) {
//        SoftwareDetailFragment fragment = new SoftwareDetailFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_software_detail, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);

        return rootView;
    }
}