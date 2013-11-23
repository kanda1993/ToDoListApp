package config;

/**
 * �f�[�^�x�[�X�̐ݒ�l
 * �o�[�W����
 * �e�[�u�����ȂǂȂǁB�B�B�B
 * @author y.kanda
 */
public class DataBaseConfig {
	/**
	 * DataBase��version
	 */
	final public static int DB_VERSION = 1;
	
	/**
	 * DataBase�̖���
	 */
	final public static String DB_NAME = "ToDoAppDB";
	
	/* 
	 * -------------------------------------------------------------
	 *   �e�[�u����
	 * -------------------------------------------------------------
	 */
	
	/**
	 * ToDo�e�[�u����
	 */
	final public static String TODO_TABLE = "todo_table";
	
	/* 
	 * -------------------------------------------------------------
	 *   �J��������
	 * -------------------------------------------------------------
	 */
	
	/** todo���Ƃ̃��j�[�NID */
	final public static String CLM_TODO_ID = "todo_id";
	/** pj���Ƃ̃��j�[�NID */
	final public static String CLM_PJ_CODE = "pj_code";
	
	/** todo�^�C�g�� */
	final public static String CLM_TODO_TITLE = "todo_title";
	/** todo���e */
	final public static String CLM_TODO = "todo";
	/** �i�� */
	final public static String CLM_PROGRESS = "progress";
	/** ���� */
	final public static String CLM_LIMIT_DATE = "limit_date";
	/** �쐬�� */
	final public static String CLM_CREATE_DATE = "create_date";
	/** ������ */
	final public static String CLM_COMPLETE_DATE = "complete_date";
	
	/* 
	 * -------------------------------------------------------------
	 *   SQL�������s�n
	 * -------------------------------------------------------------
	 */
	
	/** TODO�e�[�u���쐬 */
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
	
	/** TODO�e�[�u������ TODO�P�i���擾����B ?:todo_id  */
	final public static String SQL_SELECT_DETAILS =
									 "SELECT " 	    +
									 "	*"  	    +
									 "FROM "        +
									 "	todo_table" +
									 " WHERE "      +
									 "	todo_id = ?";
	
	/** �����_�ōő��todo_id���擾����B */
	final public static String SQL_SELECT_MAX_TODO_ID= "SELECT MAX(todo_id) FROM todo_table";
	
	
}
