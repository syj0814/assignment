package dc;

//shinyj.houseFinace 테이블 컬럼 정의
public class financeModel {
	
	int year = 0;
	int month = 0;
	String bank = null;
	int money = 0;
	
	//년
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	//월
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	//은행
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	//돈
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	public String getModel() {
		return year+" "+month+" "+bank+" "+money;
	}

}
