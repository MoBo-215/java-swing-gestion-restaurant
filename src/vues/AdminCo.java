package vues;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.AdminDAO;
import dao.ServeurDAO;
import entites.Admin;
import entites.Database;
import entites.Serveur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class AdminCo {

	private JFrame frame;
	private JTextField temail;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminCo window = new AdminCo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AdminCo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Gestion restaurant - Connexion Administrateur");
		frame.setBounds(250, 20, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelGestionRestaurant = new JPanel();
		panelGestionRestaurant.setBackground(new Color(128, 0, 0));
		panelGestionRestaurant.setForeground(new Color(255, 255, 255));
		panelGestionRestaurant.setBounds(0, 0, 786, 80);
		frame.getContentPane().add(panelGestionRestaurant);
		panelGestionRestaurant.setLayout(null);
		
		JTextField txtGestionRestaurant = new JTextField();
		txtGestionRestaurant.setText("GESTION RESTAURANT");
		txtGestionRestaurant.setBackground(new Color(128, 0, 0));
		txtGestionRestaurant.setForeground(new Color(192, 192, 192));
		txtGestionRestaurant.setBounds(0, 0, 786, 80);
		txtGestionRestaurant.setFont(new Font("Tahoma", Font.PLAIN, 30));
		txtGestionRestaurant.setHorizontalAlignment(SwingConstants.CENTER);
		panelGestionRestaurant.add(txtGestionRestaurant);
		txtGestionRestaurant.setColumns(10);
		
		JLabel lblAdminCo = new JLabel("CONNEXION ADMINISTRATEUR");
		lblAdminCo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminCo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAdminCo.setBounds(0, 111, 786, 51);
		frame.getContentPane().add(lblAdminCo);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(152, 235, 162, 44);
		frame.getContentPane().add(lblEmail);
		
		temail = new JTextField();
		temail.setText("bill.gates@apple.com");
		temail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		temail.setBounds(338, 235, 303, 44);
		frame.getContentPane().add(temail);
		temail.setColumns(10);

		JLabel lblMdp = new JLabel("MOT DE PASSE");
		lblMdp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMdp.setBounds(152, 300, 162, 44);
		frame.getContentPane().add(lblMdp);
		
		JPasswordField tmdp = new JPasswordField();
		tmdp.setEchoChar('*');
		tmdp.setText("iphone");
		tmdp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tmdp.setColumns(10);
		tmdp.setBounds(338, 300, 303, 44);
		frame.getContentPane().add(tmdp);
		
		JButton btnConnecter = new JButton("SE CONNECTER");
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.connect();
				String email = temail.getText();
				String mdp = tmdp.getText();
				AdminDAO adao = new AdminDAO();
				Admin a = adao.getAdminByEmailMdp(email, mdp);
				
				if(a==null) {
					JOptionPane.showMessageDialog(null, "Informations incorrectes.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					AdminHome ah = new AdminHome();
					ah.getFrame().setVisible(true);
					frame.dispose();
				}
			}
		});
		btnConnecter.setForeground(new Color(192, 192, 192));
		btnConnecter.setBackground(new Color(128, 0, 0));
		btnConnecter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConnecter.setBounds(286, 400, 191, 57);
		frame.getContentPane().add(btnConnecter);
	}
}
