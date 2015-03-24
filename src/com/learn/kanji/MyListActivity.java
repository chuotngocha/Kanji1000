/**
 * 
 */
package com.learn.kanji;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.learn.kanji.adapter.SimpleListImageAdapter;
import com.learn.kanji.helper.DBHelper;
import com.learn.kanji.pojo.KanjiObject;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * @author ngocha
 *
 */
public class MyListActivity extends ListActivity {

	private List<KanjiObject> results = new ArrayList<KanjiObject>();
	DBHelper dbHelper;

	public void onCreate(Bundle icicle) {

		super.onCreate(icicle);

		this.getListView().setBackgroundColor(Color.WHITE);

		dbHelper = new DBHelper(this);

		if (dbHelper.numberOfRows() == 0) {

			loadFromFile();
		}

		results = dbHelper.getAllKanjis();
		List<String> codeImages = new ArrayList<String>();
		List<String> meanings = new ArrayList<String>();

		KanjiObject obj = new KanjiObject();

		obj.setCode("ani_4949");
		obj.setOnyomi("onyomi");
		obj.setKunyomi("setKunyomi");
		obj.setMeaning("meaning");
		results.add(obj);

		for (KanjiObject kanjiObject : results) {
			codeImages.add(kanjiObject.getCode());
			meanings.add(kanjiObject.getMeaning());
		}

		ArrayAdapter<String> adapter = new SimpleListImageAdapter(this,
				codeImages, meanings);
		setListAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		Intent intent = new Intent(MyListActivity.this,
				ListDetailActivity.class);
		intent.putExtra("itemClicked", (Serializable) results.get(position));
		startActivity(intent);

	}

	private void loadFromFile() {

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

			obj.setCode(new StringBuilder("n5_").append(
					columns[0].toLowerCase(Locale.getDefault())).toString());
			obj.setOnyomi(columns[2]);
			obj.setKunyomi(columns[3]);
			obj.setMeaning(columns[4]);

			results.add(obj);
		}

		for (KanjiObject kanjiObject : results) {
			dbHelper.insertKanji(kanjiObject);
		}
	}

	private String convertStreamToString(InputStream is) {

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

}
