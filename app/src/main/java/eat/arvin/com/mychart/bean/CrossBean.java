package eat.arvin.com.mychart.bean;


import eat.arvin.com.mychart.utils.DateUtil;

/**
 * Created by Administrator on 2016/8/10.
 */
public class CrossBean {
    //价格x轴
    public float x;
    //价格y轴
    public float y;
    //均线y轴
    public float y2;
    //价格
    public String price;
    //时间
    public long time;
    //年月日时间（有则先取）
    public String timeYMD;
    //十字线显示时，指标左上的文字数组
    public String[] indexText;
    //对应indexText的颜色
    public int[] indexColor;

    public CrossBean(float x, float y) {
        this.x = x;
        this.y = y;
    }


    public String getTime() {
        if(timeYMD == null)
            return DateUtil.getShortDateJustHour(time);
        return timeYMD;
    }
}
