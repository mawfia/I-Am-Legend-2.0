package entities;

import java.util.Comparator;

public class Item implements Comparator<Item>{
	
	private String ID;
	private String description;
	private Float MSRP;
	private Float weight;
	private String image;
	
	public Item(){
	}
	
	public Item(String ID, String description, Float MSRP, Float weight){
		
		this.ID = ID;
		this.description = description;
		this.MSRP = MSRP;
		this.weight = weight;
		this.image = null;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getMSRP() {
		return MSRP;
	}
	public void setMSRP(float mSRP) {
		MSRP = mSRP;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override
	public int compare(Item i1, Item i2) {
		if( i1.ID.compareTo(i2.ID) == 0) 
			if(i1.MSRP.compareTo(i2.MSRP) == 0) return i1.weight.compareTo(i2.weight);
			else return i1.MSRP.compareTo(i2.MSRP);
		else return i1.ID.compareTo(i2.ID);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ID == null) ? 0 : ID.hashCode());
		result = prime * result + ((MSRP == null) ? 0 : MSRP.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (ID == null) {
			if (other.ID != null)
				return false;
		} else if (!ID.equals(other.ID))
			return false;
		if (MSRP == null) {
			if (other.MSRP != null)
				return false;
		} else if (!MSRP.equals(other.MSRP))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [ID=" + ID + ", description=" + description + ", MSRP=" + MSRP + ", weight=" + weight + ", image="
				+ image + "]";
	}

}
