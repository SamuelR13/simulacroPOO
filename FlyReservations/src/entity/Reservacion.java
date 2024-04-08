package entity;

public class Reservacion {
    private int id_reservacion;
    private Pasajero pasajero;
    private Vuelo vuelo;
    private String fecha_reservacion;
    private String asiento;

    public Reservacion(){}

    public Reservacion(int id_reservacion, Pasajero pasajero, Vuelo vuelo, String fecha_reservacion, String asiento) {
        this.id_reservacion = id_reservacion;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.fecha_reservacion = fecha_reservacion;
        this.asiento = asiento;
    }

    public int getId_reservacion() {
        return id_reservacion;
    }

    public void setId_reservacion(int id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public String getFecha_reservacion() {
        return fecha_reservacion;
    }

    public void setFecha_reservacion(String fecha_reservacion) {
        this.fecha_reservacion = fecha_reservacion;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    @Override
    public String toString() {
        return
                "id reservacion: " + id_reservacion + "\n" +
                "pasajero: " + pasajero.getNombre() + "\n" +
                "vuelo: " + vuelo.getId_vuelo() + "\n" +
                "fecha reservacion" + fecha_reservacion + "\n" +
                "asiento: " + asiento + "\n"
                ;
    }
}
