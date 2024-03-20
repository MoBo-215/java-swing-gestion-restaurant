package vues;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.ServeurDAO;
import entites.Database;
import entites.Serveur;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServeurCo {

	private JFrame frame;
	
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
					ServeurCo window = new ServeurCo();
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
	public ServeurCo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Gestion restaurant - Connexion serveur");
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
		
		JLabel lblServeurCo = new JLabel("CONNEXION SERVEUR");
		lblServeurCo.setHorizontalAlignment(SwingConstants.CENTER);
		lblServeurCo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblServeurCo.setBounds(0, 111, 786, 51);
		frame.getContentPane().add(lblServeurCo);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblEmail.setBounds(158, 231, 162, 44);
		frame.getContentPane().add(lblEmail);
		
		JTextField temail = new JTextField();
		temail.setText("mike.tyson@gmail.com");
		temail.setFont(new Font("Tahoma", Font.PLAIN, 20));
		temail.setColumns(10);
		temail.setBounds(344, 231, 303, 44);
		frame.getContentPane().add(temail);
		
		JLabel lblMdp = new JLabel("MOT DE PASSE");
		lblMdp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMdp.setBounds(158, 296, 162, 44);
		frame.getContentPane().add(lblMdp);
		
		JPasswordField tmdp = new JPasswordField();
		tmdp.setEchoChar('*');
		tmdp.setText("dirtydancing");
		tmdp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tmdp.setColumns(10);
		tmdp.setBounds(344, 296, 303, 44);
		frame.getContentPane().add(tmdp);
		
		JButton btnConnecter = new JButton("SE CONNECTER");
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Database.connect();
				String email = temail.getText();
				String mdp = tmdp.getText();
				ServeurDAO sdao = new ServeurDAO();
				Serveur s = sdao.getServeurByEmailMdp(email, mdp);
				
				if(s==null) {
					JOptionPane.showMessageDialog(null, "Informations incorrectes.", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					ServeurHome sh = new ServeurHome(s);
					sh.getFrame().setVisible(true);
					JOptionPane.showMessageDialog(null, "Hello "+ServeurHome.serveur.getPrenom()+" ! *(^-^)*", "Serveur - Connexion", JOptionPane.INFORMATION_MESSAGE);
					frame.dispose();
				}
			}
		});
		btnConnecter.setForeground(Color.LIGHT_GRAY);
		btnConnecter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnConnecter.setBackground(new Color(128, 0, 0));
		btnConnecter.setBounds(292, 396, 191, 57);
		frame.getContentPane().add(btnConnecter);
	}

}
