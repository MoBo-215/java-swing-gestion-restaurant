package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import entites.Database;
import entites.Tablee;
import entites.Commande;
import entites.Commande;

public class CommandeDAO {
	public void save(Commande obj) {
		try {
			if(obj.getId()!=0) {
				PreparedStatement ps = Database.connexion.prepareStatement("UPDATE commande SET dateh=?,id_tablee=?,id_serveur=?,paiement=? WHERE id=?");
				ps.setString(1, obj.getDateh().toString());
				ps.setInt(2, obj.getId_tablee());
				ps.setInt(3, obj.getId_serveur());
				ps.setInt(4, obj.getPaiement());
				ps.setInt(5, obj.getId());
				ps.executeUpdate();
			}else {
				PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO commande (dateh,id_tablee,id_serveur,paiement) VALUES(now(),?,?,?)");
				ps.setInt(1, obj.getId_tablee());
				ps.setInt(2, obj.getId_serveur());
				ps.setInt(3, obj.getPaiement());
				ps.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Commande getById(int id) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM commande WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			resultat.next();
			
			Commande a = new Commande();
			a.setId(resultat.getInt("id"));
			a.setDateh(LocalDateTime.parse(resultat.getString("dateh"), formatter));
			a.setId_tablee(resultat.getInt("id_tablee"));
			a.setId_serveur(resultat.getInt("id_serveur"));
			a.setPaiement(resultat.getInt("paiement"));
			return a;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Commande> getAll() {
		ArrayList<Commande> list = new ArrayList<>();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM commande");
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
				Commande a = new Commande();
				a.setId(resultat.getInt("id"));
				a.setDateh(LocalDateTime.parse(resultat.getString("dateh"), formatter));
				a.setId_tablee(resultat.getInt("id_tablee"));
				a.setId_serveur(resultat.getInt("id_serveur"));
				a.setPaiement(resultat.getInt("paiement"));
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
			
			PreparedStatement ps3 = Database.connexion.prepareStatement("DELETE FROM detail WHERE id_commande=?");
			ps3.setInt(1, id);
			ps3.executeUpdate();
			
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM commande WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			PreparedStatement ps2 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
			ps2.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Commande> findCommandes(String mot){
		ArrayList<Commande> list = new ArrayList<Commande>();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM commande WHERE id_serveur LIKE ? OR id_tablee LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ps.setString(2,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Commande a = new Commande();
				a.setId(resultat.getInt("id"));
				a.setDateh(LocalDateTime.parse(resultat.getString("dateh"), formatter));
				a.setId_serveur(resultat.getInt("id_serveur"));
				a.setId_tablee(resultat.getInt("id_tablee"));
				a.setPaiement(resultat.getInt("paiement"));
				list.add(a);
			}
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int nbCommandes() {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM commande");
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
	
	public Commande getLastCmd() {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM commande WHERE id=(SELECT max(id) FROM commande)");
			ResultSet resultat = ps.executeQuery();
			resultat.next();
			
			Commande a = new Commande();
			a.setId(resultat.getInt("id"));
			a.setDateh(LocalDateTime.parse(resultat.getString("dateh"), formatter));
			a.setId_tablee(resultat.getInt("id_tablee"));
			a.setId_serveur(resultat.getInt("id_serveur"));
			a.setPaiement(resultat.getInt("paiement"));
			return a;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Commande> getAllToPay() {
		ArrayList<Commande> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM commande WHERE paiement=0");
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
				Commande a = new Commande();
				a.setId(resultat.getInt("id"));
				a.setId_tablee(resultat.getInt("id_tablee"));
				a.setId_serveur(resultat.getInt("id_serveur"));
				a.setPaiement(resultat.getInt("paiement"));
				list.add(a);
			}
			return list;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public Commande getCmdToPay(String desi) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM commande WHERE paiement=0 AND id_tablee IN(SELECT id FROM tablee WHERE designation=?)");
			ps.setString(1, desi);
			ResultSet resultat = ps.executeQuery();
			resultat.next();
			
			Commande a = new Commande();
			a.setId(resultat.getInt("id"));
			a.setDateh(LocalDateTime.parse(resultat.getString("dateh"), formatter));
			a.setId_tablee(resultat.getInt("id_tablee"));
			a.setId_serveur(resultat.getInt("id_serveur"));
			a.setPaiement(resultat.getInt("paiement"));
				
			return a;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int getNbCmdsToPay() {
		try {
			int nb=0;

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM commande WHERE paiement=0");
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
				nb++;
			}
			return nb;
		}catch(Exception ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	public ArrayList<Commande> findCommandesJourServeur(int id){
		ArrayList<Commande> list = new ArrayList<Commande>();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.0");

			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM commande WHERE id_serveur=? AND dateh >= date_sub(NOW(), INTERVAL 12 HOUR)");
			ps.setInt(1, id);
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Commande a = new Commande();
				a.setId(resultat.getInt("id"));
				a.setDateh(LocalDateTime.parse(resultat.getString("dateh"), formatter));
				a.setId_serveur(resultat.getInt("id_serveur"));
				a.setId_tablee(resultat.getInt("id_tablee"));
				a.setPaiement(resultat.getInt("paiement"));
				list.add(a);
			}
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	} 
}
