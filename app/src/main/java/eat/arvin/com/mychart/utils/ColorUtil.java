package eat.arvin.com.mychart.utils;

import android.graphics.Color;

import eat.arvin.com.mychart.bean.CMinute;
import eat.arvin.com.mychart.bean.StickData;


/**
 * Created by Administrator on 2016/8/25.
 */
public class ColorUtil {
    //资金超大单线颜色
    public static final int COLOR_ZJ_SUPER = Color.parseColor("#FF9966");
    //资金大单线颜色
    public static final int COLOR_ZJ_BIG = Color.parseColor("#FF0000");
    //资金中单线颜色
    public static final int COLOR_ZJ_MIDDLE = Color.parseColor("#999900");
    //资金超小线颜色
    public static final int COLOR_ZJ_SMALL = Color.parseColor("#0099FF");
    //跌颜色
    public static final int COLOR_GREEN = Color.parseColor("#1aaa0f");
    //涨颜色
    public static final int COLOR_RED = Color.parseColor("#ff4c52");
    //平灰
    public static final int COLOR_PING_ASH = Color.parseColor("#333333");
    //平白
    public static final int COLOR_PING_WHITE = Color.parseColor("#efefef");
    //5均线颜色
    public static final int COLOR_SMA5 = Color.parseColor("#f2cfa9");
    //10均线颜色
    public static final int COLOR_SMA10 = Color.parseColor("#687cd5");
    //20均线颜色
    public static final int COLOR_SMA20 = Color.parseColor("#e9837e");
    //MACD指标DIF颜色
    public static final int COLOR_DIF = Color.parseColor("#85d6f0");
    //MACD指标DEA颜色
    public static final int COLOR_DEA = Color.parseColor("#fea044");
    //MACD指标MACD字体颜色
    public static final int COLOR_MACD = Color.parseColor("#e27bda");
    //KDJ指标K颜色
    public static final int COLOR_KDJ_K = COLOR_DIF;
    //KDJ指标D颜色
    public static final int COLOR_KDJ_D = COLOR_DEA;
    //KDJ指标J颜色
    public static final int COLOR_KDJ_J = Color.parseColor("#e27bda");

    //分时线价格颜色
    public static final int COLOR_PRICE_LINE = Color.parseColor("#3483e9");
    //分时线均线颜色
    public static final int COLOR_SMA_LINE = Color.parseColor("#df8925");
    public static final int COLOR_CROSS_LINE = Color.parseColor("#2e68b2");


    public static String getColorRGB(double curr, double change) {
        if (curr > change) return "#e36d50";
        if (curr < change) return "#3b7f19";
        return "#333333";
    }

    /**
     * 获取价格显示的颜色，curr>change是红，等于是黑，小于是绿
     * 平是灰色
     *
     * @param curr   当前价
     * @param change 变化颜色的价格
     * @return
     */
    public static int getTextColorAsh(double curr, double change) {
        if (curr == change)
            return COLOR_PING_ASH;
        if (curr < change)
            return COLOR_GREEN;
        return COLOR_RED;
    }

    /**
     * 获取价格显示的颜色，curr>change是红，等于是黑，小于是绿
     * 平是白色
     *
     * @param curr   当前价
     * @param change 变化颜色的价格
     * @return
     */
    public static int getTextColorWhite(double curr, double change) {
        if (curr == change)
            return COLOR_PING_WHITE;
        if (curr < change)
            return COLOR_GREEN;
        return COLOR_RED;
    }

    /**
     * 价格信息
     * 十字线滑动时，显示部分信息
     *
     * @param entity
     * @return
     */
    public static String getCurPriceInfo(CMinute entity, double yd) {

        StringBuffer sb = new StringBuffer();
        sb.append("   " + entity.getTimeStr());
        if (entity.getPrice() >= yd) {
            sb.append("  价格" + "<font color='#e36d50'>" + NumberUtil.beautifulDouble(entity.getPrice()) + "</font>");
        } else {
            sb.append("  价格" + "<font color='#3b7f19'>" + NumberUtil.beautifulDouble(entity.getPrice()) + "</font>");
        }
        if (entity.getRate() >= 0) {
            sb.append("  涨幅" + "<font color='#e36d50'>" + NumberUtil.beautifulDouble(entity.getRate()) + "%</font>");
        } else {
            sb.append("  涨幅" + "<font color='#3b7f19'>" + NumberUtil.beautifulDouble(entity.getRate()) + "%</font>");
        }
        sb.append("  成交" + entity.getCount());
        if (entity.getAverage() >= yd) {
            sb.append("  均价" + "<font color='#e36d50'>" + NumberUtil.beautifulDouble(entity.getAverage()) + "</font>");
        } else {
            sb.append("  均价" + "<font color='#3b7f19'>" + NumberUtil.beautifulDouble(entity.getAverage()) + "</font>");
        }
        return sb.toString();
    }

