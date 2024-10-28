package edu.school.entity;
//学生
public class Student {
   
	public Student() {
		super();
	}
	public Student(Integer id, String stuno, String realname, String pwd, String major, String sex, String bj,String photo,
			String createDate) {
		super();
		this.id = id;
		this.stuno = stuno;
		this.realname = realname;
		this.pwd = pwd;
		this.major = major;
		this.sex = sex;
		this.bj = bj;
		this.photo=photo;
		this.createDate = createDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStuno() {
		return stuno;
	}
	public void setStuno(String stuno) {
		this.stuno = stuno;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBj() {
		return bj;
	}
	public void setBj(String bj) {
		this.bj = bj;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	private Integer id;
	private String stuno;
	private String realname;//学生姓名
    private String pwd;//密码
    private String major;//专业
    private String  sex;//性别
    private String  bj;//班级

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	private String photo;
    private String  createDate;//录入日期

}
