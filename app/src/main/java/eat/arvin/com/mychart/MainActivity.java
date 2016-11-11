package eat.arvin.com.mychart;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

import eat.arvin.com.mychart.adapter.FragmentAdapter;
import eat.arvin.com.mychart.fragment.FenshiFragment;
import eat.arvin.com.mychart.fragment.KLineFragment;
import eat.arvin.com.mychart.view.NoScrollViewPager;

public class MainActivity extends FragmentActivity {
    public static final String[] TITLES = {"分时线", "日K"};
    private TabLayout tabLayout;
    private NoScrollViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (NoScrollViewPager) findViewById(R.id.viewpager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new FenshiFragment());
        fragments.add(new KLineFragment());
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, TITLES);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
