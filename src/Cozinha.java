import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Cozinha {

    static ArrayList<Prato> pratos = new ArrayList<>();

    public static void main(String[] args) {

        //DECLARACAO DE VARIAVEIS

        //Variaveis de entrada
        String nomePrato;
        int complexidadePrato;
        Prato prato;

        //Variaveis auxiliares
        Scanner leitor = new Scanner(System.in);
        boolean flag = true;
        Random gerador = new Random();

        //Threads
        Cozinheiro cozinheiro1 = new Cozinheiro();
        Cozinheiro cozinheiro2 = new Cozinheiro();




        //ENTRADA

        //Preenchendo o array de pratos
        for(int i = 1; i <= 10; i++){

            Prato novoPrato = new Prato(Integer.toString(i), gerador.nextInt(11)*1000);
            pratos.add(novoPrato);
        }

        cozinheiro1.start();
        cozinheiro2.start();
    }
}