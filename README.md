# Operating-System

# Fork-Examples

Bu Java programı, bir dosyadan okuma işlemi gerçekleştirip ardından başka bir dosyaya kopyalama işlemini içerir. Aşağıda programın ana adımları bulunmaktadır:

- **Dosya Okuma:**
  - `ornek.txt` adlı dosyadan satır satır metin okunur.
  - Her okunan satır ekrana yazdırılır.

- **Dosya Kopyalama:**
  - `cp` komutu kullanılarak `ornek.txt` dosyası, `hedefDosya.txt` adlı yeni bir dosyaya kopyalanır.
  
- **Kopyalama Sonucu Kontrol:**
  - Oluşturulan sürecin bitmesi beklenir.
  - Eğer kopyalama başarılıysa, "Dosya başarıyla kopyalandı." mesajı görüntülenir. Aksi takdirde, "Dosya kopyalama hatası." mesajı görüntülenir.

- **Hata Kontrolü:**
  - Program, dosya okuma veya kopyalama sırasında oluşabilecek hataları ele alır.
  - Hatalar ekrana yazdırılır.

# Thread-Examples

Bu Java programı, iki ayrı iş parçacığı kullanarak dosya okuma ve yazma işlemlerini eşzamanlı olarak gerçekleştirir. Aşağıda programın ana adımları bulunmaktadır:

- **Dosya Okuma İşlemi:**
  - `ornek.txt` adlı dosyadan satır satır metin okunur.
  - Her okunan satır ekrana yazdırılır.

- **Dosya Yazma İşlemi:**
  - `yeniDosya.txt` adlı yeni bir dosyaya metin yazılır.

- **İş Parçacıklarının Başlatılması:**
  - Her iş parçacığı (`dosyaOkumaThread` ve `dosyaYazmaThread`) ayrı ayrı başlatılır.

- **İş Parçacıklarının Tamamlanmasının Beklenmesi:**
  - `join` metodu kullanılarak her iki iş parçacığının da tamamlanması beklenir.

- **Sonuç:**
  - İşlemler tamamlandıktan sonra "İşlemler tamamlandı." mesajı görüntülenir.

# Thread-Examples-KILOBYTE

Bu Java programı, iki ayrı iş parçacığı kullanarak dosya okuma ve yazma işlemlerini eşzamanlı olarak gerçekleştirir. Aşağıda programın ana adımları bulunmaktadır:

- **Dosya Okuma İşlemi:**
  - `ornek.txt` adlı dosyadan satır satır metin okunur.
  - Her okunan satır ekrana yazdırılır.

- **Dosya Yazma İşlemi (1 Kilobayt):**
  - `yeniDosya.txt` adlı yeni bir dosyaya, 1 kilobayt (KB) boyutunda metin yazılır.

- **İş Parçacıklarının Başlatılması:**
  - Her iş parçacığı (`dosyaOkumaThread` ve `dosyaYazmaThread`) ayrı ayrı başlatılır.

- **İş Parçacıklarının Tamamlanmasının Beklenmesi:**
  - `join` metodu kullanılarak her iki iş parçacığının da tamamlanması beklenir.

- **Sonuç:**
  - İşlemler tamamlandıktan sonra "İşlemler tamamlandı." mesajı görüntülenir.


# Asal Sayı Bulucu

Bu Java programı, kullanıcının girdiği bir sayıdan küçük veya eşit olan tüm asal sayıları bulan çok iş parçacıklı bir programdır.

## Kullanım

Program, kullanıcının bir sayı girmesini bekler ve bu sayıdan küçük veya eşit olan asal sayıları bulur.

### Programı Çalıştırma

1. Java Runtime Environment (JRE) kurulu olduğundan emin olun.
2. `AsalSayiBulucu.java` adlı dosyayı bir Java derleyicisiyle derleyin.
3. Oluşturulan `AsalSayiBulucu.class` dosyasını çalıştırın.
4. Program, bir sayı girmenizi isteyecektir. Girilen sayıdan küçük veya eşit olan asal sayılar ekrana yazdırılacaktır.

## Sonuç:**

- Program, kullanıcının girdiği sayıyı kullanarak bir iş parçacığı oluşturur ve asal sayıları bu iş parçacığı üzerinden bulur.




