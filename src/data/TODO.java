package data;

import java.util.HashMap;
import java.util.Map;

import config.DataBaseConfig;

/**
 * TODO情報を格納するデータクラス。
 * Androidリスト表示用にmap形式に変換するメソッドあり
 * @author y.kanda
 */
public class TODO {
	
	/** ToDoユニークID */
	private int todoId;
	/** ToDoが所属するPJ */
	private String todoPjCode;
	
	/** ToDoタイトル */
	private String todoTitle;
	/** ToDoの内容　 */
	private String todoContent;
	/**　進捗率 */
	private int progress;
	
	/** 期限 */
	private String limitDate;
	/** 完了日 */
	private String completeDate;
	
	/**
	 * アンドロイド、リスト表示の仕様上Map型で渡す必要があるので変換を行う
	 * とりあえずToDoの内容を全て変換している。
	 * @return Map key:dbカラム名称 value:実値
	 */
	public Map<String,String> convertToMap(){
		Map<String,String> todoMap = new HashMap<String,String>();
		
		todoMap.put(DataBaseConfig.CLM_TODO_ID, Integer.toString(todoId));
		todoMap.put(DataBaseConfig.CLM_PJ_CODE, todoPjCode);
		
		todoMap.put(DataBaseConfig.CLM_TODO_TITLE,todoTitle);
		todoMap.put(DataBaseConfig.CLM_TODO,todoContent);
		
		todoMap.put(DataBaseConfig.CLM_PROGRESS,Integer.toString(progress));
		todoMap.put(DataBaseConfig.CLM_LIMIT_DATE,limitDate);
		todoMap.put(DataBaseConfig.CLM_LIMIT_DATE,completeDate);
		
		return todoMap;
	}
	
	
	/* 以降単純なgetter setter */
	
	public int getTodoId() {
		return todoId;
	}
	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}
	public String getTodoPjCode() {
		return todoPjCode;
	}
	public void setTodoPjCode(String todoPjCode) {
		this.todoPjCode = todoPjCode;
	}
	public String getTodoTitle() {
		return todoTitle;
	}
	public void setTodoTitle(String todoTitle) {
		this.todoTitle = todoTitle;
	}
	public String getTodoContent() {
		return todoContent;
	}
	public void setTodoContent(String todoContent) {
		this.todoContent = todoContent;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public String getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(String limitDate) {
		this.limitDate = limitDate;
	}
	public String getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}
	
}
