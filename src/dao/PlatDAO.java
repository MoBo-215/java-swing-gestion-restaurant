package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Database;
import entites.Plat;

public class PlatDAO {
	public void save(Plat obj) {
		try {
			if(obj.getId()!=0) {
				PreparedStatement ps = Database.connexion.prepareStatement("UPDATE plat SET nom=?,prix=? WHERE id=?");
				ps.setString(1, obj.getNom());
				ps.setFloat(2, obj.getPrix());
				ps.setInt(3, obj.getId());
				ps.executeUpdate();
			}else {
				PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO plat (nom,prix) VALUES(?,?)");
				ps.setString(1, obj.getNom());
				ps.setFloat(2, obj.getPrix());
				ps.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Plat getById(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM plat WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			resultat.next();
			
			Plat a = new Plat();
			a.setId(resultat.getInt("id"));
			a.setNom(resultat.getString("nom"));
			a.setPrix(resultat.getFloat("prix"));
			return a;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Plat> getAll() {
		ArrayList<Plat> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM plat");
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
				Plat a = new Plat();
				a.setId(resultat.getInt("id"));
				a.setNom(resultat.getString("nom"));
				a.setPrix(resultat.getFloat("prix"));
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
			
			PreparedStatement ps3 = Database.connexion.prepareStatement("DELETE FROM preparation WHERE id_plat=?");
			ps3.setInt(1, id);
			ps3.executeUpdate();
			
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM plat WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			PreparedStatement ps2 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
			ps2.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Plat> findPlats(String mot){
		ArrayList<Plat> list = new ArrayList<Plat>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM plat WHERE nom LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Plat a = new Plat();
				a.setId(resultat.getInt("id"));
				a.setNom(resultat.getString("nom"));
				a.setPrix(resultat.getFloat("stock"));
				list.add(a);
			}
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int nbPlats() {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM plat");
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
	
	public Plat getByNom(String nom) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM plat WHERE nom=?");
			ps.setString(1, nom);
			ResultSet resultat = ps.executeQuery();
			resultat.next();
			
			Plat a = new Plat();
			a.setId(resultat.getInt("id"));
			a.setNom(resultat.getString("nom"));
			a.setPrix(resultat.getFloat("prix"));
			return a;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
}
