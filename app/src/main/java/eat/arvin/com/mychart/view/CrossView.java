package eat.arvin.com.mychart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import eat.arvin.com.mychart.bean.CrossBean;
import eat.arvin.com.mychart.utils.ColorUtil;
import eat.arvin.com.mychart.utils.LineUtil;


/**
 * Created by Arvin on 2016/10/26.
 */
public class CrossView extends View {
    /**
     * 十字线移动的监听
     */
    public interface OnMoveListener {
        /**
         * 十字线移动(回调到数据存放的位置，判断是否需要画线后，再调用本界面画线方法)
         *
         * @param x x轴坐标
         * @param y y轴坐标
         */
        void onCrossMove(float x, float y);

        /**
         * 十字线消失的回调
         */
        void onDismiss();
    }
    private CrossBean bean;
    //手势控制
    private GestureDetector gestureDetector;
    private OnMoveListener onMoveListener;

    public CrossView(Context context, AttributeSet attrs) {
        super(context, attrs);
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                //单击隐藏十字线
                setVisibility(GONE);
                if (onMoveListener != null)
                    onMoveListener.onDismiss();
                return super.onSingleTapUp(e);
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                //滑动时，通知到接口
                if (onMoveListener != null) {
                    onMoveListener.onCrossMove(e2.getX(), e2.getY());
                }
                return super.onScroll(e1, e2, distanceX, distanceY);
            }

        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector != null)
            gestureDetector.onTouchEvent(event);
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCrossLine(canvas);
    }
    /**
     * //根据x,y画十字线
     *
     * @param canvas
     */
    private void drawCrossLine(Canvas canvas) {
        //当该点没有数据的时候，不画
        if (bean.x < 0 || bean.y < 0) return;
        boolean isJunXian = bean.y2 >= 0;
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(ColorUtil.COLOR_CROSS_LINE);
        p.setStrokeWidth(2f);
        p.setStyle(Paint.Style.FILL);
        //横线
        canvas.drawLine(0, bean.y, getWidth(), bean.y, p);
        //竖线
        canvas.drawLine(bean.x, 0, bean.x, getHeight(), p);
        if (isJunXian) {
            //均线的时候才画出圆点
            //画十字线和均线价格线交汇的圆
            canvas.drawCircle(bean.x, bean.y, 10, p);
            p.setColor(ColorUtil.COLOR_SMA_LINE);
            canvas.drawCircle(bean.x, bean.y2, 10, p);
        }
        p.setColor(Color.BLACK);
        p.setTextSize(32f);
        //1, 写价格(竖线靠左时，价格需要写到右边)
        drawPriceTextWithRect(canvas, bean.x, bean.y, bean.price, p);
        //2, 写时间
        drawTimeTextWithRect(canvas, bean.x, bean.getTime(), p);
        //3，写指标的文字
        drawIndexTexts(canvas);
        p.reset();
    }

    private void drawIndexTexts(Canvas canvas) {
        if(bean.indexText == null || bean.indexColor == null) return;
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setTextSize(26f);
        float x = 0;
        float y = getHeight() * (ChartConstant.MAIN_SCALE + ChartConstant.TIME_SCALE) + 25;
        for(int i = 0;i < bean.indexText.length; i++) {
            p.setColor(bean.indexColor[i]);
            canvas.drawText(bean.indexText[i], x, y, p);
            x += LineUtil.getTextWidth(p, bean.indexText[i]) + 30;
        }

    }

    /**
     * 写时间，并且带框
     */
    private void drawTimeTextWithRect(Canvas canvas, float x, String time, Paint p) {
        p.setTextAlign(Paint.Align.LEFT);
        float textWidth = LineUtil.getTextWidth(p, time) + 20;
        float y = getHeight() * ChartConstant.MAIN_SCALE;
        Paint rp = new Paint();
        rp.setColor(Color.WHITE);
        rp.setStyle(Paint.Style.FILL);
        rp.setStrokeWidth(2f);
        //1,先画白底
        float startX = x - textWidth / 2;
        float endX = x + textWidth / 2;
        if(startX < 0) {
            startX = 2f;
            endX = startX + textWidth;
        }
        if(endX > getWidth()) {
            endX = getWidth() - 2;
            startX = endX - textWidth;
        }
        canvas.drawRect(startX, y + 2, endX, y + 30, rp);
        rp.setColor(Color.BLACK);
        rp.setStyle(Paint.Style.STROKE);
        //2，再画黑框
        canvas.drawRect(startX, y + 2, endX, y + 30, rp);
        //3，写文字
        canvas.drawText(time, startX + 10, y + 27.5f, p);
    }

    /**
     * 写文字，并且为文字带上背景，等于在文字后方画上一个Rect
     */
    private void drawPriceTextWithRect(Canvas canvas, float x, float y, String text, Paint p) {
        float textWidth = LineUtil.getTextWidth(p, text) + 10;
        Paint rp = new Paint();
        rp.setColor(Color.WHITE);
        rp.setStyle(Paint.Style.FILL);
        rp.setStrokeWidth(2f);
        float startY = y - 15f;
        float endY = y + 15f;
        if(startY < 0) {
            startY = 0f;
            endY = startY + 30f;
        } else if(endY > getHeight()) {
            endY = getHeight();
            startY = endY - 30f;
        }

        if (x < 100) {
            //X轴在左侧，该框画在右侧
            //1,先画白底
            canvas.drawRect(getWidth() - textWidth, startY, getWidth(), endY, rp);
            rp.setColor(Color.BLACK);
            rp.setStyle(Paint.Style.STROKE);
            //2，再画黑框
            canvas.drawRect(getWidth() - textWidth, startY, getWidth(), endY, rp);
            p.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(text, getWidth() - 5f, endY - 3, p);
        } else {
            //X轴在右侧，改框画左侧
            canvas.drawRect(0, startY, textWidth, endY, rp);
            rp.setColor(Color.BLACK);
            rp.setStyle(Paint.Style.STROKE);
            canvas.drawRect(0, startY, textWidth, endY, rp);
            p.setTextAlign(Paint.Align.LEFT);
            canvas.drawText(text, 5f, endY - 3, p);
        }
    }

    /**
     * 画分时线的十字线
     */
    public void drawLine(CrossBean bean) {
        this.bean = bean;
        postInvalidate();
    }

    /**
     * 设置移动监听
     *
     * @param onMoveListener
     */
    public void setOnMoveListener(OnMoveListener onMoveListener) {
        this.onMoveListener = onMoveListener;
    }

}
