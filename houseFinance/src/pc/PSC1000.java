package pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import bc.connectToDB;
import bc.financeProcess;
import bc.uploadFinanceFile;

/*
 	String filePaht = "C:\\Users\\14z970\\Desktop\\설치파일\\";
	String fileName = "서버개발_사전과제3_주택금융신용보증_금융기관별_공급현황.csv";
	
	위 파일경로의 파일을 읽어서 shinyj DB의 finance DB 에 저장 하는 API
 */
public class PSC1000 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		String resultStr = null;
		uploadFinanceFile upFile = new uploadFinanceFile();
		resultStr = upFile.uploadToTable();
		System.out.println(resultStr);
		
	}

}
