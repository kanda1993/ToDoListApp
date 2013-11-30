package util;

import config.DataBaseConfig;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * DataBase��Open�w���p�[
 * �f�[�^�x�[�X���쐬����Ă��Ȃ��������ɂ́A�I�[�v���O�Ƀf�[�^�x�[�X�̍쐬����������B
 * @author y.kanda
 */
public class DataBaseOpenHelper extends SQLiteOpenHelper {
		
	/* TODO �e�X�g�f�[�^��insert����pSQL
	 * ���������f�[�^�����ނ̂��ʓ|�Ȃ̂� */
	private String SqlTestInsert = " INSERT INTO todo_table(todo_id,pj_code,todo_title,todo,progress,limit_date,create_date,complete_date) VALUES("
								 + "1,1,'ToDo�^�C�g��','ToDo���e',50,'2013-11-11','2013-11-07','2013-11-15'); ";
	private String SqlTestInsert2 = " INSERT INTO todo_table(todo_id,pj_code,todo_title,todo,progress,limit_date,create_date,complete_date) VALUES("
			 + "2,2,'ToDo�^�C�g��2','ToDo���e2',0,'2013-12-12','2013-12-07','2013-12-15'); ";
	
	/**
	 * �ʏ펞�F�I�[�v������
	 * �f�[�^�x�[�X�����쐬�̏ꍇ(��������)�FonCreate
	 * �f�[�^�x�[�Xvar���Â��ꍇ�FonUpgrade
	 * @param context ActivityClass
	 */
	public DataBaseOpenHelper(Context context) {
		super(context, DataBaseConfig.DB_NAME, null, DataBaseConfig.DB_VERSION);
	}
	
	/**
	 * DB�����쐬
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// ToDo�}�X�^�쐬
		db.execSQL(DataBaseConfig.SQL_CREATE_TABLE_TODO);
		// PJ�}�X�^�쐬
		db.execSQL(DataBaseConfig.SQL_CREATE_TABLE_PJ);
		
		//TODO �e�X�g�f�[�^insert
		db.execSQL(SqlTestInsert);
		db.execSQL(SqlTestInsert2);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		/*
		 * ���ɕύX�͂Ȃ��̂Ŗ������B
		 */
		//�f�[�^�x�[�X��������o�[�W�����A�b�v����ꍇ�́Aif���ŏ��ԂɃo�[�W�����A�b�v���邱�ƁB
	}

}
