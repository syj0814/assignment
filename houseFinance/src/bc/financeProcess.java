package bc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import dc.financeSQL;

public class financeProcess {
	
	//���ñ��� ���ޱ��(����) ��� ��� 
	public String getBankList() throws ClassNotFoundException, SQLException {
		//DB ���� ���� ����
		Connection conn = null;
		Statement stmt = null;
		connectToDB connDB= new connectToDB();
		
		//���� ���� ���� ����
		financeSQL SQL = new financeSQL();
		ResultSet rs = null;
		
		//��� ���� ����
		String bankName = null;
		
		//retrunString
		String retrunStr = null;
		
		//DB ����
		conn = connDB.getConn(conn);
		stmt = connDB.getStmt(conn, stmt);
		
		rs = SQL.selectFinanceList(stmt);
		
		//���� ���� ���� ���
		//System.out.print("[������:{");
		retrunStr = "[������:{";
		while(rs.next()){
			bankName = rs.getString("bank");
			//System.out.print("\""+bankName+"\"");
			retrunStr += "\""+bankName+"\"";
			if(!rs.isLast()) {
				//System.out.print(",");
				retrunStr += ",";
			}
		}
		//System.out.println("}]");
		retrunStr += "}]";
		
		rs.close();
		//DB ���� ����
		connDB.closeStmt(stmt);
		connDB.closeConn(conn);
		
		return retrunStr;
	}
	
	//�� �⵵�� ������� ��ü �����ݾ� �߿��� ���� ū �ݾ��� ����� ���
	public String getMaxYearBank() throws ClassNotFoundException, SQLException {
		//DB ���� ���� ����
		Connection conn = null;
		Statement stmt = null;
		connectToDB connDB= new connectToDB();
		
		//���� ���� ���� ����
		financeSQL SQL = new financeSQL();
		ResultSet rs = null;
		
		//��� ���� ����
		int year = 0;
		String bankName = null;
		
		//retrunString
		String retrunStr = null;
		
		//DB ����
		conn = connDB.getConn(conn);
		stmt = connDB.getStmt(conn, stmt);
		
		rs = SQL.maxYearBank(stmt);
		
		//���� ���� ���� ���
		//System.out.println("{");
		retrunStr = "{\n";
		while(rs.next()){
			
			year = Integer.parseInt(rs.getString("year"));
			bankName = rs.getString("bank");
			//System.out.println("	\"year\":"+year+"\",");
			retrunStr += "	\"year\":"+year+"\",\n";
			//System.out.println("	\"bank\":\""+bankName+"\"");
			retrunStr += "	\"bank\":\""+bankName+"\"\n";
		}
		//System.out.println("}");
		retrunStr += "}\n";
		
		rs.close();
		//DB ���� ����
		connDB.closeStmt(stmt);
		connDB.closeConn(conn);
		
		return retrunStr;
	}
	
