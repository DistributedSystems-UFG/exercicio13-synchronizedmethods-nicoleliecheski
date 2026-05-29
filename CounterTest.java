public class CounterTest {

    private static final int OPs_THREADS = 100_000;
    private static final int N_THREADS = 4;

    public static void main(String[] args) throws InterruptedException {
        unsyncTeste();
        System.out.println();
        syncTeste();
    }

    private static void unsyncTeste() throws InterruptedException {
        System.out.println("sem sync");
        Counter counter = new Counter();
        Thread[] threads = new Thread[N_THREADS];

        for (int i = 0; i < N_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPs_THREADS; j++) {
                    counter.increment();
                }
                for (int j = 0; j < OPs_THREADS; j++) {
                    counter.decrement();
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        int result = counter.value();
        System.out.println("valor final (eh esperado 0): " + result);
    }

    private static void syncTeste() throws InterruptedException {
        System.out.println("com sync");
        SyncCounter counter = new SyncCounter();
        Thread[] threads = new Thread[N_THREADS];

        for (int i = 0; i < N_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < OPs_THREADS; j++) {
                    counter.increment();
                }
                for (int j = 0; j < OPs_THREADS; j++) {
                    counter.decrement();
                }
            });
        }

        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();

        int result = counter.value();
        System.out.println("valor final (eh esperado 0): " + result);
    }
}
