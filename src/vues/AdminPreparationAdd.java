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
import dao.PlatDAO;
import dao.PreparationDAO;
import entites.Database;
import entites.Ingredient;
import entites.Plat;
import entites.Preparation;
import javax.swing.JComboBox;

public class AdminPreparationAdd extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tqte;

	/**
	 * Create the panel.
	 */
	public AdminPreparationAdd() {
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
		
		JLabel lblPreparationAdd = new JLabel("Admin - Ajouter une préparation");
		lblPreparationAdd.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreparationAdd.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPreparationAdd.setBounds(0, 83, 1030, 51);
		add(lblPreparationAdd);
		
		JLabel lblPlat = new JLabel("Plat");
		lblPlat.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblPlat.setBounds(247, 248, 135, 29);
		add(lblPlat);
		
		JComboBox cbplat = new JComboBox();
		cbplat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbplat.setBounds(412, 248, 301, 29);
		add(cbplat);

		JLabel lblIngredient = new JLabel("Ingrédient");
		lblIngredient.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblIngredient.setBounds(247, 313, 135, 29);
		add(lblIngredient);
		
		JComboBox cbingredient = new JComboBox();
		cbingredient.setFont(new Font("Tahoma", Font.PLAIN, 20));
		cbingredient.setBounds(412, 313, 301, 29);
		add(cbingredient);

		JLabel lblQuantite = new JLabel("Quantité");
		lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblQuantite.setBounds(247, 378, 135, 29);
		add(lblQuantite);
		
		tqte = new JTextField();
		tqte.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tqte.setBounds(412, 378, 301, 29);
		add(tqte);
		tqte.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Plat plat = (Plat)cbplat.getSelectedItem();
				Ingredient ingredient = (Ingredient)cbingredient.getSelectedItem();
				Float qte = Float.parseFloat(tqte.getText()+"");
				Preparation p = new Preparation(plat.getId(),ingredient.getId(),qte);
				
				new PreparationDAO().save(p);
				JOptionPane.showMessageDialog(null, "Preparation ajoutée.");
				
				AdminHome.replace(new AdminPreparationList());
			}
		});
		btnAjouter.setForeground(Color.WHITE);
		btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnAjouter.setBackground(Color.BLACK);
		btnAjouter.setBounds(412, 459, 174, 52);
		add(btnAjouter);
		
		Database.connect();
		for(Plat p: new PlatDAO().getAll()) {
			cbplat.addItem(p);
		}
		for(Ingredient i: new IngredientDAO().getAll()) {
			cbingredient.addItem(i);
		}
		
	}
}
