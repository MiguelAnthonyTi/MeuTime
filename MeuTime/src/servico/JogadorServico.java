package servico;

import modelo.Jogador;
import execoes.DadoInvalidoException;
import persistencia.JogadorPersistencia;


import java.util.List;
import java.util.Scanner;

public class JogadorServico {
    private List<Jogador> listaDeJogadores;
    private JogadorPersistencia persistencia;

    public JogadorServico() {
        this.persistencia = new JogadorPersistencia();  
        this.listaDeJogadores = this.persistencia.carregarJogadores();

        if (this.listaDeJogadores.isEmpty()) { 
            System.out.println("Nenhum jogador nesse Time:");
            adicionarJogadores();
            this.persistencia.salvarJogadores(this.listaDeJogadores);
        }
    }

// Queria colocar os 11 titular definido ja no arquivo.txt mas não consegui e tambem nem o nome do time ja definido no topo do arquivo
    private void adicionarJogadores(){
        try{
            Jogador j1 = new Jogador("Matheus Mendes", "Goleiro", 01);
            j1.setHabilidade(8);
            this.listaDeJogadores.add(j1);

            Jogador j2 = new Jogador("Mariano", "lateral (D)", 23); 
            j2.setHabilidade(5);
            this.listaDeJogadores.add(j2);

            Jogador j3 = new Jogador("Julio", "Zagueiro (D)", 18);
            j3.setHabilidade(6);
            this.listaDeJogadores.add(j3);

            Jogador j4 = new Jogador("Lucão", "Zagueiro (E)", 03);
            j4.setHabilidade(7);
            this.listaDeJogadores.add(j4);

            Jogador j5 = new Jogador("Marlon", "Lateral (E)", 6);
            j5.setHabilidade(88);
            this.listaDeJogadores.add(j5);

            Jogador j6 = new Jogador("Elizari", "Volante", 5);
            j6.setHabilidade(7);
            this.listaDeJogadores.add(j6);

            Jogador j7 = new Jogador("Miqueias", "Meio-Campo", 42); 
            j7.setHabilidade(96);
            this.listaDeJogadores.add(j7);

            Jogador j8 = new Jogador("Cauan Barros", "Meio-Campo", 88);
            j8.setHabilidade(8);
            this.listaDeJogadores.add(j8);

            Jogador j9 = new Jogador("Figueiredo", "Atacante",22);
            j9.setHabilidade(7);
            this.listaDeJogadores.add(j9);

            Jogador j10 = new Jogador("Willian do Bigode", "Centroavante", 9); 
            j10.setHabilidade(99);
            this.listaDeJogadores.add(j10);

            Jogador j11 = new Jogador("Stenio", "Atacante", 17); 
            j11.setHabilidade(95);
            this.listaDeJogadores.add(j11);

            System.out.println(this.listaDeJogadores.size() + " Esses são os 11 Titulares do America FC");
        } catch (DadoInvalidoException e ){
            System.out.println("Erro ao criar os 11 titulares" + e.getMessage());

        }
    }

    public void criarNovoJogador(Scanner scannerEntrada) {
        System.out.println("\n--- Criar Novo Jogador ---");
        try {
            System.out.print("Nome do jogador: ");
            String nome = scannerEntrada.nextLine();

            System.out.print("Posição: ");
            String posicao = scannerEntrada.nextLine();

            int camisa = 0;
            while (true) {
                System.out.print("Número da camisa: ");
                String camisaStr = scannerEntrada.nextLine();
                try {
                    camisa = Integer.parseInt(camisaStr);
                    if (camisa > 0) break;
                    System.out.println("Número da camisa deve ser positivo. Tente novamente.");
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida para camisa. Por favor, digite um número.");
                }
            }
            
            Jogador novoJogador = new Jogador(nome, posicao, camisa); 

            boolean dadosCompletamenteValidados = false;
            while(!dadosCompletamenteValidados){
                try {
                    novoJogador.setNome(nome);
                    novoJogador.setPosicao(posicao); 
                    novoJogador.setCamisa(camisa); 

                    System.out.print("Habilidade (0-10): ");
                    String habilidadeStr = scannerEntrada.nextLine();
                    int habilidadeInput = Integer.parseInt(habilidadeStr);
                    novoJogador.setHabilidade(habilidadeInput); 

                    dadosCompletamenteValidados = true; 

                } catch (DadoInvalidoException e) {
                    System.err.println("Erro de validação: " + e.getMessage());
                    System.out.println("Por favor, corrija o dado informado.");
                    
                    if (e.getMessage().toLowerCase().contains("nome")) {
                        System.out.print("Nome do jogador: "); nome = scannerEntrada.nextLine();
                    } else if (e.getMessage().toLowerCase().contains("posição") || e.getMessage().toLowerCase().contains("posicao")) {
                        System.out.print("Posição: "); posicao = scannerEntrada.nextLine();
                    } else if (e.getMessage().toLowerCase().contains("camisa")) {
                        while (true) {
                             System.out.print("Número da camisa: ");
                             String camisaStr = scannerEntrada.nextLine();
                             try {
                                 camisa = Integer.parseInt(camisaStr);
                                 if (camisa > 0) break;
                                 System.out.println("Número da camisa deve ser positivo.");
                             } catch (NumberFormatException nfe) {
                                 System.out.println("Entrada inválida para camisa.");
                             }
                         }
                    } 
                    
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida para número (camisa ou habilidade). Use apenas dígitos.");
                }
            }

            this.listaDeJogadores.add(novoJogador);
            this.persistencia.salvarJogadores(this.listaDeJogadores);
            System.out.println("Jogador '" + novoJogador.getNome() + "' criado com sucesso!");

        } catch (Exception e) { 
            System.err.println("Ocorreu um erro inesperado ao criar o jogador: " + e.getMessage());
             
        }
    }

