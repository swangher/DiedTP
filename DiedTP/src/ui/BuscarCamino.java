package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import entidades.Camino;
import entidades.Sucursal;
import gestores.GestorCamino;
import gestores.GestorSucursal;
import main.Administrador;


public class BuscarCamino extends JFrame {
	private JTable table_2;
	private JPanel panel;
	private JTextField campoNombre;
	private JComboBox<String> comboBox_1;
	
	
	public BuscarCamino() throws ClassNotFoundException, SQLException {
		
		
		
		setTitle("Busqueda de camino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(panel);
		panel.setLayout(null);
	
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.setBounds(64, 408, 89, 23);
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnAplicar);
		

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(154, 408, 89, 23);
		panel.add(btnCancelar);
		
		btnCancelar.addActionListener(volverAnterior());
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 225, 469, 118);
		panel.add(scrollPane);
	
		table_2 = new JTable();
		table_2.setSurrendersFocusOnKeystroke(true);
		
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int seleccionar = table_2.rowAtPoint(e.getPoint());
				
				
			}
		});
		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table_2);
	    datosTabla();

		JButton btnDarAlta = new JButton("Dar alta");
		btnDarAlta.setBounds(323, 350, 98, 23);
		btnDarAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnDarAlta);
		
		
		JButton btnDarBaja = new JButton("Dar baja");
		btnDarBaja.setBounds(433, 350, 89, 23);
	    panel.add(btnDarBaja);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setBounds(64, 350, 89, 23);
		panel.add(btnEditar);
		btnEditar.addActionListener(mostrarPantalla2());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(51, 207, 496, 177);
		panel.add(panel_1);
		panel_1.setBorder(BorderFactory.createTitledBorder("Resultado"));
		
		JPanel panelBusqueda = new JPanel();
		panelBusqueda.setBounds(51, 78, 496, 118);
		panelBusqueda.setBorder(BorderFactory.createTitledBorder("Búsqueda"));
		panel.add(panelBusqueda);
		panelBusqueda.setLayout(null);
		
       /*
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(10, 84, 76, 23);
		panelBusqueda.add(btnBuscar);
		 */ 
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 55, 46, 14);
		panelBusqueda.add(lblNewLabel);
				
		campoNombre = new JTextField();
		campoNombre.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				String M_datos[][]= null;
				TableRowSorter<TableModel> trs; 
				String[] Titulos = {"IDSucursal", "Nombre", "Capacidad", "Estado", "Apertura", "Cierre"}; //Arreglo de los titulos para la tabla
			    DefaultTableModel dtm_datos = new DefaultTableModel(); //creamos  un modelo para la taba de datos
				try {
					M_datos = Administrador.getInstance().contarDatosQueryCamino(campoNombre);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				dtm_datos = new DefaultTableModel(M_datos, Titulos) {
                public boolean isCellEditable(int row, int column) {//este metodo es muy util si no quieren que editen su tabla, 
                return false;  //si quieren modificar los campos al dar clic entonces borren este metodo
        }
                };
                table_2.setModel(dtm_datos);
                trs = new TableRowSorter<>(dtm_datos);
                table_2.setRowSorter(trs);
				
			}
		});
		campoNombre.setBounds(87, 52, 109, 20);
		panelBusqueda.add(campoNombre);
		campoNombre.setColumns(10);
				
						//onlyLet(campoNombre);
		/*btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				llenarTablaBuscados();
			}
*/ 
/* 
			private void llenarTablaBuscados() {
				
				
				DefaultTableModel modeloDefault = new DefaultTableModel(new String[] {"IDCamino","Nombre", "Capacidad", "Estado", "Sucursal origen", "Sucursal destino"},GestorCamino.getInstance().getCaminos().size());
				table_2.setModel(modeloDefault);
				
				TableModel modeloDatos= table_2.getModel();
				
					Camino camino = GestorCamino.getInstance().buscarCaminoPorNombre(campoNombre.getText());;
				
						
					modeloDatos.setValueAt(camino.getIdCamino(), 0, 0);
					modeloDatos.setValueAt(camino.getNombre(), 0, 1);
					modeloDatos.setValueAt(camino.getCapacidad(), 0, 2);
					modeloDatos.setValueAt(camino.getEstado(), 0, 3);
					modeloDatos.setValueAt(camino.getInicio().getNombre(), 0, 4);
					modeloDatos.setValueAt(camino.getFin().getNombre(), 0, 5);
				
				
			
			}
		});
		
		
		*/
		
	}
	
	ActionListener mostrarPantalla2 () {
		ActionListener b = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarCamino pantalla2 = 
						new EditarCamino(GestorCamino.getInstance().buscarCaminoPorNombre((table_2.getValueAt(table_2.getSelectedRow(),1)).toString()));
				//System.out.print(pantalla2.getNombre());
				pantalla2.setVisible(true);
				dispose();
			}
	};
		return b;
	}

	ActionListener volverAnterior () {
        ActionListener b= new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuPrincipal buscar = new MenuPrincipal();
               buscar.setVisible(true);
                dispose();
        }
    };
        return b;
    }
	
	/*void cargarDatos (String nombre, String idSucursal, String capacidad, JComboBox<String> combo) {
		textField.setText(nombre);
		textField_1.setText(idSucursal);
		textField_2.setText(capacidad);
	    comboBox.setSelectedItem(combo.getSelectedItem().toString());

	}
	*/
	private void onlyNum (final JTextField texto) {
	}

	private void onlyLet (final JTextField texto) {
		texto.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isAlphabetic(c) || c == '.') {
					e.consume();
				}
			}
		});	
	}
	
	/*
	ActionListener buscar (final JTextField nombre) {
        ActionListener b= new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
					admin.buscarXNombre(nombre.getText().toString());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        }
    };
        return b;
    }
	*/
	/* 
	private void llenarTablaCompleta() {
		//DefaultTableModel modelodefault = new DefaultTableModel(new String[] {"IDSucursal","Nombre", "Capacidad", "Estado", "Apertura", "Cierre"},GestorSucursal.getInstance().getSucursales().size());
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"IDCamino", "Nombre", "Capacidad", "Estado", "Sucursal origen", "Sucursal destino"
			}
		));
		
		TableModel modeloDatos= table_2.getModel();
		
		for(int i=0;i<GestorCamino.getInstance().getCaminos().size();i++) {
			Camino unCamino = GestorCamino.getInstance().getCaminos().get(i);
			
			modeloDatos.setValueAt(unCamino.getIdCamino(), i, 0);
			modeloDatos.setValueAt(unCamino.getNombre(), i, 1);
			modeloDatos.setValueAt(unCamino.getCapacidad(), i, 2);
			modeloDatos.setValueAt(unCamino.getEstado(), i, 3);
			modeloDatos.setValueAt(unCamino.getInicio().getNombre(), i, 4);
			modeloDatos.setValueAt(unCamino.getFin().getNombre(), i, 5);
			
	
		}
	}
	*/
	private void datosTabla() throws ClassNotFoundException, SQLException {
		String[] Titulos = {"IDCamino", "Nombre", "Capacidad", "Estado", "Sucursal origen", "Sucursal destino"}; //Arreglo de los titulos para la tabla
	    DefaultTableModel dtm_datos = new DefaultTableModel(); //creamos  un modelo para la taba de datos
	    TableRowSorter<TableModel> trs; //Hacemos el table row sorter para poder ordenar la tabla al presionar los encabezados de la misma
	    
	    String[][] M_datos = null;  //iniciamos una matriz donde pasaremos los datos de sql
	    Administrador.getInstance();   //iniciamos un objeto que se encargara de la conexion de datos
		
	  
		Integer contador = 0;  //creamos un contador para saber el numero de datos que obtendremos de la tabla datos de sql
                                                                      //para las consultas sql siempre vamos a ocupar un try catch por su ocurre un error
        contador = Administrador.getInstance().contarDatosCamino();
       
        M_datos= Administrador.getInstance().cargarDatosCamino(contador);
            
        dtm_datos = new DefaultTableModel(M_datos, Titulos) { //ahora agregaremos la matriz y los titulos al modelo de tabla
            public boolean isCellEditable(int row, int column) {//este metodo es muy util si no quieren que editen su tabla, 
                return false;  //si quieren modificar los campos al dar clic entonces borren este metodo
            }
        };
        table_2.setModel(dtm_datos); //ahora el modelo que ya tiene tanto los datos como los titulos lo agregamos a la tabla
        trs = new TableRowSorter<>(dtm_datos); //iniciamos el table row sorter para ordenar los datos (esto es si gustan)
        table_2.setRowSorter(trs); //y lo agregamos al jtable
	}
}