package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.CommandeDAO;
import dao.DetailDAO;
import dao.IngredientDAO;
import dao.PlatDAO;
import entites.Commande;
import entites.Database;
import entites.Detail;
import entites.Ingredient;
import entites.Plat;
import entites.Serveur;

import javax.swing.JComboBox;

public class AdminDetailEdit extends JPanel {

	private static final long serialVersionUID = 1L;
	private Detail detail;
	private JTextField tprixu;
	private JTextField tqte;

	/**
	 * Create the panel.
	 */
	public AdminDetailEdit(Detail d) {
		this.detail=d;
		
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
		
		JLabel lblDetailEdit = new JLabel("Admin - Modifier un détail");
		lblDetailEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetailEdit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblDetailEdit.setBounds(0, 83, 1030, 51);
		add(lblDetailEdit);
		
		JLabel lblCommande = new JLabel("Commande");
		lblCommande.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblCommande.setBounds(247, 429, 135, 29);
		add(lblCommande);
		
		JLabel lblPlat = new JLabel("Plat");
		lblPlat.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPlat.setBounds(247, 362, 135, 29);
		add(lblPlat);
		
		JLabel lblPrixu = new JLabel("Prix unitaire");
		lblPrixu.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPrixu.setBounds(247, 297, 135, 29);
		add(lblPrixu);
		
		tprixu = new JTextField();
		tprixu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tprixu.setText((String) null);
		tprixu.setColumns(10);
		tprixu.setBounds(409, 297, 310, 31);
		add(tprixu);
		
		JLabel lblQte = new JLabel("Quantité");
		lblQte.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblQte.setBounds(247, 229, 135, 29);
		add(lblQte);
		
		tqte = new JTextField();
		tqte.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tqte.setText((String) null);
		tqte.setColumns(10);
		tqte.setBounds(409, 229, 310, 31);
		add(tqte);
		
		JComboBox cbplat = new JComboBox();
		cbplat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbplat.setBounds(409, 362, 310, 29);
		add(cbplat);
		
		JComboBox cbcommande = new JComboBox();
		cbcommande.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbcommande.setBounds(409, 429, 310, 29);
		add(cbcommande);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.connect();
				detail.setQtec(Integer.parseInt(tqte.getText()));
				detail.setPrixu(Float.parseFloat(tprixu.getText()));
				Plat plat = (Plat)cbplat.getSelectedItem();
				detail.setId_plat(plat.getId());
				Commande commande = (Commande)cbcommande.getSelectedItem();
				detail.setId_plat(plat.getId());
				
				new DetailDAO().save(detail);
				JOptionPane.showMessageDialog(null, "Détail modifié.");
				
				AdminHome.replace(new AdminTableList());
			}
		});
		btnEnregistrer.setForeground(Color.WHITE);
		btnEnregistrer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEnregistrer.setBackground(Color.BLACK);
		btnEnregistrer.setBounds(409, 512, 174, 52);
		add(btnEnregistrer);
		
		Database.connect();
		for(Plat pl: new PlatDAO().getAll()) {
			cbplat.addItem(pl);
		}
		for(Commande c: new CommandeDAO().getAll()) {
			cbcommande.addItem(c);
		}
		
		tqte.setText(detail.getQtec()+"");
		tprixu.setText(detail.getPrixu()+"");
		Plat plat = new PlatDAO().getById(detail.getId_plat());
		cbplat.setSelectedItem(plat);
		Commande commande = new CommandeDAO().getById(detail.getId_commande());
		cbcommande.setSelectedItem(commande);
	}

}
