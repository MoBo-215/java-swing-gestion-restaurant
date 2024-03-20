package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import entites.Detail;
import entites.Database;

public class DetailDAO {
	public void save(Detail obj) {
		try {
			if(obj.getId()!=0) {
				PreparedStatement ps1 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
				ps1.executeUpdate();
				
				PreparedStatement ps = Database.connexion.prepareStatement("UPDATE detail SET qtec=?,prixu=?,id_plat=?,id_commande=? WHERE id=?");
				ps.setInt(1, obj.getQtec());
				ps.setFloat(2, obj.getPrixu());
				ps.setInt(3, obj.getId_plat());
				ps.setInt(4, obj.getId_commande());
				ps.setInt(5, obj.getId());
				ps.executeUpdate();
				
				PreparedStatement ps2 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
				ps2.executeUpdate();
			}else {
				PreparedStatement ps1 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
				ps1.executeUpdate();
				
				PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO detail (qtec,prixu,id_plat,id_commande) VALUES(?,?,?,?)");
				ps.setInt(1, obj.getQtec());
				ps.setFloat(2, obj.getPrixu());
				ps.setInt(3, obj.getId_plat());
				ps.setInt(4, obj.getId_commande());
				ps.executeUpdate();
				
				PreparedStatement ps2 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
				ps2.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Detail getById(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM detail WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			resultat.next();
			
			Detail a = new Detail();
			a.setId(resultat.getInt("id"));
			a.setQtec(resultat.getInt("qtec"));
			a.setPrixu(resultat.getFloat("prixu"));
			a.setId_plat(resultat.getInt("id_plat"));
			a.setId_commande(resultat.getInt("id_commande"));
			return a;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Detail> getAll() {
		ArrayList<Detail> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM detail");
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
				Detail a = new Detail();
				a.setId(resultat.getInt("id"));
				a.setQtec(resultat.getInt("qtec"));
				a.setPrixu(resultat.getFloat("prixu"));
				a.setId_plat(resultat.getInt("id_plat"));
				a.setId_commande(resultat.getInt("id_commande"));
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
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM detail WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Detail> findDetails(String mot){
		ArrayList<Detail> list = new ArrayList<Detail>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM detail WHERE id_plat LIKE ? OR id_commande LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ps.setString(2,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Detail a = new Detail();
				a.setId(resultat.getInt("id"));
				a.setQtec(resultat.getInt("qtec"));
				a.setPrixu(resultat.getInt("prixu"));
				a.setId_plat(resultat.getInt("id_plat"));
				a.setId_commande(resultat.getInt("id_commande"));
				list.add(a);
			}
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int nbDetails() {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM detail");
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
	
	public float getTodayCA(int id) {
		float total=0;
		float prixu=0;
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM detail WHERE id_commande IN(SELECT id FROM commande WHERE id_serveur=? AND paiement=1 AND dateh >= date_sub(NOW(), INTERVAL 12 HOUR))");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
				Detail d = new Detail();
				prixu = resultat.getFloat("prixu");
				total=total+prixu;
			}
			return total;
		}catch(Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	public ArrayList<Detail> getByIdCmd(int id) {
		ArrayList<Detail> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM detail WHERE id_commande=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
				Detail a = new Detail();
				a.setId(resultat.getInt("id"));
				a.setQtec(resultat.getInt("qtec"));
				a.setPrixu(resultat.getFloat("prixu"));
				a.setId_plat(resultat.getInt("id_plat"));
				a.setId_commande(resultat.getInt("id_commande"));
				list.add(a);
			}
			return list;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public void deleteByIdCmd(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM detail WHERE id_commande=?");
			ps.setInt(1, id);
			ps.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
