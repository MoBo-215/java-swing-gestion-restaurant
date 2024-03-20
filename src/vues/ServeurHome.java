package vues;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.ServeurDAO;
import entites.Database;
import entites.Serveur;

import java.awt.BorderLayout;

public class ServeurHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	public static Serveur serveur;

	public JFrame getFrame() {
		return this;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServeurHome frame = new ServeurHome();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	
	/**
	 * Create the frame.
	 */
	public ServeurHome() {
		
	}

	public ServeurHome(Serveur s) {
		serveur=s;
		
		setResizable(false);
		setTitle("Gestion restaurant - Serveur ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 20, 1035, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		ServeurHomePanel panel = new ServeurHomePanel();
		panel.setBounds(0,0,1035,800);
		contentPane.add(panel);
	}
	
	public static void replace(JPanel p) {
		p.setBounds(0,0,1035,800);
		contentPane.removeAll();
		contentPane.add(p);
		contentPane.revalidate();
	}

}
