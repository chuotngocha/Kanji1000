/**
 * 
 */
package com.learn.kanji.adapter;

import java.util.List;

import com.learn.kanji.R;
import com.learn.kanji.pojo.KanjiObject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author ngocha
 *
 */
public class MySimpleDetailAdapter extends ArrayAdapter<KanjiObject> {

	private final Context context;
	private final List<KanjiObject> values;
	private GifAnimationDrawable gif = null;

	public MySimpleDetailAdapter(Context context, List<KanjiObject> values) {
		super(context, R.layout.row_layout, values);
		this.context = context;
		this.values = values;

	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolderItem viewHolder;

		if (convertView == null) {

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.row_layout, parent, false);

			viewHolder = new ViewHolderItem();

			viewHolder.code = (ImageView) convertView
					.findViewById(R.id.imageIcon);

			viewHolder.book_mark_ic = (ImageView) convertView
					.findViewById(R.id.book_mark_ic);

			viewHolder.onyomi = (TextView) convertView
					.findViewById(R.id.onyomi);

			viewHolder.kunyomi = (TextView) convertView
					.findViewById(R.id.kunyomi);

			viewHolder.meaning = (TextView) convertView
					.findViewById(R.id.meaning);

			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolderItem) convertView.getTag();
		}

		KanjiObject objectItem = values.get(position);

		if (objectItem != null) {

			try {
				gif = new GifAnimationDrawable(context.getResources()
						.openRawResource(
								R.drawable.class.getDeclaredField(
										objectItem.getCode()).getInt(
										Integer.valueOf(0))),
						context.getResources());
			} catch (Exception e) {
				e.printStackTrace();
			}

			gif.setOneShot(false);
			viewHolder.code.setImageDrawable(gif);
			gif.setVisible(true, true);

			viewHolder.onyomi.setText("Onyomi: " + objectItem.getOnyomi());
			viewHolder.onyomi.setTextColor(Color.BLACK);

			viewHolder.kunyomi.setText("Kunyomi: " + objectItem.getKunyomi());
			viewHolder.kunyomi.setTextColor(Color.BLACK);

			viewHolder.meaning.setText(objectItem.getMeaning());
			viewHolder.meaning.setTextColor(Color.BLACK);

			viewHolder.book_mark_ic.setImageResource(R.drawable.book_mark_test);

		}

		return convertView;
	}

	// our ViewHolder.
	// caches our TextView
	static class ViewHolderItem {

		ImageView code;
		TextView onyomi;
		TextView kunyomi;
		TextView meaning;
		ImageView book_mark_ic;
	}

}
