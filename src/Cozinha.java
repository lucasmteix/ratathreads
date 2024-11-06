import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Cozinha {
    static ArrayList<Fogao> Fogao = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        // Variáveis de entrada
        int quantidadeCozinheiros;
        int quantidadeFogao;

        // Variáveis auxiliares
        Scanner leitor = new Scanner(System.in);
        Random gerador = new Random();
        Fogao novoFogao;
        Cozinheiro novoCozinheiro = new Cozinheiro();

        //Threads
        ArrayList<Cozinheiro> cozinheiros = new ArrayList<>();

        //Inputs
        System.out.print("Entre com o número de cozinheiros: ");
        quantidadeCozinheiros = leitor.nextInt();

        System.out.print("Entre com o número de Fogao: ");
        quantidadeFogao = leitor.nextInt();

        //Criando cozinheiros
        for(int i = 1; i<=quantidadeCozinheiros; i++){

            novoCozinheiro = new Cozinheiro();
            cozinheiros.add(novoCozinheiro);
        }

        //Criando Fogao
        for (int i = 1; i <= quantidadeFogao; i++) {
            novoFogao = new Fogao("Fogao " + i);
            Fogao.add(novoFogao);
        }

        //Iniciando os cozinheiros
        for(Cozinheiro cozinheiro : cozinheiros){

            cozinheiro.start();
        }

        //Fazendo o join para que o tempo seja contado corretamente
        for(Cozinheiro cozinheiro : cozinheiros){

            cozinheiro.join();
        }
    }
}