package app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import model.Categoria;
import model.Producto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	JComboBox cboCateg;
	private JTextField txtDes;
	private JTextField txtEstado;
	private JTextField txtStock;
	private JTextField txtPrecio;
	private JTable tlbSalida1;;
	
	//ASOCIAMIENTO
	DefaultTableModel modelo= new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
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
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 462, 477);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnRegistrar.setBounds(324, 72, 89, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(171, 400, 89, 23);
		contentPane.add(btnListado);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 41, 144, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(27, 41, 85, 14);
		contentPane.add(lblCodigo);
		
		cboCateg = new JComboBox();
		cboCateg.setBounds(122, 164, 144, 22);
		contentPane.add(cboCateg);
		
		JLabel lblCategora = new JLabel("Categor\u00EDa :");
		lblCategora.setBounds(27, 168, 85, 14);
		contentPane.add(lblCategora);
		
		txtDes = new JTextField();
		txtDes.setColumns(10);
		txtDes.setBounds(122, 73, 144, 20);
		contentPane.add(txtDes);
		
		JLabel lblDes = new JLabel("Descripci\u00F3n :");
		lblDes.setBounds(27, 73, 85, 14);
		contentPane.add(lblDes);
		
		JLabel lblEstado = new JLabel("Estado :");
		lblEstado.setBounds(27, 201, 85, 14);
		contentPane.add(lblEstado);
		
		txtEstado = new JTextField();
		txtEstado.setColumns(10);
		txtEstado.setBounds(122, 200, 144, 20);
		contentPane.add(txtEstado);
		
		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 104, 144, 20);
		contentPane.add(txtStock);
		
		JLabel lblStock = new JLabel("Stock :\r\n");
		lblStock.setBounds(27, 104, 85, 14);
		contentPane.add(lblStock);
		
		JLabel lblPrecio = new JLabel("Precio :");
		lblPrecio.setBounds(27, 139, 85, 14);
		contentPane.add(lblPrecio);
		
		
		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 136, 144, 20);
		contentPane.add(txtPrecio);
		
		JButton btnEditar = new JButton("Actualizar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar();
			}
		});
		btnEditar.setBounds(324, 116, 89, 23);
		contentPane.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
			}
		});
		btnEliminar.setBounds(324, 166, 89, 23);
		contentPane.add(btnEliminar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 250, 389, 134);
		contentPane.add(scrollPane);
		
		tlbSalida1 = new JTable();
		tlbSalida1.setBackground(Color.BLACK);
		tlbSalida1.setForeground(Color.GRAY);
		tlbSalida1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CODIGO", "DESCRIPCION", "STOCK", "PRECIO", "CATEGORIA", "ESTADO"
			}
		));
		scrollPane.setViewportView(tlbSalida1);
		
		iniciarDatos();
	}
	EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
	EntityManager em = fabrica.createEntityManager();
	void registrar() {	
		String codigo=leerCodigo();
		String descripcion=leerDes();	
		int stock=leerStock();
		double precio=leerPrecio();	
		int categoria=leerCatg();
		String estado=leerEstado();	
		
		Producto p = new Producto();
		p.setIdprod(codigo);
		p.setDescripcion(descripcion);
		p.setStock(stock);
		p.setPrecio(precio);
		p.setIdcategoria(categoria);
		p.setEstado(estado);
	
		String sql="select p from Producto p where p.idprod = :xidprod ";
		TypedQuery<Producto> query =em.createQuery(sql, Producto.class);
		query.setParameter("xidprod",codigo);
		Producto pr=null;
		try {
			pr = query.getSingleResult();
		
		} catch (Exception e) {
			
		}
		if(pr ==null){
			em.getTransaction().begin();	
			em.persist(p);
			em.getTransaction().commit(); 		
			aviso("Producto Registrado");
		}else {
			
			aviso("Codigo de Producto ya existe");	
		}
		Object aDatos[]= {codigo,descripcion,stock,precio,categoria,estado};
		modelo.addRow(aDatos);
	}	
		
	void eliminar() {
		String codigo=leerCodigo();
		Producto p= em.find(Producto.class, codigo);
		if(p== null) {
				aviso("Codigo no existe");
		}else
		{	
			aviso("Eliminado el Producto!!" );
			em.getTransaction().begin();
			em.remove(p);
			em.getTransaction().commit();
			aviso("Eliminación OK");	
		}

}

			void actualizar() {
				String codigo=leerCodigo();
				String descripcion=leerDes();	
				int stock=leerStock();
				double precio=leerPrecio();	
				int categoria=leerCatg();
				String estado=leerEstado();	
				
						
				Producto p= em.find(Producto.class, codigo);
							
				if(p == null) {
					aviso("Codigo de Producto no existe");	
					
				}else
				{	
					em.getTransaction().begin();
					p.setDescripcion(descripcion);
					p.setStock(stock);
					p.setPrecio(precio);
					p.setIdcategoria(categoria);
					p.setEstado(estado);
					em.merge(p);
					em.getTransaction().commit(); 
					aviso("Producto Actualizado");
						
				} 
				

			}
			
			private void aviso(String string) {
				JOptionPane.showMessageDialog(this, string);
				
			}

	
	
	private String leerEstado() {
		return txtEstado.getText();
	}

	private double leerPrecio() {
		return Double.parseDouble(txtPrecio.getText());
	}

	private int leerStock() {
		return Integer.parseInt(txtStock.getText());
	}

	private String leerDes() {
		return txtDes.getText();
	}

	private String leerCodigo() {
		return txtCodigo.getText();
	}
	
	private int leerCatg() {
		return cboCateg.getSelectedIndex();
	}

	/*void iniciarDatos() {
		String sql = "select * from tb_categorias";
		
		Query query = em.createNativeQuery(sql, Categoria.class);
		List<Categoria> list = (List<Categoria>) query.getResultList();
		
		for(Categoria item:list) {
			cboCateg.addItem(item.getDescripcion());			
		}
	}*/
	void iniciarDatos() {
		String sql = "select a from Categoria a" ;
		List<Categoria> lstCatg=em.createQuery(sql,Categoria.class).getResultList();
		
		for(Categoria item:lstCatg) {
			cboCateg.addItem(item.getDescripcion());			
		}
	}
	
	void listado() {	
		String sql ="Select p from Producto p"; 
		List<Producto> lstProducto=em.createQuery(sql,Producto.class).getResultList();
		System.out.println("Cantidad de Usuarios: "+lstProducto.size());
		for(Producto p: lstProducto) {
			int contenedor = tlbSalida1.getModel().getColumnCount();
			Object [] columnn = new Object[contenedor];
			columnn[0] = p.getIdprod();
			columnn[1] = p.getDescripcion();
			columnn[2] = p.getStock();
			columnn[3] = p.getPrecio();
			columnn[4] = p.getIdcategoria();
			columnn[5] = p.getEstado();
			
			((DefaultTableModel) tlbSalida1.getModel()).addRow(columnn);
	}
	
	
	}
	}
