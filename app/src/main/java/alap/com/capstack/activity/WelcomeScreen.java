package alap.com.capstack.activity;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import alap.com.capstack.R;
import alap.com.capstack.custom.NonSwipeableViewPager;
import alap.com.capstack.fragment.WelcomeScreenFragment1;
import alap.com.capstack.fragment.WelcomeScreenFragment2;

public class WelcomeScreen extends AppCompatActivity implements WelcomeScreenFragment1.NextClicked,WelcomeScreenFragment2.PrevClicked {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private NonSwipeableViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (NonSwipeableViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


//

    }

    @Override
    public void nextcliked() {
        mViewPager.setCurrentItem(1);
    }

    @Override
    public void prevcliked() {
        mViewPager.setCurrentItem(0);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a WelcomeScreenFragment1 (defined as a static inner class below).
            if (position == 0) {
                WelcomeScreenFragment1 pf = new WelcomeScreenFragment1();
                return pf;
            }
            else {
                WelcomeScreenFragment2 pf = new WelcomeScreenFragment2();
                return pf;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }


    }
}
