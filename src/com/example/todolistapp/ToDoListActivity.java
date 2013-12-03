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
import android.widget.SimpleAdapter;

/**
 * ToDo一覧画面
 * @author y.kanda
 */
public class ToDoListActivity extends ListActivity {
	
	private ArrayList<Map<String, String>> todoList;
	
	/**
	 * ToDo一覧画面初期表示
	 * ToDoテーブルから全ToDoを取得してリスト表示を行います。(暫定)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		//ToDoリスト表示に必要な情報取得
		String SqlToDoAllSelect = " SELECT "
								+ " " + DataBaseConfig.CLM_TODO_ID + ","
								+ " " + DataBaseConfig.CLM_PJ_CODE + ","
								+ " " + DataBaseConfig.CLM_TODO_TITLE + ","
								+ " " + DataBaseConfig.CLM_TODO + ","
								+ " " + DataBaseConfig.CLM_PROGRESS + ","
								+ " " + DataBaseConfig.CLM_LIMIT_DATE
								+ " FROM"
								+ 		DataBaseConfig.TODO_TABLE;
		
		//DBオープン
		DataBaseOpenHelper dbHelper = new DataBaseOpenHelper(this);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		//ToDo一覧の取得、以降使用しないのでDBクローズ
		Cursor cursor = db.rawQuery(SqlToDoAllSelect, null);
		db.close();
		
		//ToDo情報を格納する
		todoList = new ArrayList<Map<String, String>>(); 
		
		//取得ToDoの格納
		while(cursor.moveToNext()){
			Map<String, String> map = new HashMap<String, String>(); 
			map.put(DataBaseConfig.CLM_TODO_ID, cursor.getString(0));
			map.put(DataBaseConfig.CLM_PJ_CODE, cursor.getString(1));
			map.put(DataBaseConfig.CLM_TODO_TITLE,cursor.getString(2));
			map.put(DataBaseConfig.CLM_TODO,cursor.getString(3));
			map.put(DataBaseConfig.CLM_PROGRESS,cursor.getString(4));
			map.put(DataBaseConfig.CLM_LIMIT_DATE,cursor.getString(5));
			
			todoList.add(map);
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
		 * リストアイテムに対してcallback処理を実装する。
		 * List表示でクリックされたアイテムを取得する。
		 * クリックしたToDo(アイテム)のIdをセットして、ToDo詳細画面へ遷移する。
		 */
		
		//リスナー登録
		getListView().setOnItemClickListener(
			new AdapterView.OnItemClickListener(){
				
				/**
				 * parent　このアイテムがどのViewからクリックされるか(?)
				 * view 
				 * position 表示上何番目か？
				 * id rowId アイテムが何番目か(ユニークキー)
				 * 
				 */
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
