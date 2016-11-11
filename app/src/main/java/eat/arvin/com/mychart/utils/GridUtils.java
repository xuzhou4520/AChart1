package eat.arvin.com.mychart.utils;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.Log;

/**
 * Created by Arvin on 2016/11/4.
 * 画分时线K线网格
 */
public class GridUtils {
    /**
     * 画网格
     */
    public static void drawGrid(Canvas canvas, float width, float height) {
        if (canvas == null) {
            Log.w("DrawUtils", "canvas为空");
            return;
        }
        canvas.drawColor(Color.WHITE);
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setPathEffect(new DashPathEffect(
                new float[]{8, 8, 8, 8}, 1));
        p.setColor(Color.GRAY);
        p.setAntiAlias(true);
        //横虚线
        canvas.drawLine(0, height * 3 / 4, width, height * 3 / 4, p);
        canvas.drawLine(0, height * 1 / 4, width, height * 1 / 4, p);
        //竖虚线
        canvas.drawLine(width * 3 / 4, 0, width * 3 / 4, height, p);
        canvas.drawLine(width * 1 / 4, 0, width * 1 / 4, height, p);
        p.reset();
        p.setColor(Color.GRAY);
        //中间实线
        canvas.drawLine(0, height * 2 / 4, width, height * 2 / 4, p);
        canvas.drawLine(width * 2 / 4, 0, width * 2 / 4, height, p);
        //四周线
        //下
        canvas.drawLine(0, height - 1, width, height - 1, p);
        //上
        canvas.drawLine(0, 0, width, 0, p);
        //右
        canvas.drawLine(width - 1, 0, width - 1, height, p);
        //左
        canvas.drawLine(0, 0, 0, height, p);
        p.reset();
    }

    /**
     * 夜盘分时线特有的七段线
     *
     * @param canvas
     * @param width
     * @param height
     */
    public static void drawNightGrid(Canvas canvas, float width, float height) {
        if (canvas == null) {
            Log.w("DrawUtils", "canvas为空");
            return;
        }
        canvas.drawColor(Color.WHITE);
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setPathEffect(new DashPathEffect(new float[]{10, 1}, 0));
        p.setColor(Color.GRAY);
        p.setAntiAlias(true);
        //横虚线
        canvas.drawLine(0, height * 3 / 4, width, height * 3 / 4, p);
        canvas.drawLine(0, height * 1 / 4, width, height * 1 / 4, p);
        //竖虚线
        canvas.drawLine(width * 1 / 6, 0, width * 1 / 6, height, p);
        canvas.drawLine(width * 3 / 6, 0, width * 3 / 6, height, p);
        canvas.drawLine(width * 5 / 6, 0, width * 5 / 6, height, p);
        p.reset();
        p.setColor(Color.GRAY);
        //中间实线
        canvas.drawLine(0, height * 2 / 4, width, height * 2 / 4, p);
        canvas.drawLine(width * 2 / 6, 0, width * 2 / 6, height, p);
        canvas.drawLine(width * 4 / 6, 0, width * 4 / 6, height, p);
        //四周线
        canvas.drawLine(0, height - 1, width, height - 1, p);
        canvas.drawLine(0, 0, width, 0, p);
        canvas.drawLine(width - 1, 0, width - 1, height, p);
        canvas.drawLine(0, 0, 0, height, p);
        p.reset();
    }

    /**
     * 画指标网格
     */
    public static void drawIndexGrid(Canvas canvas, float y, float width, float height) {
        if (canvas == null) {
            Log.w("DrawUtils", "canvas为空");
            return;
        }
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setPathEffect(new DashPathEffect(new float[]{20, 20, 20, 20}, 0));
        p.setColor(Color.GRAY);
        p.setAntiAlias(true);
        //横虚线
        canvas.drawLine(0, y + height / 2, width, y + height / 2, p);
        //竖虚线
        canvas.drawLine(width * 3 / 4, y, width * 3 / 4, y + height, p);
        canvas.drawLine(width * 1 / 4, y, width * 1 / 4, y + height, p);
        p.reset();
        p.setColor(Color.GRAY);
        //中间实线
        canvas.drawLine(width * 2 / 4, y, width * 2 / 4, y + height, p);
        //四周线
        canvas.drawLine(0, y + height - 1, width, y + height - 1, p);
        canvas.drawLine(0, y, width, y, p);
        canvas.drawLine(width - 1, y, width - 1, y + height, p);
        canvas.drawLine(0, y, 0, y + height, p);
        p.reset();
    }


