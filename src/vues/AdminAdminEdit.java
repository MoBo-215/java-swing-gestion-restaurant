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

import dao.AdminDAO;
import entites.Admin;
import entites.Database;

public class AdminAdminEdit extends JPanel {

	private static final long serialVersionUID = 1L;
	private Admin admin;
	
	/**
	 * Create the panel.
	 */
	public AdminAdminEdit(Admin a) {
		this.admin=a;
		
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
		
		JLabel lblAdminAdd = new JLabel("Admin - Modifier un admin");
		lblAdminAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminAdd.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAdminAdd.setBounds(0, 83, 1030, 51);
		add(lblAdminAdd);
		
		JLabel lblPrenom = new JLabel("Prénom");
		lblPrenom.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPrenom.setBounds(259, 258, 90, 34);
		add(lblPrenom);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEmail.setBounds(259, 324, 135, 29);
		add(lblEmail);
		
		JLabel lblMdp = new JLabel("Mot de passe");
		lblMdp.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblMdp.setBounds(259, 385, 135, 29);
		add(lblMdp);
		
		JTextField tprenom = new JTextField();
		tprenom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tprenom.setColumns(10);
		tprenom.setBounds(421, 261, 310, 31);
		add(tprenom);
		
		JTextField temail = new JTextField();
		temail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		temail.setColumns(10);
		temail.setBounds(421, 322, 310, 31);
		add(temail);
		
		JTextField tmdp = new JTextField();
		tmdp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tmdp.setColumns(10);
		tmdp.setBounds(421, 385, 310, 31);
		add(tmdp);
		
		JButton btnAjouter = new JButton("Enregistrer");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.connect();
				admin.setPrenom(tprenom.getText());
				admin.setEmail(temail.getText());
				admin.setMot_de_passe(tmdp.getText());
				
				new AdminDAO().save(admin);
				JOptionPane.showMessageDialog(null, "Admin modifié.");
				
				AdminHome.replace(new AdminAdminList());
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setBounds(421, 468, 174, 52);
		add(btnAjouter);
		
		tprenom.setText(admin.getPrenom());
		temail.setText(admin.getEmail());
		tmdp.setText(admin.getMot_de_passe());
	}

}
