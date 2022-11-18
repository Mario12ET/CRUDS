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
import Modelo.Producto;
import dao.daoProducto;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class vProducto extends JFrame {

	private JPanel contentPane;
	private JLabel LBLD;
	private JLabel lblID;
	private JLabel lblNewLabel;
	private JTextField txtPRECIO;
	private JTextField txtCAN;
	private JButton btnAGREGAR;
	private JButton btnEDITAR;
	private JButton btnBORRAR;
	private JButton btnELIMINAR;
	private JTextField txtDES;
	private JTable tblProducto;
	daoProducto dao=new daoProducto();
    DefaultTableModel modelo=new DefaultTableModel();
    ArrayList<Producto> lista=new ArrayList<Producto>();
    int fila=-1;
    Producto Producto=new Producto();
    private JTextField txtPROV;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vProducto frame = new vProducto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void limpiar() {
		txtDES.setText("");
		txtPRECIO.setText("");
		txtCAN.setText("");
		txtPROV.setText("");
	}
	public vProducto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\mario\\Downloads\\zyro-image.png"));
		
		setTitle("Producto");
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
		
		LBLD = new JLabel("DESCRIPCION:");
		LBLD.setBounds(36, 39, 89, 14);
		contentPane.add(LBLD);
		
		txtDES = new JTextField();
		txtDES.setBounds(127, 36, 86, 20);
		contentPane.add(txtDES);
		txtDES.setColumns(10);
		
		JLabel lblPassword = new JLabel("PRECIO");
		lblPassword.setBounds(36, 67, 81, 14);
		contentPane.add(lblPassword);
		
		txtPRECIO = new JTextField();
		txtPRECIO.setColumns(10);
		txtPRECIO.setBounds(127, 64, 86, 20);
		contentPane.add(txtPRECIO);
		
		JLabel lblNombre = new JLabel("CANTIDAD");
		lblNombre.setBounds(36, 92, 81, 23);
		contentPane.add(lblNombre);
		
		txtCAN = new JTextField();
		txtCAN.setColumns(10);
		txtCAN.setBounds(127, 95, 86, 20);
		contentPane.add(txtCAN);
		
		btnAGREGAR = new JButton("AGREGAR");
		btnAGREGAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		try {
					Producto producto=new Producto();
					producto.setDescripcion(txtDES.getText());
					producto.setPrecio(txtPRECIO.getText());
					producto.setCantidad(txtCAN.getText());
					producto.setProvedor(txtPROV.getText());
					
					if (dao.insertarProducto(producto)) {
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
					if (dao.eliminarProducto(lista.get(fila).getIdProducto())) {
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
				if (txtDES.getText().equals("")||txtPRECIO.getText().equals("")
						|| txtCAN.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "CAMPOS VACIOS");
					return;
				}
				Producto.setDescripcion(txtDES.getText());
				Producto.setPrecio(txtPRECIO.getText());
				Producto.setCantidad(txtCAN.getText());
				Producto.setProvedor(txtPROV.getText());
				if(dao.editarProducto(Producto)) {
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
		
		tblProducto = new JTable();
		tblProducto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblProducto.getSelectedRow();
				Producto=lista.get(fila);	
				lblID.setText(""+lista.get(fila).getIdProducto());
				txtDES.setText(""+Producto.getDescripcion());
				txtCAN.setText(""+Producto.getCantidad());
				txtPRECIO.setText(""+Producto.getPrecio());
				txtPROV.setText(""+Producto.getProvedor());
				
			}
		});
		tblProducto.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblProducto);
		
		modelo.addColumn("ID");
		modelo.addColumn("DESCRIPCION");
		modelo.addColumn("CANTIDAD");
		modelo.addColumn("PRECIO");
		modelo.addColumn("PROVEDOR");
		tblProducto.setModel(modelo);
		
		JLabel lblNewLabel_1 = new JLabel("PROVEDOR");
		lblNewLabel_1.setBounds(36, 126, 81, 14);
		contentPane.add(lblNewLabel_1);
		
		txtPROV = new JTextField();
		txtPROV.setColumns(10);
		txtPROV.setBounds(127, 126, 86, 20);
		contentPane.add(txtPROV);
		ActualizarTabla();
		}
	public void ActualizarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
	   }
	    lista=dao.fetchProducto();
	    for(Producto u:lista) {
	    	Object o[]=new Object[5];
	    	o[0]=u.getIdProducto();	    				
	    	o[1]=u.getDescripcion();	    		    		
	    	o[2]=u.getCantidad();	 
	    	o[3]=u.getPrecio();
	    	o[4]=u.getProvedor();
	    	modelo.addRow(o);
	    }
	    tblProducto.setModel(modelo);
	   
	    }
	}

