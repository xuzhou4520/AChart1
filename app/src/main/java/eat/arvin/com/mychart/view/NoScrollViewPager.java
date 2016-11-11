package eat.arvin.com.mychart.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by Arvin on 2016/10/26.
 * 使用k线和分时图页面，
 * 当十字线出现的时候，则不可以滑动，其他地方使用无效
 */
public class NoScrollViewPager extends ViewPager {
    public NoScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
//        if (v != this && v instanceof CrossView && v.getVisibility() == VISIBLE)
//        {// 解决冲突
            return true;
//        }
//        return super.canScroll(v, checkV, dx, x, y);
    }
}
