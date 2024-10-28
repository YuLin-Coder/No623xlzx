package edu.school.entity;

import java.util.Date;

//评论
public class Comment {



	private Integer id;//主键
	private Integer sid;
	private Student student;
	private Integer teacher_id;
	private Teacher teacher;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Integer getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(Integer teacher_id) {
		this.teacher_id = teacher_id;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Integer getTiezi_id() {
		return tiezi_id;
	}

	public void setTiezi_id(Integer tiezi_id) {
		this.tiezi_id = tiezi_id;
	}

	public Tiezi getTiezi() {
		return tiezi;
	}

	public void setTiezi(Tiezi tiezi) {
		this.tiezi = tiezi;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}



	private Integer tiezi_id;

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	private Tiezi tiezi ;
	private Integer flag;//评论对象
	private String content;//
	private Date create_time;
	public Comment() {
		super();
	}
    
}
