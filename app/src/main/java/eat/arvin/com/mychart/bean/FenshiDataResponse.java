package eat.arvin.com.mychart.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public class FenshiDataResponse implements Parcelable {
    private int success;
    private String error_code;
    private String msg;
    List<CMinute> data;
    private FenshiParam param;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<CMinute> getData() {
        return data;
    }

    public void setData(List<CMinute> data) {
        this.data = data;
    }

    public FenshiParam getParam() {
        return param;
    }

    public void setParam(FenshiParam param) {
        this.param = param;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.success);
        dest.writeString(this.error_code);
        dest.writeString(this.msg);
        dest.writeTypedList(this.data);
        dest.writeParcelable(this.param, flags);
    }

    public FenshiDataResponse() {
    }

    protected FenshiDataResponse(Parcel in) {
        this.success = in.readInt();
        this.error_code = in.readString();
        this.msg = in.readString();
        this.data = in.createTypedArrayList(CMinute.CREATOR);
        this.param = in.readParcelable(FenshiParam.class.getClassLoader());
    }

    public static final Creator<FenshiDataResponse> CREATOR = new Creator<FenshiDataResponse>() {
        @Override
        public FenshiDataResponse createFromParcel(Parcel source) {
            return new FenshiDataResponse(source);
        }

        @Override
        public FenshiDataResponse[] newArray(int size) {
            return new FenshiDataResponse[size];
        }
    };

    /**
     * 当前数据是否已结束
     * 判断条件为params里面的duration最后的时间和parama里面的until转为HH:mm:SS一致
     * @return
     */
    public boolean isEnd() {
        if(param != null && param.getDuration() != null) {
            if(param.getDuration().contains("|")) {
                String[] t1 = param.getDuration().split("\\|");
                String s = t1[t1.length - 1];
                if(s.contains("-")) {
                    String[] t2 = s.split("-");
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    String time = format.format(new Date(param.getUntil() * 1000));
                    return time.equals(t2[t2.length - 1]);
                }

            }
        }
        return false;
    }
}
