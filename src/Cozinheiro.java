import javax.management.monitor.Monitor;
import java.util.Objects;
import java.util.concurrent.Semaphore;

class Cozinheiro extends Thread {

    int indice = 0;
    long comecoContagem, comecoMonitor, fimContagem, fimMonitor;

    String nome;
    Fogao fogao;

    // Semáforos para sincronização
    private static final Semaphore semaforoContagem = new Semaphore(3); // Contagem (permit 3)

    private String definirNomeCozinheiro() {
        return "Cozinheiro " + Thread.currentThread().getName().substring(Thread.currentThread().getName().length() - 1);
    }

    public synchronized void fogaoMonitor()
    {
        fogao = Cozinha.Fogao.getFirst();
        comecoMonitor = System.nanoTime();
        System.out.println("------");
        System.out.println("Usando " + fogao.nome + " por monitor:");
        System.out.println("------");
        int indice = 0;

        while(!Cozinha.Fogao.isEmpty()) {
            // Seção crítica usando synchronized
            if(indice >= Cozinha.Fogao.size())
                break;
            fogao = Cozinha.Fogao.get(indice);
            int a = 0;
            long b = 0;
            for (int i = 0; i < 1000000; i++) {
                a = 0;
                for (int j = 0; j < 3180; j++) {
                    a += j;
                    b++;
                }
            }
            System.out.println((nome + " usou " + fogao.nome));
            indice++;
        }
        fimMonitor = System.nanoTime();
    }

    public void SemaforoContagem(){
        fogao = Cozinha.Fogao.getFirst();
        indice = 0;
        comecoContagem = System.nanoTime();
        System.out.println("------");
        System.out.println("Usando " + fogao.nome + " por semáforo de contagem:");
        System.out.println("------");
        while(!Cozinha.Fogao.isEmpty()) {
            if(indice >= Cozinha.Fogao.size())
                break;
            fogao = Cozinha.Fogao.get(indice);
            try {
                semaforoContagem.acquire(); // Sincronização com semáforo de contagem

                // Seção crítica
                int a = 0;
                long b = 0;
                for (int i = 0; i < 1000000; i++) {
                    a = 0;
                    for (int j = 0; j < 3180; j++) {
                        a += j;
                        b++;
                    }
                }
                System.out.println((nome + " usou " + fogao.nome));
                indice++;

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaforoContagem.release();
            }
        }
        fimContagem = System.nanoTime();
    }

    @Override
    public void run() {
        //Processos de cozinha
        nome = definirNomeCozinheiro();

        //Chamando sincronização do fogao
        fogaoMonitor();

        SemaforoContagem();

        //Tempo do fogao
        double tempoContagem = (fimContagem - comecoContagem) / 1_000_000_000.0;
        double tempoMonitor = (fimMonitor - comecoMonitor) / 1_000_000_000.0;

        System.out.println("Tempo em Semáforo de Contagem: " + tempoContagem);
        System.out.println("Tempo em Monitor: " + tempoMonitor);

        //Melhor resultado
        if(tempoMonitor > tempoContagem) System.out.println("Contagem mais eficaz!");
        else System.out.println("Monitor mais eficaz!");
    }
}