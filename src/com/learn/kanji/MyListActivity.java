/**
 * 
 */
package com.learn.kanji;

import java.util.ArrayList;
import java.util.List;

import com.learn.kanji.adapter.MySimpleArrayAdapter;
import com.learn.kanji.pojo.TestObject;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author ngocha
 *
 */
public class MyListActivity extends ListActivity {

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		this.getListView().setBackgroundColor(Color.WHITE);

		List<TestObject> values = new ArrayList<TestObject>();

		for (int i = 0; i < 10; i++) {
			TestObject obj = new TestObject();
			obj.setFirstLine("first Line" + (i + 1));
			obj.setSecondLine("secondLine" + (i + 1));
			obj.setThirdLine("thirdLine" + (i + 1));
			obj.setFourLine("fourLine" + (i + 1));
			values.add(obj);
		}

		// use your custom layout
		ArrayAdapter<TestObject> adapter = new MySimpleArrayAdapter(this,
				values);
		setListAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		TestObject item = (TestObject) getListAdapter().getItem(position);
		Toast.makeText(this, item.toString() + " selected", Toast.LENGTH_LONG)
				.show();
	}

}
