package com.example.todolistapp;

import util.DataBaseOpenHelper;
import util.DataBaseUtil;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import config.DataBaseConfig;

/**
 * TODO�쐬��ʗp
 * @author y.kanda
 */
public class CreateToDoActivity extends Activity {
	
	//�i��(progress�����l)
	private final int ZERO_PERCENT = 0;
	
	/**
	 * ��ʕ\��
	 */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		//TODO�쐬��ʂ�\��
		setContentView(R.layout.activity_create_todo);
	}
	
	public void createToDoButtonClick(View target){
		
		//��ʂ�����͒l���擾
		String todoTitle = ConvertEditTextToString(R.id.todo);
		String todo = ConvertEditTextToString(R.id.todo_content);
		String limitDate = ConvertDatePickerToString(R.id.limit_date);
		
		//�f�[�^�x�[�X�ɃA�N�Z�X�������̂ŃI�[�v��
		DataBaseOpenHelper dbHelper = new DataBaseOpenHelper(this);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		Cursor cursor = null;
		int todo_id = 0;
		try{
			//todo_id�ɂ͌���ōő��id�����蓖�Ă�
			cursor = db.rawQuery(DataBaseConfig.SQL_SELECT_MAX_TODO_ID,null);	
			cursor.moveToFirst();
			//TODO ���Ƃ�NULL�΍�(TODo���쐬���)
			todo_id = cursor.getInt(0);
			todo_id += 1;
		}
		catch(SQLException e){
			//TODO ������ʂ��o�����犮����ʂɑJ�ڂ�����B
			//error���b�Z�[�W��\������B
			return;
		}
		finally{
			//���r���[�ɐ�L����Ă���Ɩʓ|�Ȃ̂�close
			try {
				if (cursor != null){
					//����������������ׁA�K��close
					cursor.close();
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		ContentValues values = new ContentValues();
		values.put(DataBaseConfig.CLM_TODO_ID, todo_id);
		//TODO PJ�}�X�^�𖢎����ȈׁA�����I��0�ɂ��Ă���B0�͖����ނɂ���B
		values.put(DataBaseConfig.CLM_PJ_CODE, 0);
		values.put(DataBaseConfig.CLM_TODO_TITLE, todoTitle);
		values.put(DataBaseConfig.CLM_TODO, todo);
		values.put(DataBaseConfig.CLM_PROGRESS, ZERO_PERCENT);
		values.put(DataBaseConfig.CLM_LIMIT_DATE, limitDate);
		values.put(DataBaseConfig.CLM_CREATE_DATE,DataBaseUtil.getNowDateToString() );
		
		try{
			//ToDo�e���C���T�[�g
			db.insert(DataBaseConfig.TODO_TABLE, null, values);
		}
		catch(SQLException e){
			//TODO ������ʂ��o�����犮����ʂɑJ�ڂ�����B
			//error���b�Z�[�W��\������B
			return;
		}
		finally{
			db.close();
		}
	}
	
	//TODO ���Util��
	/**
	 * ��ʏ��EditText�̒l��String�ɕϊ����Ď擾����B
	 * @param rId R.id.[��ʏ��ID]
	 */
	public String ConvertEditTextToString(int rId){
		EditText editText = (EditText)findViewById(rId);
		return editText.getText().toString();
	}
	
	/**
	 * ��ʏ��DatePicker�̒l��String�ɕϊ����Ď擾����B
	 * @param rId R.id.[��ʏ��ID]
	 * @return�@��ʏ�̓��t(String)
	 */
	public String ConvertDatePickerToString(int rId){
		//��ʂ���DatePicker���擾
		DatePicker limitDatePicker = (DatePicker)findViewById(rId);
		//�I�������N�������擾�A���Ɋւ��Ă�0�X�^�[�g�Ȃ̂�+1�����Ă���B
		String limitDateY = String.valueOf(limitDatePicker.getYear());
		String limitDateM = String.valueOf(limitDatePicker.getMonth() + 1);
		String limitDateD = String.valueOf(limitDatePicker.getDayOfMonth());
		//limitDate��SQLite�p��SringFormat�ɂ���B
		String limitDate = limitDateY + "-" + limitDateM + "-" + limitDateD;
		return limitDate;
	}
	
}
