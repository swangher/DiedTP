package ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import entidades.Camino;
import entidades.Sucursal;
import gestores.GestorCamino;
import gestores.GestorSucursal;

public class EditarCamino extends JFrame {

		private JPanel contentPane;
		private JTextField campoNombre;
		private JTextField campoTiempo;
		private JTextField campoCapacidad;
		private JComboBox<String> campoSucuOrigen;
		private JComboBox<String> campoSucuDestino;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						RegistrarCamino frame = new RegistrarCamino();
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
		public EditarCamino(Camino c) {
			setTitle("Editar Camino");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 600, 400);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("Nombre");
			lblNewLabel.setBounds(20, 63, 78, 14);
			contentPane.add(lblNewLabel);
			
			campoNombre = new JTextField();
			campoNombre.setBounds(148, 60, 120, 20);
			campoNombre.setText(c.getNombre());
			contentPane.add(campoNombre);
			campoNombre.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Tiempo de transito");
			lblNewLabel_1.setBounds(311, 60, 120, 14);
			contentPane.add(lblNewLabel_1);
			
			campoTiempo = new JTextField();
			campoTiempo.setColumns(10);
			campoTiempo.setText(c.getTiempoRecorrido().toString());
			campoTiempo.setBounds(453, 60, 86, 20);
			contentPane.add(campoTiempo);
			
			JLabel lblNewLabel_2 = new JLabel("HS.");
			lblNewLabel_2.setBounds(549, 66, 25, 14);
			contentPane.add(lblNewLabel_2);
			
			JLabel lblSucursalOrigen = new JLabel("Sucursal origen");
			lblSucursalOrigen.setBounds(20, 131, 105, 14);
			contentPane.add(lblSucursalOrigen);
			
			
			//int tamanio = GestorSucursal.getInstance().listaSucursales().length;
			//String[] listaOpciones = new String[tamanio];
			//listaOpciones[0] = "No seleccionado";
			//for(int i=1; i < tamanio; i++) {
			//	listaOpciones[i] = GestorSucursal.getInstance().listaSucursales()[i-1];
			//}
			
			ArrayList<String> listaOpciones = new ArrayList<String>();
			//listaOpciones.add("No seleccionado");
			listaOpciones.addAll(GestorSucursal.getInstance().listaSucursales());
			
			campoSucuOrigen = new JComboBox<String>(listaOpciones.toArray(new String[0]));
			campoSucuOrigen.setBounds(148, 127, 120, 22);
			campoSucuOrigen.setSelectedItem(c.getInicio().getNombre());
			contentPane.add(campoSucuOrigen);
			
			JLabel lblSucursalDestino = new JLabel("Sucursal destino");
			lblSucursalDestino.setBounds(311, 131, 96, 14);
			contentPane.add(lblSucursalDestino);
			
			campoSucuDestino = new JComboBox<String>(listaOpciones.toArray(new String[0]));
			campoSucuDestino.setBounds(451, 127, 105, 22);
			campoSucuDestino.setSelectedItem(c.getFin().getNombre());
			contentPane.add(campoSucuDestino);
			campoSucuDestino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					 JComboBox comboBox = (JComboBox) e.getSource(); 
					String opcion = comboBox.getSelectedItem().toString();
				    completarCapacidad(opcion);
				}
				
			});
			
			JLabel lblNewLabel_3_1 = new JLabel("Capacidad de almacenamiento");
			lblNewLabel_3_1.setBounds(20, 200, 203, 14);
			contentPane.add(lblNewLabel_3_1);
			
			campoCapacidad = new JTextField();
			campoCapacidad.setEnabled(false);
			campoCapacidad.setEditable(false);
			campoCapacidad.setColumns(10);
			campoCapacidad.setBounds(219, 197, 134, 20);
			completarCapacidad(c.getFin().getNombre());
			contentPane.add(campoCapacidad);
			
			JButton botonAplicar = new JButton("Aplicar");
			//Falta implementar
			/*botonAplicar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Sucursal inicio = GestorSucursal.getInstance().buscarSucursal(campoSucuOrigen.getSelectedItem().toString());
					Sucursal fin = GestorSucursal.getInstance().buscarSucursal(campoSucuDestino.getSelectedItem().toString());
					
					String formato = campoTiempo.getText()+":00:00";
					Time tiempo = Time.valueOf(formato);
					
					
					
				    //exito.setVisible(true);
					dispose();
				}
			});*/
			botonAplicar.setBounds(20, 313, 105, 23);
			contentPane.add(botonAplicar);
			
			JButton botonCancelar = new JButton("Cancelar");
			botonCancelar.setBounds(135, 313, 89, 23);
			contentPane.add(botonCancelar);
			botonCancelar.addActionListener(volverAnterior());
			
			JLabel lblNewLabel_2_1 = new JLabel("KG.");
			lblNewLabel_2_1.setBounds(363, 200, 25, 14);
			contentPane.add(lblNewLabel_2_1);
	
		}
		
		public void completarCapacidad(String nombre) {
			Sucursal s = GestorSucursal.getInstance().buscarSucursal(nombre);
			campoCapacidad.setText(String.valueOf(s.getCapacidad()));
		}
	
	ActionListener volverAnterior() {
        ActionListener a= new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BuscarCamino pantalla1 = null;
				try {
					pantalla1 = new BuscarCamino();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                //pantalla1.cargarDatos(nombre,idSucursal,capacidad,comboBox);
                pantalla1.setVisible(true);
                dispose();
                
        }
    };
        return a;
    }
	
	/*
	ActionListener aplicar(final Sucursal sucursa) {
        ActionListener a= new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
            	sucursa.setNombre(textField.getText());
            	int capacidad= Integer.parseInt(textField_1.getText());
            	sucursa.setCapacidad(capacidad);
            	Time horarioAbre= Time.valueOf(comboBox_1.getSelectedItem().toString());
				Time horarioCierra= Time.valueOf(comboBox_1_1.getSelectedItem().toString());
            	sucursa.setHorarioApertura(horarioAbre);
            	sucursa.setHorarioCierre(horarioCierra);
            	//sucursa.getEstado();
                ExitoEditarSucu editar = new ExitoEditarSucu();
                editar.setVisible(true);
                dispose();
        }
    };
        return a;
    }
	
	
	
	
	void nombreSucu (String nombre) {
		this.nombre= nombre;
	}
	
	private void onlyNum (final JTextField texto) {
		texto.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c) && c != '.') {
					e.consume();
				}
				if (c == '.' && texto.getText().contains(".")) {
					e.consume();
				}
			}
		});	
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
	}*/
}
	
	
