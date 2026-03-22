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

        if (nivelUrgencia >= 4 && pilhaEmergencia.size() >= 30) {
            System.out.println("A Pilha de Emergencia atingiu a sua capacidade maxima (30).");
            return;
        }
        if (nivelUrgencia < 4 && filaComum.size() >= 30) {
            System.out.println("A Fila Comum atingiu a sua capacidade maxima (30).");
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

//Opção 7
    
    public void mostrarEstatisticaNiveis() {
        ArrayList<Chamado> chamados = historico.getChamados();
        if (chamados.isEmpty()) {
            System.out.println("Nenhum chamado cadastrado para gerar estatisticas.");
            return;
        }

        int cont1 = 0, cont2 = 0, cont3 = 0, cont4 = 0, cont5 = 0;

        for (int i = 0; i < chamados.size(); i++) {
            char nivel = chamados.get(i).getNivelUrgencia();
            if (nivel == '1') cont1++;
            else if (nivel == '2') cont2++;
            else if (nivel == '3') cont3++;
            else if (nivel == '4') cont4++;
            else if (nivel == '5') cont5++;
        }

        System.out.println("\nEstatistica dos Niveis de Emergencia ");
        System.out.println("Nivel 1: " + cont1 + " chamado(s)");
        System.out.println("Nivel 2: " + cont2 + " chamado(s)");
        System.out.println("Nivel 3: " + cont3 + " chamado(s)");
        System.out.println("Nivel 4: " + cont4 + " chamado(s)");
        System.out.println("Nivel 5: " + cont5 + " chamado(s)");
    }


//Opção 10
    public void simularCadastro() {
        System.out.println("Simulacao de cadastro.");
        cadastrarChamado("Tatuape", "Comum", 2);
        cadastrarChamado("Pinheiros", "Emergencia", 5);
        cadastrarChamado("Bela Vista", "Comum", 3);
        cadastrarChamado("Tucuruvi", "Emergencia", 4);
        cadastrarChamado("Tucuruvi", "Comum", 1);
        cadastrarChamado("Butanta", "Emergencia", 5);
        cadastrarChamado("Mooca", "Emergencia", 5);
        cadastrarChamado("Higienopolis", "Emergencia", 4);
        cadastrarChamado("Higienopolis", "Comum", 2);
        cadastrarChamado("Higienopolis", "Emergencia", 4);

        System.out.println("Simulacao concluida. 10 chamados inseridos!");
    }

}