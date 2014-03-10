package net.sabamiso.android.yubipaku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;

public class YubiPakuView extends View {

	Paint p_fg;
	Paint p_bg;
	
	float p_w = 0.7f;
	float p_h = 0.1f;
		
	public YubiPakuView(Context context) {
		super(context);
		setFocusable(true);
		
		p_fg = new Paint();
		p_fg.setColor(Color.WHITE);
        p_fg.setAntiAlias(true);
        p_fg.setStrokeWidth(20);
        p_fg.setStyle(Paint.Style.STROKE);
        
        p_bg = new Paint();
        p_bg.setStyle(Paint.Style.FILL);
        p_bg.setColor(Color.BLACK);        
	}

	protected void clearBackground(Canvas canvas) {
		canvas.drawRect(0, 0, this.getWidth(), getHeight(), p_bg);
	}
	
	protected void drawLip(Canvas canvas) {
		float half_w = this.getWidth() / 2;
		float half_h = this.getHeight() / 2;
		float w = half_w * p_w;
		float h = half_h * p_h;
		
		RectF rect = new RectF(half_w - w, half_h - h,  half_w - w + w * 2, half_h - h + h * 2);
        canvas.drawOval(rect, p_fg);
	}
	
	protected void setMouseParams(float x, float y) {
		int half_w = this.getWidth() / 2;
		int half_h = this.getHeight() / 2;
		
		if (x < half_w) {
			p_w = (half_w - x) / (float)half_w;
		}
		else {
			p_w = (x - half_w) / (float)half_w;
		}

		if (y < half_h) {
			p_h = (half_h - y) / (float)half_h;
		}
		else {
			p_h = (y - half_h) / (float)half_h;
		}
	
		invalidate();
	}
	
	public void onSizeChanged(int w, int h, int old_w, int old_h) {
		invalidate();
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		clearBackground(canvas);		
		drawLip(canvas);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		setMouseParams(event.getX(), event.getY());
		return true;
	}
}
