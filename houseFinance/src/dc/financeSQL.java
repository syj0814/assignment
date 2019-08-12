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
				
		//쿼리 수행
		String sql;
		sql = "SELECT * FROM shinyj.finance where year = "+year+" and month ="+month+" and bank =\'"+bank+"\'" ;
		ResultSet rs = stmt.executeQuery(sql);
			
		//쿼리 수행 내역 출력
		while(rs.next()){
			fm.setYear(Integer.parseInt(rs.getString("year")));
			fm.setMonth(Integer.parseInt(rs.getString("month")));
			fm.setBank(rs.getString("bank"));
			fm.setMoney(Integer.parseInt(rs.getString("money")));
		}
			
		if(fm.getBank() == null) {
			return null;
		}
			
		//DB 연결 종료
		rs.close();
	
		return this.fm;
	}
	
	//finance Table 에 데이터 inset
	public void insert(Statement stmt, int year, int month, String bank, int money) throws SQLException {

		//쿼리 수행
		String sql;
		sql = "insert into shinyj.finance value("+year+", "+month+", \'"+bank+"\',"+money+")" ;
		stmt.executeUpdate(sql);
	}
	
	//년도별 금액 총합
	public ResultSet selectFinanceTotal(Statement stmt) throws SQLException {
		//쿼리 수행
		String sql;
		sql = "select finance.year, finance.bank, sum(finance.money) as sumMoney from  shinyj.finance group by finance.year" ;
		ResultSet rs = stmt.executeQuery(sql);
		
		//rs.close();
		return rs;
	}
	
	//년도별 은행별 금액 총합
	public ResultSet selectFinanceDetail(Statement stmt, int year) throws SQLException {
		//쿼리 수행
		String sql;
		sql = "select finance.year, finance.bank, sum(finance.money) as sumMoney from  shinyj.finance where finance.year = "+year+" group by finance.year, finance.bank" ;
		ResultSet rs = stmt.executeQuery(sql);
		
		//rs.close();
		return rs;
	}
	
	//Table 내 전체 은행명 조회 쿼리
	public ResultSet selectFinanceList(Statement stmt) throws SQLException {
		//쿼리 수행
		String sql;
		sql = "select distinct(finance.bank) from shinyj.finance";
		ResultSet rs = stmt.executeQuery(sql);
		
		//rs.close();
		return rs;
	}
	
	//Table 내 입력받은 은행명 존재 여부 확인
	public boolean isFinanceNm(Statement stmt, String bank) throws SQLException {
		//쿼리 수행
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
	
	//지원금액 합계 많은 은행과 년도 조회 쿼리 
	public ResultSet maxYearBank(Statement stmt) throws SQLException {
		//쿼리 수행
		String sql;
		sql = "select  finance.year, finance.bank, sum(finance.money) as sumMoney from shinyj.finance group by finance.year, finance.bank order by 3 desc limit 1";
		ResultSet rs = stmt.executeQuery(sql);
		
		//rs.close();
		return rs;
	}
	
	//입력받은 은행의 지원금액 평균 값이 가장 큰 년도/금액 출력
	public ResultSet maxAvgMoney(Statement stmt, String bank) throws SQLException {
		//쿼리 수행
		String sql;
		sql = "select finance.year, sum(money)/12 as avgMonth from shinyj.finance where finance.bank = \'"+bank+"\' group by finance.year order by 2 desc limit 1";;
		ResultSet rs = stmt.executeQuery(sql);
		
		//rs.close();
		return rs;
	}
	
	//입력받은 은행의 지원금액 평균 값이 가장 적은 년도/금액 출력
	public ResultSet minAvgMoney(Statement stmt, String bank) throws SQLException {
		//쿼리 수행
		String sql;
		sql = "select finance.year, sum(money)/12 as avgMonth from shinyj.finance where finance.bank = \'"+bank+"\' group by finance.year order by 2 asc limit 1";
		ResultSet rs = stmt.executeQuery(sql);
		
		//rs.close();
		return rs;
	}
	
}
