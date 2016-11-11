package eat.arvin.com.mychart.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import eat.arvin.com.mychart.R;
import eat.arvin.com.mychart.bean.FenshiDataResponse;
import eat.arvin.com.mychart.view.CrossView;
import eat.arvin.com.mychart.view.FenshiView;

/**
 * Created by Administrator on 2016/10/25.
 */
public class FenshiFragment extends LineBaseFragment {
    public static final int REFRUSH_TIME = 10000;
    //数据来源，我直接取我们服务器返回的数据
    private StringBuffer sb = new StringBuffer("{\"success\":1,\"error_code\":0,\"msg\":\"\\u8bf7\\u6c42\\u6210\\u529f\",\"data\":[{\"time\":1478827843,\"price\":20250,\"count\":4,\"money\":80580,\"average\":20145,\"rate\":0.6},{\"time\":1478827872,\"price\":20280,\"count\":1,\"money\":20280,\"average\":20172,\"rate\":0.75},{\"time\":1478827961,\"price\":20260,\"count\":1,\"money\":20260,\"average\":20186.67,\"rate\":0.65},{\"time\":1478828459,\"price\":20270,\"count\":1580,\"money\":32026600,\"average\":20269.68,\"rate\":0.7},{\"time\":1478828505,\"price\":20270,\"count\":1240,\"money\":25134800,\"average\":20269.82,\"rate\":0.7},{\"time\":1478828565,\"price\":20270,\"count\":1770,\"money\":35877900,\"average\":20269.89,\"rate\":0.7},{\"time\":1478828636,\"price\":20270,\"count\":2770,\"money\":56147900,\"average\":20269.93,\"rate\":0.7},{\"time\":1478828698,\"price\":20270,\"count\":1778,\"money\":36040060,\"average\":20269.95,\"rate\":0.7},{\"time\":1478828759,\"price\":20270,\"count\":1169,\"money\":23695630,\"average\":20269.95,\"rate\":0.7},{\"time\":1478828819,\"price\":20270,\"count\":2730,\"money\":55337100,\"average\":20269.96,\"rate\":0.7},{\"time\":1478828879,\"price\":20270,\"count\":1740,\"money\":35269800,\"average\":20269.97,\"rate\":0.7},{\"time\":1478828939,\"price\":20270,\"count\":1794,\"money\":36364380,\"average\":20269.97,\"rate\":0.7},{\"time\":1478828992,\"price\":20299,\"count\":109,\"money\":2209720,\"average\":20269.97,\"rate\":0.84},{\"time\":1478829011,\"price\":20299,\"count\":2,\"money\":40571,\"average\":20269.97,\"rate\":0.84},{\"time\":1478830158,\"price\":20272,\"count\":2,\"money\":40544,\"average\":20269.97,\"rate\":0.71},{\"time\":1478830201,\"price\":20261,\"count\":2,\"money\":40522,\"average\":20269.97,\"rate\":0.65},{\"time\":1478830281,\"price\":20278,\"count\":3,\"money\":60834,\"average\":20269.97,\"rate\":0.74},{\"time\":1478830356,\"price\":20268,\"count\":2,\"money\":40536,\"average\":20269.97,\"rate\":0.69},{\"time\":1478830436,\"price\":20268,\"count\":91,\"money\":1842716,\"average\":20269.85,\"rate\":0.69},{\"time\":1478830551,\"price\":20345,\"count\":7,\"money\":142277,\"average\":20269.87,\"rate\":1.07},{\"time\":1478830578,\"price\":20322,\"count\":70,\"money\":1422540,\"average\":20270.09,\"rate\":0.95},{\"time\":1478830775,\"price\":20307,\"count\":1,\"money\":20307,\"average\":20270.09,\"rate\":0.88},{\"time\":1478830835,\"price\":20331,\"count\":116,\"money\":2358396,\"average\":20270.49,\"rate\":1},{\"time\":1478830882,\"price\":20308,\"count\":1,\"money\":20308,\"average\":20270.5,\"rate\":0.88},{\"time\":1478830975,\"price\":20299,\"count\":1,\"money\":20299,\"average\":20270.5,\"rate\":0.84},{\"time\":1478831026,\"price\":20286,\"count\":1,\"money\":20286,\"average\":20270.5,\"rate\":0.77},{\"time\":1478831078,\"price\":20255,\"count\":15,\"money\":303825,\"average\":20270.48,\"rate\":0.62},{\"time\":1478831198,\"price\":20280,\"count\":55,\"money\":1115275,\"average\":20270.51,\"rate\":0.75},{\"time\":1478831412,\"price\":20259,\"count\":20,\"money\":405180,\"average\":20270.49,\"rate\":0.64},{\"time\":1478831541,\"price\":20279,\"count\":98,\"money\":1987142,\"average\":20270.53,\"rate\":0.74},{\"time\":1478831620,\"price\":20301,\"count\":75,\"money\":1522291,\"average\":20270.65,\"rate\":0.85},{\"time\":1478832041,\"price\":20314,\"count\":25,\"money\":507850,\"average\":20270.7,\"rate\":0.91},{\"time\":1478832119,\"price\":20345,\"count\":477,\"money\":9703139,\"average\":20272.59,\"rate\":1.07},{\"time\":1478832176,\"price\":20366,\"count\":598,\"money\":12168655,\"average\":20275.08,\"rate\":1.17},{\"time\":1478832235,\"price\":20366,\"count\":880,\"money\":17922080,\"average\":20279.24,\"rate\":1.17},{\"time\":1478832259,\"price\":20399,\"count\":4,\"money\":81577,\"average\":20279.26,\"rate\":1.34},{\"time\":1478832302,\"price\":20436,\"count\":5,\"money\":102180,\"average\":20279.3,\"rate\":1.52},{\"time\":1478832366,\"price\":20413,\"count\":1,\"money\":20413,\"average\":20279.31,\"rate\":1.41},{\"time\":1478832459,\"price\":20394,\"count\":853,\"money\":17396082,\"average\":20284.18,\"rate\":1.31},{\"time\":1478832539,\"price\":20419,\"count\":628,\"money\":12823132,\"average\":20288.21,\"rate\":1.44},{\"time\":1478832589,\"price\":20436,\"count\":1224,\"money\":25008207,\"average\":20296.2,\"rate\":1.52},{\"time\":1478832618,\"price\":20458,\"count\":13,\"money\":265954,\"average\":20296.3,\"rate\":1.63},{\"time\":1478832750,\"price\":20401,\"count\":100,\"money\":2041894,\"average\":20296.85,\"rate\":1.35},{\"time\":1478832780,\"price\":20431,\"count\":90,\"money\":1838790,\"average\":20297.4,\"rate\":1.5},{\"time\":1478832898,\"price\":20480,\"count\":2,\"money\":40979,\"average\":20297.41,\"rate\":1.74},{\"time\":1478832916,\"price\":20499,\"count\":1,\"money\":20499,\"average\":20297.42,\"rate\":1.83},{\"time\":1478833003,\"price\":20525,\"count\":4,\"money\":82074,\"average\":20297.46,\"rate\":1.96},{\"time\":1478833120,\"price\":20578,\"count\":4,\"money\":82228,\"average\":20297.51,\"rate\":2.23},{\"time\":1478833182,\"price\":20538,\"count\":320,\"money\":6572160,\"average\":20300.93,\"rate\":2.03},{\"time\":1478833252,\"price\":20499,\"count\":5,\"money\":102495,\"average\":20300.98,\"rate\":1.83},{\"time\":1478833288,\"price\":20542,\"count\":850,\"money\":17460700,\"average\":20309.76,\"rate\":2.05},{\"time\":1478833371,\"price\":20630,\"count\":56,\"money\":1150827,\"average\":20310.33,\"rate\":2.48},{\"time\":1478833393,\"price\":20666,\"count\":3,\"money\":61926,\"average\":20310.37,\"rate\":2.66},{\"time\":1478833498,\"price\":20699,\"count\":1598,\"money\":33077002,\"average\":20335.22,\"rate\":2.83},{\"time\":1478833511,\"price\":20730,\"count\":206,\"money\":4264149,\"average\":20338.2,\"rate\":2.98},{\"time\":1478833657,\"price\":20766.36,\"count\":5,\"money\":103831.8,\"average\":20338.28,\"rate\":3.16},{\"time\":1478833691,\"price\":20799,\"count\":8,\"money\":166261.44,\"average\":20338.42,\"rate\":3.32},{\"time\":1478833785,\"price\":20830,\"count\":31,\"money\":644800,\"average\":20338.99,\"rate\":3.48},{\"time\":1478833849,\"price\":20866,\"count\":6,\"money\":125196,\"average\":20339.1,\"rate\":3.66},{\"time\":1478833914,\"price\":20899,\"count\":3,\"money\":62697,\"average\":20339.17,\"rate\":3.82},{\"time\":1478833939,\"price\":20930,\"count\":7,\"money\":146386,\"average\":20339.33,\"rate\":3.97},{\"time\":1478834033,\"price\":20966,\"count\":3,\"money\":62898,\"average\":20339.4,\"rate\":4.15},{\"time\":1478834041,\"price\":20999,\"count\":3,\"money\":62997,\"average\":20339.48,\"rate\":4.32},{\"time\":1478834151,\"price\":20920,\"count\":27,\"money\":567700,\"average\":20340.19,\"rate\":3.92},{\"time\":1478834213,\"price\":20857,\"count\":911,\"money\":19000853,\"average\":20358.16,\"rate\":3.61},{\"time\":1478834247,\"price\":20924,\"count\":884,\"money\":18496816,\"average\":20376.63,\"rate\":3.94},{\"time\":1478834306,\"price\":20849,\"count\":50,\"money\":1042474,\"average\":20377.5,\"rate\":3.57},{\"time\":1478834350,\"price\":20773,\"count\":60,\"money\":1246510,\"average\":20378.39,\"rate\":3.19},{\"time\":1478834416,\"price\":20840,\"count\":1,\"money\":20840,\"average\":20378.4,\"rate\":3.53},{\"time\":1478834487,\"price\":20903,\"count\":3,\"money\":62709,\"average\":20378.46,\"rate\":3.84},{\"time\":1478834539,\"price\":20840,\"count\":1,\"money\":20840,\"average\":20378.48,\"rate\":3.53},{\"time\":1478834606,\"price\":20908,\"count\":33,\"money\":690251,\"average\":20379.13,\"rate\":3.86},{\"time\":1478834671,\"price\":20954,\"count\":54,\"money\":1131426,\"average\":20380.26,\"rate\":4.09},{\"time\":1478834703,\"price\":20908,\"count\":3,\"money\":62724,\"average\":20380.32,\"rate\":3.86},{\"time\":1478834776,\"price\":20874,\"count\":3,\"money\":62622,\"average\":20380.38,\"rate\":3.7},{\"time\":1478834821,\"price\":20908,\"count\":3,\"money\":62724,\"average\":20380.43,\"rate\":3.86},{\"time\":1478834880,\"price\":20870,\"count\":1,\"money\":20870,\"average\":20380.45,\"rate\":3.68},{\"time\":1478834985,\"price\":20860,\"count\":1,\"money\":20860,\"average\":20380.47,\"rate\":3.63},{\"time\":1478840492,\"price\":20888,\"count\":74,\"money\":1545712,\"average\":20381.84,\"rate\":3.77},{\"time\":1478840571,\"price\":20930,\"count\":66,\"money\":1381338,\"average\":20383.15,\"rate\":3.97},{\"time\":1478840638,\"price\":20954,\"count\":1413,\"money\":29608002,\"average\":20411.09,\"rate\":4.09},{\"time\":1478840699,\"price\":20996,\"count\":1208,\"money\":25344352,\"average\":20433.96,\"rate\":4.3},{\"time\":1478840756,\"price\":21050,\"count\":753,\"money\":15832624,\"average\":20448.43,\"rate\":4.57},{\"time\":1478840819,\"price\":21080,\"count\":1107,\"money\":23321250,\"average\":20469.87,\"rate\":4.72},{\"time\":1478840855,\"price\":21115,\"count\":309,\"money\":6515022,\"average\":20475.75,\"rate\":4.89},{\"time\":1478840902,\"price\":21055,\"count\":160,\"money\":3368800,\"average\":20478.61,\"rate\":4.6},{\"time\":1478840992,\"price\":21080,\"count\":743,\"money\":15644740,\"average\":20491.54,\"rate\":4.72},{\"time\":1478841049,\"price\":21110,\"count\":1027,\"money\":21674870,\"average\":20509.98,\"rate\":4.87},{\"time\":1478841125,\"price\":21130,\"count\":52,\"money\":1098760,\"average\":20510.89,\"rate\":4.97},{\"time\":1478841182,\"price\":21166,\"count\":4,\"money\":84664,\"average\":20510.97,\"rate\":5.15},{\"time\":1478841257,\"price\":21230,\"count\":10,\"money\":212021,\"average\":20511.17,\"rate\":5.46},{\"time\":1478841417,\"price\":21198,\"count\":20,\"money\":423960,\"average\":20511.57,\"rate\":5.31},{\"time\":1478841436,\"price\":21170,\"count\":3,\"money\":63510,\"average\":20511.63,\"rate\":5.17},{\"time\":1478841536,\"price\":21166,\"count\":3,\"money\":63498,\"average\":20511.69,\"rate\":5.15},{\"time\":1478841555,\"price\":21133,\"count\":3,\"money\":63399,\"average\":20511.74,\"rate\":4.98},{\"time\":1478841613,\"price\":21198,\"count\":8,\"money\":169584,\"average\":20511.9,\"rate\":5.31},{\"time\":1478841711,\"price\":21230,\"count\":3,\"money\":63626,\"average\":20511.96,\"rate\":5.46},{\"time\":1478841757,\"price\":21266,\"count\":5,\"money\":106330,\"average\":20512.07,\"rate\":5.64},{\"time\":1478841799,\"price\":21299,\"count\":3,\"money\":63897,\"average\":20512.14,\"rate\":5.81},{\"time\":1478841906,\"price\":21329,\"count\":7,\"money\":149243,\"average\":20512.3,\"rate\":5.96},{\"time\":1478842079,\"price\":21261,\"count\":2,\"money\":42590,\"average\":20512.35,\"rate\":5.62},{\"time\":1478842110,\"price\":21220,\"count\":3,\"money\":63660,\"average\":20512.41,\"rate\":5.41},{\"time\":1478842316,\"price\":21240,\"count\":304,\"money\":6456960,\"average\":20518.8,\"rate\":5.51},{\"time\":1478842377,\"price\":21284,\"count\":162,\"money\":3443608,\"average\":20522.24,\"rate\":5.73},{\"time\":1478842433,\"price\":21284,\"count\":216,\"money\":4597344,\"average\":20526.95,\"rate\":5.73},{\"time\":1478842553,\"price\":21239,\"count\":126,\"money\":2678229,\"average\":20529.57,\"rate\":5.51},{\"time\":1478842617,\"price\":21177,\"count\":277,\"money\":5866587,\"average\":20534.66,\"rate\":5.2},{\"time\":1478845076,\"price\":21129,\"count\":343,\"money\":7247247,\"average\":20540.37,\"rate\":4.96},{\"time\":1478845125,\"price\":21129,\"count\":175,\"money\":3697575,\"average\":20543.24,\"rate\":4.96},{\"time\":1478845160,\"price\":21129,\"count\":73,\"money\":1542417,\"average\":20544.43,\"rate\":4.96},{\"time\":1478845259,\"price\":21150,\"count\":293,\"money\":6196950,\"average\":20549.33,\"rate\":5.07},{\"time\":1478845289,\"price\":21150,\"count\":212,\"money\":4483800,\"average\":20552.82,\"rate\":5.07},{\"time\":1478845378,\"price\":21170,\"count\":490,\"money\":10365600,\"average\":20560.79,\"rate\":5.17},{\"time\":1478845420,\"price\":21170,\"count\":422,\"money\":8933740,\"average\":20567.67,\"rate\":5.17},{\"time\":1478845494,\"price\":21144,\"count\":512,\"money\":10825728,\"average\":20575.46,\"rate\":5.04},{\"time\":1478845548,\"price\":21144,\"count\":386,\"money\":8161584,\"average\":20581.19,\"rate\":5.04},{\"time\":1478845618,\"price\":21144,\"count\":564,\"money\":11925216,\"average\":20589.37,\"rate\":5.04},{\"time\":1478845674,\"price\":21144,\"count\":332,\"money\":7019808,\"average\":20594.07,\"rate\":5.04},{\"time\":1478845719,\"price\":21144,\"count\":524,\"money\":11079456,\"average\":20601.33,\"rate\":5.04},{\"time\":1478846486,\"price\":21189,\"count\":6,\"money\":127065,\"average\":20601.42,\"rate\":5.26}],\"param\":{\"last\":20029.89,\"duration\":\"9:30-11:30|13:00-15:00\",\"length\":14400,\"until\":1478846640},\"page\":0,\"number\":0,\"total\":0}");
    private FenshiView fenshiView;
    private CrossView crossView;
    //滑动十字线时，显示对应点详情的地方
    private TextView msgText;
    //分时数据
    private FenshiDataResponse data;

