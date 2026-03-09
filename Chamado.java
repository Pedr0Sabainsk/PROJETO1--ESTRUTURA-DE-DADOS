import java.util.ArrayList;

public class Chamado{

    private Int id;
    private Char bairro;
    private Char descricao;
    private Char nivelUrgencia;
    private Enum status;

    public Chamado (Int id, Char bairro, Char descricao,Char nivelUrgencia, Enum status) {
        this.id = id;
        this.bairro = bairro;
        this.descricao = descricao;
        this.nivelUrgencia = nivelUrgencia;
        this.status = status;

    }

    public Int getId(){
        return id;
    }

    public Char getBairro(){
        return bairro;
    }

    public Char getDescricao(){
        return descricao;
    }

    public Char getNivelUrgencia(){
        return nivelUrgencia;
    }

    public Enum getStatus(){
        return status;
    }



}