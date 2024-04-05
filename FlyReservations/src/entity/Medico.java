package entity;

public class Medico {
    private int id_medico;
    private String nombre;
    private String apellidos;
    private Especialidad Especialidad;

    public Medico(){}

    public Medico(int id_medico, String nombre, String apellidos, Especialidad Especialidad) {
        this.id_medico = id_medico;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.Especialidad = Especialidad;
    }

    public int getId_medico() {
        return id_medico;
    }

    public void setId_medico(int id_medico) {
        this.id_medico = id_medico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Especialidad getEspecialidad() {
        return Especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.Especialidad = especialidad;
    }

    @Override
    public String toString() {
        return
                "id medico:" + id_medico + "\n" +
                "nombre: " + nombre + "\n" +
                "apellidos: " + apellidos + "\n" +
                "especialidad: " + Especialidad.getNombre()
                ;
    }
}
