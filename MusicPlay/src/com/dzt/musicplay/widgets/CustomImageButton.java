package com.dzt.musicplay.widgets;

import com.dzt.musicplay.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageButton;

/**
 * 自定义ImageButton 可以在ImageButton上面设置文字
 */
public class CustomImageButton extends ImageButton {
	private static final String TAG = "CustomImageButton_dzt";
	private String mtext = "";
	private int mcolor = 0;
	private float mtextsize = 0f;
	private Paint mpatin;

	public CustomImageButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(attrs);
	}

	private void initAttrs(AttributeSet attrs) {
		TypedArray array = getContext().obtainStyledAttributes(attrs,
				R.styleable.CustomButtonAttrs);
		mtext = array.getString(R.styleable.CustomButtonAttrs_textValue);
		mcolor = array.getColor(R.styleable.CustomButtonAttrs_textColor, 230);
		mtextsize = array.getDimension(R.styleable.CustomButtonAttrs_textSize,
				25.0f);
		array.recycle(); // 回收资源
		mpatin = new Paint();
		mpatin.setTextAlign(Align.CENTER);
		Log.d(TAG, "mtextsize = " + mtextsize);
	}

	public void setText(String text) {
		this.mtext = text;
	}

	public void setColor(int color) {
		this.mcolor = color;
	}

	public void setTextSize(float textsize) {
		this.mtextsize = textsize;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		mpatin.setColor(mcolor);
		mpatin.setTextSize(mtextsize);
		canvas.drawText(mtext, canvas.getWidth() / 2,
				(canvas.getHeight() / 2)+10, mpatin);
	}
}