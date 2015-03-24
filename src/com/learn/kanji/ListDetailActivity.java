package com.learn.kanji;

import java.util.ArrayList;
import java.util.List;

import com.learn.kanji.adapter.GifAnimationDrawable;
import com.learn.kanji.adapter.MySimpleDetailAdapter;
import com.learn.kanji.pojo.KanjiObject;

import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListDetailActivity extends Activity {

	ImageView code = null;
	ImageView book_mark_ic = null;
	TextView onyomi = null;
	TextView kunyomi = null;
	TextView meaning = null;
	private GifAnimationDrawable gif = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// this.getListView().setBackgroundColor(Color.WHITE);

		setContentView(R.layout.row_layout);

		getWindow().getDecorView().setBackgroundColor(Color.WHITE);

		KanjiObject itemClicked = (KanjiObject) getIntent()
				.getSerializableExtra("itemClicked");

		// List<KanjiObject> itemsClicked = new ArrayList<KanjiObject>();
		// itemsClicked.add(itemClicked);

		if (code == null) {

			code = (ImageView) findViewById(R.id.imageIcon);

			book_mark_ic = (ImageView) findViewById(R.id.book_mark_ic);
			book_mark_ic.setBackgroundColor(Color.WHITE);

			OnClickListener btnBook_mark_ic = new OnClickListener() {

				@Override
				public void onClick(View v) {
					ColorDrawable buttonColor = (ColorDrawable) book_mark_ic
							.getBackground();
					int colorId = buttonColor.getColor();
					if (colorId == Color.WHITE) {
						book_mark_ic.setBackgroundColor(Color.YELLOW);
					} else {
						book_mark_ic.setBackgroundColor(Color.WHITE);
					}
				}
			};

			book_mark_ic.setOnClickListener(btnBook_mark_ic);

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
		}

		// ArrayAdapter<KanjiObject> adapter = new MySimpleDetailAdapter(this,
		// itemsClicked);
		// setListAdapter(adapter);
	}
}
