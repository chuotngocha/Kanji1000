/**
 * 
 */
package com.learn.kanji.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.learn.kanji.R;
import com.learn.kanji.adapter.ListKanjiAdapter;
import com.learn.kanji.helper.DBHelper;
import com.learn.kanji.pojo.KanjiObject;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author ngocha
 *
 */
public class ListKanjiActivity extends ListActivity {

	private List<KanjiObject> results = new ArrayList<KanjiObject>();
	private DBHelper dbHelper;
	private Parcelable state = null;
	private ArrayAdapter<String> adapter;
	private int positionCLicked;
	private int oldSize;
	private List<String> codeImages = new ArrayList<String>();
	private List<String> meanings = new ArrayList<String>();

	public void onCreate(Bundle savedInstanceState) {

		if (savedInstanceState != null) {
			state = savedInstanceState.getParcelable("LIST_INSTANCE_STATE");
			this.getListView().onRestoreInstanceState(state);
		}

		super.onCreate(savedInstanceState);

		getActionBar().setDisplayHomeAsUpEnabled(true);

		this.getListView().setBackgroundColor(Color.WHITE);

		dbHelper = new DBHelper(this);

		if (dbHelper.numberOfRows(MainActivity.type) == 0) {
			switch (MainActivity.type) {
			case 5: {
				loadFromFile(R.raw.list_n5);
				break;
			}

			default:
				break;
			}

		}

		loadData();

		this.oldSize = results.size();

		adapter = new ListKanjiAdapter(this, codeImages, meanings);
		setListAdapter(adapter);

	}

	public void loadData() {
		if (MainActivity.type != -1) {
			results = dbHelper.getAllKanjis(MainActivity.type);
		} else {
			results = dbHelper.getKanjisBookmarkOnly();
		}

		for (KanjiObject kanjiObject : results) {
			codeImages.add(kanjiObject.getCode());
			meanings.add(kanjiObject.getMeaning());
		}
	}

	@Override
	protected void onResume() {

		super.onResume();
		if (MainActivity.type != -1) {
			results = dbHelper.getAllKanjis(MainActivity.type);
		} else {
			results = dbHelper.getKanjisBookmarkOnly();
			if (results.size() < oldSize) {

				String item = (String) getListAdapter().getItem(
						this.positionCLicked);
				meanings.remove(positionCLicked);
				codeImages.remove(positionCLicked);
				adapter.remove(item);
			}
		}

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {

		outState.putParcelable("LIST_INSTANCE_STATE", getListView()
				.onSaveInstanceState());
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		this.positionCLicked = position;

		Intent intent = new Intent(ListKanjiActivity.this,
				KanjiDetailActivity.class);
		intent.putExtra("itemClicked", (Serializable) results.get(position));
		startActivity(intent);

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

	private void loadFromFile(int id) {

		InputStream is = getResources().openRawResource(id);
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
			obj.setMeaning(columns[4].toUpperCase(Locale.getDefault()));
			obj.setType(MainActivity.type);

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
