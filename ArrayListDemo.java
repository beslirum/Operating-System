import java.util.ArrayList;
import java.util.List;

public class Example2 {
    public static void main(String[] args) {
        // Liste bilgisi stack'te saklanır.
        List<String> stringList = new ArrayList<>();

        // Liste elemanları heap'te saklanır.
        stringList.add("Java");
        stringList.add("Python");
        stringList.add("C++");

        // Liste bilgisi üzerinde işlemler yapılabilir.
        for (String language : stringList) {
            // 'language' stack'te saklanır.
            System.out.println(language);
        }

        // 'stringList' burada kullanılabilir, çünkü bu bir referanstır ve stack'te saklanır.
    }
}
