

package com.technorapper.gauge;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractGauge extends View {


    private List<Range> ranges = new ArrayList<>();
    private double value = 0;
    private double minValue = 0;
    private double maxValue = 100;
    private Paint needleColor;
    private Paint gaugeBackGround;
    private Paint textPaint;
    private float rectTop = 0;
    private float rectLeft = 0;
    private float rectRight = 400;
    private float rectBottom = 400;
    private float padding = 0;
    private RectF rectF;
    private boolean useRangeBGColor = false;


    public AbstractGauge(Context context) {
        super(context);
    }

    public AbstractGauge(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AbstractGauge(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public AbstractGauge(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        getScaleRatio();
    }


    protected RectF getRectF() {
        if (rectF == null)
            rectF = new RectF(rectLeft + padding, rectTop + padding, rectRight - padding, rectBottom - padding);
        return rectF;
    }

    protected Float getScaleRatio() {
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        float minSize = Math.min(measuredHeight, measuredWidth) / 1f;
        float maxSize = Math.max(measuredHeight, measuredWidth) / 1f;
        float f1 = minSize / 400f;
        float f2 = minSize / 200f;
        if (measuredWidth > measuredHeight) {
            if (f2 > f1)
                return f1;
        } else {
            return minSize / 400f;

        }
        return maxSize / 400f;
    }


    public void addRange(Range range) {
        if (range == null)
            return;
        ranges.add(range);
    }


    public List<Range> getRanges() {
        return ranges;
    }

    public void setRanges(List<Range> ranges) {
        this.ranges = ranges;
    }


    protected Paint getNeedlePaint() {
        if (needleColor == null) {
            needleColor = new Paint();
            needleColor.setColor(Color.BLACK);
            needleColor.setAntiAlias(true);
            needleColor.setStyle(Paint.Style.FILL_AND_STROKE);
            needleColor.setStrokeWidth(5f);
            // needleColor.setShadowLayer(10.f,0f,5.0f,0X50000000);
            // setLayerType(LAYER_TYPE_SOFTWARE, needleColor);
        }
        return needleColor;
    }

    protected Paint getGaugeBackGround() {
        if (gaugeBackGround == null) {
            gaugeBackGround = new Paint();
            gaugeBackGround.setColor(Color.parseColor("#1a1a1a"));
            gaugeBackGround.setAntiAlias(true);
            gaugeBackGround.setStyle(Paint.Style.STROKE);
            // gaugeBackGround.setShadowLayer(15.0f,0f,5.0f,0X50000000);
            // setLayerType(LAYER_TYPE_SOFTWARE, gaugeBackGround);
        }
        return gaugeBackGround;
    }

    protected Paint getGaugeBackGround(double value) {
        if (useRangeBGColor) {
            getGaugeBackGround().setColor(Color.parseColor("#1a1a1a"));
            getGaugeBackGround().setAlpha(20);
        }
        return getGaugeBackGround();
    }

    protected int getRangeColorForValue(double value) {
        return getRangeColorForValue(value, ranges);
    }

    protected int getRangeColorForValue(double value, List<Range> ranges) {
        int color = Color.GRAY;

        for (Range range : ranges) {
            if (range.getTo() <= value)
                color = range.getColor();


            if (range.getFrom() <= value && range.getTo() >= value)
                color = range.getColor();
        }
        return color;
    }

    protected int getCalculateValuePercentage() {
        return getCalculateValuePercentage(getValue());

    }

    protected int getCalculateValuePercentage(double value) {
        return getCalculateValuePercentage(getMinValue(), getMaxValue(), value);
    }

    protected int getCalculateValuePercentage(double min, double max, double value) {
        if (min >= value)
            return 0;
        if (max <= value)
            return 100;
        return (int) ((value - min) / (max - min) * 100);
    }

    protected Paint getTextPaint() {
        if (textPaint == null) {
            textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            textPaint.setColor(Color.BLACK);
            textPaint.setStyle(Paint.Style.FILL);
            textPaint.setTextSize(25f);
            textPaint.setTextAlign(Paint.Align.CENTER);
        }
        return textPaint;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        invalidate();
    }

    public void setNeedleColor(int color) {
        getNeedlePaint().setColor(color);
    }

    protected float getRectTop() {
        return rectTop;
    }

    protected void setRectTop(float rectTop) {
        this.rectTop = rectTop;
    }

    protected float getRectLeft() {
        return rectLeft;
    }

    protected void setRectLeft(float rectLeft) {
        this.rectLeft = rectLeft;
    }

    protected float getRectRight() {
        return rectRight;
    }

    protected void setRectRight(float rectRight) {
        this.rectRight = rectRight;
    }

    protected float getRectBottom() {
        return rectBottom;
    }

    protected void setRectBottom(float rectBottom) {
        this.rectBottom = rectBottom;
    }

    public float getPadding() {
        return padding;
    }

    public void setPadding(float padding) {
        this.padding = padding;
    }

    public boolean isUseRangeBGColor() {
        return useRangeBGColor;
    }

    public void setUseRangeBGColor(boolean useRangeBGColor) {
        this.useRangeBGColor = useRangeBGColor;
    }
}
