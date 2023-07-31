package entidades;


import java.sql.Time;

public class Camino {
    private int idCamino;
    private String nombre;
    private Time tiempoRecorrido;
    private Sucursal Inicio;
    private Sucursal Fin;
    private String estado;
    private double capacidad;
    
    public Camino(String nombre, Time tiempoRecorrido, Sucursal inicio, Sucursal fin, String estado, Double capacidad) {
        this.nombre = nombre;
        this.tiempoRecorrido = tiempoRecorrido;
        this.Inicio = inicio;
        this.Fin = fin;
        this.estado = estado;
        this.capacidad = capacidad;
    }

	public int getIdCamino() {
		return idCamino;
	}

	public String getNombre() {
		return nombre;
	}

	public Time getTiempoRecorrido() {
		return tiempoRecorrido;
	}

	public Sucursal getInicio() {
		return Inicio;
	}

	public Sucursal getFin() {
		return Fin;
	}

	public String getEstado() {
		return estado;
	}

	public double getCapacidad() {
		return capacidad;
	}

    

    
    
}