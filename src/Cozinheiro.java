import java.util.Objects;

public class Cozinheiro extends Thread{

    //DECLARACAO DE ATRIBUTOS E VARIAVEIS
    String nome;
    Prato prato;
    String algoritmoDeEscalonamento;




    //Construtor
    public Cozinheiro(String algoritmoDeEscalonamento) {
        this.algoritmoDeEscalonamento = algoritmoDeEscalonamento;
    }




    //METODOS

    private String definirNomeCozinheiro(){

        return "Cozinheiro " + Thread.currentThread().getName().substring(
                Thread.currentThread().getName().length() - 1);
    }

    private void cicloDeCozimento(Prato prato){

        //Variaveis auxiliares

        int a = 0;
        long b = 0;
        /*variaveis usadas para fazer calculos apenas com o proposito de ocupar a CPU para
         * simular threads*/

        /*Essa estrutura de repeticao serve apenas para fazer calculos que ocupem a CPU
         * para simular threads. Buscou-se fazer com que roda-la uma vez ocupasse a CPU por
         * cerca de 1s*/
        for (int i = 0; i < 1000000; i++) {

            a = 0;

            for (int j = 0; j < 3180; j++) {

                a += j;
                b++;
            }

        }

        prato.trabalhoRestante--;
    }

    private void cozinharFCFS(){

        /*Essa eh a implementacao antiga de cozinhar, que jah era basicamente um FCFS, mas que
         * pode ser alterada. Foi colada aqui soh para realocar essa porcao de codigo que ainda era util*/
        while(!Cozinha.pratos.isEmpty()){

            prato = Cozinha.pratos.getFirst();
            System.out.println(nome + " cozinhando " + prato.getNome());

            while(prato.trabalhoRestante > 0){

                cicloDeCozimento(prato);
            }

            Cozinha.pratos.removeFirst();
            System.out.println(nome + " terminou " + prato.getNome());
        }
    }

    private void cozinharRR(){

        final int VALORQUANTUM = 3; //constante usada se mudar o valor do quantum mais facilmente em testes
        int quantum = VALORQUANTUM;
        int indice = 0;

        prato = Cozinha.pratos.get(indice);

        while(!Cozinha.pratos.isEmpty()){

            while(quantum > 0){

                System.out.println(nome + " trabalhou 1x em " + prato.getNome());
                cicloDeCozimento(prato);

                if(prato.trabalhoRestante <= 0){

                    System.out.println((nome + " acabou " + prato.getNome()));
                    Cozinha.pratos.remove(indice);
                    break; //quebra soh o for mais interior
                }

                quantum--;
            }

            if(Cozinha.pratos.isEmpty()) break;

            quantum = VALORQUANTUM;

            if((indice + 1) < Cozinha.pratos.size()) indice++;
            else indice = 0;

            prato = Cozinha.pratos.get(indice);
        }
    }

    @Override
    public void run(){

        nome = definirNomeCozinheiro();

        if(Objects.equals(algoritmoDeEscalonamento, "FCFS")){
            
            cozinharFCFS();
        } else if (Objects.equals(algoritmoDeEscalonamento, "RR")) {

            cozinharRR();
        }
    }
}