package com.example.todolistapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import config.DataBaseConfig;

import util.DataBaseOpenHelper;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

/**
 * ToDoList��\������B
 * 
 * @author y.kanda
 */
public class ToDoListActivity extends ListActivity {
	
	private ArrayList<Map<String, String>> todoList;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//�f�[�^�x�[�X�ɃA�N�Z�X�������̂ŃI�[�v��
		DataBaseOpenHelper dbHelper = new DataBaseOpenHelper(this);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		
		//ToDo���X�g����������
		String SqlToDoAllSelect = "SELECT * FROM todo_table";
		Cursor cursor = db.rawQuery(SqlToDoAllSelect, null);
		
		//ToDo�����i�[����B
		todoList = new ArrayList<Map<String, String>>(); 
		//�J�[�\���̏��߂���I���܂ł�ToDo���X�g���擾����B
		//while(cursor)�ɂ��Ȃ��͍̂ŏ��̃��R�[�h�𖳎�����Ȃ��悤�ɂ��邽��
		boolean isNext = cursor.moveToFirst();
		while(isNext){
			Map<String, String> map = new HashMap<String, String>(); 
			map.put(DataBaseConfig.CLM_TODO_ID, cursor.getString(0));
			map.put(DataBaseConfig.CLM_PJ_CODE, cursor.getString(1));
			map.put(DataBaseConfig.CLM_TODO_TITLE,cursor.getString(2));
			map.put(DataBaseConfig.CLM_TODO,cursor.getString(3));
			map.put(DataBaseConfig.CLM_PROGRESS,cursor.getString(4));
			map.put(DataBaseConfig.CLM_LIMIT_DATE,cursor.getString(5));
			
			todoList.add(map);
			isNext = cursor.moveToNext();
		}
		cursor.close();
		
		SimpleAdapter adapter = new SimpleAdapter
				(this,
				todoList,
				android.R.layout.simple_list_item_2,
				new String[] { DataBaseConfig.CLM_TODO_TITLE,DataBaseConfig.CLM_TODO_ID},
				new int[] { android.R.id.text1, android.R.id.text2 });
		setListAdapter(adapter);
		
		/**
		 * TODO ���������̏������s���BAPI�݂Ƃ�
		 * ��肽�����Ƃ�List�\���ŃN���b�N���ꂽ�A�C�e�����擾����B
		 * �����đJ�ڂ�����B�y�����邩����A�C�e�����̂ɏ��������Ă���B
		 */
		
		//�A�C�e�����X�i�[
		getListView().setOnItemClickListener(
			new AdapterView.OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
					
					Intent intent = new Intent(ToDoListActivity.this, ToDoDetailsActivity.class);
					String todo_id = todoList.get(position).get(DataBaseConfig.CLM_TODO_ID);
					intent.putExtra(DataBaseConfig.CLM_TODO_ID, todo_id);
					
					startActivity(intent);
				}
			}
		);
		
	}
	
	

}
