import java.util.ArrayList;

public class CentralAtendimento {
    private FilaCircular<Chamado> filaComum;
    private Pilha <Chamado> pilhaEmergencia;
    private ArrayList<Chamado> atendimentosAtivos;
    private Historico historico;
    private int proximoId;

    public CentralAtendimento() {
        this.filaComum = new FilaCircular<>(30);
        this.pilhaEmergencia = new Pilha<>(30);
        this.atendimentosAtivos = new ArrayList<>();
        this.historico = new Historico();
        this.proximoId = 1;
    }

    //Opção 1
    // Usa push para emergencias porque a pilha prioriza o ultimo caso critico recebido (LIFO).
    // Usa enqueue para comuns porque a fila mantém ordem de chegada (FIFO).
    public void cadastrarChamado(String bairro, String descricao, int nivelUrgencia) {
        if (nivelUrgencia < 1 || nivelUrgencia > 5) {
            System.out.println("Nvel de urgencia inválido. Informe um valor entre 1 e 5.");
            return;
        }

        if (nivelUrgencia >= 4 && pilhaEmergencia.isFull()) {
            System.out.println("A Pilha de Emergência atingiu a sua capacidade maxima (30).");
            return;
        }
        if (nivelUrgencia < 4 && filaComum.isFull()) {
            System.out.println("A Fila Comum atingiu a sua capacidade maxima (30).");
            return;
        }

        char nivelUrgenciaChar = Integer.toString(nivelUrgencia).charAt(0);
        Chamado chamado = new Chamado(proximoId, bairro, descricao, nivelUrgenciaChar, Chamado.Status.ABERTO);

        historico.registrarChamado(chamado);

        try {
            if (nivelUrgencia >= 4) {
                pilhaEmergencia.push(chamado);
            } else {
                filaComum.enqueue(chamado);
            }
            proximoId++;
            System.out.println("Chamado cadastrado com sucesso.");

            Chamado proximaEmergencia = proximoEmergenciaSemRemover();
            Chamado proximoComum = proximoComumSemRemover();
            if (proximaEmergencia != null) {
                System.out.println("Proxima emergencia (top): ID " + proximaEmergencia.getId());
            }
            if (proximoComum != null) {
                System.out.println("Proximo comum (front): ID " + proximoComum.getId());
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }


    //Opção 2
    // Usa pop para retirar primeiro o topo da pilha de emergencias (LIFO).
    // Se nao houver emergencia, usa dequeue para atender os comuns por ordem de chegada (FIFO).
    public void realizarAtendimento() {
        Chamado chamado;

        try {
            Chamado proximaEmergencia = proximoEmergenciaSemRemover();
            Chamado proximoComum = proximoComumSemRemover();

            if (proximaEmergencia != null) {
                System.out.println("Atendendo emergencia prioritaria (top): ID " + proximaEmergencia.getId());
                chamado = pilhaEmergencia.pop();
            } else if (proximoComum != null) {
                System.out.println("Atendendo chamado comum (front): ID " + proximoComum.getId());
                chamado = filaComum.dequeue();
            } else {
                System.out.println("Nao há chamados para atendimento.");
                return;
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return;
        }

        atendimentosAtivos.add(chamado);
        historico.atualizarStatusPorId(chamado.getId(), Chamado.Status.EM_ATENDIMENTO);
        System.out.println("Atendimento iniciado para o chamado " + chamado.getId() + ".");
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
    // Consulta o estado das estruturas para listar os chamados que ainda aguardam atendimento.
    public void chamadosAbertos() {
        if (filaComum.isEmpty() && pilhaEmergencia.isEmpty()) {
            System.out.println("Não temos chamados abertos.");
            return;
        }

        System.out.println("Chamados de Emergência Abertos");
        if (pilhaEmergencia.isEmpty()) {
            System.out.println("Nenhum chamado de emergência pendente.");
        } else {
            try {
                Chamado topoEmergencia = pilhaEmergencia.topo();
                System.out.println("Proxima emergencia (top): ID " + topoEmergencia.getId());
            } catch (Exception e) {
                System.out.println("Erro ao consultar top da pilha: " + e.getMessage());
            }
            System.out.println(pilhaEmergencia.toString());
        }

        System.out.println("\nChamados Comuns Abertos:");
        if (filaComum.isEmpty()) {
            System.out.println("Nenhum chamado comum pendente.");
        } else {
            try {
                Chamado inicioFila = filaComum.front();
                System.out.println("Proximo comum (front): ID " + inicioFila.getId());
            } catch (Exception e) {
                System.out.println("Erro ao consultar front da fila: " + e.getMessage());
            }
            System.out.println(filaComum.toString());
        }
    }

    // Usa top para consultar a proxima emergencia sem remover da pilha.
    private Chamado proximoEmergenciaSemRemover() throws Exception {
        if (pilhaEmergencia.isEmpty()) {
            return null;
        }
        return pilhaEmergencia.topo();
    }

    // Usa front para consultar o proximo comum sem remover da fila.
    private Chamado proximoComumSemRemover() throws Exception {
        if (filaComum.isEmpty()) {
            return null;
        }
        return filaComum.front();
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

    //Opção 8
    public void mostrarChamadosPorNivel(int nivelBusca) {
        ArrayList<Chamado> chamados = historico.getChamados();
        int contador = 0;

        char nivelBuscaChar = Integer.toString(nivelBusca).charAt(0);

        System.out.println("Chamados com nível de emergência " + nivelBusca + ":");
        for (int i = 0; i < chamados.size(); i++) {
            Chamado c = chamados.get(i);
            
            // Agora você compara char com char!
            if (c.getNivelUrgencia() == nivelBuscaChar) {
                System.out.println(c);
                contador++;
            }
        }
        System.out.println("Total cadastrado para o nível " + nivelBusca + ": " + contador); 
    }

    //Opção 9
    public void rankearBairros(){
        ArrayList<Chamado> lista = historico.getChamados();
        ArrayList<String> rankNomes = new ArrayList<>();
        ArrayList<Integer> rankNum = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            String bairroAtual = lista.get(i).getBairro(); //nao lembrava: get pega o valor da posição
            int indice = rankNomes.indexOf(bairroAtual);

            if (indice != -1) {
                int qtdAtual = rankNum.get(indice);
                rankNum.set(indice, qtdAtual + 1);
            } else {
                rankNomes.add(bairroAtual);
                rankNum.add(1);
            }
        }

        for (int i = 0; i < rankNum.size() - 1; i++) {
            int indiceMaior = i;
            
            for (int j = i + 1; j < rankNum.size(); j++) {
                if (rankNum.get(j) > rankNum.get(indiceMaior)) {
                    indiceMaior = j;
                }
            }

            int tempNum = rankNum.get(indiceMaior);
            rankNum.set(indiceMaior, rankNum.get(i)); //nao lembrava: set pega a posição primeiro e o valor dps
            rankNum.set(i, tempNum);

            String tempNome = rankNomes.get(indiceMaior);
            rankNomes.set(indiceMaior, rankNomes.get(i));
            rankNomes.set(i, tempNome);
        }

        for (int i = 0; i < rankNomes.size(); i++) {
        System.out.println((i + 1) + "º lugar - " + rankNomes.get(i) + ": " + rankNum.get(i) + " chamados");
}
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