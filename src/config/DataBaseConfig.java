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
	
	/* 
	 * -------------------------------------------------------------
	 *   SQL初期実行系
	 * -------------------------------------------------------------
	 */
	
	/** TODOテーブル作成 */
	final public static String SQL_CREATE_TABLE_TODO =
									 " CREATE TABLE todo_table ( "
								   +  	 CLM_TODO_ID 		 +  " integer,"
								   +  	 CLM_PJ_CODE 		 +  " integer,"
								   +  	 CLM_TODO_TITLE   	 +  " text not null,"
								   +  	 CLM_TODO  		 	 +  " text,"
							       +  	 CLM_PROGRESS 	 	 +  " intger,"
							   	   +  	 CLM_LIMIT_DATE 	 +  " text,"
							   	   +  	 CLM_CREATE_DATE   	 +  " text,"
							   	   +  	 CLM_COMPLETE_DATE   +  " text"
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
	final public static String SQL_SELECT_MAX_TODO_ID= "SELECT MAX(todo_id) FROM todo_table";
	
	
}
