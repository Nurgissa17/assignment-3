public class MyHashTable<K, V> {
    private class HashNode<K, V>{
        private K key;
        private V value;
        private HashNode<K, V> next;
        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString(){
            return "{" + key + " " + value + "}";
        }
    }
    public HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable(){
        chainArray = (HashNode<K, V>[]) new HashNode[M];
        size = 0;
    }
    private int hash(K key){
       return Math.abs(key.hashCode() % M);
    }
    public void put(K key, V value){
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        int index = hash(key);
        newNode.next = chainArray[index];
        chainArray[index] = newNode;
    }
    public V get(K key){
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        while(current != null){
            if(current.key.equals(key)){
                return current.value;
            }
            current = current.next;
        }
        return null;
    }
    public V remove(K key) {
        int index = hash(key);
        HashNode<K, V> current = chainArray[index];
        HashNode<K, V> prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    chainArray[index] = current.next;   // удалили первый
                } else {
                    prev.next = current.next;           // перепрыгнули через current
                }
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }
    public void display(){
        for(int i = 0; i < M; i++){
            HashNode<K, V> current = chainArray[i];
            System.out.print(i + ":");
            while(current != null){
                System.out.print("[" + current.key + current.value + "]");
                current = current.next;
            }
            System.out.println();
        }
    }
    public void printBuckets() {
        for (int i = 0; i < M; i++) {
            int count = 0;
            HashNode<K, V> current = chainArray[i];
            while (current != null) {
                count++;
                current = current.next;
            }
            System.out.println(count);
        }
    }
}
