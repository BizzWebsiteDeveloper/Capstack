package alap.com.capstack.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import alap.com.capstack.R;
import alap.com.capstack.custom.TouchImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FlowChartFragment extends Fragment {


    public FlowChartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_flow_chart, container, false);
        TouchImageView imageview_flowchart=(TouchImageView)v.findViewById(R.id.imageview_flowchart);
        return v;
    }

}
