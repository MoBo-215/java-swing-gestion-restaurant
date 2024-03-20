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
import entites.Database;
import entites.Tablee;

public class AdminTableEdit extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Tablee tablee;

	/**
	 * Create the panel.
	 */
	public AdminTableEdit(Tablee t) {
		this.tablee=t;
		
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
		
		JLabel lblServeurEdit = new JLabel("Admin - Modifier une table");
		lblServeurEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblServeurEdit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblServeurEdit.setBounds(0, 83, 1030, 51);
		add(lblServeurEdit);
		
		JLabel lblDesi = new JLabel("Désignation");
		lblDesi.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblDesi.setBounds(254, 301, 135, 29);
		add(lblDesi);
		
		JTextField tdesi = new JTextField();
		tdesi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tdesi.setColumns(10);
		tdesi.setBounds(416, 301, 310, 31);
		add(tdesi);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.connect();
				tablee.setDesignation(tdesi.getText());
				new TableeDAO().save(tablee);
				JOptionPane.showMessageDialog(null, "Table modifiée.");
				
				AdminHome.replace(new AdminTableList());
			}
		});
		btnEnregistrer.setForeground(Color.WHITE);
		btnEnregistrer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEnregistrer.setBackground(Color.BLACK);
		btnEnregistrer.setBounds(416, 384, 174, 52);
		add(btnEnregistrer);
		
		tdesi.setText(tablee.getDesignation());
	}

}
