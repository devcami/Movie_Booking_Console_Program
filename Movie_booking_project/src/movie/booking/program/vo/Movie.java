package movie.booking.program.vo;

/**
 *	영화제목, 영화시간, 극장, 관, 나이제한 
 *
 */
public class Movie {
	//Field
	private String movieName;
	private String theater;
	private int room;
	private int ageLimit;
	private String movieTime;
	private String[][] seats = new String[6][6];
	
	
	public Movie(String movieName, String theater, int room, int ageLimit, String movieTime) {
		this.movieName = movieName;
		this.theater = theater;
		this.room = room;
		this.ageLimit = ageLimit;
		this.movieTime = movieTime;
	}
	
	public Movie() {
	}



	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	
	public String getMovieTime() {
		return movieTime;
	}
	
	public void setMovieTime(String movieTime) {
		this.movieTime = movieTime;
	}


	public String getTheater() {
		return theater;
	}

	public void setTheater(String theater) {
		this.theater = theater;
	}

	public int getRoom() {
		return room;
	}

	public void setRoom(int room) {
		this.room = room;
	}

	public int getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(int ageLimit) {
		this.ageLimit = ageLimit;
	}

	@Override
	public String toString() {
		return theater + " " + room + "관 " + "<"+ movieName + ">" + " *" + ageLimit + "세 이상 관람 가능*"
			 + "\n영화 시간 : " + movieTime 
			 ; 
	}
	
	
	
	

}
