package eat.arvin.com.mychart.utils;

import android.graphics.Paint;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import eat.arvin.com.mychart.bean.CMinute;
import eat.arvin.com.mychart.bean.FenshiDataResponse;

/**
 * Created by Arvin on 2016/8/4.
 * 分时线等这些图标的通用工具类
 */
public class LineUtil {

    /**
     * 传入时间和分钟如15:00，解析成分钟数，
     *
     * @param minStr 17:00
     * @return
     */
    public static int getMin(String minStr) throws NumberFormatException {
        return Integer.parseInt(minStr.split(":")[0]) * 60 + Integer.parseInt(minStr.split(":")[1]);
    }

    /**
     * 通过long时间获取分钟数
     *
     * @param time
     * @return
     */
    public static int getMin(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time * 1000);
        return getMin(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
    }

    /**
     * 根据开收盘的时间，计算网格竖线的x轴坐标
     * @param duration
     * @return
     */
    public static float[] getXByDuration(String duration, float width) {
        if(TextUtils.isEmpty(duration)) return new float[]{};
        int showCount = getShowCount(duration);
        float xUnit = width / (float) showCount;
        ArrayList<Integer> timeMins = getTimesMin(duration);
        if(timeMins.size()== 6) {
            //如果白盘夜盘一起的话，需要画5条竖线（虚实虚实虚）
            float[] result = new float[5];
            //实线1
            result[1] = (timeMins.get(1) - timeMins.get(0)) * xUnit;
            //实线2
            result[3] = (timeMins.get(3) - timeMins.get(0) - (timeMins.get(2) - timeMins.get(1))) * xUnit;
            //虚线1
            result[0] =  result[1] / 2f;
            //虚线2
            result[2] = (result[3] - result[1])/ 2f + result[1];
            //虚线3
            result[4] = (width - result[3]) / 2f + result[3];
            return result;
        } else {
            //如果只有白盘的话，需要画3条竖线（虚实虚）
            float[] result = new float[3];
            //实线1
            result[1] = (timeMins.get(1) - timeMins.get(0)) * xUnit;
            //虚线1
            result[0] =  result[1] / 2f;
            //虚线2
            result[2] =  (width - result[1]) / 2f + result[1];
            return result;
        }
    }

    /**
     * 判断网格是否不规则的网格：即早中晚开盘时间是否一致，不一致的话网格需要重新画
     * @param duration
     * @return
     */
    public static boolean isIrregular(String duration) {
        ArrayList<Integer> timeMins = getTimesMin(duration);
        switch (timeMins.size()) {
            case 2:
                return false;
            case 4:
                return timeMins.get(3) - timeMins.get(2) != timeMins.get(1) - timeMins.get(0);
            case 6:
                return (timeMins.get(3) - timeMins.get(2) != timeMins.get(1) - timeMins.get(0)) || (timeMins.get(3) - timeMins.get(2) != timeMins.get(5) - timeMins.get(4));
        }
        return false;
    }

    /**
     * 获取开盘收盘时间对应的分钟数
     * @param duration
     * @return
     */
    public static ArrayList<Integer> getTimesMin(String duration) {
        ArrayList<String> times = getTimes(duration);
        ArrayList<Integer> mins = new ArrayList<>();
        for(String s : times) {
            int min = getMin(s);
            mins.add(min);
        }
        return mins;
    }

    /**
     * 获取分时线下的时间，需要展示的点数目
     *
     * @param duration 如9:00-11:00|13:00-15:00
     * @return 上面的是4小时，返回240
     */
    public static int getShowCount(String duration) {
        ArrayList<Integer> mins = getTimesMin(duration);
        switch (mins.size()) {
            case 2:
                return mins.get(1) - mins.get(0);
            case 4:
                return (mins.get(3) - mins.get(2)) + (mins.get(1) - mins.get(0));
            case 6:
                return (mins.get(5) - mins.get(4)) + (mins.get(3) - mins.get(2)) + (mins.get(1) - mins.get(0));
        }
        return 242;
    }

