/**
 * 
 */
package com.learn.kanji.helper;

import java.util.ArrayList;

import com.learn.kanji.pojo.KanjiObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author ngocha
 *
 */
public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "kanji.db";
	public static final String KANJI_TABLE_NAME = "kanjis";
	public static final String KANJI_COLUMN_ID = "id";
	public static final String KANJI_COLUMN_CODE = "code";
	public static final String KANJI_COLUMN_ONYOMI = "onyomi";
	public static final String KANJI_COLUMN_KUNYOMI = "kunyomi";
	public static final String KANJI_COLUMN_MEANING = "meaning";
	public static final String KANJI_COLUMN_TYPE = "type";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("create table if not exists kanjis"
				+ "(id integer primary key autoincrement, code text, onyomi text, kunyomi text, meaning text, type integer);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS kanjis");
		onCreate(db);
	}

	/**
	 * Get number of rows in database.
	 * 
	 * @return numberOfRows.
	 */
	public int numberOfRows() {
		SQLiteDatabase db = this.getReadableDatabase();
		int numRows = (int) DatabaseUtils.queryNumEntries(db, KANJI_TABLE_NAME);
		return numRows;
	}

	/**
	 * Insert object KANJI to database.
	 * 
	 * @param object
	 *            parameter KANJI
	 * @return Boolean.TRUE
	 */
	public boolean insertKanji(KanjiObject object) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put(KANJI_COLUMN_CODE, object.getCode());
		contentValues.put(KANJI_COLUMN_ONYOMI, object.getOnyomi());
		contentValues.put(KANJI_COLUMN_KUNYOMI, object.getKunyomi());
		contentValues.put(KANJI_COLUMN_MEANING, object.getMeaning());
		contentValues.put(KANJI_COLUMN_TYPE, object.getType());
		db.insert(KANJI_TABLE_NAME, null, contentValues);

		return Boolean.TRUE;
	}

	public ArrayList<KanjiObject> getAllKanjis() {
		ArrayList<KanjiObject> array_list_data = new ArrayList<KanjiObject>();

		SQLiteDatabase db = this.getReadableDatabase();

		Cursor res = db.rawQuery("select * from kanjis;", null);
		res.moveToFirst();
		KanjiObject obj = null;
		while (res.isAfterLast() == false) {
			obj = new KanjiObject();
			obj.setCode(res.getString(res.getColumnIndex(KANJI_COLUMN_CODE)));
			obj.setId(res.getInt(res.getColumnIndex(KANJI_COLUMN_ID)));
			obj.setKunyomi(res.getString(res
					.getColumnIndex(KANJI_COLUMN_KUNYOMI)));
			obj.setMeaning(res.getString(res
					.getColumnIndex(KANJI_COLUMN_MEANING)));
			obj.setOnyomi(res.getString(res.getColumnIndex(KANJI_COLUMN_ONYOMI)));
			obj.setType(res.getInt(res.getColumnIndex(KANJI_COLUMN_TYPE)));

			array_list_data.add(obj);

			res.moveToNext();
		}
		return array_list_data;

	}
}