    public void listarTodosJogadores() {
        System.out.println("\n--- Lista de Jogadores ---");
        if (this.listaDeJogadores.isEmpty()) {
            System.out.println("Nenhum jogador cadastrado no momento.");
            return;
        }
        for (int i = 0; i < this.listaDeJogadores.size(); i++) {
            System.out.println((i + 1) + ". " + this.listaDeJogadores.get(i).toString());
        }
    }

    public Jogador buscarJogadorPorNome(Scanner scannerEntrada) {
        System.out.println("\n--- Buscar Jogador por Nome ---");
        if (listaDeJogadores.isEmpty()){
            System.out.println("Nenhum jogador cadastrado para buscar.");
            return null;
        }
        System.out.print("Digite o nome do jogador a ser buscado: ");
        String nomeBusca = scannerEntrada.nextLine();

        for (Jogador jogador : listaDeJogadores) {
            if (jogador.getNome().equalsIgnoreCase(nomeBusca)) {
                System.out.println("Jogador encontrado: " + jogador);
                return jogador;
            }
        }
        System.out.println("Jogador com o nome '" + nomeBusca + "' não encontrado.");
        return null;
    }

    public void editarJogador(Scanner scannerEntrada) {
        System.out.println("\n--- Editar Jogador ---");
        if (listaDeJogadores.isEmpty()){
            System.out.println("Nenhum jogador cadastrado para editar.");
            return;
        }

        System.out.print("Digite o nome do jogador que deseja editar: ");
        String nomeParaEditar = scannerEntrada.nextLine();

        Jogador jogadorParaEditar = null;
        for (Jogador j : listaDeJogadores) {
            if (j.getNome().equalsIgnoreCase(nomeParaEditar)) {
                jogadorParaEditar = j;
                break;
            }
        }

        if (jogadorParaEditar == null) {
            System.out.println("Jogador '" + nomeParaEditar + "' não encontrado para edição.");
            return;
        }

        System.out.println("Editando jogador: " + jogadorParaEditar);
        System.out.println("Deixe em branco e pressione Enter para não alterar o campo.");
        
        
        String novoNomeStr, novaPosicaoStr, novaCamisaStr, novaHabilidadeStr;

        try {
           
            System.out.print("Novo nome (" + jogadorParaEditar.getNome() + "): ");
            novoNomeStr = scannerEntrada.nextLine();
            if (!novoNomeStr.trim().isEmpty()) {
                jogadorParaEditar.setNome(novoNomeStr); 
            }

            
            System.out.print("Nova posição (" + jogadorParaEditar.getPosicao() + "): ");
            novaPosicaoStr = scannerEntrada.nextLine();
            if (!novaPosicaoStr.trim().isEmpty()) {
                jogadorParaEditar.setPosicao(novaPosicaoStr); 
            }

           
            System.out.print("Nova camisa (" + jogadorParaEditar.getCamisa() + "): ");
            novaCamisaStr = scannerEntrada.nextLine();
            if (!novaCamisaStr.trim().isEmpty()) {
                try {
                    int novaCamisa = Integer.parseInt(novaCamisaStr);
                    jogadorParaEditar.setCamisa(novaCamisa);
                } catch (NumberFormatException e) {
                    System.out.println("Formato de número inválido para camisa. Valor anterior mantido.");
                }
            }

            
            System.out.print("Nova habilidade (0-100) (" + jogadorParaEditar.getHabilidade() + "): ");
            novaHabilidadeStr = scannerEntrada.nextLine();
            if (!novaHabilidadeStr.trim().isEmpty()) {
                try {
                    int novaHabilidade = Integer.parseInt(novaHabilidadeStr);
                    jogadorParaEditar.setHabilidade(novaHabilidade); 
                } catch (NumberFormatException e) {
                    System.out.println("Formato de número inválido para habilidade. Valor anterior mantido.");
                }
            }

            this.persistencia.salvarJogadores(this.listaDeJogadores); 
            System.out.println("Jogador '" + jogadorParaEditar.getNome() + "' atualizado com sucesso!");

        } catch (DadoInvalidoException e) {
            System.err.println("Erro ao atualizar jogador: " + e.getMessage() + " A alteração neste campo não foi aplicada.");
           
            this.persistencia.salvarJogadores(this.listaDeJogadores); 
        } catch (Exception e) { 
            System.err.println("Ocorreu um erro inesperado ao editar o jogador: " + e.getMessage());
        }
    }

    public void excluirJogador(Scanner scannerEntrada) {
        System.out.println("\n--- Excluir Jogador ---");
        if (listaDeJogadores.isEmpty()){
            System.out.println("Nenhum jogador cadastrado para excluir.");
            return;
        }
        System.out.print("Digite o nome do jogador que deseja excluir: ");
        String nomeParaExcluir = scannerEntrada.nextLine();

        boolean removido = listaDeJogadores.removeIf(jogador -> jogador.getNome().equalsIgnoreCase(nomeParaExcluir));

        if (removido) {
            this.persistencia.salvarJogadores(this.listaDeJogadores);
            System.out.println("Jogador '" + nomeParaExcluir + "' excluído com sucesso.");
        } else {
            System.out.println("Jogador '" + nomeParaExcluir + "' não encontrado.");
        }
    }
}

