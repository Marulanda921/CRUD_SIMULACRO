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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPasajero() {
        return idPasajero;
    }

    public void setIdPasajero(int idPasajero) {
        this.idPasajero = idPasajero;
    }

    public int getIdVuelo() {
        return idVuelo;
    }

    public void setIdVuelo(int idVuelo) {
        this.idVuelo = idVuelo;
    }

    public String getFechaReservacion() {
        return fechaReservacion;
    }

    public void setFechaReservacion(String fechaReservacion) {
        this.fechaReservacion = fechaReservacion;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public Pasajero getObjPasajero() {
        return objPasajero;
    }

    public void setObjPasajero(Pasajero objPasajero) {
        this.objPasajero = objPasajero;
    }

    public Vuelo getObjVuelo() {
        return objVuelo;
    }

    public void setObjVuelo(Vuelo objVuelo) {
        this.objVuelo = objVuelo;
    }



    //ESTO ES IMPORTANTE PORQUE SI QUIERES UNIR 3 O MAS TABLAS TIENES QUE MOSTRAR TODOS LOS ELEMENTOS AQUI QUE QUIERES QUE VEA EL USUSARIO

    @Override
    public String toString() {
        return "Reservacion{" +
                ", Fecha Reservacion=: " + fechaReservacion +
                ", Asiento =" + asiento +
                ", Nombre: =" + objPasajero.getNombre() +
                ", Apellido ='" + objPasajero.getApellido() + '\'' +
                ", Documento Identidad ='" + objPasajero.getDocumentoIdentidad() + '\'' +
                ", Destino ='" + objVuelo.getDestino() + '\'' +
                ", Fecha de salida ='" + objVuelo.getFecha_salida() + '\'' +
                ", Hora de salida ='" + objVuelo.getHora_salida()+ '\'' +
                '}';
    }
}
