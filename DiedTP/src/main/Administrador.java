package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entidades.Sucursal;
import gestores.GestorSucursal;

public class Administrador {

	private static Administrador _INSTANCE;
	
	public static Administrador getInstance() throws ClassNotFoundException, SQLException {
		if (_INSTANCE == null) {
			_INSTANCE = new Administrador();
		}
		return _INSTANCE;
	}
	
	public Administrador() throws ClassNotFoundException, SQLException {
	ConexionDB.getInstance().getConn();
	}
	
	public void insertarSucursal (String nombre, int capacidad, String estado, String horarioApertura, String horarioClausura) throws SQLException {

	Statement st = ConexionDB.getInstance().getConn().createStatement();
	
	st.executeUpdate("INSERT INTO sucursal (nombre,capacidad,estado,horarioApertura,horarioCierre) VALUES ('"+nombre+"',"+capacidad+",'"+estado+"','"+horarioApertura+"','"+horarioClausura+"')");
	
	st.close();
	}
	
	public Integer contarDatosSucursal () throws SQLException {
		Integer contador = 0;
		Statement st_cont = ConexionDB.getInstance().getConn().createStatement(); //el statement nos ayuda a procesar una sentencia sql 
        ResultSet rs_cont = st_cont.executeQuery("SELECT COUNT(*) FROM sucursal"); // asignamos los datos obtenidos de la consulta al result set
         if (rs_cont.next()) {
            contador = rs_cont.getInt(1);
	}
         return contador;
    
	}
	
	public String[][] cargarDatosSucursal (Integer contador) throws SQLException{
		 Statement st = ConexionDB.getInstance().getConn().createStatement(); //ahora vamos a  hacer lo mismo solo que esta vez no obtendremos el numero de filas en la tabla
         ResultSet rs = st.executeQuery("SELECT * FROM sucursal"); //ahora obtendremos los datos de la tabla para mostrarlos en el jtable
         String[][] M_datos;
         int cont = 0; //el contador nos ayudara para movernos en las filas de la matriz mientras que los numeros fijos (0,1,2,3) nos moveran por las 4 columnas que seran el id, nombre, etc
         M_datos = new String[contador][6]; //definimos el tamaño de la matriz 
         while (rs.next()) { //el while nos ayudara a recorrer los datos obtenidos en la consulta anterior y asignarlos a la matriz  
             M_datos[cont][0] = rs.getString("idSucursal");    //agregamos los datos a la table
             M_datos[cont][1] = rs.getString("nombre");
             M_datos[cont][2] = rs.getString("capacidad");
             M_datos[cont][3] = rs.getString("estado");
             M_datos[cont][4] = rs.getString("horarioApertura");
             M_datos[cont][5] = rs.getString("horarioCierre");
             cont = cont + 1; //avanzamos una posicion del contador para que pase a la siguiente fila
         }
		return M_datos;
	}
	
	public String[][] contarDatosQuerySucursal (JTextField textField) throws SQLException {
		int valor = 0;
        int cont = 0;
        String M_datos[][];
        String aux = "" + textField.getText();
	
		Statement st_cont = ConexionDB.getInstance().getConn().createStatement(); //hacemos lo mismo que con el metodo mostrar, buscamos el numero de filas dela tabla
        ResultSet rs = st_cont.executeQuery("SELECT COUNT(*) FROM sucursal WHERE nombre LIKE'" + textField.getText() + "%'");//solo que esta ves usamos like
        if (rs.next()) {// like nos ayudara a buscar nombres que tengan similitudes con lo que estamos escribiendo en el texfield
            valor = rs.getInt(1); //una vez que obtenimos el numero de filas continuamos a sacar  el valor que buscamos
        }
        
            M_datos = new String[valor][6];
            rs = st_cont.executeQuery("SELECT * FROM sucursal WHERE nombre LIKE'" + textField.getText() + "%'"); //aqui es donde buscaremos a a la persona en especifico o las personas
            while (rs.next()) {
                M_datos[cont][0] = rs.getString("idSucursal");
                M_datos[cont][1] = rs.getString("nombre");
                M_datos[cont][2] = rs.getString("capacidad");
                M_datos[cont][3] = rs.getString("estado");
                M_datos[cont][4] = rs.getString("horarioApertura");
                M_datos[cont][5] = rs.getString("horarioCierre");
                cont = cont + 1;
            }
            return M_datos;
	}
	
	public Integer contarDatosCamino () throws SQLException {
		Integer contador = 0;
		Statement st_cont = ConexionDB.getInstance().getConn().createStatement(); //el statement nos ayuda a procesar una sentencia sql 
        ResultSet rs_cont = st_cont.executeQuery("SELECT COUNT(*) FROM camino"); // asignamos los datos obtenidos de la consulta al result set
         if (rs_cont.next()) {
            contador = rs_cont.getInt(1);
	}
         return contador;
    
	}
	
