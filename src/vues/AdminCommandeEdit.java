package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.TableeDAO;
import dao.ServeurDAO;
import dao.CommandeDAO;
import entites.Commande;
import entites.Database;
import entites.Tablee;
import entites.Serveur;

public class AdminCommandeEdit extends JPanel {

	private static final long serialVersionUID = 1L;
	private Commande commande;
	private JTextField tdate;

	/**
	 * Create the panel.
	 */
	public AdminCommandeEdit(Commande c) {
		this.commande=c;
		
		setLayout(null);

		JTextField textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminHome.replace(new AdminHomePanel());
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
		
		JLabel lblCommandeEdit = new JLabel("Admin - Modifier une commande");
		lblCommandeEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommandeEdit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCommandeEdit.setBounds(0, 83, 1030, 51);
		add(lblCommandeEdit);
		
		JLabel lbl = new JLabel("Serveur");
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lbl.setBounds(280, 288, 135, 29);
		add(lbl);
		
		JComboBox cbserveur = new JComboBox();
		cbserveur.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbserveur.setBounds(445, 288, 301, 29);
		add(cbserveur);

		JLabel lblTablee = new JLabel("Table");
		lblTablee.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblTablee.setBounds(280, 353, 135, 29);
		add(lblTablee);
		
		JComboBox cbtablee = new JComboBox();
		cbtablee.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbtablee.setBounds(445, 353, 301, 29);
		add(cbtablee);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDate.setBounds(280, 225, 135, 29);
		add(lblDate);
		
		tdate = new JTextField();
		tdate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tdate.setColumns(10);
		tdate.setBounds(445, 225, 301, 29);
		add(tdate);
		
		JLabel lblQuantite = new JLabel("Paiement (0/1)");
		lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblQuantite.setBounds(280, 418, 155, 29);
		add(lblQuantite);

		JComboBox cbpaiement = new JComboBox();
		cbpaiement.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbpaiement.setBounds(445, 418, 301, 29);
		add(cbpaiement);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.connect();
				
				commande.setDateh(LocalDateTime.parse(tdate.getText()));
				Serveur serveur = (Serveur)cbserveur.getSelectedItem();
				commande.setId_serveur(serveur.getId());
				Tablee tablee = (Tablee)cbtablee.getSelectedItem();
				commande.setId_tablee(tablee.getId());
				
				new CommandeDAO().save(commande);
				JOptionPane.showMessageDialog(null, "Commande modifiée.");
				
				AdminHome.replace(new AdminCommandeList());
			}
		});
		btnEnregistrer.setForeground(Color.WHITE);
		btnEnregistrer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEnregistrer.setBackground(Color.BLACK);
		btnEnregistrer.setBounds(445, 499, 174, 52);
		add(btnEnregistrer);
		
		Database.connect();
		for(Serveur pl: new ServeurDAO().getAll()) {
			cbserveur.addItem(pl);
		}
		for(Tablee i: new TableeDAO().getAll()) {
			cbtablee.addItem(i);
		}
		for(int i=0;i<2;i++) {
			cbpaiement.addItem(i);
		}
		
		tdate.setText(commande.getDateh()+"");
		Serveur serveur = new ServeurDAO().getById(commande.getId_serveur());
		cbserveur.setSelectedItem(serveur);
		Tablee tablee = new TableeDAO().getById(commande.getId_tablee());
		cbtablee.setSelectedItem(tablee);
		cbpaiement.setSelectedItem(commande.getPaiement());
	}

}
