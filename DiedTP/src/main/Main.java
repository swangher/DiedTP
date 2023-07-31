package main;
import java.sql.SQLException;

import gestores.GestorCamino;
import gestores.GestorSucursal;
import ui.MenuPrincipal;

public class Main {

    
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
	
	
		Administrador.getInstance().instanciarDatos();
		
		MenuPrincipal d= new MenuPrincipal();
		d.setVisible(true);
	
	  
		
	};

};