package movie.booking.program.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import movie.booking.program.vo.Member;
import movie.booking.program.vo.Movie;
import movie.booking.program.vo.Seats;

public class FileUtil {
	
    private String filePath = "/Users/camilee/Documents/dev/";
    private String fileName = "membersInfo.txt";
    List<Member> nowId = new ArrayList<>();
	
	/**
	 * 회원정보 저장 파일
	 */
	public static List<Member> readFile(File file)throws IOException{
		FileReader fr = null;
		List<Member> memberList = new ArrayList<>();
		
		try {
			fr = new FileReader(file);
			//파일에서 읽어온 정보를 List에 저장
			memberList = readReader(fr);
		} finally {
			if(fr != null)
				fr.close();
		}
		return memberList;
	}
	/**
	 * 영화 내역 저장 List
	 */
	public static void writeMovieFile(String dir, String name, Map<String, Movie> movieMap, String key) throws IOException{
		OutputStream os = null;
		try {
			File file = new File(dir);
			
			//저장할 디렉토리가 존재하지 않으면 생성
			if(!file.exists()) {
				file.mkdir();
			}
			
			File outFile = new File(dir,name);
			
			//파일에 String값 입력
			os = new BufferedOutputStream(new FileOutputStream(outFile));
			
			String writeString = 
					movieMap.keySet() + ","
					+ movieMap.get(key).getMovieName() + ","
					+ movieMap.get(key).getMovieTime() + ","
					+ movieMap.get(key).getTheater() + ","
					+ movieMap.get(key).getRoom() + ","
					+ movieMap.get(key).getAgeLimit() + "\n";
			for(int i = 0; i < movieMap.size(); i++) {
				//저장한 string -> byte배열
				byte[] byteArr = writeString.getBytes();
				
				os.write(byteArr);
			}
		} catch(IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if(os != null) os.close();
			} catch(Exception e) {
			}
		}
	}

	/**
	 * 회원정보 저장 List 
	 */
	public static void writeFile(String dir, String name, List<Member> memberList) throws IOException{
		OutputStream os = null;
		try {
			File file = new File(dir);
			
			//저장할 디렉토리가 존재하지 않으면 생성
			if(!file.exists()) {
				file.mkdir();
			}
			
			File outFile = new File(dir,name);
			
			//파일에 String값 입력
			os = new BufferedOutputStream(new FileOutputStream(outFile));
			for(int i = 0; i < memberList.size(); i++) {
				String writeString = memberList.get(i).getMemberNo() + ","
								   + memberList.get(i).getName() + ","
								   + memberList.get(i).getId() + ","
								   + memberList.get(i).getPassword() + "\n";
				//저장한 string -> byte배열
				byte[] byteArr = writeString.getBytes();
				
				os.write(byteArr);
			}
		} catch(IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				if(os != null) os.close();
			} catch(Exception e) {
			}
		}
	}
	
	
	private static List<Member> readReader(Reader input) throws IOException {
		try {
			BufferedReader br = new BufferedReader(input);
			String data;
			
			List<Member> memberList = new ArrayList<>();
			//파일 한줄씩 읽기
			while((data = br.readLine()) != null) {
				//","로 split하여 배열에 저장
				String[] writeString = data.split(",");
				
				//모든 정보가 저장되어 있지 않다면 skip(No, Id, Pw)
				if(writeString.length != 4)continue;
				
				Member member = new Member();
				//각 개인정보 저장
				member.setMemberNo(Integer.parseInt(writeString[0]));
				member.setName(writeString[1]);
				member.setId(writeString[2]);
				member.setPassword(writeString[3]);
				
				//리스트에 객체 추가
				memberList.add(member);
				
			}
			return memberList;
		} finally {
			input.close();
		}
		
	}
	
	public static void writeSeat(String dir, String name, String[][] s) throws IOException {
		
		File file = new File(dir, name);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
			for(int i = 0; i < 6; i++) {
				for(int j = 0; j < 6; j++) {
					bw.write(s[i][j] + ",");
				}
					bw.write("\n");
			}

		}
	}
	
    public static void readSeat(File file, Seats s) throws IOException{
//      List<Member> memberList = new ArrayList<>();
      BufferedReader br = new BufferedReader(new FileReader(file));
      try {
          //파일에서 읽어온 정보를 배열에 저장
          String data;
          char col = 'A';
          
          String[] writeString = new String[6];
          String[][] seats = s.getSeats();
          
          int i = 0;
          while((data = br.readLine()) != null) {
              
              writeString = data.split(",");
              System.out.print("     " +(char)(col++) + "열 ");

              for(int j = 0; j < 6; j++) {
                  //writeStirng[0,1,2,3,4,5] = [◼☐☐☐☐☐]
                  System.out.print(" " + writeString[j]);
                  seats[i][j] = writeString[j];
              }
              i++;
              System.out.println();
              
          }
          
              
      } 
      finally {
          br.close();
      }
      
      
  }
    
    public static List<String> readDelete(File file) throws IOException {
        
        List<String> movieList = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String data;
            String str ="";
            int index = 1;
            
            while((data = br.readLine()) != null) {
            	str += (data + "\n");
            	if(index % 3 == 0) {
            		movieList.add(str);
            		str = "";
            	}
            	index++;
            }
            
        }
        
        return movieList;
        
    }
    
    public String setFileName(int memberNo) {
    	
		String name = "";
		
		try {
		nowId = readFile(new File(filePath, fileName));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	
		for(int i = 0; i < nowId.size(); i++) {
			if(nowId.get(i).getMemberNo() == memberNo) {
				name = ("/Users/camilee/Documents/dev/" + nowId.get(i).getId() + "_movieList.txt");
				break;
				}
			}
	
		return name;
    }
    
    public void finalwriteSeat(Movie selectedMovie, Seats s) {
    	
		try {
			writeSeat("/Users/camilee/Documents/dev/",
					"seats" + selectedMovie.getRoom() + ".txt"
					,s.getSeats());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
    
    public int indexmaker(int memberNo) {
    	int index = 1;
    	String name = setFileName(memberNo);
    	File f = new File(name);
    	int i = 1;
    	try(BufferedReader br = new BufferedReader(new FileReader(f))){
    		String data = "";
    		String str = "";
            while((data = br.readLine()) != null) {
            	str += (data + "\n");
            	if(i % 3 == 0) {
            		index++;
            	}
            	i++;
            	
            }
            } catch (FileNotFoundException e) {
            	e.printStackTrace();
            } catch (IOException e) {
            	e.printStackTrace();
            }
    	
    	return index;
    	
    }
//    public int writeIndex()
	

}