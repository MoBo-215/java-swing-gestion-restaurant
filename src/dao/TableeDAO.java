package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Tablee;
import entites.Database;

public class TableeDAO {
	public void save(Tablee obj) {
		try {
			if(obj.getId()!=0) {
				PreparedStatement ps = Database.connexion.prepareStatement("UPDATE tablee SET designation=? WHERE id=?");
				ps.setString(1, obj.getDesignation());
				ps.setInt(2, obj.getId());
				ps.executeUpdate();
			}else {
				PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO tablee (designation) VALUES(?)");
				ps.setString(1, obj.getDesignation());
				ps.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Tablee getById(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM tablee WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			resultat.next();
			
			Tablee a = new Tablee();
			a.setId(resultat.getInt("id"));
			a.setDesignation(resultat.getString("designation"));
			return a;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Tablee> getAll() {
		ArrayList<Tablee> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM tablee");
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
				Tablee a = new Tablee();
				a.setId(resultat.getInt("id"));
				a.setDesignation(resultat.getString("designation"));
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
			
			PreparedStatement ps3 = Database.connexion.prepareStatement("DELETE FROM commande WHERE id_tablee=?");
			ps3.setInt(1, id);
			ps3.executeUpdate();
			
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM tablee WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			PreparedStatement ps2 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
			ps2.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Tablee> findTables(String mot){
		ArrayList<Tablee> list = new ArrayList<Tablee>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM tablee WHERE designation LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Tablee a = new Tablee();
				a.setId(resultat.getInt("id"));
				a.setDesignation(resultat.getString("designation"));
				list.add(a);
			}
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int nbTables() {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM tablee");
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
	
	public ArrayList<Tablee> getAllToPay() {
		ArrayList<Tablee> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM tablee WHERE id IN(SELECT id_tablee FROM commande WHERE paiement=0)");
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
				Tablee a = new Tablee();
				a.setId(resultat.getInt("id"));
				a.setDesignation(resultat.getString("designation"));
				list.add(a);
			}
			return list;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
