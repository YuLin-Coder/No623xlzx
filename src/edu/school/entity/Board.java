package edu.school.entity;

//公告栏
public class Board {

	private Integer id;//主键

	public Board(Integer id, String title, String content, String fbsj, String editor) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.fbsj = fbsj;
		this.editor = editor;
	}

	private String title;//登录密码
	private String content;//教师姓名

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFbsj() {
		return fbsj;
	}

	public void setFbsj(String fbsj) {
		this.fbsj = fbsj;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	private String fbsj;//性别
	private String editor;//编辑者
	public Board() {
		super();
	}
    
}
