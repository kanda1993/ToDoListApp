package com.example.todolistapp;

import config.DataBaseConfig;
import util.DataBaseOpenHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/**
 * ToDo詳細
 * @author y.kanda
 */
public class ToDoDetailsActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//ToDo詳細のviewをセット
		setContentView(R.layout.activity_todo_details);
		
		//画面にセットする文字列を一時格納する用
		String todoTitleStr = "";
		String todoContentStr = "";
		String limitDateStr = "";
		String progressStr = "";
		
		//画面オブジェクト(?)取得
		TextView todoTitle = (TextView) findViewById(R.id.todo_details);
		TextView todoContent = (TextView) findViewById(R.id.todo_content_details);
		TextView limitDate = (TextView) findViewById(R.id.limit_date_details);
		final TextView progress = (TextView) findViewById(R.id.progress_details);
		final SeekBar  progressSeek = (SeekBar) findViewById(R.id.progress_seekBar);
		
		progressSeek.setOnSeekBarChangeListener(
					new OnSeekBarChangeListener() {
						
						//シークバーを掴んでいる間呼ばれる
						@Override
						public void onProgressChanged(SeekBar seekBar,int intProgress,boolean fromUser){
							//表示上の進捗率を上げる
							progress.setText(Integer.valueOf(progressSeek.getProgress()) + "%");
						}
						
						//シークバーに触れた瞬間？
						@Override
						public void onStartTrackingTouch(SeekBar seekBar) {
						}
						
						//シークバーを離した瞬間
						@Override
						public void onStopTrackingTouch(SeekBar seekBar) {
							// TODO　DBにアップデートでもかける？
						}
					}
						
				);
		
		//詳細を表示するToDoのIDを取得する。(一覧画面で選択したToDo)
		Intent intent = getIntent();
		String[] todo_id = { intent.getStringExtra(DataBaseConfig.CLM_TODO_ID) };
		
		//データベースにアクセスしたいのでオープン
		DataBaseOpenHelper dbHelper = new DataBaseOpenHelper(this);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		 //ToDoSELECT詳細結果格納
		Cursor cursor = null;
		
		try{
			//Listから選択したToDo明細をidを元に引っ張ってくる
			cursor = db.rawQuery(DataBaseConfig.SQL_SELECT_DETAILS , todo_id);
			cursor.moveToFirst();
			
			//選択結果get
			todoTitleStr = cursor.getString(2);
			todoContentStr = cursor.getString(3);
			limitDateStr = cursor.getString(5);
			progressStr = cursor.getString(4) + "%";
			progressSeek.setProgress(cursor.getInt(cursor.getColumnIndex(DataBaseConfig.CLM_PROGRESS)));
			
		}
		catch(SQLException e){
			todoTitleStr = "AppError";
			todoContentStr = e.getMessage();
		}
		finally{
			//中途半端に占有されていると面倒なのでclose
			db.close();
			
			try {
				if (cursor != null){
					//メモリ圧迫防止
					cursor.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		//画面に値渡し
		todoTitle.setText(todoTitleStr);
		todoContent.setText(todoContentStr);
		limitDate.setText(limitDateStr);
		progress.setText(progressStr);
	}
}
