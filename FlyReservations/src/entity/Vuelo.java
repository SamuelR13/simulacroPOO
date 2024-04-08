package entity;

public class Vuelo {
    private int id_vuelo;
    private String destino;
    private String fecha_salida;
    private String hora_salida;
    private Avion Avion;

    public Vuelo(){}

    public Vuelo(int id_vuelo, String destino, String fecha_salida, String hora_salida, entity.Avion avion) {
        this.id_vuelo = id_vuelo;
        this.destino = destino;
        this.fecha_salida = fecha_salida;
        this.hora_salida = hora_salida;
        Avion = avion;
    }

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
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

    public entity.Avion getAvion() {
        return Avion;
    }

    public void setAvion(entity.Avion avion) {
        Avion = avion;
    }

    @Override
    public String toString() {
        return
                "id medico:" + id_vuelo + "\n" +
                "destino: " + destino + "\n" +
                "fecha salida: " + fecha_salida + "\n" +
                        "hora salida: " + hora_salida + "\n" +
                "Avion: " + Avion.getModelo()
                ;
    }
}
