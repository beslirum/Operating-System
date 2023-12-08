/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication31;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// Bu kısım, kodn hangi paket içinde olduğunu ve hangi sınıfların ve fonksiyonların kullanılacağını belirtir.

class process{  //Bu işlem, process nesnesini temsil eder. İşleme ait:
    String name;  //adı
    int burstTime;  
    int priority;    //öncelik değikenimiz
    int arrivalTime; //varış zamanı
    int responseTime;  //yanıt süresi
    int waitingTime =0;  //bekleme süresi
    ArrayList<Integer> runningTime;
    public process(String name,int arrivalTime,int burstTime,int priority){
        this.name = name;
        this.burstTime = burstTime;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        runningTime = new ArrayList<Integer>();
    } 

  
    public void CalculateResponseTime(){
        responseTime = runningTime.get(0) - arrivalTime;
    }  //Bu kod bloğu, işlemin yanıt süresini hesaplayan bir metottur.
  //işlemin ilk kez çalıştığı zaman = runningTime

  
    public void CalculateWaitingTime(){
        waitingTime = runningTime.get(0) - arrivalTime;  //İlk olarak, waitingTime değişkenine, işlemin ilk çalışma zamanından varış zamanının çıkarılması sonucu atanır.
        int checker = runningTime.get(0);    //checker adlı bir yardımcı değişken, işlemin çalışma sürelerini kontrol etmek için kullanılır. İlk değeri, işlemin ilk çalışma zamanına (runningTime.get(0)) atanır.
        for(int i = 0;i<runningTime.size();i++){
//Eğer, mevcut çalışma zamanı (runningTime.get(i)) önceki işlemin bitiş zamanından (checker + 1) büyük ise, bu durumda işlem bir sonraki işlemin başlamış olmalıdır. Bekleme süresine bu durumu gösteren zaman dilimi eklenir.
            if(runningTime.get(i)>checker+1){
                waitingTime += runningTime.get(i) - (checker);
            }
            checker = runningTime.get(i);  //checker güncellenerek, işlemin bitiş zamanı saklanır.
        }
    }  //Bu kod bloğu, işlemin bekleme süresini hesaplar.


  
    
  public String toString(){
    // Bir dize oluşturuluyor, bu dize işlemin çalışma sürelerini içerecek.
    String runningTimes = "";
    for(int i = 0; i < runningTime.size(); i++){
        // Her bir çalışma süresi virgül ile ayrılarak dizeye ekleniyor.
        runningTimes += "," + runningTime.get(i);
    }
    
    // İşlemin özelliklerini içeren bir dize oluşturuluyor ve bu dize aşağıdaki özellikleri içeriyor:
    // - İşlemin adı (name)
    // - İşlemin varış zamanı (arrivalTime)
    // - İşlemin işlem süresi (burstTime)
    // - İşlemin öncelik seviyesi (priority)
    // - İşlemin yanıt süresi (responseTime)
    // - İşlemin bekleme süresi (waitingTime)
    // - İşlemin çalışma süreleri (runningTimes)
    return "" + name + " at: " + arrivalTime + " bt" + burstTime + " pri:" + priority 
            + " ResponseTime : " + responseTime
            + "\n WaitingTime : " + waitingTime
            + "\n Running Times : \n" + runningTimes;
}
}

/**
 *
 * @author sadievrenseker
 */
