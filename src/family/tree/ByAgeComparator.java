package family.tree;

import family.Alivable;
import family.human.Human;

import java.util.Comparator;

public class ByAgeComparator<T extends Alivable<T>> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        return o1.getAge() - o2.getAge();
    }
}
