package util;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * �f�[�^�x�[�X���g�p����ۂ֗̕��N���X
 * @author y.kanda
 */
public class DataBaseUtil {
	
	/**
	 * �f�[�^�x�[�X�I�[�v���ASQL���s�A�f�[�^�x�[�X�N���[�Y�����s����B
	 * TODO �t�Ɏg�����肪�����C������̂ŕ��u
	 * @param context�@���ݎg�p���Ă���Activity
	 * @param sql�@���sSQL
	 * @param sqlParam sql�ɓ��I�ɑg�ݍ��ރp�����[�^�[
	 * @param errorMsg�@���s���ɖ�肪�����������Ƀ��b�Z�[�W�i�[(���s���œǂݎ���)
	 * @return ���s����
	 */
	public static Cursor rawQuery (Context context,String sql,String[] sqlParam,String errorMsg){
		//���s���ʂ��i�[
		Cursor cursor = null;
		
		//�f�[�^�x�[�X�ɃA�N�Z�X�������̂ŃI�[�v��
		DataBaseOpenHelper dbHelper = new DataBaseOpenHelper(context);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		try{
			//SQL�����s
			cursor = db.rawQuery(sql , sqlParam);
			cursor.moveToFirst();
		}
		catch(SQLException e){
			//���s���ŃG���[���b�Z�[�W���擾�\�ɂ����
			errorMsg = e.getMessage();
		}
		finally{
			db.close();
		}
		
		return cursor;
	}
	
	/**
	 * ���ݓ�����SQlite�p��String�^�Ŏ擾����B
	 * @param date �ϊ�������Date
	 * @return yyyy-MM-DD (String)
	 */
	public static String getNowDateToString(){
		
		Date date = new Date();
		
		//SQLite��Date�t�H�[�}�b�g
		SimpleDateFormat formatForSqliteDate = new SimpleDateFormat("yyyy-MM-DD");
		
		//���ݓ�����SQLite�p�̃t�H�[�}�b�g
		return formatForSqliteDate.format(date);
	}
	
}
