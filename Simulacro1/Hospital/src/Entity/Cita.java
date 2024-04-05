package Entity;

public class Cita {
    private int id;
    private String date;
    private String hora;
    private String motivo;
    private int idPaciente;
    private int idMedico;


    //inyeccion de dependencias
    private Medico objMedico;
    private  Paciente objPaciente;

    public Cita() {
    }

    public Cita(String date, String hora, String motivo, int idPaciente, int idMedico, Medico objMedico, Paciente objPaciente) {
        this.date = date;
        this.hora = hora;
        this.motivo = motivo;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
        this.objMedico = objMedico;
        this.objPaciente = objPaciente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public Medico getObjMedico() {
        return objMedico;
    }

    public void setObjMedico(Medico objMedico) {
        this.objMedico = objMedico;
    }

    public Paciente getObjPaciente() {
        return objPaciente;
    }

    public void setObjPaciente(Paciente objPaciente) {
        this.objPaciente = objPaciente;
    }


    //esto es lo que quiero mostrar en este caso tenemos una conexion doble
    //asi que mostramos OBJMEDICO Y OBJPACIENTE con sus geters
    @Override
    public String toString() {
        return "Cita{" +
                ", date='" + date + '\'' +
                ", hora='" + hora + '\'' +
                ", motivo='" + motivo + '\'' +
                ", Medico=" + objMedico.getNombre() +
                ", Paciente=" + objPaciente.getNombre() +
                '}';
    }
}
