package vista;

import java.awt.EventQueue;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JProgressBar;

public class vCargando extends JFrame {

	private JPanel contentPane;
	private JProgressBar BarCargando=new JProgressBar();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vCargando frame = new vCargando();
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
	public void cargar() {
		Thread hilo=new Thread(new Runnable() {
			
			@Override
			public void run() {
				for( int i=0;i<101;i++) {
					BarCargando.setValue(i);
					try {
						Thread.sleep(10);
					}catch (InterruptedException e) {
						e.printStackTrace();
					}
				
				if(i ==100) {
					setVisible(false);
					vPrincipal p=new vPrincipal();
					p.setVisible(true);
				}
				}
			}
		});
		hilo.start();
	}
	public vCargando() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 90);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setUndecorated(true);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		BarCargando = new JProgressBar();
		BarCargando.setStringPainted(true);
		BarCargando.setBounds(0, 0, 450, 90);
		contentPane.add(BarCargando);
		cargar();
	}
}
