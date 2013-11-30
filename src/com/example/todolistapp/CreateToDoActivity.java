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
	
	/**
	 * ToDo�V�K�쐬�{�^�����s
	 * @param target
	 */
	public void createToDoButtonClick(View target){
		//���ʕ\���_�C�A���O�p�Ɏ擾
		Resources res = getResources();
		String dialogTitle = "";
		String dialogMsg = "";
		//���ʕ\���_�C�A���O���e�؂�ւ��p
		Boolean successCreate = false;
		
		//��ʂ�����͒l���擾
		String todoTitle = ConvertEditTextToString(R.id.todo);
		String todo = ConvertEditTextToString(R.id.todo_content);
		String limitDate = ConvertDatePickerToString(R.id.limit_date);
		
		//�f�[�^�x�[�X�ɃA�N�Z�X�������̂ŃI�[�v��
		DataBaseOpenHelper dbHelper = new DataBaseOpenHelper(this);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		//insert�f�[�^�̍쐬
		ContentValues values = new ContentValues();
		//�V�KToDoId��U��
		values.put(DataBaseConfig.CLM_TODO_ID, createToDoId(db));
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
			successCreate = true;
		}
		catch(SQLException e){
			//�����l�����s�Ȃ̂ŕK�v�Ȃ��������I�ɂ���ׁB
			successCreate = false;
		}
		finally{
			db.close();
		}
		
		//�쐬���ʕ\���_�C�A���O�̐؂�ւ��i�\�����e�擾�j
		if(successCreate){
			//�쐬����
			dialogTitle = res.getString(R.string.ok_title);
			dialogMsg = res.getString(R.string.insert_ok_msg);
		}
		else{
			//�쐬���s
			dialogTitle = res.getString(R.string.ng_title);
			dialogMsg = res.getString(R.string.insert_ng_msg);
		}
		//�������b�Z�[�W�̕\��
		AlertDialog resultMsg = createMesageDialog(dialogTitle,dialogMsg);
		resultMsg.show();
	}
	
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
	
	/**
	 * ToDoId�𔭍s����B
	 * @param db
	 * @return �V�Ktodo_id
	 */
	public int createToDoId(SQLiteDatabase db){
		
		int todo_id = 0;
		
		Cursor cursor = null;
		try{
			//todo_id�ɂ͌���ōő��id�����蓖�Ă�
			cursor = db.rawQuery(DataBaseConfig.SQL_SELECT_MAX_TODO_ID,null);	
			cursor.moveToFirst();
			//TODO ���Ƃ�NULL�΍�(TODo���쐬���)
			todo_id = cursor.getInt(0);
			todo_id += 1;
		}
		catch(SQLException e){
			todo_id = 0;
		}
		finally{
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
		
		return todo_id;
	}
	
	/**
	 * �^�C�g���A���b�Z�[�W�AOK�{�^����\�������V���v���ȃ_�C�A���O���쐬����B
	 * @param title
	 * @param msg
	 * @return ���b�Z�[�W�\���p�_�C�A���O
	 */
	public AlertDialog createMesageDialog(String title,String msg){
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		
		alertDialogBuilder.setTitle(title);
		alertDialogBuilder.setMessage(msg);
		alertDialogBuilder.setPositiveButton("ok", null);
		
		//�A���[�g�_�C�A���O���̂��쐬����B
		return alertDialogBuilder.create();
		
	}
}
