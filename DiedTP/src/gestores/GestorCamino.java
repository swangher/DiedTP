package gestores;
import java.util.ArrayList;
import entidades.Camino;
import entidades.Sucursal;

public class GestorCamino {
	
	private static GestorCamino _INSTANCE;
    private ArrayList<Camino> listaCaminos;

    public GestorCamino() {
        listaCaminos = new ArrayList<Camino>();
    }
    
    public static GestorCamino getInstance() {
		if (_INSTANCE == null) {
			_INSTANCE = new GestorCamino();
		}
		return _INSTANCE;
	}
    
    public ArrayList<Camino> getCaminos() {
		return listaCaminos;
	}

    public void agregaCamino(Camino c) {
        listaCaminos.add(c);
    }

public Camino buscarCaminoPorNombre (String nombre) {
		
		for (Camino unCamino: listaCaminos) {
			if (unCamino.getNombre().equals(nombre)) {
				return unCamino;
			}
		}
		return null;
	}

}