package bc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dc.financeModel;
import dc.financeSQL;

public class uploadFinanceFile {
	
	public String uploadToTable() throws ClassNotFoundException, SQLException, IOException {
		
		//파일 경로 세팅
		String filePaht = "C:\\Users\\14z970\\Desktop\\설치파일\\";
		String fileName = "서버개발_사전과제3_주택금융신용보증_금융기관별_공급현황.csv";
		
		//버퍼리더 및 지역변수 설정
		BufferedReader br = null;
		String readLine = "";
		
		//DB 연결 변수 설정
		Connection conn = null;
		Statement stmt = null;
		connectToDB connDB= new connectToDB();
		
		//쿼리 수행 변수 설정
		financeSQL SQL = new financeSQL();
		
		//처리 건수 변수 설정
		int iCount = 0;
		
		//retrunString
		String retrunStr = null;
		
		//DB 연결
		conn = connDB.getConn(conn);
		stmt = connDB.getStmt(conn, stmt);
		
		//파링 읽기
		br =  new BufferedReader( new InputStreamReader(new FileInputStream(filePaht+fileName), "euc-kr"));
			
		//파일 헤더(은행 리스트)
		readLine = br.readLine();
		// 0 : 년
		// 1 : 월
		// 2 ~ bankList.length -1 : 은행명
		String bankList[] = readLine.split(",");
			
		//파일 출력
		while((readLine = br.readLine()) != null) {
				
			String readData[] = readLine.split(","); // 구분자로 데이터 분리
				
			financeModel fm = new financeModel();
			//년 월
			fm.setYear(Integer.parseInt(readData[0]));
			fm.setMonth(Integer.parseInt(readData[1]));
			
			for(int i=2 ; i < readData.length ; i++) {
			//for(int i=2 ; i < 3 ; i++) {
				fm.setBank(bankList[i]);
				fm.setMoney(Integer.parseInt(readData[i]));
				
				//insert to Table
				if(SQL.select(stmt, fm.getYear(), fm.getMonth(), fm.getBank()) == null) {
					SQL.insert(stmt, fm.getYear(), fm.getMonth(), fm.getBank(), fm.getMoney());
					iCount++;
				}
			}
		}
		
		//DB 연결 해제
		connDB.closeStmt(stmt);
		connDB.closeConn(conn);
	
		//System.out.println("파일 To DB 완료");
		//System.out.println("처리 건수 :"+iCount);
		
		return retrunStr = "파일 To DB 완료 \n 처리 건수 : "+iCount;
		
	}
}
