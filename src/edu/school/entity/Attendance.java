package edu.school.entity;

import java.util.Date;

//公告栏
public class Attendance {

	private Integer id;//主键

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getT_id() {
		return t_id;
	}

	public void setT_id(Integer t_id) {
		this.t_id = t_id;
	}

	public Attendance(Integer id, Integer t_id, Integer status, Date create_time) {
		this.id = id;
		this.t_id = t_id;
		this.status = status;
		this.create_time = create_time;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	private Integer t_id;//教师
	private Integer status;//
	private Date create_time;//打卡时间

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	private Teacher teacher;
	public Attendance() {
		super();
	}
    
}
