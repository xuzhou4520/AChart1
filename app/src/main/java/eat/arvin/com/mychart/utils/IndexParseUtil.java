package eat.arvin.com.mychart.utils;


import java.util.List;

import eat.arvin.com.mychart.bean.StickData;

/**
 * Created by Arvin on 2016/9/14.
 * 计算各种指标
 */
public class IndexParseUtil {

    //均线跨度(SMA5,SMA10,SMA20),注意修改该值时，需要同时增加StickData里面的sma字段、修改本类initSma方法，否则不会生效
    public static final int START_SMA5 = 5;
    public static final int START_SMA10 = 10;
    public static final int START_SMA20 = 20;
    //26:计算MACD时，26段close均价DIF=(EMA(CLOSE,12) - EMA(CLOSE,26))
    public static final int START_DIF = 26;
    //35：计算MACD时，35段开始取前九日DIF值 DEA:=EMA(DIF,9)
    public static final int START_DEA = 35;
    //12:计算K值
    public static final int START_K = 12;
    //15:计算DJ
    public static final int START_DJ = 15;
    //9:计算RSV
    public static final int START_REV = 9;

    public static final int[] SMA = {START_SMA5,START_SMA10, START_SMA20};

    /**
     * 计算MACD
     * @param list
     */
    public static void initMACD(List<StickData> list) {
        if(list == null) return;
        //1计算出所有的DIF
        for(int i = 0; i < list.size(); i++) {
            if(i + START_DIF <= list.size()) {
                list.get(i + START_DIF - 1).setDif(getCloseSma(list.subList(i + START_DIF - 12, i + START_DIF)) - getCloseSma(list.subList(i + START_DIF - 26, i + START_DIF)));
            }
        }
        //2计算出所有的DEA
        for(int i = 0; i < list.size(); i++) {
            if(i + START_DEA <= list.size()) {
                list.get(i + START_DEA - 1).setDea(getDifSma(list.subList(i + START_DEA - 9, i + START_DEA)));
                //3计算MACD
                list.get(i + START_DEA - 1).setMacd(2d * (list.get(i + START_DEA - 1).getDif() - list.get(i + START_DEA - 1).getDea()));
            }
        }

    }

    /**
     * 计算KDJ
     * @param list
     */
    public static void initKDJ(List<StickData> list) {
        if(list == null) return;
        //1计算出所有的REV
        for(int i = 0; i < list.size(); i++) {
            if(i + START_REV <= list.size()) {
                //第9日开始计算RSV
                StickData data = list.get(i + START_REV - 1);
                double[] maxAndMin = getMaxAndMin(list.subList(i, i + START_REV));
                list.get(i + START_REV - 1).setRsv((data.getClose() - maxAndMin[1]) / (maxAndMin[0] - maxAndMin[1]) * 100);
            }
        }
        //2计算出所有K
        for(int i = 0; i < list.size(); i++) {
            if(i + START_K <= list.size()) {
                list.get(i + START_K - 1).setK(getRSVSma(list.subList(i + START_K - 3, i + START_K)));
            }
        }
        //3计算出所有的DJ
        for(int i = 0; i < list.size(); i++) {
            if(i + START_DJ <= list.size()) {
                StickData data = list.get(i + START_DJ - 1);
                list.get(i + START_DJ - 1).setD(getKSma(list.subList(i + START_DJ - 3, i + START_DJ)));
                list.get(i + START_DJ - 1).setJ(3 * data.getK() - 2 * data.getD());
            }
        }

    }
    /**
     * 把list里面所有数据对应的均线计算出来并且赋值到里面
     *
     * @param list k线数据
     */
    public static void initSma(List<StickData> list) {
        if (list == null) return;
        for (int i = 0; i < list.size(); i++) {
            for (int j : SMA) {
                if (i + j <= list.size()) {
                    //第5日开始计算5日均线
                    if (j == START_SMA5) {
                        //量的SMA5
                        list.get(i + j - 1).setCountSma5(getCountSma(list.subList(i, i + j)));
                        //K线的SMA5
                        list.get(i + j - 1).setSma5(getCloseSma(list.subList(i, i + j)));
                    } else
                        //第10日开始计算10日均线
                        if (j == START_SMA10) {
                            //量的SMA10
                            list.get(i + j - 1).setCountSma10(getCountSma(list.subList(i, i + j)));
                            //K线的SMA10
                            list.get(i + j - 1).setSma10(getCloseSma(list.subList(i, i + j)));
                        }else
                            //第20日开始计算20日均线
                            if (j == START_SMA20) {
                                //K线的SMA20
                                list.get(i + j - 1).setSma20(getCloseSma(list.subList(i, i + j)));
                            }
                }
            }
        }
    }

    /**
     * 计算KDJ时，取9日最高最低值
     * @param datas
     * @return
     */
    private static double[] getMaxAndMin(List<StickData> datas) {
        if(datas == null || datas.size() == 0)
            return new double[]{0, 0};
        double max = datas.get(0).getHigh();
        double min = datas.get(0).getLow();
        for(StickData data : datas) {
            max = max > data.getHigh() ? max : data.getHigh();
            min = min < data.getLow() ? min : data.getLow();
        }
        return new double[]{max, min};
    }


    /**
     * K线量计算移动平均值
     * @param datas
     * @return
     */
    private static double getCountSma(List<StickData> datas) {
        if (datas == null) return -1;
        double sum = 0;
        for (StickData data : datas) {
            sum += data.getCount();
        }
        return NumberUtil.doubleDecimal(sum / datas.size());
    }

    /**
     * K线收盘价计算移动平均价
     * @param datas
     * @return
     */
    private static double getCloseSma(List<StickData> datas) {
        if (datas == null) return -1;
        double sum = 0;
        for (StickData data : datas) {
            sum += data.getClose();
        }
        return NumberUtil.doubleDecimal(sum / datas.size());
    }

    /**
     * K线dif的移动平均值
     * @param datas
     * @return
     */
    private static double getDifSma(List<StickData> datas) {
        if (datas == null) return -1;
        double sum = 0;
        for (StickData data : datas) {
            sum += data.getDif();
        }
        return NumberUtil.doubleDecimal(sum / datas.size());
    }

    /**
     * 三日rsv移动平均值，即K值
     * @param datas
     * @return
     */
    private static double getRSVSma(List<StickData> datas) {
        if (datas == null) return -1;
        double sum = 0;
        for (StickData data : datas) {
            sum += data.getRsv();
        }
        return NumberUtil.doubleDecimal(sum / datas.size());
    }

    /**
     * 三日K移动平均值，即D值
     * @param datas
     * @return
     */
    private static double getKSma(List<StickData> datas) {
        if (datas == null) return -1;
        double sum = 0;
        for (StickData data : datas) {
            sum += data.getK();
        }
        return NumberUtil.doubleDecimal(sum / datas.size());
    }

}