public class Scheduler {
    int TQ; // time quadrant
    int contextSwitchCost;
    ArrayList<process> readyQueue;
    ArrayList<process> finishedProcesses;
    void readFile() throws Exception {
        // Dosyadan veri okuma işlemleri
        FileReader fr = new FileReader(new File("input.txt"));
        BufferedReader br = new BufferedReader(fr);
        int lineCount = 0;
        String line;

        // Satır satır dosyayı okuma
        while ((line = br.readLine()) != null) {
            lineCount++;

            // Satır sayısına göre işlemler
            if (lineCount == 1) {
                // İlk satır, zaman çeyreği (TQ) olarak atanır
                TQ = Integer.parseInt(line);
            } else if (lineCount == 2) {
                // İkinci satır, bağlam değiştirme maliyeti olarak atanır
                contextSwitchCost = Integer.parseInt(line);
            } else {
                // Diğer satırlar işlemleri temsil eder
                StringTokenizer st = new StringTokenizer(line, " ");
                String token;
                String name;
                int values[] = new int[3];
                name = st.nextToken();
                int i = 0;

                // Satırdaki her bir sayı değeri alınır
                while (st.hasMoreTokens()) {
                    values[i++] = Integer.parseInt(st.nextToken());
                }

                // Okunan değerlerle yeni bir işlem nesnesi oluşturulur ve hazır kuyruğa eklenir
                readyQueue.add(new process(name, values[0], values[1], values[2]));
            }
        }
    }
}

  void PrintQueue(ArrayList<process> al) {
    // Metodun parametresi olarak gelen işlem kuyruğu (ArrayList<process> al) üzerinde bir for-each döngüsü başlatılır.
    for (process p : al) {
        // Her bir işlem nesnesi için System.out.println() metodu kullanılarak bilgileri ekrana yazdırılır.
        System.out.println(p);
    }
}

    void calculateResponseTimes(ArrayList<process> al) {
    int twt = 0, trt = 0; // Toplam bekleme süresi (twt) ve toplam yanıt süresi (trt) değişkenleri

    // Her bir işlem nesnesi için bir for-each döngüsü başlatılır
    for (process p : al) {
        // İşlem nesnesinin CalculateResponseTime() metodu çağrılarak yanıt süresi hesaplanır
        p.CalculateResponseTime();
        // Toplam yanıt süresine işlem nesnesinin yanıt süresi eklenir
        trt += p.responseTime;

        // İşlem nesnesinin CalculateWaitingTime() metodu çağrılarak bekleme süresi hesaplanır
        p.CalculateWaitingTime();
        // Toplam bekleme süresine işlem nesnesinin bekleme süresi eklenir
        twt += p.waitingTime;
    }

    // Ortalama bekleme süresi ve ortama yanıt süresi hesaplanır ve ekrana yazdırılır
    System.out.println(" Average Waiting Time : " + (float) ((float) twt / (float) al.size())
            + " Average Response Time " + (float) ((float) trt / (float) al.size()));
}

   public Scheduler() {
    readyQueue = new ArrayList<process>(); // Hazır kuyruk (readyQueue) oluşturulur
    finishedProcesses = new ArrayList<process>(); // Tamamlanmış işlemleri tutan kuyruk (finishedProcesses) oluşturulur
    
    try {
        readFile(); // Dosyadan veri okuma işlemi yapılır
    } catch (Exception e) {
        e.printStackTrace(); // Eğer bir hata olursa hatayı yazdırır
    }
    
    PrintQueue(readyQueue); // Hazır kuyruktaki işlemleri ekrana yazdırır
    
    int timeTick = 0; // Zaman işaretçisi (timeTick) başlangıçta 0 olarak ayarlanır
    String runningProcessName = ""; // Şu anda çalışan işlemin adını takip etmek için kullanılır

    // Hazır kuyrukta işlem varken çalışacak olan while döngüsü
    while (!readyQueue.isEmpty()) {
        // SJF preemptive (Shortest Job First) algoritması uygulanır
        int i = 0;

        // İlk varış yapan işlemi arar
        while (readyQueue.size() > i && readyQueue.get(i).arrivalTime > timeTick) {
            i++;
        }

        // Eğer varış zamanına sahip işlem yoksa, zaman işaretçisini arttırır ve bir sonraki iterasyona geçer
        if (i == readyQueue.size()) {
            System.out.println("TimeTick: " + timeTick + " There is no process to run");
            timeTick++;
            continue;
        }

        int sj = readyQueue.get(i).burstTime; // En kısa işlemi (Shortest Job) temsil eden değişken
        int addr = 0;

        // En az bir varış zamanına sahip işlem olduğunu varsayar ve daha kısa işlemleri arar
        for (int j = i; j < readyQueue.size(); j++) {
            if (sj > readyQueue.get(j).burstTime && timeTick >= readyQueue.get(j).arrivalTime) {
                sj = readyQueue.get(j).burstTime;
                addr = j;
            }
        }

        // İşlem çalıştırılır
        if (!runningProcessName.equals(readyQueue.get(addr).name)) {
            timeTick += contextSwitchCost; // Bağlam değiştirme maliyetini ekler
            runningProcessName = readyQueue.get(addr).name;
        }

        readyQueue.get(addr).burstTime--; // İşlem süresini azaltır
        readyQueue.get(addr).runningTime.add(timeTick); // İşlemin çalışma zamanlarına zaman işaretçisini ekler
        System.out.println("TimeTick: " + timeTick + " Running " + readyQueue.get(addr).name);

        // Eğer işlem süresi tamamlanmışsa, tamamlanan işlemler kuyruğuna eklenir ve hazır kuyruktan çıkarılır
        if (readyQueue.get(addr).burstTime == 0) {
            finishedProcesses.add(readyQueue.get(addr));
            readyQueue.remove(addr);
        }
        timeTick++; // Zaman işaretçisini arttırır
    }

    calculateResponseTimes(finishedProcesses); // Tamamlanan işlemlerin yanıt sürelerini hesaplar
    PrintQueue(finishedProcesses); // Tamamlanan işlemleri ekrana yazdırır
}

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
    // TODO code application logic here
    
    // Scheduler sınıfından bir nesne oluşturulur
    Scheduler s = new Scheduler();
}

