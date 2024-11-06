public class Prato {

    //ATRIBUTOS

    private String nome;
    private int complexidade;
    int tempoEspera;

    public Prato(String nome, int complexidade) {
        this.nome = nome;
        this.complexidade = complexidade;
        this.tempoEspera = 0;
    }

    //METODOS


    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setComplexidade(int complexidade) {
        this.complexidade = complexidade;
    }

    public String getNome() {
        return nome;
    }

    public int getComplexidade() {
        return complexidade;
    }
}
