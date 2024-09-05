import java.util.Random;

public class ThreadEx03 {
    public static void main(String[] args) {
        // Criando um vetor de 1000 posições com valores aleatórios entre 1 e 100
        int[] vetor = new int[1000];
        Random random = new Random();

        for (int i = 0; i < vetor.length; i++) {
            vetor[i] = random.nextInt(100) + 1; // valores de 1 a 100
        }

        // Criando e iniciando duas threads
        Thread thread1 = new Thread(new ThreadVetor(1, vetor)); // Usando número ímpar
        Thread thread2 = new Thread(new ThreadVetor(2, vetor)); // Usando número par

        thread1.start();
        thread2.start();
    }
}

class ThreadVetor implements Runnable {
    private int num;
    private int[] vet;

    public ThreadVetor(int num, int[] vet) {
        this.num = num;
        this.vet = vet;
    }

    @Override
    public void run() {
        long startTime = System.nanoTime(); // Início da contagem do tempo

        if (num % 2 == 0) { // Caso o número seja par, usa o for tradicional
            for (int i = 0; i < vet.length; i++) {
         
                int value = vet[i];
            }
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1_000_000_000.0; // Tempo em segundos
            System.out.println("Thread com for tradicional (num par): " + duration + " segundos");
        } else { // Caso o número seja ímpar, usa o foreach
            for (int value : vet) {
                
            }
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1_000_000_000.0; // Tempo em segundos
            System.out.println("Thread com foreach (num ímpar): " + duration + " segundos");
        }
    }
}
