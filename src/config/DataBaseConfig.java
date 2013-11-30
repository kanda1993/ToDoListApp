package config;

/**
 * データベースの設定値
 * バージョン
 * テーブル名などなど。。。。
 * @author y.kanda
 */
public class DataBaseConfig {
	/**
	 * DataBaseのversion
	 */
	final public static int DB_VERSION = 1;
	
	/**
	 * DataBaseの名称
	 */
	final public static String DB_NAME = "ToDoAppDB";
	
	/* 
	 * -------------------------------------------------------------
	 *   テーブル名
	 * -------------------------------------------------------------
	 */
	
	/**
	 * ToDoテーブル名
	 */
	final public static String TODO_TABLE = "todo_table";
	
	/**
	 * ToDoテーブル名
	 */
	final public static String PJ_TABLE = "pj_table";
	
	/* 
	 * -------------------------------------------------------------
	 *   カラム名称
	 * -------------------------------------------------------------
	 */
	
	/** todoごとのユニークID */
	final public static String CLM_TODO_ID = "todo_id";
	/** pjごとのユニークID */
	final public static String CLM_PJ_CODE = "pj_code";
	
	/** todoタイトル */
	final public static String CLM_TODO_TITLE = "todo_title";
	/** todo内容 */
	final public static String CLM_TODO = "todo";
	/** 進捗 */
	final public static String CLM_PROGRESS = "progress";
	/** 期限 */
	final public static String CLM_LIMIT_DATE = "limit_date";
	/** 作成日 */
	final public static String CLM_CREATE_DATE = "create_date";
	/** 完了日 */
	final public static String CLM_COMPLETE_DATE = "complete_date";
	
	/** pj名称 */
	final public static String CLM_PJ_NAME = "pj_name";
	
	/** pj 論理削除区分 0:有効 1:無効 */
	final public static String CLM_PJ_STOP = "pj_stop_flg";
	
	/* 
	 * -------------------------------------------------------------
	 *   SQL初期実行系
	 * -------------------------------------------------------------
	 */
	
	/** TODOテーブル作成 */
	final public static String SQL_CREATE_TABLE_TODO =
									 " CREATE TABLE " + TODO_TABLE + " ( "
								   +  	 CLM_TODO_ID 		 +  " integer,"
								   +  	 CLM_PJ_CODE 		 +  " integer,"
								   +  	 CLM_TODO_TITLE   	 +  " text not null,"
								   +  	 CLM_TODO  		 	 +  " text,"
							       +  	 CLM_PROGRESS 	 	 +  " integer,"
							   	   +  	 CLM_LIMIT_DATE 	 +  " text,"
							   	   +  	 CLM_CREATE_DATE   	 +  " text,"
							   	   +  	 CLM_COMPLETE_DATE   +  " text"
							   	   + " );";
	
	/** PJマスタテーブル作成 */
	final public static String SQL_CREATE_TABLE_PJ =
									 " CREATE TABLE " + PJ_TABLE + " ( "
								   +  	 CLM_PJ_CODE 		 +  " integer,"
								   +  	 CLM_PJ_NAME 		 +  " text not null,"
								   +  	 CLM_PJ_STOP 		 +  " integer"
							   	   + " );";
	
	/* 
	 * -------------------------------------------------------------
	 *   SQL
	 * -------------------------------------------------------------
	 */
	
	/** TODOテーブル検索 TODO単品を取得する。 ?:todo_id  */
	final public static String SQL_SELECT_DETAILS =
									 "SELECT " 	    +
									 "	*"  	    +
									 "FROM "        +
									 "	todo_table" +
									 " WHERE "      +
									 "	todo_id = ?";
	
	/** 現時点で最大のtodo_idを取得する。 */
	final public static String SQL_SELECT_MAX_TODO_ID = "SELECT MAX(todo_id) FROM todo_table";
	
	
}
