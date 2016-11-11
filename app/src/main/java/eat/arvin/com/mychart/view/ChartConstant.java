package eat.arvin.com.mychart.view;

/**
 * Created by Arvin on 2016/10/25.
 * 图表的常量
 */
public interface ChartConstant {
    /**
     * 单击显示十字线延时，为双击全屏腾出时间
     */
    int DOUBLE_TAP_DELAY = 300;

    String[] INDEX_FENSHI_TAB = {"VOL", "资金"};
    String[] INDEX_KLINE_TAB = {"VOL", "资金", "MACD", "KDJ"};
    //K线、分时图占SurfaceView的比例
    float MAIN_SCALE = 175f / 260f;
    //时间占比
    float TIME_SCALE = 20f / 260f;
    //指标占比
    float INDEX_SCALE = 65f / 260f;

    //各个界面对应的下标
    int TYPE_FENSHI = 0;
    int TYPE_1M = 2;
    int TYPE_5M = 3;
    int TYPE_30M = 6;
    int TYPE_60M = 7;
    int TYPE_RIK = 1;
    int TYPE_ZHOUK = 4;
    int TYPE_YUEK = 5;

    //指标对应的下标
    int INDEX_VOL = 0;
    int INDEX_ZJ = 1;
    int INDEX_MACD = 2;
    int INDEX_KDJ = 3;
}
