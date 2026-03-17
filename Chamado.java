import java.util.ArrayList;

public class Chamado{

    private int id;
    private String bairro;
    private String descricao;
    private String nivelUrgencia;
    private String status;

    public Chamado (Int id, String bairro, String descricao, String nivelUrgencia, String status) {
        this.id = id;
        this.bairro = bairro;
        this.descricao = descricao;
        this.nivelUrgencia = nivelUrgencia;
        this.status = status;

    }

    public int getId(){
        return id;
    }

    public String getBairro(){
        return bairro;
    }

    public String getDescricao(){
        return descricao;
    }

    public String getNivelUrgencia(){
        return nivelUrgencia;
    }

    public String getStatus(){
        return status;
    }
}