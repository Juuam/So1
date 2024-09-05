public class ThreadEx01 {
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new RunnableTask());
            thread.start();
        }
    }
}

class RunnableTask implements Runnable {
    @Override
    public void run() {

        long id = Thread.currentThread().threadId();
        
        System.out.println("Thread ID: " + id);
    }
}