package entites;

import java.util.Objects;

public class Detail {
	private int id;
	private int qtec;
	private float prixu;
	private int id_plat;
	private int id_commande;
	
	public Detail() {
		
	}
	public Detail(int id, int qtec, float prixu, int id_plat, int id_commande) {
		setId(id);
		setQtec(qtec);
		setPrixu(prixu);
		setId_plat(id_plat);
		setId_commande(id_commande);
	}
	public Detail(int qtec, float prixu, int id_plat, int id_commande) {
		setQtec(qtec);
		setPrixu(prixu);
		setId_plat(id_plat);
		setId_commande(id_commande);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQtec() {
		return qtec;
	}
	public void setQtec(int qtec) {
		this.qtec = qtec;
	}
	public float getPrixu() {
		return prixu;
	}
	public void setPrixu(float prixu) {
		this.prixu = prixu;
	}
	public int getId_plat() {
		return id_plat;
	}
	public void setId_plat(int id_plat) {
		this.id_plat = id_plat;
	}
	public int getId_commande() {
		return id_commande;
	}
	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}
	
	@Override
	public String toString() {
		return "Detail [id=" + id + ", qtec=" + qtec + ", prixu=" + prixu + ", id_plat=" + id_plat + ", id_commande="
				+ id_commande + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, id_commande, id_plat, prixu, qtec);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Detail other = (Detail) obj;
		return id == other.id && id_commande == other.id_commande && id_plat == other.id_plat
				&& Float.floatToIntBits(prixu) == Float.floatToIntBits(other.prixu) && qtec == other.qtec;
	}
	
	
}
