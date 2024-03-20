package entites;

import java.util.Objects;

public class Serveur {
	private int id;
	private String prenom;
	private String nom;
	private String email;
	private String mot_de_passe;
	
	public Serveur() {
		
	}
	public Serveur(int id, String prenom, String nom, String email, String mot_de_passe) {
		setId(id);
		setPrenom(prenom);
		setNom(nom);
		setEmail(email);
		setMot_de_passe(mot_de_passe);
	}
	public Serveur(String prenom, String nom, String email, String mot_de_passe) {
		setPrenom(prenom);
		setNom(nom);
		setEmail(email);
		setMot_de_passe(mot_de_passe);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	@Override
	public String toString() {
		return prenom+" "+nom;
	}
	@Override
	public int hashCode() {
		return Objects.hash(email, id, mot_de_passe, nom, prenom);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Serveur other = (Serveur) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(mot_de_passe, other.mot_de_passe)
				&& Objects.equals(nom, other.nom) && Objects.equals(prenom, other.prenom);
	}
	
	
}
