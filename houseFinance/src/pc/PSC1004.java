package pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import bc.connectToDB;
import bc.financeProcess;
import bc.uploadFinanceFile;

/*
	입력받은 전체년도(2005 ~ 2017)에서 지원금액 평균 중에서 가장 작은 금액과 큰 금액을 출력하는 API
*/
public class PSC1004 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		String input = "";
		String resultStr = null;
		
		System.out.print("은행명  :");
		//버퍼리더 변수 설정
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		//콘솔 입력
		input = br.readLine();
					
		financeProcess fList = new financeProcess();
		resultStr = fList.getMaxMinAvg(input);
		System.out.println(resultStr);
	}

}