    //是否全屏
    private boolean isPause;
    /**
     * 循环刷新界面
     */
    private Handler refrushHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isShow && !isPause) {
            }
            if (!isPause) {
                refrushHandler.sendEmptyMessageDelayed(0, REFRUSH_TIME);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        data = new Gson().fromJson(sb.toString(), FenshiDataResponse.class);
        return inflater.inflate(R.layout.chart_fenshi_frag, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findViews();
        fenshiView.setUsedViews(crossView, msgText);
        indexTab.setOnTabSelectedListener(this);
        for (String s : INDEX_FENSHI_TAB) {
            indexTab.addTab(indexTab.newTab().setText(s));
        }
        fenshiView.setDataAndInvalidate(data);
    }

    @Override
    public void onResume() {
        super.onResume();
        isPause = false;
        refrushHandler.sendEmptyMessageDelayed(0, REFRUSH_TIME);
    }

    @Override
    public void onPause() {
        super.onPause();
        isPause = true;
    }

    private void findViews() {
        msgText = (TextView) getView().findViewById(R.id.cff_msg);
        fenshiView = (FenshiView) getView().findViewById(R.id.cff_fenshiview);
        crossView = (CrossView) getView().findViewById(R.id.cff_cross);

        addListener();
    }

    private void addListener() {
        fenshiView.setOnDoubleTapListener(this);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case INDEX_VOL:
                fenshiView.showVOL();
                break;
            case INDEX_ZJ:
               //TODO 显示指标资金
                break;
        }
    }

}
