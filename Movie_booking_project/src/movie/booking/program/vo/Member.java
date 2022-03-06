package movie.booking.program.vo;


/**
 *	ID, Password, 총 회원 수
 *
 */
public class Member {
	

	private String id;
	private String password;
	
	public Member() {
	}

	public Member(String id, String password) {
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	//로그인확인
	public boolean isLogin(String id, String password) {
		if(this.id.equals(id) && this.password.equals(password)) {
			return true;
		} else return false;
	}
	
	
	
}
