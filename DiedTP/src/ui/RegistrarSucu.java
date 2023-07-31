package ui;
import main.Administrador;
import main.Main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.MatteBorder;

import gestores.GestorSucursal;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.Time;
import java.awt.event.ActionEvent;

public class RegistrarSucu extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_1_1;

	public RegistrarSucu() {
		setTitle("Registrar Sucursal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(128, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(5, 5, 569, 445);
		panel.setForeground(new Color(0, 0, 0));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(24, 67, 61, 20);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(95, 67, 110, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		onlyLet(textField);
		
		JLabel lblNewLabel_1 = new JLabel("Capacidad");
		lblNewLabel_1.setBounds(262, 67, 61, 20);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(333, 67, 110, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		onlyNum(textField_1);
		
		JLabel lblNewLabel_2 = new JLabel("Estado");
		lblNewLabel_2.setBounds(24, 121, 46, 14);
		panel.add(lblNewLabel_2);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Operativa", "No operativa"}));
		comboBox.setBounds(95, 117, 110, 22);
		panel.add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Horario apertura");
		lblNewLabel_3.setBounds(24, 173, 103, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Horario cierre");
		lblNewLabel_3_1.setBounds(24, 223, 103, 14);
		panel.add(lblNewLabel_3_1);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"07:00:00", "07:30:00", "08:00:00", "08:30:00", "09:00:00", "09:30:00", "10:00:00", "10:30:00", "11:00:00", "11:30:00", "12:00:00", "12:30:00", "13:00:00", "13:30:00", "14:00:00", "14:30:00", "15:00:00", "15:30:00", "16:00:00", "16:30:00", "17:00:00", "17:30:00", "18:00:00", "18:30:00", "19:00:00", "19:30:00", "20:00:00", "20:30:00", "21:00:00", "21:30:00", "22:00:00", "22:30:00", "23:00:00", "23:30:00", "00:00:00"}));
		comboBox_1.setBounds(137, 169, 68, 22);
		panel.add(comboBox_1);
		
		comboBox_1_1 = new JComboBox();
		comboBox_1_1.setModel(new DefaultComboBoxModel(new String[] {"07:00:00", "07:30:00", "08:00:00", "08:30:00", "09:00:00", "09:30:00", "10:00:00", "10:30:00", "11:00:00", "11:30:00", "12:00:00", "12:30:00", "13:00:00", "13:30:00", "14:00:00", "14:30:00", "15:00:00", "15:30:00", "16:00:00", "16:30:00", "17:00:00", "17:30:00", "18:00:00", "18:30:00", "19:00:00", "19:30:00", "20:00:00", "20:30:00", "21:00:00", "21:30:00", "22:00:00", "22:30:00", "23:00:00", "23:30:00", "00:00:00"}));
		comboBox_1_1.setBounds(137, 219, 68, 22);
		panel.add(comboBox_1_1);
		
		
		
		JLabel lblNewLabel_4 = new JLabel("Kg");
		lblNewLabel_4.setBounds(453, 70, 46, 14);
		panel.add(lblNewLabel_4);
		
		/*JButton registrar = new JButton("Registrar");
		registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Exito exito= new Exito();
				exito.setVisible(true);
				dispose();
				exito.getBtnNewButton().addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					//RegistrarSucu registrar1= new RegistrarSucu();
					exito.dispose();
					}
				}
					);
				}
			}
		);
		*/
		JButton registrar = new JButton("Registrar");
		registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
				try {
				    Time horarioAbre= Time.valueOf((comboBox_1.getSelectedItem().toString()));
					Time horarioCierra= Time.valueOf((comboBox_1_1.getSelectedItem().toString()));
					int capacidad = Integer.parseInt(textField_1.getText().toString());
					
					GestorSucursal.getInstance().registrarSucursal(textField.getText().toString(), capacidad, comboBox.getSelectedItem().toString(), horarioAbre, horarioCierra);
					Administrador.getInstance().insertarSucursal(textField.getText().toString(), Integer.parseInt(textField_1.getText().toString()), comboBox.getSelectedItem().toString(), comboBox_1.getSelectedItem().toString(), comboBox_1_1.getSelectedItem().toString());
					
					JOptionPane.showMessageDialog(rootPane, "Se registro correctamente la sucursal");

					MenuPrincipal menu = new MenuPrincipal();
					menu.setVisible(true);
					dispose();
				}
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(rootPane, "No se ingresaron alguno de los datos, verifique e intentelo nuevamente.");
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(rootPane, "Ya existe una sucursal con el nombre ingresado, ingreselo nuevamente.");
				}
			}
			});
		
		registrar.setBounds(24, 395, 89, 23);
		panel.add(registrar);
		
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.setBounds(116, 395, 89, 23);
		panel.add(cancelar);
		
		cancelar.addActionListener(volverAnterior());
		
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
	}
}