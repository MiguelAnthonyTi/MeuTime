package modelo;

import execoes.DadoInvalidoException;

public class Jogador {
    private String nome;
    private String posicao;
    private int camisa;
    private int habilidade;

    public Jogador (String nome,String posicao, int camisa){
        if (nome == null|| nome.trim().isEmpty()) {
            this.nome = "Não Informado";

        }else{
            this.nome= nome;
        }
        this.posicao = posicao;
        this.camisa = camisa;
    }

    //GET E SET (NOME)
    public String getNome(){
        return nome;
    }

    public void setNome(String nome) throws DadoInvalidoException{
        
        if (nome == null || nome.trim().isEmpty()){
           throw new DadoInvalidoException("Nome do jogador não pode ser vazio");
    
        }
        this.nome=nome;
    }

    // GET E SET (POSICAO)
    public String getPosicao (){
        return posicao;
    }

    public void setPosicao (String posicao) throws DadoInvalidoException{
        if (posicao == null || posicao.trim().isEmpty()) {
            throw new DadoInvalidoException("Posição não pode ser vazia.");
        }
        this.posicao=posicao;
    }

    // GET E SET (CAMISA)

    public int getCamisa(){
        return camisa;
    }

    public void setCamisa(int camisa) throws DadoInvalidoException{
        if (camisa <= 0){
            throw new DadoInvalidoException("Número da camisa deve ser um valor positivo.");      
        }
        this.camisa= camisa;
    }

    // GET E SET HABILIDADE 

    public int getHabilidade(){
        return habilidade;
    }

    public void setHabilidade(int habilidade) throws DadoInvalidoException{
        if (habilidade < 0|| habilidade> 10){
            throw new DadoInvalidoException ("habilidade do jogador deve estar entre 0 e 10. Valor informado:" + habilidade);
        }
        this.habilidade = habilidade;
    }

    @Override
    public String toString() {
        return "Jogador [Nome=> " + nome + ", Posição=> " + posicao + ", Camisa=> " + camisa + ", Habilidade=> " + habilidade + "]";
    }

    public String paraFormatoCSV(){
        return String.join("," ,nome, posicao,String.valueOf(camisa),String.valueOf(habilidade));
    }

    public static Jogador deFormatoCSV(String linhaCSV) throws DadoInvalidoException{
        if (linhaCSV == null || linhaCSV.trim().isEmpty()){
            throw new DadoInvalidoException("Linha CSV não pode ser vazia para criar jogador.");
        } 
        String[] dados = linhaCSV.split(",", -1);
        if (dados.length != 4){
            throw new DadoInvalidoException("Formato CSV inválido para jogador. Esperados 4 campos, recebidos: " + dados.length + " em '" + linhaCSV + "'");
        }

        try{
            String nome = dados[0].trim();
            String posicao = dados[1].trim();

            if (nome.isEmpty()) throw new DadoInvalidoException("Nome no CSV não pode ser vazio.");
            if (posicao.isEmpty()) throw new DadoInvalidoException("Posição no CSV não pode ser vazia.");

            int camisa = Integer.parseInt(dados[2].trim());
            int habilidade = Integer.parseInt(dados[3].trim());

            Jogador jogador = new Jogador(nome, posicao, camisa);
            jogador.setHabilidade(habilidade);
            jogador.setCamisa(camisa); 

            return jogador;
        } catch (NumberFormatException e ){
            throw new DadoInvalidoException("Erro ao converter número (camisa ou habilidade) do CSV: '" + linhaCSV + "'. Detalhe: " + e.getMessage());

        } catch (DadoInvalidoException e){
            throw new DadoInvalidoException("Dado inválido ao processar linha CSV '" + linhaCSV + "': " + e.getMessage());
        }
    }

}
