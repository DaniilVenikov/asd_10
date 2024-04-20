public class Main {
    public static void main(String[] args) {
        PowerSet set1 = new PowerSet();
        set1.put("A");
        set1.put("B");
        set1.put("C");
        PowerSet set2 = new PowerSet();
        set2.put("B");
        set2.put("C");

        System.out.println(set2.isSubset(set1));
    }
}
