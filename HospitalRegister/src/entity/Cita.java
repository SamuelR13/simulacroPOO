package entity;

public class Cita {
    private int id_cita;
    private Paciente paciente;
    private Medico medico;
    private String fecha_cita;
    private String hora_cita;
    private String motivo;

    public Cita(){}

    public Cita(int id_cita, Paciente paciente, Medico medico, String fecha_cita, String hora_cita, String motivo) {
        this.id_cita = id_cita;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha_cita = fecha_cita;
        this.hora_cita = hora_cita;
        this.motivo = motivo;
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getHora_cita() {
        return hora_cita;
    }

    public void setHora_cita(String hora_cita) {
        this.hora_cita = hora_cita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public String toString() {
        return
                "id cita: " + id_cita + "\n" +
                "id paciente: " + paciente + "\n" +
                "id medico: " + medico + "\n" +
                "fecha cita" + fecha_cita + "\n" +
                "hora cita" + hora_cita + "\n" +
                "motivo: " + motivo + "\n"
                ;
    }
}