    /**
     * 价格、涨跌量、涨幅
     *
     * @param now
     * @param diff
     * @param rate
     * @return
     */
    public static String getHtmlText(String now, String diff, String rate) {
        StringBuffer sb = new StringBuffer();
        if (Double.parseDouble(diff) >= 0) {
            sb.append("<font color='#e36d50'>" + NumberUtil.getMoneyString(Double.parseDouble(now == null ? "0" : now)) + "</font>");
            sb.append("　　<font color='#e36d50'>+" + diff + "</font>");
            sb.append("　　<font color='#e36d50'>" + rate + "%</font>");
        } else {
            sb.append("<font color='#3b7f19'>" + NumberUtil.getMoneyString(Double.parseDouble(now == null ? "0" : now)) + "</font>");
            sb.append("　　<font color='#3b7f19'>" + diff + "</font>");
            sb.append("　　<font color='#3b7f19'>" + rate + "%</font>");
        }
        return sb.toString();
    }


    public static String getCurPriceInfo(StickData entity) {
        StringBuffer sb = new StringBuffer();
        sb.append("  " + entity.getTime());
        if (entity.getOpen() >= entity.getLast()) {
            sb.append("  开" + "<font color='#e36d50'>" + entity.getOpen() + "</font>");
        } else {
            sb.append("  开" + "<font color='#3b7f19'>" + entity.getOpen() + "</font>");
        }

        if (entity.getClose() >= entity.getLast()) {
            sb.append("  收" + "<font color='#3b7f19'>" + entity.getClose() + "</font>");
        } else {
            sb.append("  收" + "<font color='#e36d50'>" + entity.getClose() + "</font>");
        }

        if (entity.getHigh() >= entity.getLast()) {
            sb.append("  高" + "<font color='#e36d50'>" + entity.getHigh() + "</font>");
        } else {
            sb.append("  高" + "<font color='#3b7f19'>" + entity.getHigh() + "</font>");
        }
        if (entity.getLow() >= entity.getLast()) {
            sb.append("  低" + "<font color='#e36d50'>" + entity.getLow() + "</font>");
        } else {
            sb.append("  低" + "<font color='#3b7f19'>" + entity.getLow() + "</font>");
        }
        sb.append("  量" + entity.getCount());
        sb.append("  额" + NumberUtil.formartBigNumber(entity.getMoney()));
//        if (entity.getClose() >= entity.getLast()) {
//            sb.append("  量" + "<font color='#3b7f19'>" + entity.getCount() + "</font>");
//        } else {
//            sb.append("  量" + "<font color='#e36d50'>" + entity.getCount() + "</font>");
//        }
//        if (entity.getOpen() >= entity.getClose()) {
//            sb.append("  额" + "<font color='#3b7f19'>" + NumberUtil.formartBigNumber(entity.getMoney()) + "</font>");
//        } else {
//            sb.append("  额" + "<font color='#e36d50'>" + NumberUtil.formartBigNumber(entity.getMoney()) + "</font>");
//        }
        sb.append("  SMA5:" + "<font color='#f2cfa9'>" + NumberUtil.beautifulDouble(entity.getSma5()) + "</font>");
        sb.append("  SMA10:" + "<font color='#687cd5'>" + NumberUtil.beautifulDouble(entity.getSma10()) + "</font>");
        sb.append("  SMA20:" + "<font color='#e9837e'>" + NumberUtil.beautifulDouble(entity.getSma20()) + "</font>");
        return sb.toString();
    }
}
