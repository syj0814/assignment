package pc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;

import bc.connectToDB;
import bc.financeProcess;
import bc.uploadFinanceFile;

/*
 	String filePaht = "C:\\Users\\14z970\\Desktop\\��ġ����\\";
	String fileName = "��������_��������3_���ñ����ſ뺸��_���������_������Ȳ.csv";
	
	�� ���ϰ���� ������ �о shinyj DB�� finance DB �� ���� �ϴ� API
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
