package edu.school.entity;

//公告栏
public class Yyzx {
	public Integer getYid() {
		return yid;
	}

	public void setYid(Integer yid) {
		this.yid = yid;
	}

	private Integer yid;//主键
	private Integer s_id;//外键
	private Integer status;//状态 0表示，预约失败，1表示预约中，预约成功


	public Integer getS_id() {
		return s_id;
	}

	public Yyzx(Integer yid, Integer s_id, Integer status, String tname, String bz, String yysj) {
		this.yid = yid;
		this.s_id = s_id;
		this.status = status;
		this.tname = tname;
		this.bz = bz;
		this.yysj = yysj;
	}

	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getYysj() {
		return yysj;
	}

	public void setYysj(String yysj) {
		this.yysj = yysj;
	}

	private String tname;//教师名称
	private String bz;//备注
	private String yysj;//预约时间
	public Yyzx() {
		super();
	}
    
}
