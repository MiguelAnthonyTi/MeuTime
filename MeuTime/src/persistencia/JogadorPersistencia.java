package persistencia;
import modelo.Jogador;
import execoes.DadoInvalidoException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JogadorPersistencia {
    private static final String MEUTIME_ARQUIVO_TXT = "jogadores.txt";
    private static final String NOME_DO_TIME ="America FC";

    public void salvarJogadores(List<Jogador> listaDeJogadores) {
        
        File arquivo = new File(MEUTIME_ARQUIVO_TXT);
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(arquivo))) { 
            escritor.write(NOME_DO_TIME);
            escritor.newLine();

            for (Jogador jogador : listaDeJogadores) {
                
                escritor.write(jogador.paraFormatoCSV());
                escritor.newLine();
            }
            
        } catch (IOException e) {
            System.err.println("ERRO AO SALVAR JOGADORES NO ARQUIVO .TXT: " + e.getMessage());
            
        }
    }

    public List<Jogador> carregarJogadores() {
        
        List<Jogador> jogadoresCarregados = new ArrayList<>();
        File arquivo = new File(MEUTIME_ARQUIVO_TXT);

        
        if (!arquivo.exists()) {
            System.out.println("Arquivo " + MEUTIME_ARQUIVO_TXT + " não encontrado. Uma nova lista vazia será usada e o arquivo será criado ao salvar.");
            return jogadoresCarregados; 
        }

        try (BufferedReader leitor = new BufferedReader(new FileReader(arquivo))) {
            
            String linha;
            while ((linha = leitor.readLine()) != null) {
                if (linha.trim().isEmpty()) { 
                    continue;
                }
                try {
                    Jogador jogador = Jogador.deFormatoCSV(linha);
                    jogadoresCarregados.add(jogador);
                } catch (DadoInvalidoException e) {
                    System.err.println("AVISO: Linha mal formatada ou com dados inválidos no arquivo .txt: '" + linha + "'. Erro: " + e.getMessage());
                }
            }
            
        } catch (IOException e) { 
            System.err.println("ERRO AO CARREGAR JOGADORES DO ARQUIVO .TXT: " + e.getMessage());
            
        }
        return jogadoresCarregados;
    }
}

