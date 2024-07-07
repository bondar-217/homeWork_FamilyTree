package family;

import family.human.Gender;
import family.human.Human;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public interface Alivable <T extends Alivable>{
    public abstract HashSet<T> getParents();
    public abstract int getId();
    public abstract String getName();
    public abstract int getAge();
    public abstract T getMother();
    public abstract T getFather();
    public abstract T getChildByName(String name);
    public abstract void setParent(T parent);
    public abstract void setDeathDate(LocalDate deathDate);
    public abstract boolean addChild(T child);
    public abstract Gender getGender();
    public abstract Set<T> getChildren();

}
