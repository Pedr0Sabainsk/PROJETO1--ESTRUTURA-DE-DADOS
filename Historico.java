import java.util.ArrayList;

public class Historico {
    private ArrayList<Chamado> chamados;

    public Historico() {
        this.chamados = new ArrayList<>();
    }

    public void registrarChamado(Chamado chamado) {
        chamados.add(chamado);
    }

    public ArrayList<Chamado> getChamados() {
        return chamados;
    }

    public boolean atualizarStatusPorId(int id, Chamado.Status novoStatus) {
        for (Chamado chamado : chamados) {
            if (chamado.getId() == id) {
                chamado.setStatus(novoStatus);
                return true;
            }
        }
        return false;
    }
}
