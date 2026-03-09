if (urgencia >= 4){
    //vai para uma PILHA de emergência//
} else {
    //vai para uma FILA comum//

if (pilha){
    /*desempilha aquele que está no topo*/
} else { 
    //desenfileira//
}

public void concluiAtendimento(String concluido){
    for (String atendimento : atendimentosAtivos){
        System.out.println(atendimento);
    }
    System.out.println("Qual atendimento foi concluído?");
    String concluido = sla.nextLine();
    atendimentosAtivos.remove(concluido);
    /*falta isso: 
    d) Marcar como FINALIZADO na lista linear (ArrayList) de HISTÓRICO. Para atualizar o
    status como FINALIZADO no histórico, realizar uma busca sequencial na estrutura
    utilizada pelo id do chamado.*/
}

public void sai(String resposta){
    System.out.println("Você tem certeza que quer sair?");
    String resposta;
    resposta = sla.nextLine();
    if(resposta.toLowerCase().equals("sim")){
        System.out.println("Fechando sistema.");
        System.exit(0);
    } else {
        menu();
    }
}

switch(respostaMenu){
case(1):
chamado();
break;
case(2):
realizaAtendimento();
break;
case(3):
concluiAtendimento(String concluido);
break;
case(4):
mostraChamados();
break;
case(5):
mostraListaAtendimentos();
break;
case(6):
mostraHistoricoChamados();
break;
case(7):
mostraEstatisticaEmergencia();
break;
case(8):
mostraChamadosEmergencia();
break;
case(9):
mostraRanking();
break;
case(10):
simulaCadastro();
break;
case(11):
sai(String resposta):
break;
default:
System.out.println("Digite um valor válido.");
}