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

import dao.CommandeDAO;
import dao.ServeurDAO;
import dao.TableeDAO;
import entites.Database;
import entites.Commande;

public class AdminCommandeList extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AdminCommandeList() {
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
		textField.setBounds(0, 0, 1030, 80);
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField.setColumns(10);
		textField.setBackground(new Color(128, 0, 0));
		add(textField);
		
		JLabel lblCommandeList = new JLabel("Admin - Liste des commandes");
		lblCommandeList.setBounds(0, 83, 1030, 51);
		lblCommandeList.setHorizontalAlignment(SwingConstants.CENTER);
		lblCommandeList.setFont(new Font("Tahoma", Font.PLAIN, 25));
		add(lblCommandeList);
		
		Database.connect();
		ArrayList<Commande> commandes = new CommandeDAO().getAll();
		String columns[] = {"ID", "DATE", "SERVEUR", "TABLE", "PAIEMENT"};
		String data[][] = new String[commandes.size()][columns.length];
		int i=0;
		for(Commande s: commandes) {
			data[i][0]=s.getId()+"";
			data[i][1]=s.getDateh()+"";
			data[i][2]=new ServeurDAO().getById(s.getId_serveur()).getPrenom()+" "+new ServeurDAO().getById(s.getId_serveur()).getNom().toUpperCase();
			data[i][3]=new TableeDAO().getById(s.getId_tablee()).getDesignation();
			data[i][4]=s.getPaiement()+"";
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
					Commande p = new CommandeDAO().getById(selectedId);
					
					AdminHome.replace(new AdminCommandeEdit(p));
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez une commande à modifier.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
					int input = JOptionPane.showConfirmDialog(null, "Etes-vous sûr de vouloir supprimer cette commande ?", "ATTENTION !", JOptionPane.YES_NO_OPTION); 
					if(input==0) {
						int rowIndex = table.getSelectedRow();	
						int selectedId = Integer.parseInt(data[rowIndex][0]); 
						new CommandeDAO().deleteById(selectedId); 
						
						model.removeRow(rowIndex);
						JOptionPane.showMessageDialog(null, "Commande supprimée.");
					}
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez une commande à supprimer.", "ERROR", JOptionPane.ERROR_MESSAGE);
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
				ArrayList<Commande> commandes = new CommandeDAO().findCommandes(txt);
				String columns[] = {"ID", "DATE", "SERVEUR", "TABLE", "PAIEMENT"};
				String data[][] = new String[commandes.size()][columns.length];
				int i=0;
				for(Commande s: commandes) {
					data[i][0]=s.getId()+"";
					data[i][1]=s.getDateh()+"";
					data[i][2]=new ServeurDAO().getById(s.getId_serveur()).getPrenom()+" "+new ServeurDAO().getById(s.getId_serveur()).getPrenom();
					data[i][3]=new TableeDAO().getById(s.getId_tablee()).getDesignation();
					data[i][4]=s.getPaiement()+"";
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
				ArrayList<Commande> commandes = new CommandeDAO().getAll();
				String columns[] = {"ID", "DATE", "SERVEUR", "TABLE", "PAIEMENT"};
				String data[][] = new String[commandes.size()][columns.length];
				int i=0;
				for(Commande s: commandes) {
					data[i][0]=s.getId()+"";
					data[i][1]=s.getDateh()+"";
					data[i][2]=new ServeurDAO().getById(s.getId_serveur()).getPrenom()+" "+new ServeurDAO().getById(s.getId_serveur()).getPrenom();
					data[i][3]=new TableeDAO().getById(s.getId_tablee()).getDesignation();
					data[i][4]=s.getPaiement()+"";
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
