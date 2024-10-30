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

    private void cozinhar(int duracao){

        //Variaveis auxiliares

        int a = 0;
        long b = 0;
        /*variaveis usadas para fazer calculos apenas com o proposito de ocupar a CPU para
        * simular threads*/

        /*Essa estrutura de decisão repete a estrutura de repeticao aninhada nela duracao vezes.
        * Como a ideia eh que a estrutura interna dure cerca de 1s para ser executada, duracao
        * seria o tempo em segundos de duracao de cada prato*/
        for(int t = 0; t < duracao; t++) {

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
        }
    }

    private String definirNomeCozinheiro(){

        return "Cozinheiro " + Thread.currentThread().getName().substring(
                Thread.currentThread().getName().length() - 1);
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

    private void cozinharFCFS(){

        /*Essa eh a implementacao antiga de cozinhar, que jah era basicamente um FCFS, mas que
        * pode ser alterada. Foi colada aqui soh para realocar essa porcao de codigo que ainda era util*/
        while(!Cozinha.pratos.isEmpty()){

            prato = Cozinha.pratos.getFirst();
            Cozinha.pratos.removeFirst();
            System.out.println(nome + " cozinhando " + prato.getNome());
            cozinhar(prato.getComplexidade());
            System.out.println(nome + " terminou " + prato.getNome());
        }
    }

    private void cozinharRR(){


    }
}