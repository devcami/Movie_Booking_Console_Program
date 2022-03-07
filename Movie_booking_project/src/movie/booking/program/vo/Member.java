package movie.booking.program.vo;


/**
 *	회원 번호, ID, Password, 이름
 *
 */
public class Member {
	
	private int memberNo;
	private String id;
	private String password;
	private String name;
	
	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void printInfo() {
		System.out.println("회원번호 : " + memberNo);
		System.out.println("이름 : " + name);
		System.out.println("ID : " + id);
	}
	
	
	
	
}
