package entites;

import java.util.Objects;

public class Preparation {
	private int id;
	private int id_plat;
	private int id_ingredient;
	private float qte;
	
	public Preparation() {
		
	}
	public Preparation(int id, int id_plat, int id_ingredient, float qte) {
		setId(id);
		setId_plat(id_plat);
		setId_ingredient(id_ingredient);
		setQte(qte);
	}
	public Preparation(int id_plat, int id_ingredient, float qte) {
		setId_plat(id_plat);
		setId_ingredient(id_ingredient);
		setQte(qte);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_plat() {
		return id_plat;
	}
	public void setId_plat(int id_plat) {
		this.id_plat = id_plat;
	}
	public int getId_ingredient() {
		return id_ingredient;
	}
	public void setId_ingredient(int id_ingredient) {
		this.id_ingredient = id_ingredient;
	}
	public float getQte() {
		return qte;
	}
	public void setQte(float qte) {
		this.qte = qte;
	}
	
	@Override
	public String toString() {
		return "Preparation [id=" + id + ", id_plat=" + id_plat + ", id_ingredient=" + id_ingredient + ", qte=" + qte
				+ "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, id_ingredient, id_plat, qte);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Preparation other = (Preparation) obj;
		return id == other.id && id_ingredient == other.id_ingredient && id_plat == other.id_plat
				&& Float.floatToIntBits(qte) == Float.floatToIntBits(other.qte);
	}
	
	
}
