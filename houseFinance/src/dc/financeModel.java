package dc;

//shinyj.houseFinace ���̺� �÷� ����
public class financeModel {
	
	int year = 0;
	int month = 0;
	String bank = null;
	int money = 0;
	
	//��
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	//��
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	//����
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	//��
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
