package ui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextPane;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;

	public MenuPrincipal() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Menú de funcionalidades");
		lblNewLabel.setBounds(210, 24, 165, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel.setFont( new Font("Default",Font.PLAIN,15));
		
		JButton btnNewButton = new JButton("Registrar sucursal");
		btnNewButton.setBounds(41, 81, 142, 23);
		contentPane.add(btnNewButton);
		
	    btnNewButton.addActionListener(abrirRegistrarSucu());
		
		
		
		
	    JButton botonRegistrarCaminos = new JButton("Registrar caminos");
        botonRegistrarCaminos.setBounds(392, 145, 165, 23);
        contentPane.add(botonRegistrarCaminos);

        botonRegistrarCaminos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                llamarRegistrarCamino();
            }
        });
		
		JButton btnNewButton_2 = new JButton("Registrar lista de productos");
		btnNewButton_2.setBounds(392, 81, 165, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Registrar stock");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		

		btnNewButton_3.setBounds(41, 145, 142, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_3_1 = new JButton("Crear orden de provisión");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3_1.setBounds(210, 145, 165, 23);
		contentPane.add(btnNewButton_3_1);
		
		JButton btnBuscarSucursal = new JButton("Buscar sucursal");
		btnBuscarSucursal.setBounds(224, 81, 137, 23);
		contentPane.add(btnBuscarSucursal);

        btnBuscarSucursal.addActionListener(buscarSucursal());
       
		JButton btnBuscarCamino = new JButton("Buscar camino");
		btnBuscarCamino.setBounds(41, 207, 142, 23);
		contentPane.add(btnBuscarCamino);
		btnBuscarCamino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCamino buscarCamino = null;
				try {
					buscarCamino = new BuscarCamino();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				buscarCamino.setVisible(true);
				dispose();
			}
		});
		
		
	}
	ActionListener abrirRegistrarSucu () {
        ActionListener a=new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrarSucu registrar = new RegistrarSucu();
                registrar.setVisible(true);
                dispose();
        }
    };
        return a;
    }
	
	ActionListener buscarSucursal () {
        ActionListener b= new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BuscarSucu buscar = null;
				try {
					buscar = new BuscarSucu();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                buscar.setVisible(true);
                dispose();
        }
    };
        return b;
    }
	  public void llamarRegistrarCamino() {
	        RegistrarCamino rc = new RegistrarCamino();
	        rc.setVisible(true);
	        dispose();
	    }
	}
	
	
	
	
	
	
	

