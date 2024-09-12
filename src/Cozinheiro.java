public class Cozinheiro extends Thread{

    String nome;
    Prato prato;

    private void cozinhar(int tempo){

        try{

            Thread.sleep(prato.getComplexidade());
        }
        catch (InterruptedException e){

            e.printStackTrace();
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

            prato = Cozinha.pratos.getFirst();
            Cozinha.pratos.removeFirst();
            System.out.println(nome + " cozinhando " + prato.getNome());
            cozinhar(prato.getComplexidade()*100);
            System.out.println(nome + " terminou " + prato.getNome());
        }
    }
}