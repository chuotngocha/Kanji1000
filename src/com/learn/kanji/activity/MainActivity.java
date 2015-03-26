/**
 * 
 */
package com.learn.kanji.activity;

import com.learn.kanji.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author ngocha
 *
 */
public class MainActivity extends Activity implements OnClickListener {

	private Button buttonN5;
	private Button buttonN4;
	private Button buttonN3;
//	private Button buttonN2;
//	private Button buttonN1;
	private Button buttonBookmark;
	public static int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main_activity);
		buttonN5 = (Button) findViewById(R.id.kanji_n5);
		buttonN5.setOnClickListener(this);
		buttonN4 = (Button) findViewById(R.id.kanji_n4);
		buttonN4.setOnClickListener(this);
		buttonN3 = (Button) findViewById(R.id.kanji_n3);
		buttonN3.setOnClickListener(this);
//		buttonN2 = (Button) findViewById(R.id.kanji_n2);
//		buttonN2.setOnClickListener(this);
//		buttonN1 = (Button) findViewById(R.id.kanji_n1);
//		buttonN1.setOnClickListener(this);
		buttonBookmark = (Button) findViewById(R.id.kanji_bookmark);
		buttonBookmark.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.kanji_n5: {
			type = 5;
			break;
		}
		case R.id.kanji_n4: {
			type = 4;
			break;
		}
		case R.id.kanji_n3: {
			type = 3;
			break;
		}
//		case R.id.kanji_n2: {
//			type = 2;
//			break;
//		}
//		case R.id.kanji_n1: {
//			type = 1;
//			break;
//		}
		default:
			type = -1;
			break;
		}
		Intent intent = new Intent(MainActivity.this, ListKanjiActivity.class);
		startActivity(intent);

	}

}
