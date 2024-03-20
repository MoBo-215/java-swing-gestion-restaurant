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

import dao.IngredientDAO;
import dao.TableeDAO;
import entites.Ingredient;
import entites.Tablee;

public class AdminIngredientAdd extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AdminIngredientAdd() {
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
		
		JLabel lblIngredientAdd = new JLabel("Admin - Ajouter un ingrédient");
		lblIngredientAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngredientAdd.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblIngredientAdd.setBounds(0, 83, 1030, 51);
		add(lblIngredientAdd);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNom.setBounds(247, 248, 135, 29);
		add(lblNom);
		
		JTextField tnom = new JTextField();
		tnom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tnom.setColumns(10);
		tnom.setBounds(412, 246, 310, 31);
		add(tnom);

		JLabel lblStock = new JLabel("Stock");
		lblStock.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblStock.setBounds(247, 313, 135, 29);
		add(lblStock);
		
		JTextField tstock = new JTextField();
		tstock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tstock.setColumns(10);
		tstock.setBounds(412, 311, 310, 31);
		add(tstock);
		
		JLabel lblStockMin = new JLabel("Stock minimal");
		lblStockMin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblStockMin.setBounds(247, 376, 138, 29);
		add(lblStockMin);
		
		JTextField tstockmin = new JTextField();
		tstockmin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tstockmin.setColumns(10);
		tstockmin.setBounds(412, 376, 310, 31);
		add(tstockmin);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nom = tnom.getText();
				float stock = Float.parseFloat(tstock.getText());	
				float stockmin = Float.parseFloat(tstockmin.getText());				
				Ingredient i = new Ingredient(nom,stock,stockmin);
				
				new IngredientDAO().save(i);
				JOptionPane.showMessageDialog(null, "Ingrédient ajouté.");
				
				AdminHome.replace(new AdminIngredientList());
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setBounds(412, 459, 174, 52);
		add(btnAjouter);
	}
}