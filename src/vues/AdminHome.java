package vues;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AdminHome extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	
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
					AdminHome frame = new AdminHome();
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
	public AdminHome() {
		setResizable(false);
		setTitle("Gestion restaurant - Administrateur");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 20, 1045, 745);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		AdminHomePanel panel = new AdminHomePanel();
		contentPane.add(panel);
	}

	public static void replace(JPanel p) {
		p.setBounds(0,0,1035,735);
		contentPane.removeAll();
		contentPane.add(p);
		contentPane.revalidate();
	}
}
