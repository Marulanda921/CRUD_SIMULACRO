package Entity;

public class Reservacion {

    private int id;
    private int idPasajero;
    private int idVuelo;
    private String fechaReservacion;
    private String asiento;


    //Inyeccion de dependencias para traer los valores de las otras clases
    private Pasajero objPasajero;
    private Vuelo objVuelo;

    public Reservacion() {
    }

    public Reservacion(int idPasajero, int idVuelo, String fechaReservacion, String asiento, Pasajero objPasajero, Vuelo objVuelo) {
        this.idPasajero = idPasajero;
        this.idVuelo = idVuelo;
        this.fechaReservacion = fechaReservacion;
        this.asiento = asiento;
        this.objPasajero = objPasajero;
        this.objVuelo = objVuelo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public void setFechaReservacion(String fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public void setObjPasajero(Pasajero objPasajero) {
        this.objPasajero = objPasajero;
    }

    public void setObjVuelo(Vuelo objVuelo) {
        this.objVuelo = objVuelo;
    }

    @Override
    public String toString() {
        return "Reservacion{" +
                ", Pasajero=" + objPasajero.getNombre() +
                ", Vuelo=" + objVuelo.getDestino() +
                ", fechaReservacion='" + fechaReservacion + '\'' +
                ", asiento='" + asiento + '\'' +
                '}';
    }
}
