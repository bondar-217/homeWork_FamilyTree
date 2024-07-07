package family.human;

import family.Alivable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HumansIterator<T extends Alivable<T>> implements Iterator<T> {
    private List<T> list;
    private int index;
    public HumansIterator(List<T> list){
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public T next() {
        return list.get(index++);
    }
}
