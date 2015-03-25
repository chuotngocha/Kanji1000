/**
 * 
 */
package com.learn.kanji.adapter;

import java.util.List;

import com.learn.kanji.R;

import android.annotation.SuppressLint;
import android.content.Context;
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
public class ListKanjiAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final List<String> codeImages;
	private final List<String> meanings;

	public ListKanjiAdapter(Context context, List<String> codeImages,
			List<String> meanings) {
		super(context, R.layout.listview_layout, codeImages);
		this.context = context;
		this.codeImages = codeImages;
		this.meanings = meanings;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolderItem viewHolder;

		if (convertView == null) {

			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			convertView = inflater.inflate(R.layout.listview_layout, parent,
					false);

			viewHolder = new ViewHolderItem();

			viewHolder.code = (ImageView) convertView.findViewById(R.id.icon);

			viewHolder.meaning = (TextView) convertView
					.findViewById(R.id.meaning);

			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolderItem) convertView.getTag();
		}

		String codeItem = codeImages.get(position);

		if (codeItem != null) {

			try {
				viewHolder.code.setImageResource(R.drawable.class
						.getDeclaredField(codeItem).getInt(Integer.valueOf(0)));
			} catch (Exception e) {
				e.printStackTrace();
			}

			viewHolder.meaning.setText(meanings.get(position));

		}

		return convertView;
	}

	static public class ViewHolderItem {

		public ImageView code;
		public TextView meaning;
	}

}
