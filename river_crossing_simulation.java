
import java.util.concurrent.Semaphore;

public class NehirGecisi {
    private Semaphore mutex = new Semaphore(1);
    private Semaphore kurtSemaphore = new Semaphore(1);
    private Semaphore koyunSemaphore = new Semaphore(0);
    private Semaphore lahanaSemaphore = new Semaphore(0);

    private boolean kurtGecti = false;
    private boolean koyunGecti = false;
    private boolean lahanaGecti = false;

    public void kurtGec() throws InterruptedException {
        kurtSemaphore.acquire();
        mutex.acquire();
        while (koyunGecti || lahanaGecti) {
            mutex.release();
            kurtSemaphore.acquire();
            mutex.acquire();
        }
        System.out.println("Kurt geciyor.");
        kurtGecti = true;
        mutex.release();
        koyunSemaphore.release();
    }

    public void koyunGec() throws InterruptedException {
        koyunSemaphore.acquire();
        mutex.acquire();
        while (kurtGecti || lahanaGecti) {
            mutex.release();
            koyunSemaphore.acquire();
            mutex.acquire();
        }
        System.out.println("Koyun geciyor.");
        koyunGecti = true;
        mutex.release();
        lahanaSemaphore.release();
    }

    public void lahanaGec() throws InterruptedException {
        lahanaSemaphore.acquire();
        mutex.acquire();
        while (kurtGecti || koyunGecti) {
            mutex.release();
            lahanaSemaphore.acquire();
            mutex.acquire();
        }
        System.out.println("Lahanalar geciyor.");
        lahanaGecti = true;
        mutex.release();
        kurtSemaphore.release();
    }

    public void tekneyeEkle(int tip) {
        try {
            mutex.acquire();
            if (tip == 0) {
                kurtSemaphore.release();
            } else if (tip == 1) {
                koyunSemaphore.release();
            } else if (tip == 2) {
                lahanaSemaphore.release();
            }
            mutex.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        NehirGecisi nehirGecisi = new NehirGecisi();

        new Thread(() -> {
            try {
                nehirGecisi.kurtGec();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                nehirGecisi.koyunGec();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                nehirGecisi.lahanaGec();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
