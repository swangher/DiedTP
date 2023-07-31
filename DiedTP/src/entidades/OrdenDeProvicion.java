package entidades;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;

public class OrdenDeProvicion {
    private int idOrden;
    private Sucursal sucursal;
    private Date fecha;
    private Time horaMaxima;
    private HashMap<Producto, Integer> productos;

    public OrdenDeProvicion() {
    }
}
