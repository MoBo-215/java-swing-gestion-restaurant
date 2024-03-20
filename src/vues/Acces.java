package vues;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Acces {

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
					Acces window = new Acces();
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
	public Acces() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Gestion restaurant - Accès");
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
		
		JLabel lblAccesLogiciel = new JLabel("ACCES LOGICIEL DE GESTION");
		lblAccesLogiciel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblAccesLogiciel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccesLogiciel.setBounds(0, 111, 786, 51);
		frame.getContentPane().add(lblAccesLogiciel);

		JButton btnAdminCo = new JButton("ADMINISTRATEUR");
		btnAdminCo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AdminCo a = new AdminCo();
				a.getFrame().setVisible(true);
			}
		});
		btnAdminCo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAdminCo.setBackground(new Color(128, 0, 0));
		btnAdminCo.setForeground(new Color(192, 192, 192));
		btnAdminCo.setBounds(71, 258, 299, 150);
		frame.getContentPane().add(btnAdminCo);
		
		JButton btnServeurCo = new JButton("SERVEUR");
		btnServeurCo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ServeurCo s = new ServeurCo();
				s.getFrame().setVisible(true);
			}
		});
		btnServeurCo.setForeground(Color.LIGHT_GRAY);
		btnServeurCo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnServeurCo.setBackground(new Color(128, 0, 0));
		btnServeurCo.setBounds(420, 258, 299, 150);
		frame.getContentPane().add(btnServeurCo);
	}
}
