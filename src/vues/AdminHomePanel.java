package vues;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.AdminDAO;
import dao.CommandeDAO;
import dao.DetailDAO;
import dao.IngredientDAO;
import dao.PlatDAO;
import dao.PreparationDAO;
import dao.ServeurDAO;
import dao.TableeDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminHomePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AdminHomePanel() {
		setLayout(null);
		
		JTextField textField = new JTextField();
		textField.setText("GESTION RESTAURANT");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField.setColumns(10);
		textField.setBackground(new Color(128, 0, 0));
		textField.setBounds(0, 0, 1030, 80);
		add(textField);
		
		JLabel lblMenuGestion = new JLabel("Menu Admin - Gestion des données");
		lblMenuGestion.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuGestion.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblMenuGestion.setBounds(0, 83, 1030, 51);
		add(lblMenuGestion);
		
		JLabel lblServeurs = new JLabel(new ServeurDAO().nbServeurs()+" serveur(s)");
		lblServeurs.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblServeurs.setHorizontalAlignment(SwingConstants.CENTER);
		lblServeurs.setBounds(91, 192, 208, 39);
		add(lblServeurs);
		
		JButton btnListe1 = new JButton("Liste");
		btnListe1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome.replace(new AdminServeurList());
			}
		});
		btnListe1.setForeground(new Color(255, 255, 255));
		btnListe1.setBackground(new Color(0, 0, 0));
		btnListe1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnListe1.setBounds(91, 241, 99, 39);
		add(btnListe1);
		
		JButton btnAjouter1 = new JButton("Ajouter");
		btnAjouter1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome.replace(new AdminServeurAdd());
			}
		});
		btnAjouter1.setForeground(new Color(255, 255, 255));
		btnAjouter1.setBackground(new Color(0, 0, 0));
		btnAjouter1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAjouter1.setBounds(200, 241, 99, 39);
		add(btnAjouter1);
		
		JLabel lblCommandes = new JLabel(new CommandeDAO().nbCommandes()+" commande(s)");
		lblCommandes.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommandes.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCommandes.setBounds(91, 529, 208, 39);
		add(lblCommandes);
		
		JButton btnListe2 = new JButton("Liste");
		btnListe2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome.replace(new AdminCommandeList());
			}
		});
		btnListe2.setForeground(Color.WHITE);
		btnListe2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnListe2.setBackground(Color.BLACK);
		btnListe2.setBounds(143, 578, 99, 39);
		add(btnListe2);
		
		JLabel lblDetails = new JLabel(new DetailDAO().nbDetails()+" détail(s)");
		lblDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetails.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDetails.setBounds(727, 529, 208, 39);
		add(lblDetails);
		
		JButton btnListe3 = new JButton("Liste");
		btnListe3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome.replace(new AdminDetailList());
			}
		});
		btnListe3.setForeground(Color.WHITE);
		btnListe3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnListe3.setBackground(Color.BLACK);
		btnListe3.setBounds(781, 578, 99, 39);
		add(btnListe3);
		
		JLabel lblTables = new JLabel(new TableeDAO().nbTables()+" table(s)");
		lblTables.setHorizontalAlignment(SwingConstants.CENTER);
		lblTables.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTables.setBounds(91, 361, 208, 39);
		add(lblTables);
		
		JButton btnListe4 = new JButton("Liste");
		btnListe4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome.replace(new AdminTableList());
			}
		});
		btnListe4.setForeground(Color.WHITE);
		btnListe4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnListe4.setBackground(Color.BLACK);
		btnListe4.setBounds(91, 410, 99, 39);
		add(btnListe4);
		
		JButton btnAjouter4 = new JButton("Ajouter");
		btnAjouter4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome.replace(new AdminTableAdd());
			}
		});
		btnAjouter4.setForeground(Color.WHITE);
		btnAjouter4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAjouter4.setBackground(Color.BLACK);
		btnAjouter4.setBounds(200, 410, 99, 39);
		add(btnAjouter4);
		
		JLabel lblPlats = new JLabel(new PlatDAO().nbPlats()+" plat(s)");
		lblPlats.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlats.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPlats.setBounds(412, 361, 208, 39);
		add(lblPlats);
		
		JButton btnListe5 = new JButton("Liste");
		btnListe5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome.replace(new AdminPlatList());
			}
		});
		btnListe5.setForeground(Color.WHITE);
		btnListe5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnListe5.setBackground(Color.BLACK);
		btnListe5.setBounds(412, 410, 99, 39);
		add(btnListe5);
		
		JButton btnAjouter5 = new JButton("Ajouter");
		btnAjouter5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome.replace(new AdminPlatAdd());
			}
		});
		btnAjouter5.setForeground(Color.WHITE);
		btnAjouter5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAjouter5.setBackground(Color.BLACK);
		btnAjouter5.setBounds(521, 410, 99, 39);
		add(btnAjouter5);
		
		JLabel lblIngredients = new JLabel(new IngredientDAO().nbIngredients()+" ingrédient(s)");
		lblIngredients.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredients.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblIngredients.setBounds(727, 361, 208, 39);
		add(lblIngredients);
		
		JButton btnListe6 = new JButton("Liste");
		btnListe6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome.replace(new AdminIngredientList());
			}
		});
		btnListe6.setForeground(Color.WHITE);
		btnListe6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnListe6.setBackground(Color.BLACK);
		btnListe6.setBounds(727, 410, 99, 39);
		add(btnListe6);
		
		JButton btnAjouter6 = new JButton("Ajouter");
		btnAjouter6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome.replace(new AdminIngredientAdd());
			}
		});
		btnAjouter6.setForeground(Color.WHITE);
		btnAjouter6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAjouter6.setBackground(Color.BLACK);
		btnAjouter6.setBounds(836, 410, 99, 39);
		add(btnAjouter6);
		
		JLabel lblAdmin = new JLabel(new AdminDAO().nbAdmins()+" admin(s)");
		lblAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAdmin.setBounds(727, 192, 208, 39);
		add(lblAdmin);
		
		JButton btnListe7 = new JButton("Liste");
		btnListe7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome.replace(new AdminAdminList());
			}
		});
		btnListe7.setForeground(Color.WHITE);
		btnListe7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnListe7.setBackground(Color.BLACK);
		btnListe7.setBounds(727, 241, 99, 39);
		add(btnListe7);
		
		JButton btnAjouter7 = new JButton("Ajouter");
		btnAjouter7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome.replace(new AdminAdminAdd());
			}
		});
		btnAjouter7.setForeground(Color.WHITE);
		btnAjouter7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAjouter7.setBackground(Color.BLACK);
		btnAjouter7.setBounds(836, 241, 99, 39);
		add(btnAjouter7);
		
		JLabel lblPreparations = new JLabel(new PreparationDAO().nbPreparations()+" préparation(s)");
		lblPreparations.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreparations.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPreparations.setBounds(412, 192, 208, 39);
		add(lblPreparations);
		
		JButton btnListe8 = new JButton("Liste");
		btnListe8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome.replace(new AdminPreparationList());
			}
		});
		btnListe8.setForeground(Color.WHITE);
		btnListe8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnListe8.setBackground(Color.BLACK);
		btnListe8.setBounds(412, 241, 99, 39);
		add(btnListe8);
		
		JButton btnAjouter8 = new JButton("Ajouter");
		btnAjouter8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminHome.replace(new AdminPreparationAdd());
			}
		});
		btnAjouter8.setForeground(Color.WHITE);
		btnAjouter8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAjouter8.setBackground(Color.BLACK);
		btnAjouter8.setBounds(521, 241, 99, 39);
		add(btnAjouter8);
	}
}
