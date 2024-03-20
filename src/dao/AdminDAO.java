package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Admin;
import entites.Database;
import entites.Admin;

public class AdminDAO {
	public void save(Admin obj) {
		try {
			if(obj.getId()!=0) {
				PreparedStatement ps = Database.connexion.prepareStatement("UPDATE admin SET prenom=?,email=?,mot_de_passe=? WHERE id=?");
				ps.setString(1, obj.getPrenom());
				ps.setString(2, obj.getEmail());
				ps.setString(3, obj.getMot_de_passe());
				ps.setInt(4, obj.getId());
				ps.executeUpdate();
			}else {
				PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO admin (prenom,email,mot_de_passe) VALUES(?,?,?)");
				ps.setString(1, obj.getPrenom());
				ps.setString(2, obj.getEmail());
				ps.setString(3, obj.getMot_de_passe());
				ps.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Admin getById(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM admin WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			resultat.next();
			
			Admin a = new Admin();
			a.setId(resultat.getInt("id"));
			a.setPrenom(resultat.getString("prenom"));
			a.setEmail(resultat.getString("email"));
			a.setMot_de_passe(resultat.getString("mot_de_passe"));
			return a;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Admin> getAll() {
		ArrayList<Admin> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM admin");
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
				Admin a = new Admin();
				a.setId(resultat.getInt("id"));
				a.setPrenom(resultat.getString("prenom"));
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
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM admin WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Admin> findAdmins(String mot){
		ArrayList<Admin> list = new ArrayList<Admin>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM admin WHERE prenom LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Admin a = new Admin();
				a.setId(resultat.getInt("id"));
				a.setPrenom(resultat.getString("prenom"));
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
	
	public int nbAdmins() {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM admin");
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
	
	public Admin getAdminByEmailMdp(String email, String mdp) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM admin WHERE email=? AND mot_de_passe=?");
			ps.setString(1, email);
			ps.setString(2, mdp);
			ResultSet resultat = ps.executeQuery();
			
			resultat.next();
			Admin a = new Admin();
			a.setId(resultat.getInt("id"));
			a.setPrenom(resultat.getString("prenom"));
			a.setEmail(resultat.getString("email"));
			a.setMot_de_passe(resultat.getString("mot_de_passe"));
			return a;
		}catch(Exception ex){
			return null;
		}
	}
}
