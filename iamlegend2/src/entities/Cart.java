package entities;

public class Cart {
	private int id;
	private boolean active;
	private int customerAccountNumber;
	private int survivalScore;
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getSurvivalScore() {
		return survivalScore;
	}
	public void setSurvivalScore(int survivalScore) {
		this.survivalScore = survivalScore;
	}
	public int getId() {
		return id;
	}
	public int getCustomerAccountNumber() {
		return customerAccountNumber;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", active=" + active + ", customerAccountNumber=" + customerAccountNumber
				+ ", survivalScore=" + survivalScore + "]";
	}
}
