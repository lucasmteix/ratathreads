public class Prato {

    //ATRIBUTOS

    private String nome;
    private final int complexidade;
    int trabalhoRestante; //representa quanto trabalho ainda ha a ser feito naquele prato, comeca igual a complexidade

    public Prato(String nome, int complexidade) {
        this.nome = nome;
        this.complexidade = complexidade;
        trabalhoRestante = this.complexidade;
    }

    //METODOS


    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getComplexidade() {
        return complexidade;
    }
}
