package Entity;

public class Medico {
    private int id;
    private String nombre;
    private String apellidos;
    private int idEspecialidad;

    public Medico() {
    }

    //INYECCION DE DEPENDENCIAS , ES GUARDAR UN OBJETO O CLASE DENTRO DE OTRA CLASE

    private Especialidad objEspecialidad;

    //se ponen todos menos el id de la entidad ya que viene desde la base de datos

    public Medico(String nombre, String apellidos, int idEspecialidad, Especialidad objEspecialidad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.idEspecialidad = idEspecialidad;
        this.objEspecialidad = objEspecialidad;
    }

    //Arriba añadi el objeto que trajimos desde Especialidad dentro del constructor
    //ahora creo getters y setters para el mismo


    //en el caso de los getters y setters si se ponen todos

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public Especialidad getObjEspecialidad() {
        return objEspecialidad;
    }

    public void setObjEspecialidad(Especialidad objEspecialidad) {
        this.objEspecialidad = objEspecialidad;
    }

    @Override
    public String toString() {
        return ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", Especialidad: " + this.objEspecialidad.getNombre();
    }
}
