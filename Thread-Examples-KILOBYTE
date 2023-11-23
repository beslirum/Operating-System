// ThreadOrnekKilobyte.java

import java.io.*;

public class ThreadOrnekKilobyte {

    public static void main(String[] args) {
        // Dosya adları
        String dosyaAdiOkuma = "ornek.txt";
        String dosyaAdiYazma = "yeniDosya.txt";

        // Dosyadan okuma işlemi
        Thread dosyaOkumaThread = new Thread(() -> {
            try {
                // BufferedReader ile dosyadan satır satır okuma işlemi gerçekleştirilir.
                BufferedReader br = new BufferedReader(new FileReader(dosyaAdiOkuma));
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

        // Dosyaya yazma işlemi (1 kilobayt)
        Thread dosyaYazmaThread = new Thread(() -> {
            try {
                // BufferedWriter ile yeniDosya.txt adlı dosyaya yazma işlemi gerçekleştirilir.
                BufferedWriter bw = new BufferedWriter(new FileWriter(dosyaAdiYazma));

                // 1 kilobayt (KB) boyutunda bir metin oluşturun (1024 karakter)
                StringBuilder kilobaytMetin = new StringBuilder();
                for (int i = 0; i < 1024; i++) {
                    kilobaytMetin.append("A");
                }

                // Metni dosyaya yazın
                bw.write(kilobaytMetin.toString());
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
