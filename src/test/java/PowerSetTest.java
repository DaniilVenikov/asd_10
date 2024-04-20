import org.junit.Test;
import static org.junit.Assert.*;

public class PowerSetTest {

    @Test
    public void testPut() {
        PowerSet set = new PowerSet();
        set.put("A");
        assertTrue(set.get("A"));

        set.put("A");
        assertEquals(1, set.size());
    }

    @Test
    public void testRemove() {
        PowerSet set = new PowerSet();
        set.put("A");
        assertTrue(set.remove("A"));
        assertFalse(set.get("A"));
        assertEquals(0, set.size());
    }

    @Test
    public void testIntersection() {
        PowerSet set1 = new PowerSet();
        set1.put("A");
        set1.put("B");
        PowerSet set2 = new PowerSet();
        set2.put("B");
        set2.put("C");

        PowerSet intersection = set1.intersection(set2);
        assertTrue(intersection.get("B"));
        assertFalse(intersection.get("A"));
        assertFalse(intersection.get("C"));
        assertEquals(1, intersection.size());

        PowerSet emptyIntersection = set1.intersection(new PowerSet());
        assertEquals(0, emptyIntersection.size());
    }

    @Test
    public void testUnion() {
        PowerSet set1 = new PowerSet();
        set1.put("A");
        set1.put("B");
        PowerSet set2 = new PowerSet();
        set2.put("B");
        set2.put("C");

        PowerSet union = set1.union(set2);
        assertTrue(union.get("A"));
        assertTrue(union.get("B"));
        assertTrue(union.get("C"));
        assertEquals(3, union.size());

        PowerSet emptyUnion1 = new PowerSet().union(set1);
        assertEquals(emptyUnion1.size(), set1.size());

        PowerSet emptyUnion2 = set1.union(new PowerSet());
        assertEquals(emptyUnion2.size(), set1.size());
    }

    @Test
    public void testDifference() {
        PowerSet set1 = new PowerSet();
        set1.put("A");
        set1.put("B");
        PowerSet set2 = new PowerSet();
        set2.put("B");
        set2.put("C");

        PowerSet difference = set1.difference(set2);
        assertTrue(difference.get("A"));
        assertFalse(difference.get("B"));
        assertFalse(difference.get("C"));
        assertEquals(1, difference.size());

        PowerSet emptyDifference1 = set1.difference(new PowerSet());
        assertEquals(emptyDifference1.size(), set1.size());

        PowerSet emptyDifference2 = new PowerSet().difference(set1);
        assertEquals(0, emptyDifference2.size());
    }

    @Test
    public void testIsSubset() {
        PowerSet set1 = new PowerSet();
        set1.put("A");
        set1.put("B");
        set1.put("C");
        PowerSet set2 = new PowerSet();
        set2.put("B");
        set2.put("C");

        assertTrue(set1.isSubset(set2));
        assertFalse(set2.isSubset(set1));

        PowerSet set3 = new PowerSet();
        set3.put("D");
        set3.put("E");

        assertFalse(set3.isSubset(set1));
    }

    @Test
    public void testPerformance() {
        PowerSet set1 = new PowerSet();
        PowerSet set2 = new PowerSet();
        PowerSet set3 = new PowerSet();


        for (int i = 0; i < 20000; i++) {
            set1.put("Element" + i);
            set2.put("Element" + (i + 10000));
        }

        for (int i = 0; i < 15000; i++) {
            set3.put("Element" + i);
        }


        long startTime = System.currentTimeMillis();
        PowerSet intersection = set1.intersection(set3);
        long endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime < 2000);


        startTime = System.currentTimeMillis();
        PowerSet union = set1.union(set3);
        endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime < 2000);


        startTime = System.currentTimeMillis();
        PowerSet difference = set1.difference(set3);
        endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime < 2000);


        startTime = System.currentTimeMillis();
        boolean isSubset = set3.isSubset(set1);
        endTime = System.currentTimeMillis();
        assertTrue(endTime - startTime < 2000);
    }
}
