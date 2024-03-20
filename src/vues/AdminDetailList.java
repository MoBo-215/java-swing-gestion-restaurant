package vues;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;

import dao.DetailDAO;
import dao.PlatDAO;
import entites.Detail;
import entites.Database;

public class AdminDetailList extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AdminDetailList() {
		setLayout(null);

		JTextField textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminHome.replace(new AdminHomePanel());
			}
		});
		textField.setBounds(0, 0, 1030, 80);
		textField.setText("GESTION RESTAURANT");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField.setColumns(10);
		textField.setBackground(new Color(128, 0, 0));
		add(textField);
		
		JLabel lblDetailList = new JLabel("Admin - Liste des détails");
		lblDetailList.setBounds(0, 83, 1030, 51);
		lblDetailList.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetailList.setFont(new Font("Tahoma", Font.PLAIN, 25));
		add(lblDetailList);
		
		Database.connect();
		ArrayList<Detail> details = new DetailDAO().getAll();
		String columns[] = {"ID", "QUANTITE", "PRIX UNITAIRE", "PLAT", "COMMANDE"};
		String data[][] = new String[details.size()][columns.length];
		int i=0;
		for(Detail s: details) {
			data[i][0]=s.getId()+"";
			data[i][1]=s.getQtec()+"";
			data[i][2]=s.getPrixu()+"";
			data[i][3]=new PlatDAO().getById(s.getId_plat()).getNom();
			data[i][4]=s.getId_commande()+"";
			i++;
		}
		DefaultTableModel model = new DefaultTableModel(data,columns);
		
		JTable table = new JTable(model);
		table.setBounds(1, 25, 823, 0);
		table.setRowHeight(30);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 153, 825, 521);
		scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = Color.BLACK;
		    }
		});
		scrollPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = Color.BLACK;
		    }
		});
		add(scrollPane);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.setBounds(845, 153, 170, 40);
		btnModifier.setForeground(new Color(255, 255, 255));
		btnModifier.setBackground(new Color(0, 0, 0));
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1) {
					int rowIndex = table.getSelectedRow();
					int selectedId = Integer.parseInt(data[rowIndex][0]);
					Detail p = new DetailDAO().getById(selectedId);
					
					AdminHome.replace(new AdminDetailEdit(p));
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez un detail à modifier.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(845, 203, 170, 40);
		btnSupprimer.setForeground(new Color(255, 255, 255));
		btnSupprimer.setBackground(new Color(0, 0, 0));
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() != -1) {	
					int input = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer ce detail ?", "ATTENTION !", JOptionPane.YES_NO_OPTION); 
					if(input==0) {
						int rowIndex = table.getSelectedRow();	
						int selectedId = Integer.parseInt(data[rowIndex][0]); 
						new DetailDAO().deleteById(selectedId); 
						
						model.removeRow(rowIndex);
						JOptionPane.showMessageDialog(null, "Detail supprimé.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez un detail à supprimer.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(btnSupprimer);
		
		JTextField trechercher = new JTextField();
		trechercher.setBounds(845, 532, 171, 40);
		trechercher.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(trechercher);
		trechercher.setColumns(10);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.setBounds(845, 582, 170, 40);
		btnRechercher.setForeground(new Color(255, 255, 255));
		btnRechercher.setBackground(new Color(0, 0, 0));
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String txt = trechercher.getText();
				ArrayList<Detail> details = new DetailDAO().findDetails(txt);
				String columns[] = {"ID", "QUANTITE", "PRIX UNITAIRE", "PLAT", "COMMANDE"};
				String data[][] = new String[details.size()][columns.length];
				int i=0;
				for(Detail s: details) {
					data[i][0]=s.getId()+"";
					data[i][1]=s.getQtec()+"";
					data[i][2]=s.getPrixu()+"";
					data[i][3]=s.getId_plat()+"";
					data[i][4]=s.getId_commande()+"";
					i++;
				}
				DefaultTableModel model = new DefaultTableModel(data,columns);
				table.setModel(model);
			}
		});
		btnRechercher.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(btnRechercher);

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(845, 634, 170, 40);
		btnAnnuler.setForeground(new Color(255, 255, 255));
		btnAnnuler.setBackground(new Color(0, 0, 0));
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Detail> details = new DetailDAO().getAll();
				String columns[] = {"ID", "QUANTITE", "PRIX UNITAIRE", "PLAT", "COMMANDE"};
				String data[][] = new String[details.size()][columns.length];
				int i=0;
				for(Detail s: details) {
					data[i][0]=s.getId()+"";
					data[i][1]=s.getQtec()+"";
					data[i][2]=s.getPrixu()+"";
					data[i][3]=s.getId_plat()+"";
					data[i][4]=s.getId_commande()+"";
					i++;
				}
				DefaultTableModel model = new DefaultTableModel(data,columns);
				table.setModel(model);
			}
		});
		btnAnnuler.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(btnAnnuler);
		
		trechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRechercher.doClick();
			}
		});
	}

}
