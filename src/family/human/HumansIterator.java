package family.human;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class HumansIterator implements Iterator<Human> {
    private List<Human> list;
    private int index;
    public HumansIterator(List<Human> list){
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return index < list.size();
    }

    @Override
    public Human next() {
        return list.get(index++);
    }
}
