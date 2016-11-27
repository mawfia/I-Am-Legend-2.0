package entities;

public class CartItems {
	private int id;
	private int cartId;
	private int inventoryItemId;
	public int getId() {
		return id;
	}
	public int getCartId() {
		return cartId;
	}
	public int getInventoryItemId() {
		return inventoryItemId;
	}
	@Override
	public String toString() {
		return "CartItems [id=" + id + ", cartId=" + cartId + ", inventoryItemId=" + inventoryItemId + "]";
	}
	

}
