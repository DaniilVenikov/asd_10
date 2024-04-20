import java.util.ArrayList;
import java.util.List;

public class PowerSet
{
    private final List<String> values;

    public PowerSet()
    {
        values = new ArrayList<>(20000);
    }

    public int size()
    {
        return values.size();
    }


    public void put(String value)
    {
        if (get(value)) {
            return;
        }

        values.add(value);
    }

    public boolean get(String value)
    {
        return values.contains(value);
    }

    public boolean remove(String value)
    {
        return values.remove(value);
    }

    public PowerSet intersection(PowerSet set2)
    {
        PowerSet result = new PowerSet();
        for (String value : values) {
            if (set2.get(value)) {
                result.put(value);
            }
        }
        return result;
    }

    public PowerSet union(PowerSet set2)
    {
        PowerSet result = new PowerSet();
        result.values.addAll(values);
        for (String value : set2.values) {
            result.put(value);
        }
        return result;
    }

    public PowerSet difference(PowerSet set2)
    {
        PowerSet result = new PowerSet();
        for (String value : values) {
            if (!set2.get(value)) {
                result.put(value);
            }
        }
        return result;
    }

    public boolean isSubset(PowerSet set2)
    {
        for (String value : set2.values) {
            if (!values.contains(value)) {
                return false;
            }
        }
        return true;
    }
}

