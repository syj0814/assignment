package pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import bc.connectToDB;
import bc.financeProcess;
import bc.uploadFinanceFile;

/*
	�� �⵵�� �� ����� ��ü �����ݾ� �߿��� ���� ū �ݾ��� ������� ����ϴ� API
*/
public class PSC1003 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		String resultStr = null;
		financeProcess fList = new financeProcess();
		resultStr = fList.getMaxYearBank();
		System.out.println(resultStr);	
		
	}

}
