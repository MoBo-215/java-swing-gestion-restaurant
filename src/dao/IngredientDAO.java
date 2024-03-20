package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import entites.Ingredient;
import entites.Ingredient;
import entites.Database;

public class IngredientDAO {
	public void save(Ingredient obj) {
		try {
			if(obj.getId()!=0) {
				PreparedStatement ps = Database.connexion.prepareStatement("UPDATE ingredient SET nom=?,stock=?,stock_min=? WHERE id=?");
				ps.setString(1, obj.getNom());
				ps.setFloat(2, obj.getStock());
				ps.setFloat(3, obj.getStock_min());
				ps.setInt(4, obj.getId());
				ps.executeUpdate();
			}else {
				PreparedStatement ps = Database.connexion.prepareStatement("INSERT INTO ingredient (nom,stock,stock_min) VALUES(?,?,?)");
				ps.setString(1, obj.getNom());
				ps.setFloat(2, obj.getStock());
				ps.setFloat(3, obj.getStock_min());
				ps.executeUpdate();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Ingredient getById(int id) {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM ingredient WHERE id=?");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			resultat.next();
			
			Ingredient a = new Ingredient();
			a.setId(resultat.getInt("id"));
			a.setNom(resultat.getString("nom"));
			a.setStock(resultat.getFloat("stock"));
			a.setStock_min(resultat.getFloat("stock_min"));
			return a;
		}catch(Exception ex){
			ex.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Ingredient> getAll() {
		ArrayList<Ingredient> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM ingredient");
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
				Ingredient a = new Ingredient();
				a.setId(resultat.getInt("id"));
				a.setNom(resultat.getString("nom"));
				a.setStock(resultat.getFloat("stock"));
				a.setStock_min(resultat.getFloat("stock_min"));
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
			
			PreparedStatement ps3 = Database.connexion.prepareStatement("DELETE FROM preparation WHERE id_ingredient=?");
			ps3.setInt(1, id);
			ps3.executeUpdate();
			
			PreparedStatement ps = Database.connexion.prepareStatement("DELETE FROM ingredient WHERE id=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			
			PreparedStatement ps2 = Database.connexion.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
			ps2.executeUpdate();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public ArrayList<Ingredient> findIngredients(String mot){
		ArrayList<Ingredient> list = new ArrayList<Ingredient>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM ingredient WHERE nom LIKE ?");
			ps.setString(1,"%"+mot+"%");
			ResultSet resultat=ps.executeQuery();
			
			while(resultat.next()) {
				Ingredient a = new Ingredient();
				a.setId(resultat.getInt("id"));
				a.setNom(resultat.getString("nom"));
				a.setStock(resultat.getFloat("stock"));
				a.setStock_min(resultat.getFloat("stock_min"));
				list.add(a);
			}
			return list;
		}catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public int nbIngredients() {
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM ingredient");
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
	
	public ArrayList<Ingredient> getAllByIdPlat(int id) {
		ArrayList<Ingredient> list = new ArrayList<>();
		try {
			PreparedStatement ps = Database.connexion.prepareStatement("SELECT * FROM ingredient WHERE id IN(SELECT id_ingredient FROM preparation WHERE id_plat=?)");
			ps.setInt(1, id);
			ResultSet resultat = ps.executeQuery();
			while(resultat.next()) {
				Ingredient a = new Ingredient();
				a.setId(resultat.getInt("id"));
				a.setNom(resultat.getString("nom"));
				a.setStock(resultat.getFloat("stock"));
				a.setStock_min(resultat.getFloat("stock_min"));
				list.add(a);
			}
			return list;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
