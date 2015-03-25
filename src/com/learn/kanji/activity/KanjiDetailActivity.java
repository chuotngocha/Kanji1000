package com.learn.kanji.activity;

import com.learn.kanji.R;
import com.learn.kanji.adapter.GifAnimationDrawable;
import com.learn.kanji.helper.DBHelper;
import com.learn.kanji.pojo.KanjiObject;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class KanjiDetailActivity extends Activity implements OnClickListener {

	private ImageView code = null;
	private ImageView book_mark_ic = null;
	private TextView onyomi = null;
	private TextView kunyomi = null;
	private TextView meaning = null;
	private GifAnimationDrawable gif = null;
	private int id;
	private DBHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		setContentView(R.layout.row_layout);

		dbHelper = new DBHelper(this);

		getWindow().getDecorView().setBackgroundColor(Color.WHITE);

		KanjiObject itemClicked = (KanjiObject) getIntent()
				.getSerializableExtra("itemClicked");

		if (code == null) {

			code = (ImageView) findViewById(R.id.imageIcon);

			book_mark_ic = (ImageView) findViewById(R.id.book_mark_ic);
			book_mark_ic.setBackgroundColor(Color.WHITE);
			book_mark_ic.setOnClickListener(this);

			onyomi = (TextView) findViewById(R.id.onyomi);

			kunyomi = (TextView) findViewById(R.id.kunyomi);

			meaning = (TextView) findViewById(R.id.meaning_detail);
		}
		if (itemClicked != null) {

			try {
				gif = new GifAnimationDrawable(getResources().openRawResource(
						R.drawable.class
								.getDeclaredField(itemClicked.getCode())
								.getInt(Integer.valueOf(0))), getResources());
			} catch (Exception e) {
				e.printStackTrace();
			}

			gif.setOneShot(false);
			code.setImageDrawable(gif);
			gif.setVisible(true, true);

			onyomi.setText("Onyomi: " + itemClicked.getOnyomi());
			onyomi.setTextColor(Color.BLACK);

			kunyomi.setText("Kunyomi: " + itemClicked.getKunyomi());
			kunyomi.setTextColor(Color.BLACK);

			meaning.setText(itemClicked.getMeaning());
			meaning.setTextColor(Color.BLACK);

			book_mark_ic.setImageResource(R.drawable.book_mark_test);
			this.id = itemClicked.getId();
			if (itemClicked.getIsbookmark() == 1) {
				book_mark_ic.setBackgroundColor(Color.YELLOW);
			}

		}

	}

	@Override
	public void onClick(View v) {

		ColorDrawable buttonColor = (ColorDrawable) book_mark_ic
				.getBackground();
		int colorId = buttonColor.getColor();
		if (colorId == Color.WHITE) {
			book_mark_ic.setBackgroundColor(Color.YELLOW);
			dbHelper.bookMark(this.id, 1);
		} else {
			book_mark_ic.setBackgroundColor(Color.WHITE);
			dbHelper.bookMark(this.id, 0);
		}
	}

	@Override
	public void onBackPressed() {
		this.finish();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			this.finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case android.R.id.home:
			this.finish();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
