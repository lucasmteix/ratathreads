public class Cozinheiro extends Thread{

    String nome;
    Prato prato;

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

        String nomeCozinheiro = "Cozinheiro " + Thread.currentThread().getName().substring(Thread.currentThread().getName().length() - 1);

        return nomeCozinheiro;
    }

    @Override
    public void run(){

        nome = definirNomeCozinheiro();

        while(!Cozinha.pratos.isEmpty()){

            int quantum = 3;

            try {

                Cozinha.semaforoBinario.acquire();

                prato = Cozinha.pratos.getFirst();
                Cozinha.pratos.removeFirst();
            } catch (InterruptedException e){

                e.printStackTrace();
            } finally {

                Cozinha.semaforoBinario.release();
            }

            System.out.println(nome + " cozinhando " + prato.getNome());

            if(prato.getComplexidade() > quantum){

                cozinhar(quantum);
                prato.setComplexidade(prato.getComplexidade()-quantum);

                Cozinha.pratos.add(prato);
                System.out.println(nome + " largou " + prato.getNome());
            }
            else{

                cozinhar(prato.getComplexidade());
                System.out.println(nome + " terminou " + prato.getNome());
            }
        }
    }
}