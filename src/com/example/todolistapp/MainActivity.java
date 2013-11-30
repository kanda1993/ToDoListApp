package com.example.todolistapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * メインメニューのActivity
 * メニュー
 * ToDoリストへ遷移
 * 新規ToDoリスト作成へ遷移
 * 完了ToDoリストへ遷移
 * App終了ボタン
 * @author y.kanda
 */
public class MainActivity extends Activity {
	
	/**
	 * 初期表示
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//メインメニューのviewをセット
		setContentView(R.layout.activity_main);
	}
	
	/**
	 * app終了時の処理(画面外から終了された時用)
	 */
	@Override
	public void onDestroy(){
		super.onDestroy();
	}
	
	// -----------以降ボタン実行時の処理------------
	
	/**
	 * ToDoリストボタン実行時の処理
	 * ToDoList画面へ遷移。
	 * @param target
	 */
	public void todoListButtonClick(View target){
		Intent intent = new Intent(MainActivity.this,ToDoListActivity.class);
		startActivity(intent);
	}
	
	/**
	 * ToDo新規作成ボタン実行時の処理
	 * ToDo新規作成画面へ遷移。
	 * @param target
	 */
	public void createToDoButtonClick(View target){
		Intent intent = new Intent(MainActivity.this,CreateToDoActivity.class);
		startActivity(intent);
	}
	
	/**
	 * 終了ボタン実行時の処理
	 * アプリケーションを終了させる。
	 */
	public void exitButtonClick(View target){
		finish();
	}
}
