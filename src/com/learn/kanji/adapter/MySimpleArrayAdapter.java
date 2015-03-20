/**
 * 
 */
package com.learn.kanji.adapter;

import java.util.List;

import com.learn.kanji.R;
import com.learn.kanji.pojo.TestObject;

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
public class MySimpleArrayAdapter extends ArrayAdapter<TestObject> {

	private final Context context;
	private final List<TestObject> values;
	private GifAnimationDrawable gif = null;

	public MySimpleArrayAdapter(Context context, List<TestObject> values) {
		super(context, R.layout.row_layout, values);
		this.context = context;
		this.values = values;

	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.row_layout, parent, false);

		try {
			gif = new GifAnimationDrawable(context.getResources()
					.openRawResource(R.drawable.ashi), context.getResources());
		} catch (Exception e) {
			e.printStackTrace();
		}

		ImageView imageView = (ImageView) rowView.findViewById(R.id.imageIcon);
		gif.setOneShot(false);
		gif.setVisible(true, true);
		imageView.setImageDrawable(gif);

		TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
		firstLine.setText(values.get(position).getFirstLine());
		firstLine.setTextColor(Color.BLACK);

		TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);
		secondLine.setText(values.get(position).getSecondLine());
		secondLine.setTextColor(Color.BLACK);

		TextView thirdLine = (TextView) rowView.findViewById(R.id.thirdLine);
		thirdLine.setText(values.get(position).getThirdLine());
		thirdLine.setTextColor(Color.BLACK);

		TextView fourLine = (TextView) rowView.findViewById(R.id.fourLine);
		fourLine.setText(values.get(position).getFourLine());
		fourLine.setTextColor(Color.BLACK);

		return rowView;
	}

}
