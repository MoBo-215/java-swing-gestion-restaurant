package vues;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.CommandeDAO;
import dao.ServeurDAO;
import dao.TableeDAO;
import entites.Commande;
import entites.Database;

import javax.swing.JTable;
import javax.swing.JScrollPane;

public class ServeurCommandesJour extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;

	/**
	 * Create the panel.
	 */
	public ServeurCommandesJour() {
		int idServeur = ServeurHome.serveur.getId();
		
		setLayout(null);
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ServeurHome.replace(new ServeurHomePanel());
			}
		});
		textField.setText("GESTION RESTAURANT");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField.setColumns(10);
		textField.setBackground(new Color(128, 0, 0));
		textField.setBounds(0, 0, 1030, 80);
		add(textField);

		JLabel lblCommandesJour = new JLabel("Serveur - Liste des commandes du jour");
		lblCommandesJour.setBounds(0, 83, 1030, 51);
		lblCommandesJour.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommandesJour.setFont(new Font("Tahoma", Font.PLAIN, 25));
		add(lblCommandesJour);
		
		Database.connect();
		ArrayList<Commande> commandes = new CommandeDAO().findCommandesJourServeur(idServeur);
		String columns[] = {"ID", "DATE", "SERVEUR", "TABLE", "PAIEMENT"};
		String data[][] = new String[commandes.size()][columns.length];
		int i=0;
		for(Commande s: commandes) {
			data[i][0]=s.getId()+"";
			data[i][1]=s.getDateh()+"";
			data[i][2]=new ServeurDAO().getById(s.getId_serveur()).getPrenom()+" "+new ServeurDAO().getById(s.getId_serveur()).getNom().toUpperCase();
			data[i][3]=new TableeDAO().getById(s.getId_tablee()).getDesignation();
			data[i][4]=s.getPaiement()+"";
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(data,columns);
		
		table = new JTable(model);
		table.setBounds(35, 170, 958, 536);
		add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(23, 159, 982, 561);
		add(scrollPane);
	}
}
