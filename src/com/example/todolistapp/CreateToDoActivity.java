package com.example.todolistapp;

import util.DataBaseOpenHelper;
import util.DataBaseUtil;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.SQLException;
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
	 * 画面表示
	 */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//TODO作成画面を表示
		setContentView(R.layout.activity_create_todo);
	}
	
	/**
	 * ToDo新規作成ボタン実行
	 * @param target
	 */
	public void createToDoButtonClick(View target){

		//結果表示ダイアログ内容切り替え用
		Boolean successCreateFlg = false;
		
		//データベースにアクセスしたいのでオープン
		DataBaseOpenHelper dbHelper = new DataBaseOpenHelper(this);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		//insertデータの作成
		ContentValues values = new ContentValues();
		
		//画面から入力値を取得
		String todoTitle = ConvertEditTextToString(R.id.todo);
		String todo = ConvertEditTextToString(R.id.todo_content);
		String limitDate = ConvertDatePickerToString(R.id.limit_date);
		
		//新規ToDoIdを振番
		values.put(DataBaseConfig.CLM_TODO_ID, createToDoId(db));
		//TODO PJマスタを未実装な為、強制的に0にしている。0は未分類にする。
		values.put(DataBaseConfig.CLM_PJ_CODE, 0);
		values.put(DataBaseConfig.CLM_TODO_TITLE, todoTitle);
		values.put(DataBaseConfig.CLM_TODO, todo);
		values.put(DataBaseConfig.CLM_PROGRESS, ZERO_PERCENT);
		values.put(DataBaseConfig.CLM_LIMIT_DATE, limitDate);
		values.put(DataBaseConfig.CLM_CREATE_DATE,DataBaseUtil.getNowDateToString() );
		
		try{
			//ToDo容をインサート
			db.insert(DataBaseConfig.TODO_TABLE, null, values);
			successCreateFlg = true;
		}
		catch(SQLException e){
			//初期値が失敗なので必要ないが明示的にする為。
			successCreateFlg = false;
		}
		finally{
			db.close();
		}
		
		//結果ダイアログの表示
		showResultDialog(successCreateFlg);
	}
	
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
	 * ToDoIdを発行する。
	 * @param db
	 * @return 新規todo_id
	 */
	public int createToDoId(SQLiteDatabase db){
		
		int todo_id = 0;
		
		Cursor cursor = null;
		try{
			//todo_idには現状で最大のidを割り当てる
			cursor = db.rawQuery(DataBaseConfig.SQL_SELECT_MAX_TODO_ID,null);	
			cursor.moveToFirst();
			//未登録の場合は0が取得される
			todo_id = cursor.getInt(0);
			todo_id += 1;
		}
		catch(SQLException e){
			//特に必要なかった？
		}
		finally{
			try {
				if (cursor != null){
					//メモリを圧迫する為、必ずclose
					cursor.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return todo_id;
	}
	
	/**
	 * 作成結果ダイアログを表示する。
	 * @param successCreateFlg　作成が成功したか。
	 */
	public void showResultDialog(Boolean successCreateFlg){
		
		//結果表示ダイアログ用に取得
		Resources res = getResources();
		String dialogTitle = "";
		String dialogMsg = "";
		
		//作成結果表示ダイアログの切り替え（表示内容取得）
		if(successCreateFlg){
			//作成成功
			dialogTitle = res.getString(R.string.ok_title);
			dialogMsg = res.getString(R.string.insert_ok_msg);
		}
		else{
			//作成失敗
			dialogTitle = res.getString(R.string.ng_title);
			dialogMsg = res.getString(R.string.insert_ng_msg);
		}
		//完了メッセージの表示
		AlertDialog resultMsg = createMesageDialog(dialogTitle,dialogMsg);
		resultMsg.show();
	}
	
	/**
	 * タイトル、メッセージ、OKボタンを表示したシンプルなダイアログを作成する。
	 * @param title
	 * @param msg
	 * @return メッセージ表示用ダイアログ
	 */
	public AlertDialog createMesageDialog(String title,String msg){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		//タイトル、メッセージの設定
		alertDialogBuilder.setTitle(title);
		alertDialogBuilder.setMessage(msg);
		//ダイアログ閉じる用
		alertDialogBuilder.setPositiveButton("ok", null);
		
		//アラートダイアログ自体を作成する。
		return alertDialogBuilder.create();
	}
}
