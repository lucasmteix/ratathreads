public class Cozinheiro extends Thread{

    Prato prato;

    private void cozinhar(int tempo){

        try{

            Thread.sleep(prato.getComplexidade());
        }
        catch (InterruptedException e){

            e.printStackTrace();
        }
    }
    @Override
    public void run(){

        while(!Cozinha.pratos.isEmpty()){

            prato = Cozinha.pratos.getFirst();
            Cozinha.pratos.removeFirst();
            System.out.println("Cozinhando " + prato.getNome());
            cozinhar(prato.getComplexidade()*100);
            System.out.println("Terminei " + prato.getNome());
        }
    }
}
