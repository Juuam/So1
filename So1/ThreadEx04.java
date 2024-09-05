import java.util.Random;

public class ThreadEx04 {
    // Distância máxima que os sapos devem percorrer
    private static final int DISTANCIA_MAXIMA = 100;
    // Tamanho máximo do pulo de cada sapo
    private static final int TAMANHO_MAXIMO_PULO = 10;
    // Variável para controlar a ordem de chegada dos sapos
    private static int posicao = 1;

    public static void main(String[] args) {
        // Criando e iniciando 5 threads (5 sapos)
        Thread[] sapos = new Thread[5];
        for (int i = 0; i < 5; i++) {
            sapos[i] = new Thread(new Sapo(i + 1));
            sapos[i].start();
        }
        // Aguardar todas as threads terminarem
        for (int i = 0; i < 5; i++) {
            try {
                sapos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Corrida finalizada!");
    }

    // Método sincronizado para definir a colocação dos sapos
    public static synchronized int getPosicao() {
        return posicao++;
    }
}

class Sapo implements Runnable {
    private int numeroDoSapo;
    private int distanciaPercorrida = 0;
    private static final Random random = new Random();

    public Sapo(int numeroDoSapo) {
        this.numeroDoSapo = numeroDoSapo;
    }

    @Override
    public void run() {
        System.out.println("Sapo " + numeroDoSapo + " começou a corrida!");
        
        // Enquanto o sapo não percorrer a distância máxima
        while (distanciaPercorrida < CorridaDeSapos.DISTANCIA_MAXIMA) {
            // Gera um pulo aleatório entre 1 e TAMANHO_MAXIMO_PULO
            int pulo = random.nextInt(CorridaDeSapos.TAMANHO_MAXIMO_PULO) + 1;
            distanciaPercorrida += pulo;

            // Exibe o tamanho do pulo e a distância percorrida até o momento
            System.out.println("Sapo " + numeroDoSapo + " pulou " + pulo + " metros. Distância total percorrida: " + Math.min(distanciaPercorrida, CorridaDeSapos.DISTANCIA_MAXIMA) + " metros.");
            
            // Simula o tempo entre os saltos
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Sapo chegou ao final e obtém sua colocação de forma sincronizada
        int colocacao = CorridaDeSapos.getPosicao();
        System.out.println("Sapo " + numeroDoSapo + " chegou! Colocação: " + colocacao);
    }
}
