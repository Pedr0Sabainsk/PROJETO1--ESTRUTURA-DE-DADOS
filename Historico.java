import java.util.ArrayList;

public class Historico{
    private ArrayList<Chamado> chamado;

    public Pedido(){
        this.Cadastro = new ArrayList<>();
    }

    public void cadastraCliente(Chamado chamado){
        this.chamado.add (chamado);
    }
}
