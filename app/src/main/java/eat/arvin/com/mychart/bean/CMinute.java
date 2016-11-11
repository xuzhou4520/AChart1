package eat.arvin.com.mychart.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 分时所需要的 数据字段
 */
public class CMinute implements Cloneable, Serializable, Parcelable {

	public long time;
	//最新价
	public double price;
	//交易量
	public long count;
	//均价
	public double average ;
	//涨跌幅
	public double rate ;
	public double money ;
	public long getTime() {
		return time;
	}

	public String getTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			return sdf.format(new Date(time * 1000));
		} catch (Exception e) {
			return "--:--";
		}
	}
	public void setTime(long time) {
		this.time = time;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Object clone() {
		CMinute o = null;
		try {
			o = (CMinute) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}


	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(this.time);
		dest.writeDouble(this.price);
		dest.writeLong(this.count);
		dest.writeDouble(this.average);
		dest.writeDouble(this.rate);
		dest.writeDouble(this.money);
	}

	public CMinute() {
	}

	protected CMinute(Parcel in) {
		this.time = in.readLong();
		this.price = in.readDouble();
		this.count = in.readLong();
		this.average = in.readDouble();
		this.rate = in.readDouble();
		this.money = in.readDouble();
	}

	public static final Creator<CMinute> CREATOR = new Creator<CMinute>() {
		@Override
		public CMinute createFromParcel(Parcel source) {
			return new CMinute(source);
		}

		@Override
		public CMinute[] newArray(int size) {
			return new CMinute[size];
		}
	};
}
