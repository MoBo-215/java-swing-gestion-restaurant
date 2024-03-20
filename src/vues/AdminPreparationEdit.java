package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class AdminPreparationEdit extends JPanel {

	private static final long serialVersionUID = 1L;
	private Preparation preparation;

	/**
	 * Create the panel.
	 */
	public AdminPreparationEdit(Preparation p) {
		this.preparation=p;
		
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
		
		JLabel lblPreparationEdit = new JLabel("Admin - Modifier une préparation");
		lblPreparationEdit.setHorizontalAlignment(SwingConstants.CENTER);
		lblPreparationEdit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPreparationEdit.setBounds(0, 83, 1030, 51);
		add(lblPreparationEdit);
		
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
		
		JTextField tqte = new JTextField();
		tqte.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tqte.setBounds(412, 378, 301, 29);
		add(tqte);
		tqte.setColumns(10);
		
		JButton btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.connect();
				Plat plat = (Plat)cbplat.getSelectedItem();
				preparation.setId_plat(plat.getId());
				Ingredient ingredient = (Ingredient)cbingredient.getSelectedItem();
				preparation.setId_ingredient(ingredient.getId());
				preparation.setQte(Float.parseFloat(tqte.getText()));
				
				new PreparationDAO().save(preparation);
				JOptionPane.showMessageDialog(null, "Préparation modifiée.");
				
				AdminHome.replace(new AdminPreparationList());
			}
		});
		btnEnregistrer.setForeground(Color.WHITE);
		btnEnregistrer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEnregistrer.setBackground(Color.BLACK);
		btnEnregistrer.setBounds(412, 459, 174, 52);
		add(btnEnregistrer);
		
		Database.connect();
		for(Plat pl: new PlatDAO().getAll()) {
			cbplat.addItem(pl);
		}
		for(Ingredient i: new IngredientDAO().getAll()) {
			cbingredient.addItem(i);
		}
		
		Plat plat = new PlatDAO().getById(preparation.getId_plat());
		cbplat.setSelectedItem(plat);
		Ingredient ingredient = new IngredientDAO().getById(preparation.getId_ingredient());
		cbingredient.setSelectedItem(ingredient);
		tqte.setText(preparation.getQte()+"");
	}

}
