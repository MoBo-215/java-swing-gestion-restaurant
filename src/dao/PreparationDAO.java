package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Database;
import entites.Preparation;

public class PreparationDAO {
	public void save(Preparation obj) {
		try {
			if(obj.getId()!=0) {
				PreparedStatement ps = Database.connexion.prepareStatement("UPDATE preparation SET id_plat=?,id_ingredient=?,qte=? WHERE id=?");
				ps.setInt(1, obj.getId_plat());
				ps.setInt(2, obj.getId_ingredient());
				ps.setFloat(3, obj.getQte());
				ps.setInt(4, obj.getId());
				ps.executeUpdate();
			}else {
				PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO preparation (id_plat,id_ingredient,qte) VALUES(?,?,?)");
				ps.setInt(1, obj.getId_plat());
				ps.setInt(2, obj.getId_ingredient());
				ps.setFloat(3, obj.getQte());
				ps.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Preparation getById(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM preparation WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			resultat.next();
			
			Preparation a = new Preparation();
			a.setId(resultat.getInt("id"));
			a.setId_plat(resultat.getInt("id_plat"));
			a.setId_ingredient(resultat.getInt("id_ingredient"));
			a.setQte(resultat.getFloat("qte"));
			return a;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Preparation> getAll() {
		ArrayList<Preparation> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM preparation");
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
				Preparation a = new Preparation();
				a.setId(resultat.getInt("id"));
				a.setId_plat(resultat.getInt("id_plat"));
				a.setId_ingredient(resultat.getInt("id_ingredient"));
				a.setQte(resultat.getFloat("qte"));
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
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM preparation WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Preparation> findPreparations(String mot){
		ArrayList<Preparation> list = new ArrayList<Preparation>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM preparation WHERE id_plat LIKE ? OR id_ingredient LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ps.setString(2,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Preparation a = new Preparation();
				a.setId(resultat.getInt("id"));
				a.setId_plat(resultat.getInt("id_plat"));
				a.setId_ingredient(resultat.getInt("id_ingredient"));
				a.setQte(resultat.getFloat("qte"));
				list.add(a);
			}
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int nbPreparations() {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM preparation");
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
}
