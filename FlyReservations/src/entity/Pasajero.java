package entity;

public class Pasajero {
    private int id_pasajero;
    private String nombre;
    private String apellidos;
    private String documento_identidad;

    public Pasajero(){}
    public Pasajero(int id_pasajero, String nombre, String apellidos, String documento_identidad) {
        this.id_pasajero = id_pasajero;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.documento_identidad = documento_identidad;
    }

    public int getId_pasajero() {
        return id_pasajero;
    }

    public void setId_pasajero(int id_pasajero) {
        this.id_pasajero = id_pasajero;
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

    public String getDocumento_identidad() {
        return documento_identidad;
    }

    public void setDocumento_identidad(String documento_identidad) {
        this.documento_identidad = documento_identidad;
    }

    @Override
    public String toString() {
        return
                "id paciente:" + id_pasajero + "\n" +
                "nombre: " + nombre + "\n" +
                "apellidos: " + apellidos + "\n" +
                "documento_identidad: " + documento_identidad + "\n"
                ;
    }
}
