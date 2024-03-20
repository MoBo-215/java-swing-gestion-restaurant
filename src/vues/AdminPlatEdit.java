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

import dao.PlatDAO;
import entites.Database;
import entites.Plat;

public class AdminPlatEdit extends JPanel {

	private static final long serialVersionUID = 1L;
	private Plat plat;

	/**
	 * Create the panel.
	 */
	public AdminPlatEdit(Plat p) {
		this.plat=p;
		
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
		
		JLabel lblPlatEdit = new JLabel("Admin - Modifier un plat");
		lblPlatEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlatEdit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPlatEdit.setBounds(0, 83, 1030, 51);
		add(lblPlatEdit);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNom.setBounds(248, 286, 135, 29);
		add(lblNom);
		
		JTextField tnom = new JTextField();
		tnom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tnom.setColumns(10);
		tnom.setBounds(413, 284, 310, 31);
		add(tnom);

		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPrix.setBounds(248, 351, 135, 29);
		add(lblPrix);
		
		JTextField tprix = new JTextField();
		tprix.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tprix.setColumns(10);
		tprix.setBounds(413, 349, 310, 31);
		add(tprix);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.connect();
				plat.setNom(tnom.getText());
				plat.setPrix(Float.parseFloat(tprix.getText()));
				
				new PlatDAO().save(plat);
				JOptionPane.showMessageDialog(null, "Plat modifié.");
				
				AdminHome.replace(new AdminPlatList());
			}
		});
		btnModifier.setForeground(Color.WHITE);
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnModifier.setBackground(Color.BLACK);
		btnModifier.setBounds(413, 476, 174, 52);
		add(btnModifier);
		
		tnom.setText(plat.getNom());
		tprix.setText(plat.getPrix()+"");
	}

}
