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

import dao.TableeDAO;
import entites.Tablee;

public class AdminTableAdd extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AdminTableAdd() {
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
		
		JLabel lblTableAdd = new JLabel("Admin - Ajouter une table");
		lblTableAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableAdd.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblTableAdd.setBounds(0, 83, 1030, 51);
		add(lblTableAdd);
		
		JLabel lblDesi = new JLabel("Désignation");
		lblDesi.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDesi.setBounds(254, 301, 135, 29);
		add(lblDesi);
		
		JTextField tdesi = new JTextField();
		tdesi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tdesi.setColumns(10);
		tdesi.setBounds(416, 301, 310, 31);
		add(tdesi);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String desi = tdesi.getText();				
				Tablee t = new Tablee(desi);
				new TableeDAO().save(t);
				JOptionPane.showMessageDialog(null, "Table ajoutée.");
				
				AdminHome.replace(new AdminTableList());
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setBounds(416, 384, 174, 52);
		add(btnAjouter);
	}

}
