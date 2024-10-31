import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Cozinha {

    static ArrayList<Prato> pratos = new ArrayList<>();
    static Semaphore semaforoBinario = new Semaphore(1);
    static Semaphore semaforoDeContagem = new Semaphore(2);

    public static void main(String[] args) throws InterruptedException {

        //DECLARACAO DE VARIAVEIS

        //Variaveis de entrada
        int quantidadePratos;
        int quantidadeCozinheiros;

        //Variaveis auxiliares
        Scanner leitor = new Scanner(System.in);
        Random gerador = new Random();
        Prato novoPrato;
        Cozinheiro novoCozinheiro;
        String nomesPratos[] = {"Feijões", "Arroz com feijão", "Pizza", "Hambúrguer",
                "Pastel", "Macarrão", "Lasanha", "Sushi", "Bife à parmegiana", "Coxinha",
                "Strogonoff", "Churrasco", "Feijoada", "Tacos", "Empada", "Torta de frango",
                "Salada Caesar", "Escondidinho", "Frango à milanesa", "Sopa de legumes"};

        //Threads
        ArrayList<Cozinheiro> cozinheiros = new ArrayList<>(); /*array de tamanho dinamico com os cozinheiros,
                                                                *cada cozinheiro eh uma thread*/




        //ENTRADA

        System.out.print("Entre com o número de cozinheiros: ");
        quantidadeCozinheiros = leitor.nextInt();

        /*System.out.print("Entre com o número de pratos: ");
        quantidadePratos =  leitor.nextInt();*/

        long comeco = System.nanoTime();

        //Criando os cozinheiros
        for(int i = 1; i<=quantidadeCozinheiros; i++){

            novoCozinheiro = new Cozinheiro();
            cozinheiros.add(novoCozinheiro);
        }

        //Preenchendo o array de pratos
        pratos.add(new Prato("Feijoada", 8));
        pratos.add(new Prato("Arroz com Feijão", 4));
        pratos.add(new Prato("Pizza Margherita", 5));
        pratos.add(new Prato("Hambúrguer", 6));
        pratos.add(new Prato("Pastel de Carne", 3));
        pratos.add(new Prato("Macarrão à Bolonhesa", 7));
        pratos.add(new Prato("Lasanha de Queijo", 9));
        pratos.add(new Prato("Sushi", 6));
        pratos.add(new Prato("Bife à Parmegiana", 8));
        pratos.add(new Prato("Churrasco", 10));



        //Iniciando os cozinheiros
        for(Cozinheiro cozinheiro : cozinheiros){

            cozinheiro.start();
        }

        //Fazendo o join para que o tempo seja contado corretamente
        for(Cozinheiro cozinheiro : cozinheiros){

            cozinheiro.join();
        }

        long fim = System.nanoTime();
        System.out.println("t: " + (double)(fim - comeco)/1000000000);
    }
}