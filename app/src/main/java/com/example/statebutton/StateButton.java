package com.example.statebutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

/**
 * Created by jack on 2017/10/8.
 *
 */

public class StateButton extends AppCompatButton {

    //圆角半径
    int cornerRadius = 0;
    //边框宽度
    int borderStroke = 0;
    //边框颜色
    int borderColor = 0;
    //enable为false时的颜色
    int unableColor = 0;
    //背景shape
    GradientDrawable shape;

    int colorId;
    int alpha;
    boolean unable;

    public StateButton(Context context) {
        this(context, null);
    }

    public StateButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        if(shape == null) {
            shape = new GradientDrawable();
        }

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StateButton, 0, 0);

        cornerRadius = (int) a.getDimension(R.styleable.StateButton_corner_radius, 0);
        borderStroke = (int) a.getDimension(R.styleable.StateButton_border_stroke, 0);
        borderColor = a.getColor(R.styleable.StateButton_border_color, 0);
        unableColor = a.getColor(R.styleable.StateButton_unable_color, Color.GRAY);

        ColorDrawable buttonColor = (ColorDrawable) getBackground();
        colorId = buttonColor.getColor();
        alpha = 255;

        if(unable) {
            shape.setColor(unableColor);
        }else{
            shape.setColor(colorId);
        }

        a.recycle();

        init();
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        if(pressed){
            shape.setAlpha((int) (alpha*0.6));
            init();
        }else{
            shape.setAlpha(alpha);
            init();
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        unable = !enabled;
        if(shape != null) {
            shape = new GradientDrawable();
            if(unable) {
                shape.setColor(unableColor);
            }else{
                shape.setColor(colorId);
            }
            init();
        }
    }


    public void init() {
        //设置圆角半径
        shape.setCornerRadius(cornerRadius);
        //设置边框宽度和颜色
        shape.setStroke(borderStroke, borderColor);
        //将GradientDrawable设置为背景
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            setBackground(shape);
        } else {
            setBackgroundDrawable(shape);
        }

    }

    public float getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        init();
    }

    public int getBorderStroke() {
        return borderStroke;
    }

    public void setBorderStroke(int borderStroke) {
        this.borderStroke = borderStroke;
        init();
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(@ColorInt int borderColor) {
        this.borderColor = borderColor;
        init();
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = Color.parseColor(borderColor);
        init();
    }

    public int getUnableColor() {
        return unableColor;
    }

    public void setUnableColor(@ColorInt int unableColor) {
        this.unableColor = unableColor;
        init();
    }

    public void setUnableColor(String unableColor) {
        this.unableColor = Color.parseColor(unableColor);
        init();
    }

}