    /**
     * 补全分时图中缺失的点，如某个时间段没有数据，则该时间段都取前一条数据的值
     *
     * @param response
     * @return
     */
    public static ArrayList<CMinute> getAllFenshiData(FenshiDataResponse response) {
        ArrayList<CMinute> fsDatasTemp = new ArrayList<CMinute>();
        if(response.getData() == null || response.getData().size() == 0)
            return  fsDatasTemp;
        int nightStartMin = 0, nightStopMin = 0;
        int morningStartMin = 0, morningStopMin = 0, afternoonStartMin = 0, afternoonStopMin = 0;
        if (response.getParam() == null) return fsDatasTemp;
        //停盘的 duration 9:30-11:30|13:00-15:00"
        //我这里全部转换成分钟
        String duration = response.getParam().getDuration();
        if (duration.contains("|")) {
            String ds[] = duration.split("\\|");
            if (ds[0].contains("-")) {
                //9:30-11:30
                String mins[] = ds[0].split("-");
                morningStartMin = getMin(mins[0]);
                morningStopMin = getMin(mins[1]);

            }
            if (ds[1].contains("-")) {
                //13:00-15:00
                String mins[] = ds[1].split("-");
                afternoonStartMin = getMin(mins[0]);
                afternoonStopMin = getMin(mins[1]);

            }
            if (ds.length == 3) {
                String mins[] = ds[2].split("-");
                nightStartMin = getMin(mins[0]);
                nightStopMin = getMin(mins[1]);
            }
        } else {
            if (duration.contains("-")) {
                //9:30-11:30
                String mins[] = duration.split("-");
                morningStartMin = getMin(mins[0]);
                afternoonStopMin = getMin(mins[1]);

            }
        }
        int drawCount = 0;
        //是否有夜盘
        boolean hasNight = nightStartMin > 0;
        if (!hasNight)
            drawCount = afternoonStopMin - morningStartMin - (afternoonStartMin - morningStopMin);
        else
            drawCount = nightStopMin - morningStartMin - (nightStartMin - afternoonStopMin) - (afternoonStartMin - morningStopMin);
        fsDatasTemp.addAll(response.getData());
        int firstMin = getMin(fsDatasTemp.get(0).getTime());
        while (firstMin < morningStartMin && fsDatasTemp != null && fsDatasTemp.size() > 1) {
            fsDatasTemp.remove(0);
            response.getData().remove(0);
            firstMin = getMin(fsDatasTemp.get(0).getTime());
        }

        //服务器返回数据的第一个数据时间
        long firstLongTime = fsDatasTemp.get(0).getTime();
        for (int i = 0; i < response.getData().size(); i++) {
            if (i == 0) {
                //1,先补全第一点到开盘时间中间的点
                int div = firstMin - morningStartMin - 1;
                if (div >= 1) {
                    for (int j = 0; j <= div; j++) {
                        int min = getMin(firstLongTime - (j + 1) * 60);
                        if(hasNight) {
                            if((min > morningStopMin && min < afternoonStartMin) || (min > afternoonStopMin && min < nightStartMin))
                                continue;
                        }else {
                            if(min > morningStopMin && min < afternoonStartMin && afternoonStartMin != 0)
                                continue;
                        }
                        CMinute temp = new CMinute();
                        temp.setTime(firstLongTime - (j + 1) * 60);
                        temp.setPrice(response.getParam().getLast());
                        fsDatasTemp.add(0, temp);
                    }
                }
            } else {
                CMinute currentObject = response.getData().get(i);
                CMinute beforeObject = response.getData().get(i - 1);
                int currentMin = getMin(currentObject.getTime());
                int beforeMin = getMin(beforeObject.getTime());
                //当前时间 比上一次的时间要大2分钟   正常数据的时候是1分钟
                int div = currentMin - beforeMin - 1;
                //没有休盘时间或者是 在休盘时间外
                if (morningStopMin == 0 || currentMin <= morningStopMin || currentMin >= afternoonStartMin) {
                    for (int j = 0; j < div; j++) {
                        CMinute temp = (CMinute) beforeObject.clone();
                        temp.setTime(beforeObject.getTime() + ((j + 1) * 60));
                        temp.setCount(0);
                        temp.setMoney(0);
                        int tempMin = beforeMin + (j + 1);
                        if (morningStopMin > 0) {
                            //有停盘点
                            //没有夜盘
                            if (!hasNight) {
                                if (tempMin > morningStopMin && tempMin < afternoonStartMin) {
                                    //正好在停盘时间内
                                } else {
                                    fsDatasTemp.add(i + fsDatasTemp.size() - response.getData().size() + 1, temp);
                                }
                            } else {
                                //有夜盘
                                if ((tempMin > morningStopMin && tempMin < afternoonStartMin) || (tempMin > afternoonStopMin && tempMin < nightStartMin)) {
                                    //正好在停盘时间内
                                } else {
                                    fsDatasTemp.add(i + fsDatasTemp.size() - response.getData().size() + 1, temp);
                                }
                            }

                        } else {
                            //没有中间停盘时间
                            fsDatasTemp.add(i + fsDatasTemp.size() - response.getData().size() + 1, temp);
                        }
                    }
                }
            }
        }

        //until 画线最后到达的位置
        int until = getMin(response.getParam().getUntil());
        if (until <= (hasNight ? nightStopMin : afternoonStopMin) && fsDatasTemp.size() < drawCount) {
            CMinute lasteObject = fsDatasTemp.get(fsDatasTemp.size() - 1);
            int lasteMin = getMin(lasteObject.getTime());
            int div = until - lasteMin - 1;
            if (div >= 1) {
                for (int j = 0; j <= div; j++) {
                    CMinute temp = new CMinute();
                    temp.setTime((lasteObject.getTime() + (j + 1) * 60));
                    temp.setPrice(lasteObject.getPrice());
                    temp.setAverage(lasteObject.getAverage());
                    if (morningStopMin > 0) {
                        //有停盘点
                        int tempMin = lasteMin + (j + 1);
                        if (hasNight) {
                            if ((tempMin > morningStopMin && tempMin < afternoonStartMin) || (tempMin > afternoonStopMin && tempMin < nightStartMin)) {
                                //正好在停盘时间内  不添加
                            } else {
                                fsDatasTemp.add(fsDatasTemp.size(), temp);
                            }
                        } else {
                            if (tempMin > morningStopMin && tempMin < afternoonStartMin) {
                                //正好在停盘时间内  不添加
                            } else {
                                fsDatasTemp.add(fsDatasTemp.size(), temp);
                            }
                        }

                    }

                }
            }
        }
        //20160801 解析后的时间有时错乱了，需要对时间进行排序
        //TODO 此处需要优化，应该从循环中发现哪里添加错误，而不是简单的进行排序，排序反而会消耗内存、时间，需要修改
        Collections.sort(fsDatasTemp, new Comparator<CMinute>() {
            @Override
            public int compare(CMinute cMinute, CMinute t1) {
                return new Long(cMinute.getTime()).compareTo(new Long(t1.getTime()));
            }
        });
        return fsDatasTemp;
    }

