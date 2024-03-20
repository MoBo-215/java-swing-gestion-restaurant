package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Serveur;
import entites.Admin;
import entites.Serveur;
import entites.Database;

public class ServeurDAO {
	public void save(Serveur obj) {
		try {
			if(obj.getId()!=0) {
				PreparedStatement ps = Database.connexion.prepareStatement("UPDATE serveur SET prenom=?,nom=?,email=?,mot_de_passe=? WHERE id=?");
				ps.setString(1, obj.getPrenom());
				ps.setString(2, obj.getNom());
				ps.setString(3, obj.getEmail());
				ps.setString(4, obj.getMot_de_passe());
				ps.setInt(5, obj.getId());
				ps.executeUpdate();
			}else {
				PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO serveur (prenom,nom,email,mot_de_passe) VALUES(?,?,?,?)");
				ps.setString(1, obj.getPrenom());
				ps.setString(2, obj.getNom());
				ps.setString(3, obj.getEmail());
				ps.setString(4, obj.getMot_de_passe());
				ps.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Serveur getById(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM serveur WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			resultat.next();
			
			Serveur a = new Serveur();
			a.setId(resultat.getInt("id"));
			a.setPrenom(resultat.getString("prenom"));
			a.setNom(resultat.getString("nom"));
			a.setEmail(resultat.getString("email"));
			a.setMot_de_passe(resultat.getString("mot_de_passe"));
			return a;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Serveur> getAll() {
		ArrayList<Serveur> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM serveur");
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
				Serveur a = new Serveur();
				a.setId(resultat.getInt("id"));
				a.setPrenom(resultat.getString("prenom"));
				a.setNom(resultat.getString("nom"));
				a.setEmail(resultat.getString("email"));
				a.setMot_de_passe(resultat.getString("mot_de_passe"));
				list.add(a);
			}
			return list;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public void deleteById(int id) {
		try {
			PreparedStatement ps1 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
			ps1.executeUpdate();
			
			PreparedStatement ps3 = Database.connexion.prepareStatement("DELETE FROM commande WHERE id_serveur=?");
			ps3.setInt(1, id);
			ps3.executeUpdate();
			
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM serveur WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			PreparedStatement ps2 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
			ps2.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Serveur> findServeurs(String mot){
		ArrayList<Serveur> list = new ArrayList<Serveur>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM serveur WHERE prenom LIKE ? OR nom LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ps.setString(2,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Serveur a = new Serveur();
				a.setId(resultat.getInt("id"));
				a.setPrenom(resultat.getString("prenom"));
				a.setNom(resultat.getString("nom"));
				a.setEmail(resultat.getString("email"));
				a.setMot_de_passe(resultat.getString("mot_de_passe"));
				list.add(a);
			}
			
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int nbServeurs() {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM serveur");
			ResultSet resultat = ps.executeQuery();
			
			int nb=0;
			while(resultat.next()) {
				nb++;
			}
			
			return nb;
		}catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	public Serveur getServeurByEmailMdp(String email, String mdp) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM serveur WHERE email=? AND mot_de_passe=?");
			ps.setString(1, email);
			ps.setString(2, mdp);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Serveur a = new Serveur();
			a.setId(resultat.getInt("id"));
			a.setPrenom(resultat.getString("prenom"));
			a.setNom(resultat.getString("nom"));
			a.setEmail(resultat.getString("email"));
			a.setMot_de_passe(resultat.getString("mot_de_passe"));
			return a;
		}catch(Exception ex){
			return null;
		}
	}
	
	
}
