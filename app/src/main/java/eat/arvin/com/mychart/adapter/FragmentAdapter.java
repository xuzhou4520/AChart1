package eat.arvin.com.mychart.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentPagerAdapter {
    //各个页面对应的标题
    private String[] titles;



    public void setFragmentList(ArrayList<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public ArrayList<Fragment> getFragmentList() {
        return fragmentList;
    }

    private ArrayList<Fragment> fragmentList;

    public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    public FragmentAdapter(FragmentManager fm, ArrayList<Fragment> fragmentList, String[] titles) {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int arg0) {
        return fragmentList.get(arg0);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(titles != null && fragmentList != null && titles.length == fragmentList.size()) {
            return titles[position];
        }
        return super.getPageTitle(position);
    }
}
