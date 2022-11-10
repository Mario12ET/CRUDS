package vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
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
import Modelo.ALUMNO;
import dao.daoAlumno;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@SuppressWarnings("serial")
public class vALUMNO extends JFrame {

	private JPanel contentPane;
	private JLabel LBLD;
	private JLabel lblID;
	private JLabel lblNewLabel;
	private JButton btnAGREGAR;
	private JButton btnEDITAR;
	private JButton btnBORRAR;
	private JButton btnELIMINAR;
	private JTextField txtNOMBRE;
	private JTable tblALUMNO;
	daoAlumno dao=new daoAlumno();
    DefaultTableModel modelo=new DefaultTableModel();
    ArrayList<ALUMNO> lista=new ArrayList<ALUMNO>();
    int fila=-1;
    ALUMNO alumno=new ALUMNO();
    private JComboBox cbosSEME;
    private JComboBox cboMUN;
    private JComboBox cboGRUPO;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vALUMNO frame = new vALUMNO();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void limpiar() {
		lblID.setText("");
		txtNOMBRE.setText("");
		
	}
	public vALUMNO() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mario\\Downloads\\a.png"));
		setTitle("CRUD USUARIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(36, 11, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblID = new JLabel("1");
		lblID.setBounds(127, 11, 46, 14);
		contentPane.add(lblID);
		
		LBLD = new JLabel("NOMBRE");
		LBLD.setBounds(36, 36, 57, 14);
		contentPane.add(LBLD);
		
		txtNOMBRE = new JTextField();
		txtNOMBRE.setBounds(127, 36, 86, 20);
		contentPane.add(txtNOMBRE);
		txtNOMBRE.setColumns(10);
		
		JLabel lblPassword = new JLabel("GRUPO");
		lblPassword.setBounds(36, 70, 57, 14);
		contentPane.add(lblPassword);
		
		JLabel lblNombre = new JLabel("MUNICIPIO");
		lblNombre.setBounds(36, 97, 86, 14);
		contentPane.add(lblNombre);
		
		btnAGREGAR = new JButton("AGREGAR");
		btnAGREGAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		try {
					ALUMNO alumno=new ALUMNO();
					alumno.setNombre(txtNOMBRE.getText());
					alumno.setGrupo(cboGRUPO.getSelectedItem().toString());
					alumno.setMunicipio(cboMUN.getSelectedItem().toString());
					alumno.setSemestre(cbosSEME.getSelectedItem().toString());
					
					if (dao.insertarAlumno(alumno)) {
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
					if (dao.eliminarAlumno(lista.get(fila).getId())) {
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
				if (txtNOMBRE.getText().equals("")
						|| txtNOMBRE.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "CAMPOS VACIOS");
					return;
				}
				alumno.setNombre(getName());
				alumno.setGrupo(cboGRUPO.getSelectedItem().toString());
				alumno.setMunicipio(cboMUN.getSelectedItem().toString());
				alumno.setSemestre(cbosSEME.getSelectedItem().toString());
				
				if(dao.editarAlumno(alumno)) {
					
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
		
		tblALUMNO = new JTable();
		tblALUMNO.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblALUMNO.getSelectedRow();
				alumno=lista.get(fila);	
				lblID.setText(""+lista.get(fila).getId());
				txtNOMBRE.setText(""+alumno.getNombre());
							}
		});
		tblALUMNO.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblALUMNO);
		
		modelo.addColumn("ID");
		modelo.addColumn("NOMBRE");
		modelo.addColumn("GRUPO");
		modelo.addColumn("MUNICIPIO");
		modelo.addColumn("SEMESTRE");
		tblALUMNO.setModel(modelo);
		
		JLabel lblNewLabel_1 = new JLabel("SEMESTRE");
		lblNewLabel_1.setBounds(36, 130, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		cboGRUPO = new JComboBox();
		cboGRUPO.setModel(new DefaultComboBoxModel(new String[] {"105", "305", "505", "", "\t"}));
		cboGRUPO.setBounds(127, 66, 86, 22);
		contentPane.add(cboGRUPO);
		
		cboMUN = new JComboBox();
		cboMUN.setModel(new DefaultComboBoxModel(new String[] {"TECAMAC", "ZUMPANGO", "TEMAMATLA"}));
		cboMUN.setBounds(127, 93, 86, 22);
		contentPane.add(cboMUN);
		
		cbosSEME = new JComboBox();
		cbosSEME.setModel(new DefaultComboBoxModel(new String[] {"PRIMERO", "TERCERO", "QUINTO"}));
		cbosSEME.setBounds(127, 126, 86, 22);
		contentPane.add(cbosSEME);
		ActualizarTabla();
		}
	public void ActualizarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
	   }
	    lista=dao.fetchALUMNO();
	    for(ALUMNO u:lista) {
	    	Object o[]=new Object[5];
	    	o[0]=u.getId();	    			
	    	o[1]=u.getNombre();	    		    		
	    	o[2]=u.getGrupo();	 
	    	o[3]=u.getMunicipio();
	    	o[4]=u.getSemestre();
	    	modelo.addRow(o);
	    }
	    tblALUMNO.setModel(modelo);
	   
	    }
	}


