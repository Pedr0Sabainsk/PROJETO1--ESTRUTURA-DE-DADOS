import java.util.ArrayList;
import java.util.LinkedList;

public class CentralAtendimento {
    private LinkedList<Chamado> filaComum;
    private ArrayList<Chamado> pilhaEmergencia;
    private ArrayList<Chamado> atendimentosAtivos;
    private Historico historico;
    private int proximoId;

    public CentralAtendimento() {
        this.filaComum = new LinkedList<>();
        this.pilhaEmergencia = new ArrayList<>();
        this.atendimentosAtivos = new ArrayList<>();
        this.historico = new Historico();
        this.proximoId = 1;
    }

//Opção 1
    public void cadastrarChamado(String bairro, String descricao, int nivelUrgencia) {
        if (nivelUrgencia < 1 || nivelUrgencia > 5) {
            System.out.println("Nivel de urgencia invalido. Informe um valor entre 1 e 5.");
            return;
        }

        char nivelUrgenciaChar = (char) ('0' + nivelUrgencia);
        Chamado chamado = new Chamado(
                proximoId,
                bairro,
                descricao,
                nivelUrgenciaChar,
                Chamado.Status.ABERTO);

        historico.registrarChamado(chamado);

        if (nivelUrgencia >= 4) {
            pilhaEmergencia.add(chamado);
        } else {
            filaComum.add(chamado);
        }

        proximoId++;
        System.out.println("Chamado cadastrado com sucesso.");
    }


    //Opção 2
    public void realizarAtendimento() {
        Chamado chamado;

        if (!pilhaEmergencia.isEmpty()) {
            chamado = pilhaEmergencia.remove(pilhaEmergencia.size() - 1);
        } else if (!filaComum.isEmpty()) {
            chamado = filaComum.removeFirst();
        } else {
            System.out.println("Nao ha chamados para atendimento.");
            return;
        }

        atendimentosAtivos.add(chamado);
        historico.atualizarStatusPorId(chamado.getId(), Chamado.Status.EM_ATENDIMENTO);
        System.out.println("Atendimento iniciado para o chamado ID " + chamado.getId() + ".");
    }


//Opção 3
    public void concluirAtendimento(int indiceEscolhido) {
        if (atendimentosAtivos.isEmpty()) {
            System.out.println("Lista de atendimentos ativa vazia.");
            return;
        }

        if (indiceEscolhido < 0 || indiceEscolhido >= atendimentosAtivos.size()) {
            System.out.println("Indice invalido.");
            return;
        }

        Chamado chamado = atendimentosAtivos.remove(indiceEscolhido);
        historico.atualizarStatusPorId(chamado.getId(), Chamado.Status.FINALIZADO);
        System.out.println("Atendimento concluido para o chamado ID " + chamado.getId() + ".");
    }

    //Opção 4
    public void chamadosAbertos() {
    if (filaComum.isEmpty() && pilhaEmergencia.isEmpty()) {
        System.out.println("Não temos chamados abertos.");
        return;
    }

    System.out.println("Chamados de Emergencia Abertos");
    if (pilhaEmergencia.isEmpty()) {
        System.out.println("Nenhum chamado de emergencia pendente.");
    } else {
        for (int i = 0; i < pilhaEmergencia.size(); i++) {
            System.out.println("Emergencia " + i + ": " + pilhaEmergencia.get(i));
        }
    }

    System.out.println("\nChamados Comuns Abertos");
    if (filaComum.isEmpty()) {
        System.out.println("Nenhum chamado comum pendente.");
    } else {
        for (int i = 0; i < filaComum.size(); i++) {
            System.out.println("Comum " + i + ": " + filaComum.get(i));
        }
    }
}


//Opção 5
    public void mostrarAtendimentosAtivos() {
        if (atendimentosAtivos.isEmpty()) {
            System.out.println("Lista de atendimentos ativa vazia.");
            return;
        }

        for (int i = 0; i < atendimentosAtivos.size(); i++) {
            System.out.println("Indice " + i + " -> " + atendimentosAtivos.get(i));
        }
    }
//Opção 6

    public void historicoCompleto() {
        ArrayList<Chamado> chamados = historico.getChamados();
        if (chamados.isEmpty()) {
            System.out.println("Historico de chamados vazio.");
            return;
        }

        for (int i = 0; i < chamados.size(); i++) {
            System.out.println("Chamado " + i + ": " + chamados.get(i));
        }
    }


//Opção 10
    public void simularCadastro() {
        System.out.println("Simulação de cadastro.");
        cadastrarChamado("Tatuapé", "Comum", '2');
        cadastrarChamado("Pinheiros", "Emergência", '5');
        cadastrarChamado("Bela Vista", "Comum", '3');
        cadastrarChamado("Tucuruvi", "Emergência", '4');
        cadastrarChamado("Tucuruvi", "Comum", '1');
        cadastrarChamado("Butantã", "Emergência", '5');
        cadastrarChamado("Mooca", "Emergência", '5');
        cadastrarChamado("Higienópolis", "Emergência", '4');
        cadastrarChamado("Higienópolis", "Comum", '2');
        cadastrarChamado("Higienópolis", "Emergência", '4');

        System.out.println("Simulação concluída.");
    }

}