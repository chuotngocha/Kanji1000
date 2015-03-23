/**
 * 
 */
package com.learn.kanji;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.learn.kanji.adapter.MySimpleArrayAdapter;
import com.learn.kanji.pojo.KanjiObject;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

		List<KanjiObject> values = new ArrayList<KanjiObject>();

		InputStream is = getResources().openRawResource(R.raw.list_n5);
		String map = convertStreamToString(is);
		Scanner scanner = new Scanner(map);
		KanjiObject obj = null;
		while (scanner.hasNextLine()) {

			String line = scanner.nextLine();

			// no more lines to read
			if (line == null) {
				scanner.close();
				break;
			}

			obj = new KanjiObject();
			String[] columns = line.split("\t");

			obj.setCode(columns[0].toLowerCase(Locale.getDefault()));
			obj.setOnyomi(columns[2]);
			obj.setKunyomi(columns[3]);
			obj.setMeaning(columns[4]);

			values.add(obj);
		}

		for (KanjiObject kanjiObject : values) {
			System.out.println(kanjiObject.toString());
		}
		System.out.println();

		// use your custom layout
//		ArrayAdapter<KanjiObject> adapter = new MySimpleArrayAdapter(this,
//				values);
//		setListAdapter(adapter);

	}

	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append((line + "\n"));
			}
		} catch (IOException e) {
			Log.w("LOG", e.getMessage());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				Log.w("LOG", e.getMessage());
			}
		}
		return sb.toString();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		KanjiObject item = (KanjiObject) getListAdapter().getItem(position);
		Toast.makeText(this, item.toString() + " selected", Toast.LENGTH_LONG)
				.show();
	}

}
