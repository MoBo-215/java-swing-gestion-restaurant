package vues;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import dao.CommandeDAO;
import dao.DetailDAO;
import dao.IngredientDAO;
import dao.PlatDAO;
import dao.TableeDAO;
import entites.Commande;
import entites.Database;
import entites.Detail;
import entites.Ingredient;
import entites.Plat;
import entites.Tablee;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.List;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class ServeurHomePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public float totalPrix;

	/**
	 * Create the panel.
	 */
	public ServeurHomePanel() {
		setLayout(null);
		
		JTextField textField = new JTextField();
		textField.setBounds(0, 0, 1030, 80);
		textField.setText("GESTION RESTAURANT");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setForeground(Color.LIGHT_GRAY);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		textField.setColumns(10);
		textField.setBackground(new Color(128, 0, 0));
		add(textField);
				
		JLabel lblCommande = new JLabel("Commande :");
		lblCommande.setBounds(10, 90, 185, 29);
		lblCommande.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblCommande);
		
		List listCommande = new List();
		listCommande.setFont(new Font("Tahoma", Font.PLAIN, 20));
		listCommande.setBounds(10, 125, 270, 224);
		add(listCommande);
				
		DetailDAO ddao = new DetailDAO();
		CommandeDAO cdao = new CommandeDAO();
		JLabel lblCaCmdsJour = new JLabel("<html>CA/jour : "+ddao.getTodayCA(ServeurHome.serveur.getId())+"0€<br>Nb cmds/jour : "+cdao.findCommandesJourServeur(ServeurHome.serveur.getId()).size()+"</html>");
		lblCaCmdsJour.setBounds(658, 130, 170, 74);
		lblCaCmdsJour.setFont(new Font("Tahoma", Font.PLAIN, 17));
		add(lblCaCmdsJour);
		
		JLabel lblNomServeur = new JLabel(ServeurHome.serveur.getPrenom()+" "+ServeurHome.serveur.getNom().toUpperCase());
		lblNomServeur.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNomServeur.setBounds(658, 90, 170, 38);
		add(lblNomServeur);
		
		JButton btnRefresh = new JButton("ACTUALISER");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServeurHome.replace(new ServeurHomePanel());
			}
		});
		btnRefresh.setBounds(838, 90, 170, 38);
		btnRefresh.setForeground(Color.LIGHT_GRAY);
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnRefresh.setBackground(new Color(128, 0, 0));
		add(btnRefresh);
		
		JButton btnCmdJour = new JButton("<html><center>COMMANDES<br>DU JOUR</center></html>");
		btnCmdJour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServeurHome.replace(new ServeurCommandesJour());
			}
		});
		btnCmdJour.setForeground(new Color(0, 0, 0));
		btnCmdJour.setFont(new Font("Tahoma", Font.PLAIN, 17));
		btnCmdJour.setBackground(new Color(255, 255, 0));
		btnCmdJour.setBounds(838, 143, 170, 51);
		add(btnCmdJour);
		
		JLabel lblPrixTotalCmd = new JLabel("0"+totalPrix+"0");
		lblPrixTotalCmd.setBounds(298, 90, 347, 109);
		lblPrixTotalCmd.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblPrixTotalCmd.setForeground(new Color(255, 255, 0));
		lblPrixTotalCmd.setBackground(new Color(0, 0, 128));
		lblPrixTotalCmd.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPrixTotalCmd);
		
		JPanel pprixtotalcmd = new JPanel();
		pprixtotalcmd.setBackground(new Color(0, 0, 128));
		pprixtotalcmd.setBounds(298, 90, 347, 109);
		add(pprixtotalcmd);
		
		JLabel lblCommandesRegler = new JLabel("Commandes à régler : "+cdao.getNbCmdsToPay());
		lblCommandesRegler.setBounds(10, 470, 240, 29);
		lblCommandesRegler.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblCommandesRegler);
		
		JLabel lblListeTablesCmd = new JLabel("Tables à regler :");
		lblListeTablesCmd.setBounds(10, 500, 240, 29);
		lblListeTablesCmd.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblListeTablesCmd);
		
		JButton btnEscalopePoulet = new JButton("<html><center>ESCALOPE<br>POULET</center><html>");
		btnEscalopePoulet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlatDAO pdao = new PlatDAO();
				Plat p = pdao.getById(4);
				listCommande.add(p.getNom());
				totalPrix = totalPrix + p.getPrix();
				lblPrixTotalCmd.setText(totalPrix+"0");
			}
		});
		btnEscalopePoulet.setBounds(838, 216, 170, 169);
		btnEscalopePoulet.setForeground(new Color(255, 255, 255));
		btnEscalopePoulet.setBackground(new Color(0, 0, 255));
		btnEscalopePoulet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(btnEscalopePoulet);
		
		JButton btnFishBurger = new JButton("<html><center>FISH<br>BURGER</center><html>");
		btnFishBurger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlatDAO pdao = new PlatDAO();
				Plat p = pdao.getById(3);
				listCommande.add(p.getNom());
				totalPrix = totalPrix + p.getPrix();
				lblPrixTotalCmd.setText(totalPrix+"0");
			}
		});
		btnFishBurger.setBounds(658, 216, 170, 169);
		btnFishBurger.setForeground(Color.WHITE);
		btnFishBurger.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFishBurger.setBackground(Color.BLUE);
		add(btnFishBurger);
		
		JButton btnChickenBurger = new JButton("<html><center>CHICKEN<br>BURGER</center><html>");
		btnChickenBurger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlatDAO pdao = new PlatDAO();
				Plat p = pdao.getById(2);
				listCommande.add(p.getNom());
				totalPrix = totalPrix + p.getPrix();
				lblPrixTotalCmd.setText(totalPrix+"0");
			}
		});
		btnChickenBurger.setBounds(478, 216, 170, 169);
		btnChickenBurger.setForeground(Color.WHITE);
		btnChickenBurger.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnChickenBurger.setBackground(Color.BLUE);
		add(btnChickenBurger);
		
		JButton btnBeefBurger = new JButton("<html><center>BEEF<br>BURGER</center><html>");
		btnBeefBurger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlatDAO pdao = new PlatDAO();
				Plat p = pdao.getById(1);
				listCommande.add(p.getNom());
				totalPrix = totalPrix + p.getPrix();
				lblPrixTotalCmd.setText(totalPrix+"0");
			}
		});
		btnBeefBurger.setBounds(298, 216, 170, 169);
		btnBeefBurger.setForeground(Color.WHITE);
		btnBeefBurger.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnBeefBurger.setBackground(Color.BLUE);
		add(btnBeefBurger);
		
		JButton btnSteakFrites = new JButton("<html><center>STEAK<br>FRITES</center><html>");
		btnSteakFrites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlatDAO pdao = new PlatDAO();
				Plat p = pdao.getById(5);
				listCommande.add(p.getNom());
				totalPrix = totalPrix + p.getPrix();
				lblPrixTotalCmd.setText(totalPrix+"0");
			}
		});
		btnSteakFrites.setBounds(838, 395, 170, 169);
		btnSteakFrites.setForeground(Color.WHITE);
		btnSteakFrites.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnSteakFrites.setBackground(Color.BLUE);
		add(btnSteakFrites);
		
		JButton btnMousseChoco = new JButton("<html><center>MOUSSE<br>CHOCO</center><html>");
		btnMousseChoco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlatDAO pdao = new PlatDAO();
				Plat p = pdao.getById(12);
				listCommande.add(p.getNom());
				totalPrix = totalPrix + p.getPrix();
				lblPrixTotalCmd.setText(totalPrix+"0");
			}
		});
		btnMousseChoco.setBounds(658, 395, 170, 169);
		btnMousseChoco.setForeground(new Color(0, 0, 0));
		btnMousseChoco.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnMousseChoco.setBackground(new Color(255, 255, 0));
		add(btnMousseChoco);
		
		JButton btnFlan = new JButton("<html><center>FLAN<br>VANILLE</center><html>");
		btnFlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlatDAO pdao = new PlatDAO();
				Plat p = pdao.getById(11);
				listCommande.add(p.getNom());
				totalPrix = totalPrix + p.getPrix();
				lblPrixTotalCmd.setText(totalPrix+"0");
			}
		});
		btnFlan.setBounds(478, 395, 170, 169);
		btnFlan.setForeground(Color.BLACK);
		btnFlan.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnFlan.setBackground(Color.YELLOW);
		add(btnFlan);
		
		JButton btnTiramisu = new JButton("<html><center>TIRAMISU</center><html>");
		btnTiramisu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlatDAO pdao = new PlatDAO();
				Plat p = pdao.getById(10);
				listCommande.add(p.getNom());
				totalPrix = totalPrix + p.getPrix();
				lblPrixTotalCmd.setText(totalPrix+"0");
			}
		});
		btnTiramisu.setBounds(298, 395, 170, 169);
		btnTiramisu.setForeground(Color.BLACK);
		btnTiramisu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnTiramisu.setBackground(Color.YELLOW);
		add(btnTiramisu);
		
		JButton btnCafe = new JButton("<html><center>CAFE</center><html>");
		btnCafe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlatDAO pdao = new PlatDAO();
				Plat p = pdao.getById(9);
				listCommande.add(p.getNom());
				totalPrix = totalPrix + p.getPrix();
				lblPrixTotalCmd.setText(totalPrix+"0");
			}
		});
		btnCafe.setBounds(838, 574, 170, 169);
		btnCafe.setForeground(new Color(0, 0, 0));
		btnCafe.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCafe.setBackground(new Color(0, 255, 0));
		add(btnCafe);
		
		JButton btnEauPlate = new JButton("<html><center>EAU<br>PLATE</center><html>");
		btnEauPlate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlatDAO pdao = new PlatDAO();
				Plat p = pdao.getById(8);
				listCommande.add(p.getNom());
				totalPrix = totalPrix + p.getPrix();
				lblPrixTotalCmd.setText(totalPrix+"0");
			}
		});
		btnEauPlate.setBounds(658, 574, 170, 169);
		btnEauPlate.setForeground(Color.BLACK);
		btnEauPlate.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnEauPlate.setBackground(Color.GREEN);
		add(btnEauPlate);
		
		JButton btnPerrier = new JButton("<html><center>PERRIER</center><html>");
		btnPerrier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlatDAO pdao = new PlatDAO();
				Plat p = pdao.getById(7);
				listCommande.add(p.getNom());
				totalPrix = totalPrix + p.getPrix();
				lblPrixTotalCmd.setText(totalPrix+"0");
			}
		});
		btnPerrier.setBounds(478, 574, 170, 169);
		btnPerrier.setForeground(Color.BLACK);
		btnPerrier.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPerrier.setBackground(Color.GREEN);
		add(btnPerrier);
		
		JButton btnCoca = new JButton("<html><center>COCA</center><html>");
		btnCoca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlatDAO pdao = new PlatDAO();
				Plat p = pdao.getById(6);
				listCommande.add(p.getNom());
				totalPrix = totalPrix + p.getPrix();
				lblPrixTotalCmd.setText(totalPrix+"0");
			}
		});
		btnCoca.setBounds(298, 574, 170, 169);
		btnCoca.setForeground(Color.BLACK);
		btnCoca.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnCoca.setBackground(Color.GREEN);
		add(btnCoca);

		JComboBox cbTableCmd = new JComboBox();
		cbTableCmd.setBounds(95, 356, 185, 29);
		add(cbTableCmd);
		
		JLabel lblTable = new JLabel("Table :");
		lblTable.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTable.setBounds(10, 355, 78, 29);
		add(lblTable);
		
		Database.connect();
		ArrayList<Commande> tabcmds = new CommandeDAO().getAllToPay();
		String columns[] = {"Tables","Prix"};
		String data[][] = new String[tabcmds.size()][columns.length];
		int i=0;
		for(Commande c: tabcmds) {
			TableeDAO tdao = new TableeDAO();
			data[i][0] = tdao.getById(c.getId_tablee()).getDesignation();
			
			float prixtotal=0;
			for(Detail d: ddao.getByIdCmd(c.getId())) {
				prixtotal=prixtotal+d.getPrixu();
			}
			
			data[i][1] = prixtotal+"0€";
			i++;
		}
		DefaultTableModel tabmodel = new DefaultTableModel(data,columns);
		
		JTable tabletabs = new JTable(tabmodel);
		tabletabs.setUpdateSelectionOnSort(false);
		tabletabs.setShowGrid(false);
		tabletabs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tabletabs.setBounds(10, 539, 270, 160);
		add(tabletabs);
		
		JButton btnValider = new JButton("VALIDER");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlatDAO pdao = new PlatDAO();
				CommandeDAO cdao = new CommandeDAO();
				IngredientDAO idao = new IngredientDAO();
				
				Tablee t = (Tablee)cbTableCmd.getSelectedItem();
				Commande c = new Commande(t.getId(),ServeurHome.serveur.getId(),0);
				new CommandeDAO().save(c);
				tabcmds.add(c);
				
				int cmd_ok=-1;
				for(int i=0;i<listCommande.getItemCount();i++) {
					Commande c1 = cdao.getLastCmd();
					Plat p = pdao.getByNom(listCommande.getItem(i).toString());
					Detail d = new Detail(1,p.getPrix(),p.getId(),c1.getId());
					new DetailDAO().save(d);
					
					for(Ingredient in: idao.getAllByIdPlat(p.getId())){
						if(in.getStock()<=0) {
							JOptionPane.showMessageDialog(null, in.getNom()+" : stock vide.","ATTENTION !", JOptionPane.WARNING_MESSAGE);
							cdao.deleteById(c1.getId());
							cmd_ok=0;
							break;
						}else if(in.getStock()<=in.getStock_min()){
							JOptionPane.showMessageDialog(null, in.getNom()+" : stock minimum.","ATTENTION !", JOptionPane.WARNING_MESSAGE);
							in.setStock(in.getStock()-1);
							idao.save(in);
							cmd_ok=1;
						}else {
							in.setStock(in.getStock()-1);
							idao.save(in);
							cmd_ok=1;
						}
					}
				}
				
				if(cmd_ok==0) {
					JOptionPane.showMessageDialog(null, "Commande annulée.","ERROR", JOptionPane.ERROR_MESSAGE);
					listCommande.removeAll();
				}else if(cmd_ok==1) {
					JOptionPane.showMessageDialog(null, "Commande validée.");					
					listCommande.removeAll();
					tabmodel.addRow(new Commande[] {c});
				}
				
				cbTableCmd.setSelectedIndex(0);
				totalPrix=0;
				lblPrixTotalCmd.setText("0"+totalPrix+"0");
				lblCommandesRegler.setText("Commandes à régler : "+cdao.getNbCmdsToPay());
				ServeurHome.replace(new ServeurHomePanel());
			}
		});
		btnValider.setForeground(new Color(255, 255, 255));
		btnValider.setBackground(new Color(255, 0, 0));
		btnValider.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnValider.setBounds(10, 392, 127, 29);
		add(btnValider);
		
		JButton btnRetirer = new JButton("RETIRER");
		btnRetirer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlatDAO pdao = new PlatDAO();
				
				int pos = listCommande.getSelectedIndex();
				if(pos!=-1) {
					totalPrix = totalPrix - pdao.getByNom(listCommande.getSelectedItem()).getPrix();
					if(totalPrix==0) {
						lblPrixTotalCmd.setText("0"+totalPrix+"0");
					}else {
						lblPrixTotalCmd.setText(totalPrix+"0");						
					}
					listCommande.remove(pos);
				}else {
					JOptionPane.showMessageDialog(null, "Choisissez un plat à retirer.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRetirer.setForeground(new Color(255, 255, 255));
		btnRetirer.setBackground(new Color(255, 0, 0));
		btnRetirer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRetirer.setBounds(147, 392, 133, 29);
		add(btnRetirer);
		
		JButton btnViderCommande = new JButton("VIDER COMMANDE");
		btnViderCommande.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listCommande.removeAll();
				totalPrix=0;
				lblPrixTotalCmd.setText("0"+totalPrix+"0");
			}
		});
		btnViderCommande.setForeground(new Color(255, 255, 255));
		btnViderCommande.setBackground(new Color(255, 0, 0));
		btnViderCommande.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnViderCommande.setBounds(10, 431, 270, 29);
		add(btnViderCommande);
		
		JButton btnConfirmerPaiement = new JButton("CONFIRMER PAIEMENT");
		btnConfirmerPaiement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CommandeDAO cdao = new CommandeDAO();
				int row = tabletabs.getSelectedRow();
				int col = tabletabs.getSelectedColumn();
				
				if(row==-1 && col==-1) {
					JOptionPane.showMessageDialog(null, "Sélectionnez une table à régler.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					if(col==1) {
						col=0;
						String desi_table = tabletabs.getModel().getValueAt(row, col).toString();
						Commande c = cdao.getCmdToPay(desi_table);
						c.setPaiement(1);
						cdao.save(c);
						JOptionPane.showMessageDialog(null, "Paiement confirmé.");
						tabmodel.removeRow(row);
						lblCaCmdsJour.setText("<html>CA/jour : "+ddao.getTodayCA(ServeurHome.serveur.getId())+"0€<br>Nb cmds/jour : "+cdao.findCommandesJourServeur(ServeurHome.serveur.getId()).size()+"</html>");
						lblCommandesRegler.setText("Commandes à régler : "+cdao.getNbCmdsToPay());
					}else if(col==0){
						String desi_table = tabletabs.getModel().getValueAt(row, col).toString();
						Commande c = cdao.getCmdToPay(desi_table);
						c.setPaiement(1);
						cdao.save(c);
						JOptionPane.showMessageDialog(null, "Paiement confirmé.");
						tabmodel.removeRow(row);
						lblCaCmdsJour.setText("<html>CA/jour : "+ddao.getTodayCA(ServeurHome.serveur.getId())+"0€<br>Nb cmds/jour : "+cdao.findCommandesJourServeur(ServeurHome.serveur.getId()).size()+"</html>");
						lblCommandesRegler.setText("Commandes à régler : "+cdao.getNbCmdsToPay());						
					}
				}
			}
		});
		btnConfirmerPaiement.setForeground(Color.WHITE);
		btnConfirmerPaiement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnConfirmerPaiement.setBackground(Color.RED);
		btnConfirmerPaiement.setBounds(10, 714, 270, 29);
		add(btnConfirmerPaiement);
		
		Database.connect();
		for(Tablee t: new TableeDAO().getAll()) {
			cbTableCmd.addItem(t);
		}
	}
}
