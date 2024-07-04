package family.tree;

import java.io.Serializable;
import java.util.*;

import family.human.Human;
import family.human.HumansIterator;


public class FamilyTree implements Serializable, Iterable<Human> {
    private List<Human> humans;

    public FamilyTree(List<Human> list){
        humans = new ArrayList<>(list);
    }

    public FamilyTree() {
        humans = new ArrayList<>();
    }

    public boolean add(Human human) {
        if (!humans.contains(human))
            return humans.add(human);
        return false;
    }

    private List<Human> getTree() {
        return humans;
    }

    public boolean setParentRelationship(Human human) {
        if (!human.getParents().isEmpty()) {
            for (Human parent : human.getParents()) {
                if (!parent.getChildren().contains(human)) {
                    return parent.addChild(human);
                }
            }
        }
        return false;
    }

    //
    public void setAllRelationships(){
        for (Human human : humans){
            setParentRelationship(human);
        }
    }

    public Human getByName(String name){
        for (Human human : humans){
            if (human.getName().equals(name)){
                return human;
            }
        }
        return null;
    }

    public Human getById(int id){
        for (Human human : humans){
            if (human.getId() == id){
                return human;
            }
        }
        return null;
    }

    public Set<Human> getSiblings(Human human) {
        Set<Human> siblings = new HashSet<>();
        for (Human parent : human.getParents()) {
            for (Human el : parent.getChildren()) {
                if (!el.equals(human)) {
                    siblings.add(el);
                }
            }
        }
        return siblings;
    }

    public HashSet<Human> getDescendants(Human human, int generation) {
        HashSet<Human> hs = new HashSet<>();
        if (generation == 0) {
            hs.addAll(this.getSiblings(human));
            hs.add(human);
        }
        for (Human child : human.getChildren()) {
            hs.addAll(getDescendants(child, generation - 1));
        }
        return hs;
    }

    public HashSet<Human> getAncestors(Human human, int generation){
        HashSet<Human> hs = new HashSet<>();
        if (generation == 0){
            if (human != null){
                hs.add(human);
            }
        }
        for (Human parent : human.getParents()){
            if (parent != null){
                hs.addAll(getAncestors(parent, generation - 1));
            }
        }
        return hs;
    }

    public void sortById(){
        humans.sort(null);
    }

    public void sortByName(){
        humans.sort(new ByNameComparator());
    }

    public void sortByAge(){
        humans.sort(new ByAgeComparator());
    }
    @Override
    public Iterator<Human> iterator() {
        return new HumansIterator(humans);
    }
}
