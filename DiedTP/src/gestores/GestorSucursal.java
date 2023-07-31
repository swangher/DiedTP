package gestores;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import entidades.Sucursal;
import excepciones.NombreSucursalExistenteException;

public class GestorSucursal {

	public ArrayList<Sucursal> sucursales= new ArrayList<>();
    public ArrayList<Sucursal> sucursalesEdit = new ArrayList<>();
	
	
	private static GestorSucursal _INSTANCE;
	
	
	public static GestorSucursal getInstance() {
		if (_INSTANCE == null) {
			_INSTANCE = new GestorSucursal();
		}
		return _INSTANCE;
	}
	
	public ArrayList<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(ArrayList<Sucursal> sucursales) {
		this.sucursales = sucursales;
	} 
	
	public Sucursal buscarSucursal (String nombre) {
		
		for (Sucursal unaSucursal: sucursales) {
			if (unaSucursal.getNombre().equals(nombre)) {
				return unaSucursal;
			}
		}
		return null;
	}
	
	public void registrarSucursal (String nombre, int capacidad, String estado, Time horarioApertura, Time horarioCierre) {
		Sucursal sucursal = new Sucursal();
		sucursal.setNombre(nombre);
		sucursal.setCapacidad(capacidad);
		sucursal.setEstado(estado);
		sucursal.setHorarioApertura(horarioApertura);
        sucursal.setHorarioCierre(horarioCierre);	
        
        sucursales.add(sucursal);
	}
	
	public void compararNombreSucursal (String nombre) throws NombreSucursalExistenteException {
		for (Sucursal unaSucursal: GestorSucursal.getInstance().sucursales) {
     		if (unaSucursal.getNombre().equals(nombre)) {
     			throw new NombreSucursalExistenteException();
     		}
     	}
		
	}
	
	
	
	
	
	public List<String> listaSucursales() {
        List<String> res = new ArrayList<String>();
        for(Sucursal s: sucursales)
            res.add(s.getNombre());
        return res;
    }
	
	
	
}
