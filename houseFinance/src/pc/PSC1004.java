package pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import bc.connectToDB;
import bc.financeProcess;
import bc.uploadFinanceFile;

/*
	�Է¹��� ��ü�⵵(2005 ~ 2017)���� �����ݾ� ��� �߿��� ���� ���� �ݾװ� ū �ݾ��� ����ϴ� API
*/
public class PSC1004 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// TODO Auto-generated method stub
		
		String input = "";
		String resultStr = null;
		
		System.out.print("�����  :");
		//���۸��� ���� ����
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		//�ܼ� �Է�
		input = br.readLine();
					
		financeProcess fList = new financeProcess();
		resultStr = fList.getMaxMinAvg(input);
		System.out.println(resultStr);
	}

}
