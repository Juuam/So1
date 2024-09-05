import java.util.Random;

public class ThreadEx02 {
    public static void main(String[] args) {
        // Definindo o tamanho da matriz
        int rows = 3;
        int columns = 5;
        int[][] matrix = new int[rows][columns];
        Random random = new Random();

        // Preenchendo a matriz com números aleatórios entre 1 e 100
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = random.nextInt(100) + 1; // números de 1 a 100
            }
        }

        // Exibindo a matriz
        System.out.println("Matriz gerada:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        // Criando e iniciando 3 threads para calcular a soma de cada linha
        for (int i = 0; i < rows; i++) {
            int[] row = matrix[i];
            Thread thread = new Thread(new RowSumTask(row, i));
            thread.start();
        }
    }
}

class RowSumTask implements Runnable {
    private int[] row;
    private int rowIndex;

    public RowSumTask(int[] row, int rowIndex) {
        this.row = row;
        this.rowIndex = rowIndex;
    }

    @Override
    public void run() {
        int sum = 0;
        for (int value : row) {
            sum += value;
        }
        System.out.println("Linha " + rowIndex + " - Soma: " + sum);
    }
}
