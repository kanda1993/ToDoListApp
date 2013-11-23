package com.example.todolistapp;

import config.DataBaseConfig;
import util.DataBaseOpenHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

/**
 * ToDo�ڍ׉��
 * @author y.kanda
 */
public class ToDoDetailsActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//��ʂɃZ�b�g���镶������ꎞ�i�[����p
		String todoTitleStr = "";
		String todoContentStr = "";
		String limitDateStr = "";
		String progressStr = "";
		
		//ToDoSELECT�ڍ׌��ʊi�[
		Cursor cursor = null;
		
		//ToDo�ڍׂ�view���Z�b�g
		setContentView(R.layout.activity_todo_details);
		
		//��ʃI�u�W�F�N�g(?)�擾
		TextView todoTitle = (TextView) findViewById(R.id.todo_details);
		TextView todoContent = (TextView) findViewById(R.id.todo_content_details);
		TextView limitDate = (TextView) findViewById(R.id.limit_date_details);
		TextView progress = (TextView) findViewById(R.id.progress_details);
		
		//�ڍׂ�\������ToDo��ID���擾����B(�ꗗ��ʂőI������ToDo)
		Intent intent = getIntent();
		String[] todo_id = { intent.getStringExtra(DataBaseConfig.CLM_TODO_ID) };
		
		try{
			//�f�[�^�x�[�X�ɃA�N�Z�X�������̂ŃI�[�v��
			DataBaseOpenHelper dbHelper = new DataBaseOpenHelper(this);
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			
			//List����I������ToDo��id�����Ɉ��������Ă���
			cursor = db.rawQuery(DataBaseConfig.SQL_SELECT_DETAILS , todo_id);
			cursor.moveToFirst();
			
			//�I������get
			todoTitleStr = cursor.getString(2);
			todoContentStr = cursor.getString(3);
			limitDateStr = cursor.getString(5);
			progressStr = cursor.getString(4);
			
		}
		catch(SQLException e){
			todoTitleStr = "AppError�I";
			todoContentStr = e.getMessage();
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
		
		//��ʂɒl�n��
		todoTitle.setText(todoTitleStr);
		todoContent.setText(todoContentStr);
		limitDate.setText(limitDateStr);
		progress.setText(progressStr);
	}
}
