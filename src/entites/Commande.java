package entites;

import java.time.LocalDateTime;
import java.util.Objects;

public class Commande {
	private int id;
	private LocalDateTime dateh;
	private int id_tablee;
	private int id_serveur;
	private int paiement;
	
	public Commande() {
		
	}
	public Commande(int id, int id_tablee, int id_serveur, int paiement) {
		setId(id);
		setId_tablee(id_tablee);
		setId_serveur(id_serveur);
		setPaiement(paiement);
	}
	public Commande(int id_tablee, int id_serveur, int paiement) {
		setId_tablee(id_tablee);
		setId_serveur(id_serveur);
		setPaiement(paiement);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getDateh() {
		return dateh;
	}
	public void setDateh(LocalDateTime dateh) {
		this.dateh = dateh;
	}
	public int getId_tablee() {
		return id_tablee;
	}
	public void setId_tablee(int id_tablee) {
		this.id_tablee = id_tablee;
	}
	public int getId_serveur() {
		return id_serveur;
	}
	public void setId_serveur(int id_serveur) {
		this.id_serveur = id_serveur;
	}
	public int getPaiement() {
		return paiement;
	}
	public void setPaiement(int paiement) {
		this.paiement = paiement;
	}
	
	@Override
	public String toString() {
		return id+"";
	}
	@Override
	public int hashCode() {
		return Objects.hash(dateh, id, id_serveur, id_tablee, paiement);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commande other = (Commande) obj;
		return Objects.equals(dateh, other.dateh) && id == other.id && id_serveur == other.id_serveur
				&& id_tablee == other.id_tablee && paiement == other.paiement;
	}
}
