public class MyTestingClass {
    int id;
    public MyTestingClass(int id) {
        this.id = id;
    }
    @Override
    public int hashCode() {
        return id;
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MyTestingClass)) return false;
        return ((MyTestingClass)obj).id == id;
    }
}