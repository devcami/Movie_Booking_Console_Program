package movie.booking.program.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import movie.booking.program.vo.Member;

public class FileUtil {
	
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
			
			//파일이 존재한다면 삭제
			if(outFile.exists()) {
				outFile.delete();
			}
			
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

}
