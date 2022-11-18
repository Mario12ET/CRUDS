package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.USUARIO;
import dao.daoUSUARIO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vLogin extends JFrame {
	private JLabel lblUsuario;
	private JButton btnCancelar;
	private JPanel contentPane;
	private JButton btnEntrar;
	private JPasswordField txtPASS;
	private JTextField txtUser;
	private JLabel lblNewLabel_1;
	daoUSUARIO dao=new daoUSUARIO();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vLogin frame = new vLogin();
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
	public vLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setUndecorated(true);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUsuario = new JLabel("USUARIO:");
		lblUsuario.setBounds(10, 31, 66, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblNewLabel = new JLabel("PASSWORD:");
		lblNewLabel.setBounds(10, 104, 121, 14);
		contentPane.add(lblNewLabel);
		
		btnEntrar = new JButton("ENTRAR");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				USUARIO user=new USUARIO();
				user.setUser(txtUser.getText());
				user.setPassword(String.valueOf(txtPASS.getPassword()));
				if(dao.loginUsuario(user)) {
				JOptionPane.showMessageDialog(null,"BIENVENIDO");
				vCargando cargando=new vCargando();
				setVisible(false);
				cargando.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null,"USUARIO y/o CONTRASEÑA INCORRECTA");
				}
			}
		});
		btnEntrar.setBounds(10, 160, 89, 23);
		contentPane.add(btnEntrar);
		
		btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "ADIOS");
				System.exit(0);
			}
		});
		btnCancelar.setBounds(109, 160, 99, 23);
		contentPane.add(btnCancelar);
		txtPASS = new JPasswordField();
		txtPASS.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtPASS.getText().length()>=10) {
					e.consume();
				}
			}
		});
		txtPASS.setBounds(10, 129, 212, 20);
		contentPane.add(txtPASS);
		
		txtUser = new JTextField();
		txtUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txtUser.getText().length()>9) {
					e.consume();
					}
			}
		});
		txtUser.setBounds(10, 56, 212, 20);
		contentPane.add(txtUser);
		txtUser.setColumns(10);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\mario\\Downloads\\d.png"));
		lblNewLabel_1.setBounds(232, 30, 201, 153);
		contentPane.add(lblNewLabel_1);
	}
}
