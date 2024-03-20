package entites;

import java.util.Objects;

public class Ingredient {
	private int id;
	private String nom;
	private float stock;
	private float stock_min;
	
	public Ingredient() {
		
	}
	public Ingredient(int id, String nom, float stock, float stock_min) {
		setId(id);
		setNom(nom);
		setStock(stock);
		setStock_min(stock_min);
	}
	public Ingredient(String nom, float stock, float stock_min) {
		setNom(nom);
		setStock(stock);
		setStock_min(stock_min);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public float getStock() {
		return stock;
	}
	public void setStock(float stock) {
		this.stock = stock;
	}
	public float getStock_min() {
		return stock_min;
	}
	public void setStock_min(float stock_min) {
		this.stock_min = stock_min;
	}
	
	@Override
	public String toString() {
		return nom;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, nom, stock, stock_min);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		return id == other.id && Objects.equals(nom, other.nom)
				&& Float.floatToIntBits(stock) == Float.floatToIntBits(other.stock)
				&& Float.floatToIntBits(stock_min) == Float.floatToIntBits(other.stock_min);
	}
}
