package entites;

import java.util.Objects;

public class Tablee {
	private int id;
	private String designation;
	
	public Tablee() {
		
	}
	public Tablee(int id, String designation) {
		setId(id);
		setDesignation(designation);
	}
	public Tablee(String designation) {
		setDesignation(designation);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	@Override
	public String toString() {
		return designation;
	}
	@Override
	public int hashCode() {
		return Objects.hash(designation, id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablee other = (Tablee) obj;
		return Objects.equals(designation, other.designation) && id == other.id;
	}
	
}
