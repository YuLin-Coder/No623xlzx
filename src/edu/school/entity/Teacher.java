package edu.school.entity;

//心理老师
public class Teacher {

	private Integer id;//主键
	private String tno;//教师工号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	private String pwd;//登录密码
	private String name;//教师姓名
	private String sex;//性别
	private String major;//专业技能
    private String detail;//详细介绍
	private  String phone;//联系手机号



	public Teacher(Integer id, String tno, String pwd, String name, String sex, String major, String detail, String phone, String imgUrl) {
		this.id = id;
		this.tno = tno;
		this.pwd = pwd;
		this.name = name;
		this.sex = sex;
		this.major = major;
		this.detail = detail;
		this.phone = phone;
		this.imgUrl = imgUrl;

	}

	@Override
	public String toString() {
		return "Teacher{" +
				"id=" + id +
				", tno='" + tno + '\'' +
				", pwd='" + pwd + '\'' +
				", name='" + name + '\'' +
				", sex='" + sex + '\'' +
				", major='" + major + '\'' +
				", detail='" + detail + '\'' +
				", phone='" + phone + '\'' +
				", imgUrl='" + imgUrl + '\'' +
				'}';
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	private String imgUrl;//头像
	public Teacher() {
		super();
	}
    
}
