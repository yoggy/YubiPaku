package net.sabamiso.android.yubipaku;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class YubiPakuActivity extends Activity {

	YubiPakuView view;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
		
		super.onCreate(savedInstanceState);
		
		view = new YubiPakuView(this);
		setContentView(view);
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
