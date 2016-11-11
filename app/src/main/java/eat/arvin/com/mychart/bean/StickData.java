package eat.arvin.com.mychart.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 蜡烛图  开 高 低 收 成交量 成交额
 */
public class StickData implements Parcelable {

    //时间
    private long time;
    //开盘
    private double open;
    //收盘
    private double close;
    //最高
    private double high;
    //最低
    private double low;
    //量
    private long count;
    //昨收
    private double last;
    //涨跌幅
    private double rate;
    //价格
    private double money;
    //计算均线的零时保存的值
    private double maValue;
    //5段均线
    private double sma5;
    //10段均线
    private double sma10;
    //20段均线
    private double sma20;
    //量5段均线
    private double countSma5;
    //量10段均线
    private double countSma10;
    //MACD的三个参数
    private double dif;//线
    private double dea;//线
    private double macd;//柱状
    //KDJ的三根线
    private double k;
    private double d;
    private double j;
    //计算K时需要
    private double rsv;
    //K线资金
    //超大单净值
    private double sp;
    //大单净值
    private double bg;
    //中单净值
    private double md;
    //小单净值
    private double sm;

    public StickData() {
    }

    public StickData(double maValue) {
        this.maValue = maValue;
    }

    /**
     * 显示全部时间
     *
     * @return
     */
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.format(new Date(time * 1000));
        } catch (Exception e) {
            return "--:--";
        }
    }

    public long getTimeLone() {
        return time;
    }
    /**
     * 只显示到月
     *
     * @return
     */
    public String getTimeNoDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        try {
            return sdf.format(new Date(time * 1000));
        } catch (Exception e) {
            return "--:--";
        }
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getLast() {
        return last;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getMaValue() {
        return maValue;
    }

    public void setMaValue(double maValue) {
        this.maValue = maValue;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }


    public double getSma5() {
        return sma5;
    }

    public void setSma5(double sma5) {
        this.sma5 = sma5;
    }

    public double getSma10() {
        return sma10;
    }

    public void setSma10(double sma10) {
        this.sma10 = sma10;
    }

    public double getSma20() {
        return sma20;
    }

    public void setSma20(double sma20) {
        this.sma20 = sma20;
    }

    public double getCountSma5() {
        return countSma5;
    }

    public void setCountSma5(double countSma5) {
        this.countSma5 = countSma5;
    }

    public double getCountSma10() {
        return countSma10;
    }

    public void setCountSma10(double countSma10) {
        this.countSma10 = countSma10;
    }

    public double getDif() {
        return dif;
    }

    public void setDif(double dif) {
        this.dif = dif;
    }

    public double getDea() {
        return dea;
    }

    public void setDea(double dea) {
        this.dea = dea;
    }

    public double getMacd() {
        return macd;
    }

    public void setMacd(double macd) {
        this.macd = macd;
    }

    public double getK() {
        return k;
    }

    public void setK(double k) {
        this.k = k;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getJ() {
        return j;
    }

    public void setJ(double j) {
        this.j = j;
    }

    public double getRsv() {
        return rsv;
    }

    public void setRsv(double rsv) {
        this.rsv = rsv;
    }

    /**
     * 是否上涨
     * @return
     */
    public boolean isRise() {
        return open <= close;
    }

    public double getSp() {
        return sp;
    }

    public void setSp(double sp) {
        this.sp = sp;
    }

    public double getBg() {
        return bg;
    }

    public void setBg(double bg) {
        this.bg = bg;
    }

    public double getMd() {
        return md;
    }

    public void setMd(double md) {
        this.md = md;
    }

    public double getSm() {
        return sm;
    }

    public void setSm(double sm) {
        this.sm = sm;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.time);
        dest.writeDouble(this.open);
        dest.writeDouble(this.close);
        dest.writeDouble(this.high);
        dest.writeDouble(this.low);
        dest.writeLong(this.count);
        dest.writeDouble(this.last);
        dest.writeDouble(this.rate);
        dest.writeDouble(this.money);
        dest.writeDouble(this.maValue);
        dest.writeDouble(this.sma5);
        dest.writeDouble(this.sma10);
        dest.writeDouble(this.sma20);
        dest.writeDouble(this.countSma5);
        dest.writeDouble(this.countSma10);
        dest.writeDouble(this.dif);
        dest.writeDouble(this.dea);
        dest.writeDouble(this.macd);
        dest.writeDouble(this.k);
        dest.writeDouble(this.d);
        dest.writeDouble(this.j);
        dest.writeDouble(this.rsv);
        dest.writeDouble(this.sp);
        dest.writeDouble(this.bg);
        dest.writeDouble(this.md);
        dest.writeDouble(this.sm);
    }

    protected StickData(Parcel in) {
        this.time = in.readLong();
        this.open = in.readDouble();
        this.close = in.readDouble();
        this.high = in.readDouble();
        this.low = in.readDouble();
        this.count = in.readLong();
        this.last = in.readDouble();
        this.rate = in.readDouble();
        this.money = in.readDouble();
        this.maValue = in.readDouble();
        this.sma5 = in.readDouble();
        this.sma10 = in.readDouble();
        this.sma20 = in.readDouble();
        this.countSma5 = in.readDouble();
        this.countSma10 = in.readDouble();
        this.dif = in.readDouble();
        this.dea = in.readDouble();
        this.macd = in.readDouble();
        this.k = in.readDouble();
        this.d = in.readDouble();
        this.j = in.readDouble();
        this.rsv = in.readDouble();
        this.sp = in.readDouble();
        this.bg = in.readDouble();
        this.md = in.readDouble();
        this.sm = in.readDouble();
    }

    public static final Creator<StickData> CREATOR = new Creator<StickData>() {
        @Override
        public StickData createFromParcel(Parcel source) {
            return new StickData(source);
        }

        @Override
        public StickData[] newArray(int size) {
            return new StickData[size];
        }
    };
}
