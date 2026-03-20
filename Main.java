import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        CentralAtendimento central = new CentralAtendimento();
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
            String entradaOpcao = scanner.nextLine();
            try {
                opcao = Integer.parseInt(entradaOpcao);
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    System.out.print("Bairro: ");
                    String bairro = scanner.nextLine();

                    System.out.print("Descricao: ");
                    String descricao = scanner.nextLine();

                    System.out.print("Nivel de urgencia (1-5): ");
                    String entradaNivel = scanner.nextLine();
                    int nivelUrgencia;

                    try {
                        nivelUrgencia = Integer.parseInt(entradaNivel);
                    } catch (NumberFormatException e) {
                        System.out.println("Nivel de urgencia invalido. Informe um numero entre 1 e 5.");
                        break;
                    }

                    central.cadastrarChamado(bairro, descricao, nivelUrgencia);
                    break;
                case 2:
                    central.realizarAtendimento();
                    break;
                case 3:
                    central.mostrarAtendimentosAtivos();
                    System.out.print("Digite o indice do atendimento que deseja concluir: ");
                    String entradaIndice = scanner.nextLine();
                    int indiceEscolhido;

                    try {
                        indiceEscolhido = Integer.parseInt(entradaIndice);
                    } catch (NumberFormatException e) {
                        System.out.println("Indice invalido.");
                        break;
                    }

                    central.concluirAtendimento(indiceEscolhido);
                    break;
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    System.out.println("Em desenvolvimento");
                    break;
                case 11:
                    System.out.println("Fechando sistema.");
                    break;
                default:
                    System.out.println("Digite uma opcao valida.");
            }
        } while (opcao != 11);
    }
}