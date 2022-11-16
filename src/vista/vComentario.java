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
import Modelo.Comentario;
import dao.daoComentario;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class vComentario extends JFrame {

	private JPanel contentPane;
	private JLabel LBLD;
	private JLabel lblID;
	private JLabel lblNewLabel;
	private JButton btnAGREGAR;
	private JButton btnEDITAR;
	private JButton btnBORRAR;
	private JButton btnELIMINAR;
	private JTextField txtComentario;
	private JTable tblComentarioS;
	daoComentario dao=new daoComentario();
    DefaultTableModel modelo=new DefaultTableModel();
    ArrayList<Comentario> lista=new ArrayList<Comentario>();
    int fila=-1;
    Comentario Comentario=new Comentario();
    private JTextField txtUsuario;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vComentario frame = new vComentario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public void limpiar() {
		lblID.setText("");
		txtComentario.setText("");
		txtUsuario.setText("");
		txtComentario.setText("");	}
	public vComentario() {

		setTitle("CRUD Comentario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 469, 556);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(36, 30, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblID = new JLabel("1");
		lblID.setBounds(127, 30, 46, 14);
		contentPane.add(lblID);
		
		LBLD = new JLabel("COMENTARIO");
		LBLD.setBounds(36, 58, 81, 14);
		contentPane.add(LBLD);
		
		txtComentario= new JTextField();
		txtComentario.setBounds(127, 55, 86, 20);
		contentPane.add(txtComentario);
		txtComentario.setColumns(10);
		
		JLabel lbl = new JLabel("USUARIO");
		lbl.setBounds(36, 89, 57, 14);
		contentPane.add(lbl);
		
		btnAGREGAR = new JButton("AGREGAR");
		btnAGREGAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		try {
					Comentario com=new Comentario();
					com.setTexto(txtComentario.getText());
					com.setUsuario(txtUsuario.getText());					
					if (dao.insertaComentario(com)) {
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
					int opcion=JOptionPane.showConfirmDialog(null,"ESTA SEGURO DE ELIMINAR ESTE Comentario?");
					if(opcion==0)
					if (dao.eliminarComentario(lista.get(fila).getId())) {
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
				if (txtComentario.getText().equals("")||txtUsuario.getText().equals("")){
					JOptionPane.showMessageDialog(null, "CAMPOS VACIOS");
					return;
				}
				Comentario.setTexto(txtComentario.getText());
				Comentario.setUsuario(txtUsuario.getText());
				if(dao.editarComentario(Comentario)) {
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
		
		tblComentarioS = new JTable();
		tblComentarioS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblComentarioS.getSelectedRow();
				Comentario=lista.get(fila);	
				lblID.setText(""+lista.get(fila).getId());
				txtComentario.setText(""+Comentario.getId());
				txtComentario.setText(""+Comentario.getTexto());
				txtUsuario.setText(""+Comentario.getUsuario());
				
			}
		});
		tblComentarioS.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblComentarioS);
		
		modelo.addColumn("ID");
		modelo.addColumn("TEXTO");
		modelo.addColumn("USUARIO");
		tblComentarioS.setModel(modelo);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(127, 86, 86, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		ActualizarTabla();
		}
	public void ActualizarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
	   }
	    lista=dao.fetchComentarios();
	    for(Comentario u:lista) {
	    	Object o[]=new Object[4];
	    	o[0]=u.getId();	    				
	    	o[1]=u.getTexto();	    		    		
	    	o[2]=u.getUsuario();	 
	    	modelo.addRow(o);
	    }
	    tblComentarioS.setModel(modelo);
	   
	    }
	}

