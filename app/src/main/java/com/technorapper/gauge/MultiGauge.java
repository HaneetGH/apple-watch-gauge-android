/*
 * *
 *  * Created by Haneet Singh Chhabra on 12/9/19 5:34 PM
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 12/9/19 2:47 PM
 *
 */


package com.technorapper.gauge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MultiGauge extends FullGauge {

    private static String firstString;
    private String secondString;
    private String threeString;
    private String fourString;
    private float distance = 25f;
    private float gaugeBGWidth = 20f;
    private double secondValue = 0;
    private double thirdValue = 0;
    private double forthValue = 0;
    private double secondMinValue = 0;
    private double thirdMinValue = 0;
    private double secondMaxValue = 100;
    private double thirdMaxValue = 100;

    public static String getFirstString() {
        return firstString;
    }

    public void setFirstString(String firstString) {
        MultiGauge.firstString = firstString;
    }

    public String getSecondString() {
        return secondString;
    }

    public void setSecondString(String secondString) {
        this.secondString = secondString;
    }

    public String getThreeString() {
        return threeString;
    }

    public void setThreeString(String threeString) {
        this.threeString = threeString;
    }

    public String getFourString() {
        return fourString;
    }

    public void setFourString(String fourString) {
        this.fourString = fourString;
    }


    public double getForthValue() {
        return forthValue;
    }

    public void setForthValue(double forthValue) {
        this.forthValue = forthValue;
    }


    public double getForthMaxValue() {
        return forthMaxValue;
    }

    public void setForthMaxValue(double forthMaxValue) {
        this.forthMaxValue = forthMaxValue;
    }

    public double getForthMinValue() {
        return forthMinValue;
    }

    public void setForthMinValue(double forthMinValue) {
        this.forthMinValue = forthMinValue;
    }

    private double forthMaxValue = 100;
    private double forthMinValue = 0;

    private List<Range> secondRanges = new ArrayList<>();
    private List<Range> thirdRanges = new ArrayList<>();

    public List<Range> getFourthRanges() {
        return fourthRanges;
    }

    public void setFourthRanges(List<Range> fourthRanges) {
        this.fourthRanges = fourthRanges;
    }

    private List<Range> fourthRanges = new ArrayList<>();


    private RectF getSecondRect() {
        return new RectF(getRectLeft() + getPadding() + distance, getRectTop() + getPadding() + distance, getRectRight() - getPadding() - distance, getRectBottom() - getPadding() - distance);
    }

    private RectF getThirdRect() {
        return new RectF(getRectLeft() + getPadding() + distance * 2, getRectTop() + getPadding() + distance * 2, getRectRight() - getPadding() - distance * 2, getRectBottom() - getPadding() - distance * 2);
    }

    private RectF getForthRect() {
        return new RectF(getRectLeft() + getPadding() + distance * 3, getRectTop() + getPadding() + distance * 3, getRectRight() - getPadding() - distance * 3, getRectBottom() - getPadding() - distance * 3);
    }

    public MultiGauge(Context context) {
        super(context);
        init();
    }

    public MultiGauge(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MultiGauge(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MultiGauge(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init() {

        getGaugeBackGround().setStrokeWidth(gaugeBGWidth);
        getGaugeBackGround().setColor(Color.parseColor("#ff1a1a1a"));
        getTextPaint().setTextSize(35f);
        setPadding(20f);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Draw Base Arc's
        if (getRanges() != null) {
            drawBaseArc(canvas, getSecondRect(), getStartAngle(), getSweepAngle(), getGaugeBackGround(getSecondValue()));
            drawBaseArc(canvas, getThirdRect(), getStartAngle(), getSweepAngle(), getGaugeBackGround(getThirdValue()));
            drawBaseArc(canvas, getForthRect(), getStartAngle(), getSweepAngle(), getGaugeBackGround(getForthValue()));

            //Draw Value Arc's
            drawValueArcOnCanvas(canvas, getSecondRect(), getStartAngle(),
                    calculateSweepAngle(getSecondValue(), getSecondMinValue(), getSecondMaxValue()),
                    getSecondValue(), 10, 153, getSecondString(), getSecondRanges());

            drawValueArcOnCanvas(canvas, getThirdRect(), getStartAngle(),
                    calculateSweepAngle(getThirdValue(), getThirdMinValue(), getThirdMaxValue()),
                    getThirdValue(), 10, 128, getThreeString(), getThirdRanges());

            drawValueArcOnCanvas(canvas, getForthRect(), getStartAngle(),
                    calculateSweepAngle(getForthValue(), getForthMinValue(), getForthMaxValue()),
                    getForthValue(), 10, 103, getFourString(), getFourthRanges());
        }


    }


    private Paint getRangePaint(double value, List<Range> ranges) {
        Paint color = new Paint(Paint.ANTI_ALIAS_FLAG);
        color.setStrokeWidth(gaugeBGWidth);
        color.setStyle(Paint.Style.STROKE);
        color.setColor(getGaugeBackGround().getColor());
        color.setStrokeCap(Paint.Cap.ROUND);

        for (Range range : ranges) {
            if (range.getTo() <= value)
                color.setColor(range.getColor());

            if (range.getFrom() <= value && range.getTo() >= value)
                color.setColor(range.getColor());
        }
        return color;
    }


    public double getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(double secondValue) {
        this.secondValue = secondValue;
        invalidate();
    }

    public double getThirdValue() {
        return thirdValue;
    }

    public void setThirdValue(double thirdValue) {
        this.thirdValue = thirdValue;
        invalidate();
    }

    public double getSecondMinValue() {
        return secondMinValue;
    }

    public void setSecondMinValue(double secondMinValue) {
        this.secondMinValue = secondMinValue;
    }

    public double getThirdMinValue() {
        return thirdMinValue;
    }

    public void setThirdMinValue(double thirdMinValue) {
        this.thirdMinValue = thirdMinValue;
    }

    public double getSecondMaxValue() {
        return secondMaxValue;
    }

    public void setSecondMaxValue(double secondMaxValue) {
        this.secondMaxValue = secondMaxValue;
    }

    public double getThirdMaxValue() {
        return thirdMaxValue;
    }

    public void setThirdMaxValue(double thirdMaxValue) {
        this.thirdMaxValue = thirdMaxValue;
    }

    public void addSecondRange(Range range) {
        this.secondRanges.add(range);
    }

    public void addThirdRange(Range range) {
        this.thirdRanges.add(range);
    }

    public void addForthRange(Range range) {
        this.fourthRanges.add(range);
    }

    public List<Range> getSecondRanges() {
        return secondRanges;
    }

    public void setSeconRanges(List<Range> secondRanges) {
        this.secondRanges = secondRanges;
    }

    public List<Range> getThirdRanges() {
        return thirdRanges;
    }

    public void setThirdRanges(List<Range> thirdRanges) {
        this.thirdRanges = thirdRanges;
    }


    @Override
    public boolean performClick() {
        return super.performClick();
    }


    private void isPointInCircle(View view, int clickX, int clickY, RectF rectF) {
        double radius = Math.sqrt(callsqure((clickX - getHeight())) + +callsqure(((clickY - getWidth()))));

        if (radius > 169 && radius < 181) {


        } else if (radius == 155) {


        }

    }

    public int callsqure(int num) {
        return num * num;
    }


}
