
import java.io.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ForkOrnek {

    public static void main(String[] args) {
        // Adım 1: Dosya adının belirlenmesi
        String dosyaAdi = "ornek.txt";
        // Dosya adımızı ornek.txt olarak belirledik

        try {
            // Adım 2: Dosyadan okuma işlemi yapacağız
            
            BufferedReader br = new BufferedReader(new FileReader(dosyaAdi));

            //BufferedReader kullanılarak ornek.txt dosyasından satır satır okuma işlemi gerçekleştirilir ve her okunan satır ekrana yazdırılır.

            
            String satir;
            while ((satir = br.readLine()) != null) {
                System.out.println("Dosya Okuma: " + satir);
            }
            br.close();

            // Adım 3: Yeni bir süreç (process) oluştur
            ProcessBuilder processBuilder = new ProcessBuilder("cp", dosyaAdi, "hedefDosya.txt");
            Process process = processBuilder.start();

            //ProcessBuilder sınıfı kullanılarak bir yeni süreç (process) oluşturulur. 
            //Bu süreç, Linux/Unix tabanlı sistemlerde kullanılan cp komutu ile ornek.txt dosyasını hedefDosya.txt adlı yeni bir dosyaya kopyalamak için kullanılır.

            // Adım 4: Yeni sürecin bitmesini beklemek
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Dosya başarıyla kopyalandı.");
            } else {
                System.out.println("Dosya kopyalama hatası.");
            }
}
//waitFor metodu kullanılarak yeni oluşturulan sürecin bitmesi beklenir. 
//Eğer süreç başarıyla tamamlanırsa (exit code 0), ekrana "Dosya başarıyla kopyalandı." yazdırılır; aksi takdirde "Dosya kopyalama hatası." yazdırılır.

        
        catch (IOException | InterruptedException e) {
            // Adım 5: Hata kontrolü
            e.printStackTrace();

//try-catch bloğu, dosya işlemleri veya süreç oluşturma işlemleri sırasında oluşabilecek hataları ele alır ve bu hataları ekrana yazdırır. 
//IOException dosya işlemleri için, InterruptedException ise süreç oluşturma işlemi sırasında kesinti (interrupt) durumları için yakalanır.

        }
    }
}
