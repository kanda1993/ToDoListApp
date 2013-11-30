package com.example.todolistapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

/**
 * ���C�����j���[��Activity
 * ���j���[
 * ToDo���X�g�֑J��
 * �V�KToDo���X�g�쐬�֑J��
 * ����ToDo���X�g�֑J��
 * App�I���{�^��
 * @author y.kanda
 */
public class MainActivity extends Activity {
	
	/**
	 * �����\��
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//���C�����j���[��view���Z�b�g
		setContentView(R.layout.activity_main);
	}
	
	/**
	 * app�I�����̏���(��ʊO����I�����ꂽ���p)
	 */
	@Override
	public void onDestroy(){
		super.onDestroy();
	}
	
	// -----------�ȍ~�{�^�����s���̏���------------
	
	/**
	 * ToDo���X�g�{�^�����s���̏���
	 * ToDoList��ʂ֑J�ځB
	 * @param target
	 */
	public void todoListButtonClick(View target){
		Intent intent = new Intent(MainActivity.this,ToDoListActivity.class);
		startActivity(intent);
	}
	
	/**
	 * ToDo�V�K�쐬�{�^�����s���̏���
	 * ToDo�V�K�쐬��ʂ֑J�ځB
	 * @param target
	 */
	public void createToDoButtonClick(View target){
		Intent intent = new Intent(MainActivity.this,CreateToDoActivity.class);
		startActivity(intent);
	}
	
	/**
	 * �I���{�^�����s���̏���
	 * �A�v���P�[�V�������I��������B
	 */
	public void exitButtonClick(View target){
		finish();
	}
}
