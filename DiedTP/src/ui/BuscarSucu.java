package ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import excepciones.NoSeleccionaTuplaException;
import gestores.GestorSucursal;
import main.Administrador;


public class BuscarSucu extends JFrame {
	private JTable table_2;
	private JPanel contentPane;
	private JTextField textField;
	private JComboBox<String> comboBox_1;
	
	
	public BuscarSucu() throws ClassNotFoundException, SQLException {

		setTitle("Busqueda de sucursal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//btnNewButton_1.addActionListener(buscar(textField));
		
		JButton btnNewButton_1_1 = new JButton("Aplicar");
		btnNewButton_1_1.setBounds(64, 408, 89, 23);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnNewButton_1_1);
		

		JButton btnNewButton_1_2 = new JButton("Cancelar");
		btnNewButton_1_2.setBounds(154, 408, 89, 23);
		contentPane.add(btnNewButton_1_2);
		
		btnNewButton_1_2.addActionListener(volverAnterior());
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 208, 469, 118);
		contentPane.add(scrollPane);
	
		table_2 = new JTable();
		table_2.setSurrendersFocusOnKeystroke(true);
		
		table_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table_2);
		datosTabla();

		JButton btnNewButton_1_1_1_1 = new JButton("Dar alta");
		btnNewButton_1_1_1_1.setBounds(323, 333, 98, 23);
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(btnNewButton_1_1_1_1);
		
		
		JButton btnNewButton_1_1_1_1_1 = new JButton("Dar baja");
		btnNewButton_1_1_1_1_1.setBounds(433, 333, 89, 23);
		contentPane.add(btnNewButton_1_1_1_1_1);

		JButton bEditar = new JButton("Editar");
		bEditar.setBounds(64, 333, 89, 23);
		contentPane.add(bEditar);
		bEditar.addActionListener(mostrarPantalla2());
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(51, 190, 496, 177);
		contentPane.add(panel_1);
		panel_1.setBorder(BorderFactory.createTitledBorder("Resultado"));
		
		JPanel panel = new JPanel();
		panel.setBounds(51, 51, 496, 118);
		panel.setBorder(BorderFactory.createTitledBorder("Búsqueda"));
		contentPane.add(panel);
		panel.setLayout(null);
		
        
		/*JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.setBounds(10, 84, 76, 23);
		panel.add(btnNewButton_1);
		*/
		JLabel lblNewLabel = new JLabel("Nombre");
	    lblNewLabel.setBounds(10, 55, 46, 14);
		panel.add(lblNewLabel);
				
	    textField = new JTextField();
		textField.setBounds(87, 52, 109, 20);
	    panel.add(textField);
		textField.setColumns(10);
				
		onlyLet(textField);
		
		
	/*
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					llenarTablaBuscados();
				} catch (SucursalNullException e1) {
					JOptionPane.showMessageDialog(rootPane, "No se encontró ninguna sucursal con ese nombre.");
					
				} catch (ClassNotFoundException e1) {
				} catch (SQLException e1) {
				}
			}

			private void llenarTablaBuscados() throws SucursalNullException, ClassNotFoundException, SQLException{
				
				Sucursal unaSucu = GestorSucursal.getInstance().buscarSucursal(textField.getText());;
				
				if (unaSucu == null) {
					throw new SucursalNullException();
					
				}
				
				DefaultTableModel modelodefault = new DefaultTableModel(new String[] {"IDSucursal","Nombre", "Capacidad", "Estado", "Apertura", "Cierre"},GestorSucursal.getInstance().getSucursales().size()+1);
				table_2.setModel(modelodefault);
				
				TableModel modeloDatos= table_2.getModel();
				
			//	for(int i=0;i<Main.gestorS.getSucursales().size();i++) {
					
					modeloDatos.setValueAt(unaSucu.getIdSucursal(), 0, 0);
					modeloDatos.setValueAt(unaSucu.getNombre(), 0, 1);
					modeloDatos.setValueAt(unaSucu.getCapacidad(), 0, 2);
					modeloDatos.setValueAt(unaSucu.getEstado(), 0, 3);
					modeloDatos.setValueAt(unaSucu.getHorarioApertura(), 0, 4);
					modeloDatos.setValueAt(unaSucu.getHorarioCierre(), 0, 5);
				
					
				//}
				
			
			}
		});
		*/
		}	
	ActionListener mostrarPantalla2 () {
		ActionListener b = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				EditarSucu pantalla2 = new EditarSucu((table_2.getValueAt(table_2.getSelectedRow(),1)).toString(),(table_2.getValueAt(table_2.getSelectedRow(),4)).toString(),(table_2.getValueAt(table_2.getSelectedRow(),5)).toString(),(table_2.getValueAt(table_2.getSelectedRow(),2)).toString());
				
				pantalla2.setVisible(true);
				dispose();
				}
				catch(NoSeleccionaTuplaException | ArrayIndexOutOfBoundsException | NullPointerException e1) {
				}
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

			
			@Override
			public void keyReleased(KeyEvent e) {
				String M_datos[][]= null;
				TableRowSorter<TableModel> trs; 
				String[] Titulos = {"IDSucursal", "Nombre", "Capacidad", "Estado", "Apertura", "Cierre"}; //Arreglo de los titulos para la tabla
			    DefaultTableModel dtm_datos = new DefaultTableModel(); //creamos  un modelo para la taba de datos
				try {
					M_datos = Administrador.getInstance().contarDatosQuerySucursal(textField);
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
	private void datosTabla() throws ClassNotFoundException, SQLException {
		String[] Titulos = {"IDSucursal", "Nombre", "Capacidad", "Estado", "Apertura", "Cierre"}; //Arreglo de los titulos para la tabla
	    DefaultTableModel dtm_datos = new DefaultTableModel(); //creamos  un modelo para la taba de datos
	    TableRowSorter<TableModel> trs; //Hacemos el table row sorter para poder ordenar la tabla al presionar los encabezados de la misma
	    
	    String[][] M_datos = null;  //iniciamos una matriz donde pasaremos los datos de sql
	    Administrador.getInstance();   //iniciamos un objeto que se encargara de la conexion de datos
		
	  
		Integer contador = 0;  //creamos un contador para saber el numero de datos que obtendremos de la tabla datos de sql
                                                                      //para las consultas sql siempre vamos a ocupar un try catch por su ocurre un error
        contador = Administrador.getInstance().contarDatosSucursal();
       
        M_datos= Administrador.getInstance().cargarDatosSucursal(contador);
            
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
	

