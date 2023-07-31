package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import main.Main;

public class RegistrarCamino extends JFrame {

	private JPanel contentPane;
	private JTextField campoNombre;
	private JTextField campoTiempo;
	private JTextField campoCapacidad;
	private JComboBox campoEstado;
	private JComboBox<String> campoSucuOrigen;
	private JComboBox<String> campoSucuDestino;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		
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
	}*/

	/**
	 * Create the frame.
	 */
	public RegistrarCamino() {
		setTitle("Registar Camino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(20, 30, 78, 14);
		contentPane.add(lblNewLabel);
		
		campoNombre = new JTextField();
		campoNombre.setBounds(148, 27, 120, 20);
		contentPane.add(campoNombre);
		campoNombre.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tiempo de transito");
		lblNewLabel_1.setBounds(311, 27, 120, 14);
		contentPane.add(lblNewLabel_1);
		
		campoTiempo = new JTextField();
		campoTiempo.setColumns(10);
		campoTiempo.setBounds(453, 27, 86, 20);
		contentPane.add(campoTiempo);
		
		JLabel lblNewLabel_2 = new JLabel("HS.");
		lblNewLabel_2.setBounds(549, 33, 25, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblSucursalOrigen = new JLabel("Sucursal origen");
		lblSucursalOrigen.setBounds(20, 79, 105, 14);
		contentPane.add(lblSucursalOrigen);
		
		
		//int tamanio = GestorSucursal.getInstance().listaSucursales().length;
		//String[] listaOpciones = new String[tamanio];
		//listaOpciones[0] = "No seleccionado";
		//for(int i=1; i < tamanio; i++) {
		//	listaOpciones[i] = GestorSucursal.getInstance().listaSucursales()[i-1];
		//}
		
		ArrayList<String> listaOpciones = new ArrayList<String>();
		listaOpciones.add("No seleccionado");
		listaOpciones.addAll(GestorSucursal.getInstance().listaSucursales());
		
		campoSucuOrigen = new JComboBox<String>(listaOpciones.toArray(new String[0]));
		campoSucuOrigen.setBounds(148, 75, 120, 22);
		contentPane.add(campoSucuOrigen);
		
		JLabel lblSucursalDestino = new JLabel("Sucursal destino");
		lblSucursalDestino.setBounds(311, 79, 96, 14);
		contentPane.add(lblSucursalDestino);
		
		campoSucuDestino = new JComboBox<String>(listaOpciones.toArray(new String[0]));
		campoSucuDestino.setBounds(451, 75, 105, 22);
		contentPane.add(campoSucuDestino);
		campoSucuDestino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JComboBox comboBox = (JComboBox) e.getSource(); 
				String opcion = comboBox.getSelectedItem().toString();
			     
			     if(opcion != "No seleccionado") {
			    	 completarCapacidad(opcion);
			     }
			     else {
			    	 campoCapacidad.setText("");
			     }
			}
			
		});
		
		JLabel lblNewLabel_3 = new JLabel("Estado");
		lblNewLabel_3.setBounds(20, 134, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		campoEstado = new JComboBox();
		campoEstado.setModel(new DefaultComboBoxModel(new String[] {"No seleccionado","Operativa", "No operativa"}));
		campoEstado.setBounds(148, 130, 120, 22);
		contentPane.add(campoEstado);
		
		JLabel lblNewLabel_3_1 = new JLabel("Capacidad de almacenamiento");
		lblNewLabel_3_1.setBounds(20, 200, 203, 14);
		contentPane.add(lblNewLabel_3_1);
		
		campoCapacidad = new JTextField();
		campoCapacidad.setEnabled(false);
		campoCapacidad.setEditable(false);
		campoCapacidad.setColumns(10);
		campoCapacidad.setBounds(219, 197, 134, 20);
		contentPane.add(campoCapacidad);
		
		JButton botonRegistrar = new JButton("Registrar");
		botonRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Sucursal inicio = GestorSucursal.getInstance().buscarSucursal(campoSucuOrigen.getSelectedItem().toString());
				Sucursal fin = GestorSucursal.getInstance().buscarSucursal(campoSucuDestino.getSelectedItem().toString());
				
				String formato = campoTiempo.getText()+":00:00";
				Time tiempo = Time.valueOf(formato);
				
				
				Camino c = new Camino(campoNombre.getText().toString(), tiempo ,inicio, fin, campoEstado.getSelectedItem().toString(),Double.parseDouble(campoCapacidad.getText()));
				GestorCamino.getInstance().agregaCamino(c);
				
				ExitoRegistrarCamino exito = new ExitoRegistrarCamino();
			    exito.setVisible(true);
				dispose();
			}
		});
		botonRegistrar.setBounds(20, 313, 105, 23);
		contentPane.add(botonRegistrar);
		
		JButton botonCancelar = new JButton("Cancelar");
		botonCancelar.setBounds(135, 313, 89, 23);
		contentPane.add(botonCancelar);
		botonCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                MenuPrincipal buscar = new MenuPrincipal();
                buscar.setVisible(true);
                dispose();
			}
		});
		
		JLabel lblNewLabel_2_1 = new JLabel("KG.");
		lblNewLabel_2_1.setBounds(363, 200, 25, 14);
		contentPane.add(lblNewLabel_2_1);
		
	}
	
	public void completarCapacidad(String nombre) {
		Sucursal s = GestorSucursal.getInstance().buscarSucursal(nombre);
		campoCapacidad.setText(String.valueOf(s.getCapacidad()));
	}
	
	
}
