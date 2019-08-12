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
		
		//���� ��� ����
		String filePaht = "C:\\Users\\14z970\\Desktop\\��ġ����\\";
		String fileName = "��������_��������3_���ñ����ſ뺸��_���������_������Ȳ.csv";
		
		//���۸��� �� �������� ����
		BufferedReader br = null;
		String readLine = "";
		
		//DB ���� ���� ����
		Connection conn = null;
		Statement stmt = null;
		connectToDB connDB= new connectToDB();
		
		//���� ���� ���� ����
		financeSQL SQL = new financeSQL();
		
		//ó�� �Ǽ� ���� ����
		int iCount = 0;
		
		//retrunString
		String retrunStr = null;
		
		//DB ����
		conn = connDB.getConn(conn);
		stmt = connDB.getStmt(conn, stmt);
		
		//�ĸ� �б�
		br =  new BufferedReader( new InputStreamReader(new FileInputStream(filePaht+fileName), "euc-kr"));
			
		//���� ���(���� ����Ʈ)
		readLine = br.readLine();
		// 0 : ��
		// 1 : ��
		// 2 ~ bankList.length -1 : �����
		String bankList[] = readLine.split(",");
			
		//���� ���
		while((readLine = br.readLine()) != null) {
				
			String readData[] = readLine.split(","); // �����ڷ� ������ �и�
				
			financeModel fm = new financeModel();
			//�� ��
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
		
		//DB ���� ����
		connDB.closeStmt(stmt);
		connDB.closeConn(conn);
	
		//System.out.println("���� To DB �Ϸ�");
		//System.out.println("ó�� �Ǽ� :"+iCount);
		
		return retrunStr = "���� To DB �Ϸ� \n ó�� �Ǽ� : "+iCount;
		
	}
}