    //是否白盘和夜盘一起显示
    public static boolean hasNight(String duration) {
        if (duration.contains("|")) {
            return duration.split("\\|").length == 3;
        }
        return false;
    }

    /**
     * 解析开收盘时间点
     * @param duration 9:30-11:30|13:00-15:00(可能会有1、2、3段时间)
     * @return {"9:30", "11:30", "13:00", "15:00"}
     */
    public static ArrayList<String> getTimes(String duration) {
        ArrayList<String> result = new ArrayList<>();
        if(TextUtils.isEmpty(duration)) return result;
        if(duration.contains("|")) {
            String[] t1 = duration.split("\\|");
            for(String s : t1) {
                result.add(s.split("-")[0]);
                result.add(s.split("-")[1]);
            }
        } else {
                result.add(duration.split("-")[0]);
                result.add(duration.split("-")[1]);
        }
        return result;
    }

    /**
     * 计算出Y轴显示价格的最大最小值
     * @param max
     * @param min
     * @param yd
     * @return
     */
    public static double[] getMaxAndMinByYd(double max, double min, double yd) {
        double limit = Math.abs(max - yd) > Math.abs(yd - min) ? Math.abs(max - yd) : Math.abs(yd - min);
        double[] result = new double[2];
        result[0] = yd + limit;
        result[1] = yd - limit;
        return result;
    }

    /**
     * 获取该画笔写出文字的高度
     * @param paint
     * @return
     */
    public static float getTextHeight(Paint paint) {
        Paint.FontMetrics fm = paint.getFontMetrics();
        return fm.descent - fm.ascent;
    }

    /**
     * 获取该画笔写出文字的宽度
     * @param paint
     * @return
     */
    public static float getTextWidth(Paint paint, String str) {
        return paint.measureText(str);
    }

    /**
     * 获取数组中最大最小值
     * @param list
     * @return
     */
    public static float[] getMaxAndMin(float[] list) {
        if(list == null || list.length == 0) return new float[]{0, 0};
        float max = 0,min =0;
        float[] temp = list.clone();
        Arrays.sort(temp);
        max = temp[temp.length - 1];
        min = temp[0];
        return new float[] {max, min};
    }
    /**
     * 获取数组中最大最小值
     * @param list1
     * @return
     */
    public static float[] getMaxAndMin(float[] list1, float[] list2) {
        float max = 0, min = 0;
        float[] f1 = getMaxAndMin(list1);
        float[] f2 = getMaxAndMin(list2);
        max = f1[0] > f2[0] ? f1[0] : f2[0];
        min = f1[1] < f2[1] ? f1[1] : f2[1];
        return new float[] {max, min};
    }
    /**
     * 获取数组中最大最小值
     * @param list1
     * @return
     */
    public static float[] getMaxAndMin(float[] list1, float[] list2, float[] list3) {
        float max = 0, min = 0;
        float[] f1 = getMaxAndMin(list1);
        float[] f2 = getMaxAndMin(list2);
        float[] f3 = getMaxAndMin(list3);
        max = f1[0] > f2[0] ? f1[0] : f2[0];
        max = max > f3[0] ? max : f3[0];
        min = f1[1] < f2[1] ? f1[1] : f2[1];
        min = min < f3[1] ? min : f3[1];
        return new float[] {max, min};
    }

    /**
     * 获取数组中最大最小值
     * @param list1
     * @return
     */
    public static float[] getMaxAndMin(float[] list1, float[] list2, float[] list3, float[] list4) {
        float max = 0, min = 0;
        float[] f1 = getMaxAndMin(list1);
        float[] f2 = getMaxAndMin(list2);
        float[] f3 = getMaxAndMin(list3);
        float[] f4 = getMaxAndMin(list4);
        float[] f123 = getMaxAndMin(f1, f2, f3);
        max = f123[0] > f4[0] ? f123[0] : f4[0];
        min = f123[1] < f4[1] ? f123[1] : f4[1];
        return new float[] {max, min};
    }
}