	public String[][] cargarDatosCamino  (Integer contador) throws SQLException{
		 Statement st = ConexionDB.getInstance().getConn().createStatement(); //ahora vamos a  hacer lo mismo solo que esta vez no obtendremos el numero de filas en la tabla
         ResultSet rs = st.executeQuery("SELECT * FROM camino"); //ahora obtendremos los datos de la tabla para mostrarlos en el jtable
         String[][] M_datos;
         int cont = 0; //el contador nos ayudara para movernos en las filas de la matriz mientras que los numeros fijos (0,1,2,3) nos moveran por las 4 columnas que seran el id, nombre, etc
         M_datos = new String[contador][6]; //definimos el tamaño de la matriz 
         while (rs.next()) { //el while nos ayudara a recorrer los datos obtenidos en la consulta anterior y asignarlos a la matriz  
        	 M_datos[cont][0] = rs.getString("idCamino");    //agregamos los datos a la table
             M_datos[cont][1] = rs.getString("nombre");
             M_datos[cont][2] = rs.getString("capacidad");
             M_datos[cont][3] = rs.getString("estado");
             M_datos[cont][4] = rs.getString("sucursalinicio");
             M_datos[cont][5] = rs.getString("sucursalfin");
             cont = cont + 1; //avanzamos una posicion del contador para que pase a la siguiente fila
         }
		return M_datos;
	}
	
	public String[][] contarDatosQueryCamino  (JTextField textField) throws SQLException {
		int valor = 0;
        int cont = 0;
        String M_datos[][];
        String aux = "" + textField.getText();
	
		Statement st_cont = ConexionDB.getInstance().getConn().createStatement(); //hacemos lo mismo que con el metodo mostrar, buscamos el numero de filas dela tabla
        ResultSet rs = st_cont.executeQuery("SELECT COUNT(*) FROM camino WHERE nombre LIKE'" + textField.getText() + "%'");//solo que esta ves usamos like
        if (rs.next()) {// like nos ayudara a buscar nombres que tengan similitudes con lo que estamos escribiendo en el texfield
            valor = rs.getInt(1); //una vez que obtenimos el numero de filas continuamos a sacar  el valor que buscamos
        }
       
            M_datos = new String[valor][6];
            rs = st_cont.executeQuery("SELECT * FROM camino WHERE nombre LIKE'" + textField.getText() + "%'"); //aqui es donde buscaremos a a la persona en especifico o las personas
            while (rs.next()) {
            	 M_datos[cont][0] = rs.getString("idCamino");    //agregamos los datos a la table
                 M_datos[cont][1] = rs.getString("nombre");
                 M_datos[cont][2] = rs.getString("capacidad");
                 M_datos[cont][3] = rs.getString("estado");
                 M_datos[cont][4] = rs.getString("sucursalinicio");
                 M_datos[cont][5] = rs.getString("sucursalfin");
                 
                
                 cont = cont + 1;
            }
            return M_datos;
	}
	
	
	public void instanciarDatos () throws SQLException {
		String consultaSucursales = "SELECT * FROM sucursal";
		
		Statement statement = ConexionDB.getInstance().getConn().createStatement();
        ResultSet resultSet = statement.executeQuery(consultaSucursales);
		
        while (resultSet.next()) {
        	Sucursal sucursal = new Sucursal();
        	sucursal.setIdSucursal(resultSet.getInt(1)); 
        	sucursal.setNombre(resultSet.getString(2));
        	int capacidad = Integer.parseInt(resultSet.getString(3));
        	sucursal.setCapacidad(capacidad);
        	sucursal.setEstado(resultSet.getString(4));
        	
        	Time horarioAbre = resultSet.getTime(5);
        	Time horarioCierra = resultSet.getTime(6);
        	sucursal.setHorarioApertura(horarioAbre);
        	sucursal.setHorarioCierre(horarioCierra);
        	
            GestorSucursal.getInstance().sucursales.add(sucursal);
        }
        statement.close();
	}
	public void reemplazarDatos () throws SQLException {
		PreparedStatement ps = null;
	
		if (!GestorSucursal.getInstance().sucursalesEdit.isEmpty()) {
		for (Sucursal unaSucursal: GestorSucursal.getInstance().sucursalesEdit) {
			int idSucursal = unaSucursal.getIdSucursal();
			String nombre = unaSucursal.getNombre();
			int capacidad = unaSucursal.getCapacidad();
			String estado = unaSucursal.getEstado();
			Time horarioApertura = unaSucursal.getHorarioApertura();
			Time horarioCierre = unaSucursal.getHorarioCierre();
			String updateSucursal = "UPDATE sucursal SET nombre = ?,capacidad =?,estado =?,horarioApertura=?,horarioCierre=? WHERE idSucursal= ?";
			ps = ConexionDB.getInstance().getConn().prepareStatement(updateSucursal);
	        ps.setString(1,nombre);
	        ps.setInt(2,capacidad);
	        ps.setString(3, estado);
	        ps.setTime(4, horarioApertura);
	        ps.setTime(5, horarioCierre);
	        ps.setInt(6, idSucursal);
	        ps.executeUpdate();
	    
		}
		}
	    ps.close();
	}
}
	
	


