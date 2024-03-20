package entites;

import java.util.Objects;

public class Plat {
	private int id;
	private String nom;
	private float prix;
	
	public Plat() {
		
	}
	public Plat(int id, String nom, float prix) {
		setId(id);
		setNom(nom);
		setPrix(prix);
	}
	public Plat(String nom, float prix) {
		setNom(nom);
		setPrix(prix);
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
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	@Override
	public String toString() {
		return nom;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, nom, prix);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plat other = (Plat) obj;
		return id == other.id && Objects.equals(nom, other.nom)
				&& Float.floatToIntBits(prix) == Float.floatToIntBits(other.prix);
	}
	
	
}
