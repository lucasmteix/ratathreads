import java.util.ArrayList;
import java.util.Scanner;

public class Cozinha extends Thread{

    static ArrayList<Prato> pratos = new ArrayList<>();

    @Override
    public void run(){


    }

    public static void main(String[] args) {

        //DECLARACAO DE VARIAVEIS

        //Variaveis de entrada
        String nomePrato;
        int complexidadePrato;
        Prato prato;

        //Variaveis auxiliares
        Scanner leitor = new Scanner(System.in);
        boolean flag = true;

        while(flag){

            prato = new Prato();
            
            System.out.print("\nNome do prato: ");
            prato.setNome(leitor.nextLine());

            System.out.print("Complexidade do prato: ");
            prato.setComplexidade(leitor.nextInt());

            pratos.add(prato);

            System.out.print("Continuar?");
            flag = leitor.nextBoolean();
            leitor.nextLine();
        }

        for(Prato pratoX : pratos){

            System.out.println(pratoX.getNome());
        }
    }
}
