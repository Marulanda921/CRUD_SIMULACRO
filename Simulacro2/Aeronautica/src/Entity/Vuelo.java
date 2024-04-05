package Entity;

public class Vuelo {
    private int id;
    private String destino;
    private String fecha_salida;
    private String hora_salida;
    private int idAvion;

    private Avion objAvion;

    public Vuelo() {
    }

    public Vuelo(String destino, String fecha_salida, String hora_salida, int idAvion, Avion objAvion) {
        this.destino = destino;
        this.fecha_salida = fecha_salida;
        this.hora_salida = hora_salida;
        this.idAvion = idAvion;
        this.objAvion = objAvion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public String getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public int getIdAvion() {
        return idAvion;
    }

    public void setIdAvion(int idAvion) {
        this.idAvion = idAvion;
    }

    public Avion getObjAvion() {
        return objAvion;
    }

    public void setObjAvion(Avion objAvion) {
        this.objAvion = objAvion;
    }

    @Override
    public String toString() {
        return ", destino='" + destino + '\'' +
                ", fecha_salida='" + fecha_salida + '\'' +
                ", hora_salida='" + hora_salida + '\'' +
                ", Avion =" + this.objAvion.getModelo() +
                '}';
    }
}
