public class Chamado {

    public enum Status {
        ABERTO,
        EM_ATENDIMENTO,
        FINALIZADO
    }

    private int id;
    private String bairro;
    private String descricao;
    private char nivelUrgencia;
    private Status status;

    public Chamado(int id, String bairro, String descricao, char nivelUrgencia, Status status) {
        this.id = id;
        this.bairro = bairro;
        this.descricao = descricao;
        this.nivelUrgencia = nivelUrgencia;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getBairro() {
        return bairro;
    }

    public String getDescricao() {
        return descricao;
    }

    public char getNivelUrgencia() {
        return nivelUrgencia;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + " | Bairro: " + bairro
                + " | Descricao: " + descricao
                + " | Nivel de urgencia: " + nivelUrgencia
                + " | Status: " + status;
    }
}