	//��ü �⵵ �Է¹��� ������ �����ݾ� ��� �߿��� ����ū �ݾװ� ���� �ݾ� ���
	public String getMaxMinAvg(String inBank) throws ClassNotFoundException, SQLException, IOException {
		//DB ���� ���� ����
		Connection conn = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		connectToDB connDB= new connectToDB();
				
		//���� ���� ���� ����
		financeSQL SQL = new financeSQL();
		ResultSet max_rs = null;
		ResultSet min_rs = null;
		boolean financeNm = false;
		
		//�Է� ���� ����
		String input = null;
		//��� ���� ����
		int year = 0;
		double avgMoney = 0;
		
		//retrunString
		String retrunStr = null;
				
		//DB ����
		conn = connDB.getConn(conn);
		stmt1 = connDB.getStmt(conn, stmt1);
		stmt2 = connDB.getStmt(conn, stmt2);
		stmt3 = connDB.getStmt(conn, stmt3);
		
		financeNm = SQL.isFinanceNm(stmt1, inBank);
		
		//finance Talbel �� ���� ����� �Է� �� �Է� ���� return
		if(!financeNm) {
			retrunStr = "�Է� ����� ����";
			return retrunStr;
		}
				
		//���� ���� ���� ���
		//System.out.println("{");
		retrunStr = "{\n";
		//System.out.println("	\"bank\":\""+inBank+"\"");
		retrunStr += "	\"bank\":\""+inBank+"\"\n";
		//System.out.println("	\"bank\":\""+inBank+"\"");
		retrunStr += "	\"Support_amount\"\n	[\n";
		
		max_rs = SQL.maxAvgMoney(stmt1, inBank);
		while(max_rs.next()){		
			year = Integer.parseInt(max_rs.getString("year"));
			avgMoney = Double.parseDouble(max_rs.getString("avgMonth"));
			avgMoney = Math.ceil(avgMoney);
			//System.out.print("	\"year\":"+year+"\",");
			retrunStr += "		{\"year\":"+year+",";
			//System.out.println("	\"amount\":\""+(int)avgMoney+"\"");
			retrunStr += "\"amount\":"+(int)avgMoney+"},\n";
		}
		//System.out.println("}");
		//retrunStr += "}\n";
		
		min_rs = SQL.minAvgMoney(stmt1, inBank);
		while(min_rs.next()){
			year = Integer.parseInt(min_rs.getString("year"));
			avgMoney = Double.parseDouble(min_rs.getString("avgMonth"));
			avgMoney = Math.ceil(avgMoney);
			//System.out.print("	\"year\":"+year+"\",");
			retrunStr += "		{\"year\":"+year+",";
			//System.out.println("	\"amount\":\""+(int)avgMoney+"\"");
			retrunStr += "\"amount\":"+(int)avgMoney+"}\n";
		}
		//System.out.println("}");
		retrunStr += "	]	\n}\n";
		
		max_rs.close();
		min_rs.close();
		//DB ���� ����
		connDB.closeStmt(stmt1);
		connDB.closeStmt(stmt1);
		connDB.closeConn(conn);
		
		return retrunStr;
	}
	
	//�⵵�� �� ��������� �����ݾ� �հ踦 ���
	public String getSummury() throws ClassNotFoundException, SQLException{
		
		//DB ���� ���� ����
		Connection conn = null;
		Statement stmt1 = null;
		Statement stmt2 = null;
		connectToDB connDB= new connectToDB();
				
		//���� ���� ���� ����
		financeSQL SQL = new financeSQL();
		ResultSet total_rs = null;
		ResultSet detail_rs = null;
		
		//DB ����
		conn = connDB.getConn(conn);
		stmt1 = connDB.getStmt(conn, stmt1);
		stmt2 = connDB.getStmt(conn, stmt2);
		
		//��� ���� ����
		int year = 0;
		int total_amount = 0;
		int detail_amount = 0;
		String bankName = null;
		
		//retrunString
		String retrunStr = null;
		
		total_rs = SQL.selectFinanceTotal(stmt1);
		
		//System.out.println("\"name\":\"���ñ��� ������Ȳ\"");
		//System.out.println("[");
		retrunStr = "\"name\":\"���ñ��� ������Ȳ\"\n[\n";
		//���� ���� ���� ���
		while(total_rs.next()){
			
			year = Integer.parseInt(total_rs.getString("year"));
			total_amount = Integer.parseInt(total_rs.getString("sumMoney"));
			
			//System.out.println("	{");
			retrunStr += "	{\n";
			//System.out.println("		\"year\":\""+year+"��\",");
			retrunStr += "		\"year\":\""+year+"��\",\n";
			//System.out.println("		\"total_amount\":"+total_amount+",");
			retrunStr += "		\"total_amount\":"+total_amount+",\n";
			
			//System.out.println("		\"detail_amount\":");
			retrunStr += "		\"detail_amount\":\n";
			//System.out.print("			{");
			retrunStr += "			{";
			
			detail_rs = SQL.selectFinanceDetail(stmt2, year);
			while(detail_rs.next()) {
				bankName = detail_rs.getString("bank");
				detail_amount = Integer.parseInt(detail_rs.getString("sumMoney"));
				//System.out.print("\""+bankName+"\":");
				//System.out.print(detail_amount);
				retrunStr += "\""+bankName+"\":"+detail_amount;
				if(!detail_rs.isLast()) {
					//System.out.print(",");
					retrunStr += ",";
				}
			}
			//System.out.println("}");
			retrunStr += "}\n";
			//System.out.println("	}");
			retrunStr += "	}\n";
			detail_rs.close();
			
		}
		
		//System.out.println("]");
		retrunStr += "]\n";
		total_rs.close();
		//DB ���� ����
		connDB.closeStmt(stmt2);
		connDB.closeStmt(stmt1);
		connDB.closeConn(conn);
		
		return retrunStr;
	}
	
	
}
