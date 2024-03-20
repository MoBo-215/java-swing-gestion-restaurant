package vues;

import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

import dao.ServeurDAO;
import entites.Serveur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class AdminServeurAdd extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	public AdminServeurAdd() {
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
		
		JLabel lblServeurAdd = new JLabel("Admin - Ajouter un serveur");
		lblServeurAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblServeurAdd.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblServeurAdd.setBounds(0, 83, 1030, 51);
		add(lblServeurAdd);
		
		JLabel lblPrenom = new JLabel("Prénom");
		lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPrenom.setBounds(259, 229, 90, 34);
		add(lblPrenom);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNom.setBounds(259, 293, 90, 31);
		add(lblNom);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEmail.setBounds(259, 357, 135, 29);
		add(lblEmail);
		
		JLabel lblMdp = new JLabel("Mot de passe");
		lblMdp.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblMdp.setBounds(259, 418, 135, 29);
		add(lblMdp);
		
		JTextField tprenom = new JTextField();
		tprenom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tprenom.setColumns(10);
		tprenom.setBounds(421, 232, 310, 31);
		add(tprenom);
		
		JTextField tnom = new JTextField();
		tnom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tnom.setColumns(10);
		tnom.setBounds(421, 293, 310, 31);
		add(tnom);
		
		JTextField temail = new JTextField();
		temail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		temail.setColumns(10);
		temail.setBounds(421, 355, 310, 31);
		add(temail);
		
		JTextField tmdp = new JTextField();
		tmdp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tmdp.setColumns(10);
		tmdp.setBounds(421, 418, 310, 31);
		add(tmdp);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String prenom = tprenom.getText();
				String nom = tnom.getText();
				String email = temail.getText();
				String mdp = tmdp.getText();
				
				Serveur s = new Serveur(prenom,nom,email,mdp);
				new ServeurDAO().save(s);
				JOptionPane.showMessageDialog(null, "Serveur ajouté.");
				
				AdminHome.replace(new AdminServeurList());
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setBounds(421, 501, 174, 52);
		add(btnAjouter);
	}

}
