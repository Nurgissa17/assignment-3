import java.util.Random;
public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            table.put(new MyTestingClass(random.nextInt()), new Student(i));
        }
        table.printBuckets();
    }
}