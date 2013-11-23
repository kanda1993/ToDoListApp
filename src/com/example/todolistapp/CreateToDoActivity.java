package com.example.todolistapp;

import java.text.SimpleDateFormat;
import java.util.Date;

import util.DataBaseOpenHelper;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import config.DataBaseConfig;

/**
 * TODO作成画面用
 * @author y.kanda
 */
public class CreateToDoActivity extends Activity {
	
	//進捗(progress初期値)
	private final int ZERO_PERCENT = 0;
	
	/**
	 * onCreate
	 */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//TODO作成画面を表示
		setContentView(R.layout.activity_create_todo);
	}
	
	public void createToDoButtonClick(View target){
		
		//画面から入力値を取得
		String todoTitle = ConvertEditTextToString(R.id.todo);
		String todo = ConvertEditTextToString(R.id.todo_content);
		String limitDate = ConvertDatePickerToString(R.id.limit_date);
		
		//データベースにアクセスしたいのでオープン
		DataBaseOpenHelper dbHelper = new DataBaseOpenHelper(this);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		//todo_idには現状で最大のidを割り当てる
		Cursor cursor = db.rawQuery(DataBaseConfig.SQL_SELECT_MAX_TODO_ID,null);	
		cursor.moveToFirst();
		int todo_id = cursor.getInt(0);
		//TODO close必ず実行するようにする。
		cursor.close();
		//あとでNULL対策
		todo_id += 1;

		
		ContentValues values = new ContentValues();
		values.put(DataBaseConfig.CLM_TODO_ID, todo_id);
		//TODO PJマスタを未実装な為、強制的に0にしている。0は未分類にする。
		values.put(DataBaseConfig.CLM_PJ_CODE, 0);
		values.put(DataBaseConfig.CLM_TODO_TITLE, todoTitle);
		values.put(DataBaseConfig.CLM_TODO, todo);
		values.put(DataBaseConfig.CLM_PROGRESS, ZERO_PERCENT);
		values.put(DataBaseConfig.CLM_LIMIT_DATE, limitDate);
		values.put(DataBaseConfig.CLM_CREATE_DATE, getNowDateToString());
		
		//DB内容をインサート
		db.insert(DataBaseConfig.TODO_TABLE, null, values);
		
		db.close();
	}
	
	//TODO 後でUtilへ
	/**
	 * 画面上のEditTextの値をStringに変換して取得する。
	 * @param rId R.id.[画面上のID]
	 */
	public String ConvertEditTextToString(int rId){
		EditText editText = (EditText)findViewById(rId);
		return editText.getText().toString();
	}
	
	/**
	 * 画面上のDatePickerの値をStringに変換して取得する。
	 * @param rId R.id.[画面上のID]
	 * @return　画面上の日付(String)
	 */
	public String ConvertDatePickerToString(int rId){
		//画面からDatePickerを取得
		DatePicker limitDatePicker = (DatePicker)findViewById(rId);
		//選択した年月日を取得、月に関しては0スタートなので+1をしている。
		String limitDateY = String.valueOf(limitDatePicker.getYear());
		String limitDateM = String.valueOf(limitDatePicker.getMonth() + 1);
		String limitDateD = String.valueOf(limitDatePicker.getDayOfMonth());
		//limitDateをSQLite用のSringFormatにする。
		String limitDate = limitDateY + "-" + limitDateM + "-" + limitDateD;
		return limitDate;
	}
	
	/**
	 * 現在日時をSQlite用にString型で取得する。
	 * @return yyyy-MM-DD (String)
	 */
	public String getNowDateToString(){
				
		Date nowDate = new Date();
		//SQLiteのDateフォーマット
		SimpleDateFormat formatForSqliteDate = new SimpleDateFormat("yyyy-MM-DD");
		
		//現在日時をSQLite用のフォーマット
		return formatForSqliteDate.format(nowDate);
	}
}
