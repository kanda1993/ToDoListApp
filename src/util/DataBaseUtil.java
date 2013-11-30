package util;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * データベースを使用する際の便利クラス
 * @author y.kanda
 */
public class DataBaseUtil {
	
	/**
	 * データベースオープン、SQL実行、データベースクローズを実行する。
	 * TODO 逆に使い勝手が悪い気がするので放置
	 * @param context　現在使用しているActivity
	 * @param sql　実行SQL
	 * @param sqlParam sqlに動的に組み込むパラメーター
	 * @param errorMsg　実行時に問題が発生した時にメッセージ格納(実行元で読み取り可)
	 * @return 実行結果
	 */
	public static Cursor rawQuery (Context context,String sql,String[] sqlParam,String errorMsg){
		//クエリ実行結果格納
		Cursor cursor = null;
		
		//データベースオープン
		DataBaseOpenHelper dbHelper = new DataBaseOpenHelper(context);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		try{
			//SQL実行
			cursor = db.rawQuery(sql , sqlParam);
			cursor.moveToFirst();
		}
		catch(SQLException e){
			 //実行元でエラーメッセージを取得可能にする為
			errorMsg = e.getMessage();
		}
		finally{
			db.close();
		}
		
		return cursor;
	}
	
	/**
	 * 現在日時をSQlite用にString型で取得する。
	 * @return yyyy-MM-DD (String)
	 */
	public static String getNowDateToString(){
		
		Date date = new Date();
		
		//SQLiteで日付を扱うためのフォーマット
		SimpleDateFormat formatForSqliteDate = new SimpleDateFormat("yyyy-MM-DD");
		
		//現在日時を文字列で返却
		return formatForSqliteDate.format(date);
	}
	
}
