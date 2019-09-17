/*
 * *
 *  * Created by Haneet Singh Chhabra on 12/9/19 5:34 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/9/19 5:33 PM
 *
 */


package com.technorapper.gauge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;

import java.util.List;

public class FullGauge extends AbstractGauge {

    private float sweepAngle = 360;
    private float startAngle = 270;
    private float gaugeBGWidth = 20f;
    private boolean displayValuePoint = false;


    public FullGauge(Context context) {
        super(context);
        init();
    }

    public FullGauge(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FullGauge(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public FullGauge(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        getGaugeBackGround().setStrokeWidth(gaugeBGWidth);
        getTextPaint().setTextSize(35f);
        setPadding(20f);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //Draw Base Arc to display visual range
        drawBaseArc(canvas);

        //Draw Value Arc to display Value range
        drawValueArcOnCanvas(canvas);

        //drawText
        //drawValueText(canvas);

        //draw value  point indicator
        drawValuePoint(canvas);

    }

    private void drawBaseArc(Canvas canvas) {
        drawBaseArc(canvas, getRectF(), startAngle, sweepAngle, getGaugeBackGround(getValue()));

    }

    protected void drawBaseArc(Canvas canvas, RectF rectF, float startAngle, float sweepAngle, Paint paint) {
        prepareCanvas(canvas);
        canvas.drawArc(rectF, startAngle, sweepAngle, false, paint);
        finishCanvas(canvas);
    }

    protected void drawValuePoint(Canvas canvas) {
        if (displayValuePoint) {
            prepareCanvas(canvas);
            //draw Value point indicator
            float rotateValue = calculateSweepAngle(getValue(), getMinValue(), getMaxValue());
            canvas.rotate(rotateValue, getRectRight() / 2f, getRectBottom() / 2f);
            canvas.drawCircle(400f / 2f, getPadding(), 8f, getRangePaintForValue(getValue(), getRanges()));
            canvas.drawLine(200f - 3f, 11f, 210f - 4f, 19f, getArrowPaint());
            canvas.drawLine(210f - 4f, 20f, 200f - 3f, 27f, getArrowPaint());
            finishCanvas(canvas);
        }
    }

    private Paint getArrowPaint() {
        Paint color = new Paint(Paint.ANTI_ALIAS_FLAG);
        color.setStrokeWidth(4f);
        color.setStyle(Paint.Style.STROKE);
        color.setColor(Color.WHITE);
        color.setStrokeCap(Paint.Cap.ROUND);
        return color;
    }

    protected void prepareCanvas(Canvas canvas) {
        canvas.save();
        canvas.translate((getWidth() / 2f) - ((getRectRight() / 2f) * getScaleRatio()), (getHeight() / 2f) - 200f * getScaleRatio());
        canvas.scale(getScaleRatio(), getScaleRatio());
    }

    protected void finishCanvas(Canvas canvas) {
        canvas.restore();
    }


    private void drawValueText(Canvas canvas) {
        canvas.save();
        canvas.translate((getWidth() / 2f) - ((getRectRight() / 2f) * getScaleRatio()), (getHeight() / 2f) - 220f * getScaleRatio());
        canvas.scale(getScaleRatio(), getScaleRatio());
        canvas.drawText(getValue() + "", 200f, 240f, getTextPaint());
        canvas.restore();
    }


    protected Paint getRangePaintForValue(double value, List<Range> ranges) {


        Paint color = new Paint(Paint.ANTI_ALIAS_FLAG);
        color.setStrokeWidth(gaugeBGWidth);
        color.setStyle(Paint.Style.STROKE);
        color.setShader(new LinearGradient(0, 0, 0, getHeight(), ranges.get(0).getColor(), ranges.get(1).getColor(), Shader.TileMode.CLAMP));

        //color.setColor(getGaugeBackGround().getColor());
        color.setStrokeCap(Paint.Cap.ROUND);
        // color.setColor(getRangeColorForValue(value, ranges));
        return color;
    }

    private void drawValueArcOnCanvas(Canvas canvas) {
        float sweepAngle = calculateSweepAngle(getValue(), getMinValue(), getMaxValue());
        drawValueArcOnCanvas(canvas, getRectF(), getStartAngle(), sweepAngle, getValue(), 10, 178, MultiGauge.getFirstString(), getRanges());
    }


    protected void drawValueArcOnCanvas(Canvas canvas, RectF rectF, float startAngle, float sweepAngle, double value, int x, int y, String text, List<Range> ranges) {
        prepareCanvas(canvas);
        Path mArc = new Path();
        mArc.addArc(rectF, startAngle, sweepAngle);
        //mArc.arcTo(rectF,startAngle,sweepAngle);
        //  mArc.addRect(rectF, Path.Direction.CCW);

        canvas.drawArc(rectF, startAngle, sweepAngle, false, getRangePaintForValue(value, ranges));

        Paint titlePaint = new Paint();

        titlePaint.setColor(Color.parseColor("#ffffff"));
        titlePaint.setAntiAlias(true);
        titlePaint.setTypeface(Typeface.MONOSPACE);

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTextSize(9f);

        int centerX = (int) (rectF.left + rectF.right) / 2;
        int centerY = (int) (rectF.top + rectF.bottom) / 2;
        int radius = (int) (rectF.right - rectF.left) / 2;
        int mTextWidth = Math.round(titlePaint.measureText("test".toString()));

        float cx = getWidth() / 2f;
        float cy = getHeight() / 2f;
        // canvas.drawTextOnPath("test", mArc , 0, 20, titlePaint);
        canvas.drawText(text, rectF.centerX() + x, rectF.centerY() - y, titlePaint);


        finishCanvas(canvas);
    }

    protected float calculateSweepAngle(double to, double min, double max) {
        float valuePer = getCalculateValuePercentage(min, max, to);
        return sweepAngle / 100 * valuePer;
    }


    protected float getSweepAngle() {
        return sweepAngle;
    }

    protected void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
    }

    protected float getStartAngle() {
        return startAngle;
    }

    protected void setStartAngle(float startAngle) {
        this.startAngle = startAngle;
    }

    protected float getGaugeBGWidth() {
        return gaugeBGWidth;
    }

    protected void setGaugeBGWidth(float gaugeBGWidth) {
        this.gaugeBGWidth = gaugeBGWidth;
    }

    public boolean isDisplayValuePoint() {
        return displayValuePoint;
    }

    public void setDisplayValuePoint(boolean displayValuePoint) {
        this.displayValuePoint = displayValuePoint;
    }
}
