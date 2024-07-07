package family.human;

//TODO
// Exceptions

import family.Alivable;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Human implements Serializable, Comparable<Human>, Alivable<Human> {
    private int id;
    private String name;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private Gender gender;
    private Human mother, father;
    private Set<Human> children;

    public Human(int id, String name, LocalDate birthDate, Gender gender, Human mother, Human father){
        this(id, name, birthDate, gender);
        setParent(mother);
        setParent(father);
    }

    // Этот конструктор для корневых элементов дерева, для которых не указываются родители
    public Human(int id, String name, LocalDate birthDate, Gender gender){
        this.id = id;
        children = new HashSet<>();
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public HashSet<Human> getParents(){
        HashSet<Human> set = new HashSet<>();
        if (mother != null)
            set.add( mother);
        if (father != null)
            set.add( father);
        return set;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    // Теперь возраст выдает сколько полных лет

    public int getAge(){
        LocalDate endDate;
        if (deathDate == null){
            endDate = LocalDate.now();
        } else endDate = deathDate;
        Period period = Period.between(birthDate, endDate);
        return period.getYears();
    }

    // Нижеследующие 2 метода для того, чтобы проследить родословную по конкретной линии

    public Human getMother(){
        if (mother != null)
            return (Human) mother;
        return null;
    }

    public Human getFather(){
        if (father != null)
            return father;
        return null;
    }

    // Подойдет для поиска братьев, сестер, дядь, теть, племянников
    //(Перенесено в FamilyTree)

    public Human getChildByName(String name){
        for (Human child : children){
            if(child.getName().equals(name)){
                return (Human) child;
            }
        }
        return null;
    }

    // на случай, если родители не были указаны в конструкторе.
    // может также понадобиться при продлении дерева вверх
    // исправлено
    public void setParent(Human parent){
        if (parent.gender == Gender.Female)
            this.mother = parent;
        else this.father = parent;
    }

    // дело сделано
    public void setDeathDate(LocalDate deathDate) {
        this.deathDate = deathDate;
    }

    public boolean addChild(Human child){
        return children.add(child);
    }

    public Gender getGender() {
        return gender;
    }

    public Set<Human> getChildren(){
        return children;
    }

    @Override
    public String toString() {
        return "#" + id + ": " + name + " " + getAge() + " y.o. " + gender.toString();

    }

    // проверка на детей. т.к. гипотетически могут быть тёзки-близнецы, но с разным набором детей
    // они не будут равны (исправлено)
    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().getName().equals(this.getClass().getName()))
            return false;

        Human human = (Human) obj;
        if (this.hashCode() != human.hashCode())
            return false;
      return this.id == human.id && this.name.equals(human.getName()) && this.birthDate.isEqual(human.birthDate)
                && this.children.equals(human.children);
//        return this.id == human.id;
    }

    private int setSize(){
        if (children == null){
            return 0;
        } else return children.size();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, birthDate, mother, father, this.setSize());
    }

    @Override
    public int compareTo(Human o) {
        return this.id - o.getId();
    }

    // Поиск предков и потомков в n-ном поколении перенесены в FamilyTree
}
