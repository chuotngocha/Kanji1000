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
public class MySimpleArrayAdapter extends ArrayAdapter<KanjiObject> {

	private final Context context;
	private final List<KanjiObject> values;
	private GifAnimationDrawable gif = null;

	public MySimpleArrayAdapter(Context context, List<KanjiObject> values) {
		super(context, R.layout.row_layout, values);
		this.context = context;
		this.values = values;

	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolderItem viewHolder;

		// View rowView = inflater.inflate(R.layout.row_layout, parent, false);
		if (convertView == null) {

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.row_layout, parent, false);

			viewHolder = new ViewHolderItem();

			viewHolder.code = (ImageView) convertView
					.findViewById(R.id.imageIcon);

			viewHolder.onyomi = (TextView) convertView
					.findViewById(R.id.firstLine);

			viewHolder.onyomi = (TextView) convertView
					.findViewById(R.id.secondLine);

			viewHolder.onyomi = (TextView) convertView
					.findViewById(R.id.thirdLine);

			viewHolder.onyomi = (TextView) convertView
					.findViewById(R.id.fourLine);

			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolderItem) convertView.getTag();
		}

		try {
			gif = new GifAnimationDrawable(context.getResources()
					.openRawResource(R.drawable.ashi), context.getResources());
		} catch (Exception e) {
			e.printStackTrace();
		}

		KanjiObject objectItem = values.get(position);

		if (objectItem != null) {

			gif.setOneShot(false);
			viewHolder.code.setImageDrawable(gif);
			gif.setVisible(true, true);

			viewHolder.onyomi.setText(objectItem.getCode());
			viewHolder.onyomi.setTextColor(Color.BLACK);

			viewHolder.onyomi.setText(objectItem.getCode());
			viewHolder.onyomi.setTextColor(Color.BLACK);

			viewHolder.onyomi.setText(objectItem.getCode());
			viewHolder.onyomi.setTextColor(Color.BLACK);

			viewHolder.onyomi.setText(objectItem.getCode());
			viewHolder.onyomi.setTextColor(Color.BLACK);
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
	}

}