    /**
     * 画指标的网格
     *
     * @param canvas
     * @param y      左上角y
     * @param width  宽
     * @param height 高
     */
    public static void drawNightIndexGrid(Canvas canvas, float y, float width, float height) {
        if (canvas == null) {
            Log.w("DrawUtils", "canvas为空");
            return;
        }
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setPathEffect(new DashPathEffect(new float[]{10, 1}, 0));
        p.setColor(Color.GRAY);
        p.setAntiAlias(true);
        //横虚线
        canvas.drawLine(0, y + height / 2, width, y + height / 2, p);
        //竖虚线
        canvas.drawLine(width * 1 / 6, y, width * 1 / 6, y + height, p);
        canvas.drawLine(width * 3 / 6, y, width * 3 / 6, y + height, p);
        canvas.drawLine(width * 5 / 6, y, width * 5 / 6, y + height, p);
        p.reset();
        p.setColor(Color.GRAY);
        //中间实线
        canvas.drawLine(0, y + height * 2 / 4, width, y + height * 2 / 4, p);
        canvas.drawLine(width * 2 / 6, y, width * 2 / 6, y + height, p);
        canvas.drawLine(width * 4 / 6, y, width * 4 / 6, y + height, p);
        //四周线
        canvas.drawLine(0, y + height - 1, width, y + height - 1, p);
        canvas.drawLine(0, y, width, y, p);
        canvas.drawLine(width - 1, y, width - 1, y + height, p);
        canvas.drawLine(0, y, 0, y + height, p);
        p.reset();
    }

    /**
     * 由于早中晚开盘时间不等，需要画一个竖线不规则的网格
     * @param canvas
     * @param width
     * @param height
     * @param duration
     */
    public static void drawIrregularGrid(Canvas canvas, float width, float height, String duration) {
        float[] lineX = LineUtil.getXByDuration(duration, width);
        if (canvas == null) {
            Log.w("DrawUtils", "canvas为空");
            return;
        }
        canvas.drawColor(Color.WHITE);
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setPathEffect(new DashPathEffect(
                new float[]{8, 8, 8, 8}, 1));
        p.setColor(Color.GRAY);
        p.setAntiAlias(true);
        //横虚线
        canvas.drawLine(0, height * 3 / 4, width, height * 3 / 4, p);
        canvas.drawLine(0, height * 1 / 4, width, height * 1 / 4, p);
        //竖虚线
        canvas.drawLine(lineX[0], 0, lineX[0], height, p);
        canvas.drawLine(lineX[2], 0, lineX[2], height, p);
        if(lineX.length == 5) {
            canvas.drawLine(lineX[4], 0, lineX[4], height, p);
        }
        p.reset();
        p.setColor(Color.GRAY);
        //中间实线(横)
        canvas.drawLine(0, height * 2 / 4, width, height * 2 / 4, p);
        //中间实线(竖)
        canvas.drawLine(lineX[1], 0, lineX[1], height, p);
        if(lineX.length == 5) {
            canvas.drawLine(lineX[3], 0, lineX[3], height, p);
        }
        //四周线
        //下
        canvas.drawLine(0, height - 1, width, height - 1, p);
        //上
        canvas.drawLine(0, 0, width, 0, p);
        //右
        canvas.drawLine(width - 1, 0, width - 1, height, p);
        //左
        canvas.drawLine(0, 0, 0, height, p);
        p.reset();
    }

    /**
     * 由于早中晚开盘时间不等，需要画一个竖线不规则的网格
     * @param canvas
     * @param y
     * @param width
     * @param height
     * @param duration
     */
    public static void drawIrregularIndexGrid(Canvas canvas, float y, float width, float height, String duration) {
        if (canvas == null) {
            Log.w("DrawUtils", "canvas为空");
            return;
        }
        float[] lineX = LineUtil.getXByDuration(duration, width);
        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);
        p.setPathEffect(new DashPathEffect(new float[]{20, 20, 20, 20}, 0));
        p.setColor(Color.GRAY);
        p.setAntiAlias(true);
        //横虚线
        canvas.drawLine(0, y + height / 2, width, y + height / 2, p);
        //竖虚线
        canvas.drawLine(lineX[0], y, lineX[0], y + height, p);
        canvas.drawLine(lineX[2], y, lineX[2], y + height, p);
        if(lineX.length == 5){
            canvas.drawLine(lineX[4], y, lineX[4], y + height, p);
        }
        p.reset();
        p.setColor(Color.GRAY);
        //中间实线
        canvas.drawLine(lineX[1], y, lineX[1], y + height, p);
        if(lineX.length == 5) {
            canvas.drawLine(lineX[3], y, lineX[3], y + height, p);
        }
        //四周线
        canvas.drawLine(0, y + height - 1, width, y + height - 1, p);
        canvas.drawLine(0, y, width, y, p);
        canvas.drawLine(width - 1, y, width - 1, y + height, p);
        canvas.drawLine(0, y, 0, y + height, p);
        p.reset();
    }

}
