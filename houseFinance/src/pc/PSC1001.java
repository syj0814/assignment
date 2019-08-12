package pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import bc.connectToDB;
import bc.financeProcess;
import bc.uploadFinanceFile;

/*
	shinyj DB의 finance Table에 저장된 은행명 출력 하는 API
*/
public class PSC1001 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		String resultStr = null;
		financeProcess fList = new financeProcess();
		resultStr = fList.getBankList();
		System.out.println(resultStr);
		
	}

}
