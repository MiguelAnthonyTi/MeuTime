import servico.JogadorServico;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner ler = new Scanner (System.in);
        JogadorServico jogadorServico = new JogadorServico();

        int opcao = 0; 

        do{
            exibirMenuPrincipal();
            String entradaOpcao = ler.nextLine().trim();

            try {
                if (entradaOpcao.isEmpty()){
                    opcao =0;
                    System.out.println("Nada foi digitado tente novamente");
                } else {
                    opcao = Integer.parseInt(entradaOpcao);
                }
            } catch (NumberFormatException e ){
                System.out.println("Nenhuma opção foi selecionada, digite um numero de 1-6");
                opcao =0;
            }

            switch (opcao){
                case 1:
                    jogadorServico.criarNovoJogador(ler);
                    break;

                case 2:
                    jogadorServico .listarTodosJogadores();
                    break;

                case 3 :
                    jogadorServico.buscarJogadorPorNome(ler);
                    break;
                
                case 4: 
                    jogadorServico.editarJogador(ler);
                    break;

                case 5: 
                    jogadorServico.excluirJogador(ler);
                    break;

                case 6:
                    System.out.println("Feche o programa");
                    break;
                default:
                System.out.println("Por favor tente novamente, digitando numero de 1-6");
            }

            if (opcao != 6 && opcao != 0){
                System.out.println("Apenas aperte ENTER pra continuar");
                ler.nextLine();
            } else if (opcao == 0 && !entradaOpcao.isEmpty()){

            }
        } while (opcao != 6);

        ler.close();
        System.out.println("Programa foi finalizado!!");
    }

    public static void exibirMenuPrincipal(){
        System.out.println("\n >>>>>GERENCIADOR DE JOGADORES MeuTime<<<<<"); 
        System.out.println("1. Criar Novo Jogador");
        System.out.println("2. Listar Todos os Jogadores");
        System.out.println("3. Buscar Jogador por Nome");
        System.out.println("4. Editar Jogador");
        System.out.println("5. Excluir Jogador");
        System.out.println("6. Sair");
        System.out.println("\nEscolha uma opção: ");
    }
}
