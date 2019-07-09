package softagi.eqraa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import softagi.eqraa.Fragments.CarsFragment;
import softagi.eqraa.Fragments.FoodFragment;
import softagi.eqraa.Fragments.FootballFragment;

public class MainActivity extends AppCompatActivity
{
    TabLayout tabLayout;
    ViewPager viewPager;

    FragmentPagerAdapter fragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tabs);

        // to change status bar views to black
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        fragmentPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager())
        {
            Fragment [] fragments = new Fragment[]
                    {
                            new CarsFragment(),
                            new FootballFragment(),
                            new FoodFragment()
                    };

            String [] names = new String[]
                    {
                            "CARS",
                            "FOOTBALL",
                            "FOOD"
                    };

            @Override
            public Fragment getItem(int position)
            {
                return fragments[position];
            }

            @Override
            public int getCount()
            {
                return fragments.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position)
            {
                return names[position];
            }
        };

        viewPager.setAdapter(fragmentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
