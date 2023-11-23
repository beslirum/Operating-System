import java.util.Scanner;

public class AsalSayiBulucu implements Runnable {
    private int sinir; // Asal sayıları bulma sınırını tanımlar

    public AsalSayiBulucu(int sinir) {
        this.sinir = sinir;
    }

    @Override
    public void run() {
        System.out.println("Asal Sayılar:");

        // 2'den başlayarak belirlenen sınıra kadar olan sayıları kontrol et
        for (int i = 2; i <= sinir; i++) {
            if (isAsal(i)) {
                System.out.print(i + " ");
            }
        }
    }

    // Bir sayının asal olup olmadığını kontrol eden metod
    private boolean isAsal(int sayi) {
        if (sayi <= 1) {
            return false; // 1 ve daha küçük sayılar asal değildir
        }

        // 2'den başlayarak sayının kareköküne kadar olan sayılara bölünüp bölünmediği kontrol edilir
        for (int i = 2; i <= Math.sqrt(sayi); i++) {
            if (sayi % i == 0) {
                return false; // Eğer bir sayı bölünebiliyorsa asal değildir
            }
        }

        return true; // Sayı asalsa true döner
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Bir sayı girin: ");
        int girilenSayi = scanner.nextInt();

        // Asal sayıları bulan iş parçacığını oluştur
        Thread asalSayiThread = new Thread(new AsalSayiBulucu(girilenSayi));

        // İş parçacığını başlat
        asalSayiThread.start();

        try {
            // İş parçacığının bitmesini bekle
            asalSayiThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        scanner.close();
    }
}
