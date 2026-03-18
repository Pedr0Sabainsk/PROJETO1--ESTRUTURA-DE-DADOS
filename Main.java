import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static LinkedList<Chamado> filaComum = new LinkedList<>();
    private static ArrayList<Chamado> pilhaEmergencia = new ArrayList<>();
    private static ArrayList<Chamado> atendimentosAtivos = new ArrayList<>();
    private static Historico historico = new Historico();
    private static int proximoId = 1;

    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int opcao;

        do {
            System.out.println();
            System.out.println("Sistema de atendimento de Emergencia Urbana");
            System.out.println("1. Cadastrar chamado");
            System.out.println("2. Realizar atendimento chamado");
            System.out.println("3. Concluir atendimento");
            System.out.println("4. Mostrar chamados abertos");
            System.out.println("5. Mostrar lista ativa de atendimentos");
            System.out.println("6. Mostrar historico completo dos chamados");
            System.out.println("7. Mostrar estatistica dos niveis de emergencia");
            System.out.println("8. Mostrar os chamados com nivel de emergencia");
            System.out.println("9. Mostrar Ranking de bairros");
            System.out.println("10. Simular Cadastro de Chamado");
            System.out.println("11. Sair");
            System.out.print("Opcao: ");
            opcao = lerInteiro();

            switch (opcao) {
                case 1:
                    cadastrarChamado();
                    break;
                case 2:
                    realizarAtendimentoChamado();
                    break;
                case 3:
                    concluirAtendimento();
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    System.out.println("Opcao fora do escopo desta etapa.");
                    break;
                case 11:
                    System.out.println("Fechando sistema.");
                    break;
                default:
                    System.out.println("Digite uma opcao valida.");
            }
        } while (opcao != 11);
    }

    public static void cadastrarChamado() {
        System.out.println("Bairro: ");
        String bairro = scanner.nextLine();

        System.out.print("Descricao: ");
        String descricao = scanner.nextLine();

        char nivelUrgencia = lerNivelUrgencia();

        Chamado chamado = new Chamado(
                proximoId,
                bairro,
                descricao,
                nivelUrgencia,
                Chamado.Status.ABERTO);

        historico.registrarChamado(chamado);

        if (nivelUrgencia >= '4'){
            pilhaEmergencia.add(chamado);
        } else {
            filaComum.add(chamado);
        }

        proximoId++;
    }

    public static void realizarAtendimentoChamado(){
        if (!pilhaEmergencia.isEmpty()){
            Chamado chamado = pilhaEmergencia.top();
            pilhaEmergencia.pop();
        }
        if (!filaComum.isEmpty()){
            Chamado chamado = filaComum.top();
            System.out.println("Não há chamados.");
            return;
        }
    }

    public static concluirAtendimento(){
        if (!atendimentosAtivos.isEmpty()){
            System.out.println("Lista de atendimentos vazia.");
        }
        for (){

        }
        System.out.println("Digite o índice do atendimento que deseja concluir: ");
        int indice = scanner.nextInt();
        if (indice > atendimentosAtivos.length|| indice < atendimentosAtivos.length){
            return;
        } else {
            atendimentosAtivos.remove(indice);
        }
    }
}