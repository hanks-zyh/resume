package com.zyh.resume;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Ö÷½çÃæ
 * @author zyh
 */
public class HomeActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		init();
	}

	private void init() {
		findViewById(R.id.ll_p1).setOnClickListener(this);;
		findViewById(R.id.ll_p2).setOnClickListener(this);;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_p1:
				startActivity(new Intent(this, Project1Activity.class));
				break;
			case R.id.ll_p2:
				startActivity(new Intent(this, Project1Activity.class));
				break;
			default:
				break;
		}
	}
}
