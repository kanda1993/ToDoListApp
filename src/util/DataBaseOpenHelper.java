package util;

import config.DataBaseConfig;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DataBaseのOpenヘルパー
 * データベースが作成されていない初期時には、オープン前にデータベースの作成処理が走る。
 * @author y.kanda
 */
public class DataBaseOpenHelper extends SQLiteOpenHelper {

	/* TODO テストデータをinsertする用SQL
	 * いちいちデータつっこむのが面倒なので */
	private String SqlTestInsert = " INSERT INTO todo_table(todo_id,pj_code,todo_title,todo,progress,limit_date,create_date,complete_date) VALUES("
	                                                         + "1,1,'ToDoタイトル','ToDo内容',50,'2013-11-11','2013-11-07','2013-11-15'); ";
	private String SqlTestInsert2 = " INSERT INTO todo_table(todo_id,pj_code,todo_title,todo,progress,limit_date,create_date,complete_date) VALUES("
	                 + "2,2,'ToDoタイトル2','ToDo内容2',0,'2013-12-12','2013-12-07','2013-12-15'); ";
	
	/**
	 * 通常時：オープン処理
	 * データベースが未作成の場合(初期処理)：onCreate
	 * データベースvarが古い場合：onUpgrade
	 * @param context ActivityClass
	 */
	public DataBaseOpenHelper(Context context) {
	        super(context, DataBaseConfig.DB_NAME, null, DataBaseConfig.DB_VERSION);
	}
	
	/**
	 * DB初期作成
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
	        // ToDoマスタ作成
	        db.execSQL(DataBaseConfig.SQL_CREATE_TABLE_TODO);
	        // PJマスタ作成
	        db.execSQL(DataBaseConfig.SQL_CREATE_TABLE_PJ);
	        //　PJその他の追加
	        db.execSQL(DataBaseConfig.SQL_INSERT_ATHER_PJ);
	        
	        //TODO テストデータinsert
	        db.execSQL(SqlTestInsert);
	        db.execSQL(SqlTestInsert2);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
	        /*
	         * 特に変更はないので未処理。
	         */
	        //データベースが複数回バージョンアップする場合は、if分で順番にバージョンアップすること。
	}

}