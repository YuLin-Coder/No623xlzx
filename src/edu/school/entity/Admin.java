package edu.school.entity;

//管理员
public class Admin {
   
	private Integer id;    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickname;
	}

	public Admin(Integer id, String username, String pwd, String nickname) {
		super();
		this.id = id;
		this.username = username;
		this.pwd = pwd;
		this.nickname = nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	private String username;
    private String pwd;
    private String nickname;
	
	public Admin() {
		super();
	}
    
}
