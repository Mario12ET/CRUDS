package vista;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vPrincipal extends JFrame {
	private JDesktopPane desktopPane;
	double ancho=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	double alto=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	private JButton btnNewButton;
	private JMenuBar menuBar;
	private JPanel contentPane;
    private JToolBar barraHerramientas;
    vUSUARIO vUSUARIO=new vUSUARIO();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPrincipal frame = new vPrincipal();
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
	public vPrincipal() {
		setTitle("SISTEMA POS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1306, 22);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("CRUDS");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("USUARIO");
		mnNewMenu.add(mntmNewMenuItem);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 22, 424, 28);
		contentPane.add(toolBar);
		
		btnNewButton = new JButton("CRUD USUARIOS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vUSUARIO.setVisible(true);
				
			}
		});
		toolBar.add(btnNewButton);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(0, 53, 408, 221);
		desktopPane.setSize((int)ancho,(int)alto);
		contentPane.add(desktopPane);
		desktopPane.add(vUSUARIO);
	}
}
