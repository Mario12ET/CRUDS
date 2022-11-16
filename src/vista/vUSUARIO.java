package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Modelo.USUARIO;
import dao.daoUSUARIO;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class vUSUARIO extends JInternalFrame {

	private JPanel contentPane;
	private JLabel LBLD;
	private JLabel lblID;
	private JLabel lblNewLabel;
	private JTextField txtPASSWORD;
	private JTextField txtNOMBRE;
	private JButton btnAGREGAR;
	private JButton btnEDITAR;
	private JButton btnBORRAR;
	private JButton btnELIMINAR;
	private JTextField txtUSER;
	private JTable tblUSUARIOS;
	daoUSUARIO dao=new daoUSUARIO();
    DefaultTableModel modelo=new DefaultTableModel();
    ArrayList<USUARIO> lista=new ArrayList<USUARIO>();
    int fila=-1;
    USUARIO usuario=new USUARIO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vUSUARIO frame = new vUSUARIO();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void limpiar() {
		lblID.setText("");
		txtUSER.setText("");
		txtPASSWORD.setText("");
		txtNOMBRE.setText("");
	}
	public vUSUARIO() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mario\\Downloads\\a.png"));
		setTitle("CRUD USUARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(36, 30, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblID = new JLabel("1");
		lblID.setBounds(127, 30, 46, 14);
		contentPane.add(lblID);
		
		LBLD = new JLabel("USUARIO:");
		LBLD.setBounds(36, 58, 57, 14);
		contentPane.add(LBLD);
		
		txtUSER = new JTextField();
		txtUSER.setBounds(127, 55, 86, 20);
		contentPane.add(txtUSER);
		txtUSER.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(36, 92, 57, 14);
		contentPane.add(lblPassword);
		
		txtPASSWORD = new JTextField();
		txtPASSWORD.setColumns(10);
		txtPASSWORD.setBounds(127, 86, 86, 20);
		contentPane.add(txtPASSWORD);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(36, 128, 57, 14);
		contentPane.add(lblNombre);
		
		txtNOMBRE = new JTextField();
		txtNOMBRE.setColumns(10);
		txtNOMBRE.setBounds(127, 117, 86, 20);
		contentPane.add(txtNOMBRE);
		
		btnAGREGAR = new JButton("AGREGAR");
		btnAGREGAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		try {
					USUARIO user=new USUARIO();
					user.setUser(txtUSER.getText());
					user.setPassword(txtNOMBRE.getText());
					user.setNombre(txtPASSWORD.getText());
					
					if (dao.insertaUsuario(user)) {
						JOptionPane.showMessageDialog(null, "SE AGREGO CORRECTAMENTE");
					}else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR");
				}
		            ActualizarTabla();
			}
		});
		btnAGREGAR.setBounds(22, 171, 89, 23);
		contentPane.add(btnAGREGAR);
		
		btnELIMINAR = new JButton("ELIMINAR");
		btnELIMINAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion=JOptionPane.showConfirmDialog(null,"ESTA SEGURO DE ELIMINAR ESTE USUARIO?");
					if(opcion==0)
					if (dao.eliminarUsuario(lista.get(fila).getId())) {
						 ActualizarTabla();
						 limpiar();
						JOptionPane.showMessageDialog(null, "SE ELIMINO CORRECTAMENTE");
					}else {
						JOptionPane.showMessageDialog(null, "ERROR");
					}
				} catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "ERROR");
				}
		           
			}
			
		});
		btnELIMINAR.setBounds(127, 171, 89, 23);
		contentPane.add(btnELIMINAR);
		
		btnEDITAR = new JButton("EDITAR");
		btnEDITAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				if (txtUSER.getText().equals("")||txtPASSWORD.getText().equals("")
						|| txtNOMBRE.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "CAMPOS VACIOS");
					return;
				}
				usuario.setUser(txtUSER.getText());
				usuario.setPassword(txtPASSWORD.getText());
				usuario.setNombre(txtNOMBRE.getText());
				if(dao.editarUsuario(usuario)) {
				ActualizarTabla();
				limpiar();
				JOptionPane.showMessageDialog(null, "SE ACTUALIZO CORRECTAMENTE!!");
				}else {
					JOptionPane.showMessageDialog(null, "ERROR");
			    }
			  
			}catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "ERROR");
			}
			}
		});
		btnEDITAR.setBounds(330, 171, 89, 23);
		contentPane.add(btnEDITAR);
		
		btnBORRAR = new JButton("BORRAR");
		btnBORRAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    limpiar();
			}
		});
		btnBORRAR.setBounds(221, 171, 89, 23);
		contentPane.add(btnBORRAR);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 219, 397, 219);
		contentPane.add(scrollPane);
		
		tblUSUARIOS = new JTable();
		tblUSUARIOS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblUSUARIOS.getSelectedRow();
				usuario=lista.get(fila);	
				lblID.setText(""+lista.get(fila).getId());
				txtUSER.setText(""+usuario.getId());
				txtPASSWORD.setText(""+usuario.getPassword());
				txtNOMBRE.setText(""+usuario.getNombre());
				
			}
		});
		tblUSUARIOS.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblUSUARIOS);
		
		modelo.addColumn("ID");
		modelo.addColumn("USER");
		modelo.addColumn("PASSWORD");
		modelo.addColumn("NOMBRE");
		tblUSUARIOS.setModel(modelo);
		ActualizarTabla();
		}
	public void ActualizarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
	   }
	    lista=dao.fetchUsuarios();
	    for(USUARIO u:lista) {
	    	Object o[]=new Object[4];
	    	o[0]=u.getId();	    				
	    	o[1]=u.getUser();	    		    		
	    	o[2]=u.getNombre();	 
	    	o[3]=u.getPassword();
	    	modelo.addRow(o);
	    }
	    tblUSUARIOS.setModel(modelo);
	   
	    }
	}

