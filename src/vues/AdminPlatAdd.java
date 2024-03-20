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
import entites.Plat;

public class AdminPlatAdd extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AdminPlatAdd() {
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
		
		JLabel lblPlatAdd = new JLabel("Admin - Ajouter un plat");
		lblPlatAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlatAdd.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPlatAdd.setBounds(0, 83, 1030, 51);
		add(lblPlatAdd);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNom.setBounds(245, 292, 135, 29);
		add(lblNom);
		
		JTextField tnom = new JTextField();
		tnom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tnom.setColumns(10);
		tnom.setBounds(410, 290, 310, 31);
		add(tnom);

		JLabel lblPrix = new JLabel("Prix");
		lblPrix.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPrix.setBounds(245, 357, 135, 29);
		add(lblPrix);
		
		JTextField tprix = new JTextField();
		tprix.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tprix.setColumns(10);
		tprix.setBounds(410, 355, 310, 31);
		add(tprix);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = tnom.getText();
				float prix = Float.parseFloat(tprix.getText());			
				Plat p = new Plat(nom,prix);
				
				new PlatDAO().save(p);
				JOptionPane.showMessageDialog(null, "Plat ajouté.");
				
				AdminHome.replace(new AdminPlatList());
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setBounds(410, 477, 174, 52);
		add(btnAjouter);
	}

}
