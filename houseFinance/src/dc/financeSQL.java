package dc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bc.connectToDB;

public class financeSQL {
	
	financeModel fm = new financeModel();
	connectToDB connDB= new connectToDB();
	
	// finace Table select
	public financeModel select(Statement stmt, int year, int month, String bank) throws SQLException {
				
		//���� ����
		String sql;
		sql = "SELECT * FROM shinyj.finance where year = "+year+" and month ="+month+" and bank =\'"+bank+"\'" ;
		ResultSet rs = stmt.executeQuery(sql);
			
		//���� ���� ���� ���
		while(rs.next()){
			fm.setYear(Integer.parseInt(rs.getString("year")));
			fm.setMonth(Integer.parseInt(rs.getString("month")));
			fm.setBank(rs.getString("bank"));
			fm.setMoney(Integer.parseInt(rs.getString("money")));
		}
			
		if(fm.getBank() == null) {
			return null;
		}
			
		//DB ���� ����
		rs.close();
	
		return this.fm;
	}
	
	//finance Table �� ������ inset
	public void insert(Statement stmt, int year, int month, String bank, int money) throws SQLException {

		//���� ����
		String sql;
		sql = "insert into shinyj.finance value("+year+", "+month+", \'"+bank+"\',"+money+")" ;
		stmt.executeUpdate(sql);
	}
	
	//�⵵�� �ݾ� ����
	public ResultSet selectFinanceTotal(Statement stmt) throws SQLException {
		//���� ����
		String sql;
		sql = "select finance.year, finance.bank, sum(finance.money) as sumMoney from  shinyj.finance group by finance.year" ;
		ResultSet rs = stmt.executeQuery(sql);
		
		//rs.close();
		return rs;
	}
	
	//�⵵�� ���ະ �ݾ� ����
	public ResultSet selectFinanceDetail(Statement stmt, int year) throws SQLException {
		//���� ����
		String sql;
		sql = "select finance.year, finance.bank, sum(finance.money) as sumMoney from  shinyj.finance where finance.year = "+year+" group by finance.year, finance.bank" ;
		ResultSet rs = stmt.executeQuery(sql);
		
		//rs.close();
		return rs;
	}
	
	//Table �� ��ü ����� ��ȸ ����
	public ResultSet selectFinanceList(Statement stmt) throws SQLException {
		//���� ����
		String sql;
		sql = "select distinct(finance.bank) from shinyj.finance";
		ResultSet rs = stmt.executeQuery(sql);
		
		//rs.close();
		return rs;
	}
	
	//Table �� �Է¹��� ����� ���� ���� Ȯ��
	public boolean isFinanceNm(Statement stmt, String bank) throws SQLException {
		//���� ����
		String sql;
		int Check = 0;
		sql = "select count(*) as count from shinyj.finance where finance.bank =\'"+bank+"\'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		Check = Integer.parseInt(rs.getString("count"));
		
		if(Check == 0) {
			return false;
		}
		else {
			return true;
		}
		//rs.close();
	}
	
	//�����ݾ� �հ� ���� ����� �⵵ ��ȸ ���� 
	public ResultSet maxYearBank(Statement stmt) throws SQLException {
		//���� ����
		String sql;
		sql = "select  finance.year, finance.bank, sum(finance.money) as sumMoney from shinyj.finance group by finance.year, finance.bank order by 3 desc limit 1";
		ResultSet rs = stmt.executeQuery(sql);
		
		//rs.close();
		return rs;
	}
	
	//�Է¹��� ������ �����ݾ� ��� ���� ���� ū �⵵/�ݾ� ���
	public ResultSet maxAvgMoney(Statement stmt, String bank) throws SQLException {
		//���� ����
		String sql;
		sql = "select finance.year, sum(money)/12 as avgMonth from shinyj.finance where finance.bank = \'"+bank+"\' group by finance.year order by 2 desc limit 1";;
		ResultSet rs = stmt.executeQuery(sql);
		
		//rs.close();
		return rs;
	}
	
	//�Է¹��� ������ �����ݾ� ��� ���� ���� ���� �⵵/�ݾ� ���
	public ResultSet minAvgMoney(Statement stmt, String bank) throws SQLException {
		//���� ����
		String sql;
		sql = "select finance.year, sum(money)/12 as avgMonth from shinyj.finance where finance.bank = \'"+bank+"\' group by finance.year order by 2 asc limit 1";
		ResultSet rs = stmt.executeQuery(sql);
		
		//rs.close();
		return rs;
	}
	
}
