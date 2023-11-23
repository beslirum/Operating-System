import java.io.*;

public class ThreadOrnek {

    public static void main(String[] args) {
        // Dosya okuma işlemi için bir örnek
        String dosyaAdi = "ornek.txt";

        // Dosyadan okuma işlemi
        Thread dosyaOkumaThread = new Thread(() -> {
            try {
                // BufferedReader ile dosyadan satır satır okuma işlemi gerçekleştirilir.
                BufferedReader br = new BufferedReader(new FileReader(dosyaAdi));
                String satir;
                while ((satir = br.readLine()) != null) {
                    System.out.println("Dosya Okuma: " + satir);
                }
                br.close();
            } catch (IOException e) {
                // Dosya okuma işlemi sırasında oluşan hatalar ele alınır.
                e.printStackTrace();
            }
        });

        // Dosyaya yazma işlemi
        Thread dosyaYazmaThread = new Thread(() -> {
            try {
                // BufferedWriter ile yeniDosya.txt adlı dosyaya yazma işlemi gerçekleştirilir.
                BufferedWriter bw = new BufferedWriter(new FileWriter("yeniDosya.txt"));
                bw.write("Bu dosya yeni bir dosyadan yazıldı.");
                bw.close();
            } catch (IOException e) {
                // Dosya yazma işlemi sırasında oluşan hatalar ele alınır.
                e.printStackTrace();
            }
        });

        // İş parçacıklarını başlat
        dosyaOkumaThread.start();
        dosyaYazmaThread.start();

        try {
            // İş parçacıklarının bitmesini bekle
            dosyaOkumaThread.join();
            dosyaYazmaThread.join();
        } catch (InterruptedException e) {
            // İş parçacıklarının beklenmeyen bir biçimde sonlanması durumunda hata ele alınır.
            e.printStackTrace();
        }

        System.out.println("İşlemler tamamlandı.");
    }
}
