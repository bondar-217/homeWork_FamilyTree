import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Human {
    private final String name;
    private final LocalDate birthDate;
    private final Gender gender;
    private Human mother, father;
    private Set<Human> children;

    public Human(String name, LocalDate birthDate, Gender gender, Human mother, Human father){
        this(name, birthDate, gender);
        setParents(mother, father);
    }

    // Этот конструктор для корневых элементов дерева, для которых не указываются родители
    public Human(String name, LocalDate birthDate, Gender gender){
        children = new HashSet<>();
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public HashSet<Human> getParents(){
        HashSet<Human> set = new HashSet<>();
        set.add(mother);
        set.add(father);
        return set;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return (LocalDate.now().getYear() - birthDate.getYear());
    }

    // Нижеследующие 2 метода для того, чтобы проследить родословную по конкретной линии

    public Human getMother(){
        if (mother != null)
            return mother;
        return null;
    }

    public Human getFather(){
        if (father != null)
            return father;
        return null;
    }

    // Подойдет для поиска братьев, сестер, дядь, теть, племянников
    public HashSet<Human> getSiblings(){
        HashSet<Human> hs = new HashSet<>();
        hs.addAll(this.getMother().getChildren());
        hs.addAll(this.getFather().getChildren());
        return hs;
    }

    public Human getChildByName(String name){
        for (Human child : children){
            if(child.getName().equals(name)){
                return child;
            }
        }
        return null;
    }

    // на случай, если родители не были указаны в конструкторе.
    // может также понадобиться при продлении дерева вверх
    public void setParents(Human mother, Human father){
        if (mother.gender == Gender.Female && father.gender == Gender.Male
        && mother.getAge() > this.getAge() && father.getAge() > this.getAge()){
            this.mother = mother;
            this.father = father;
            mother.addChild(this);
            father.addChild(this);
        }
        else System.err.printf("Wrong parents for %s\n", this.getName());
    }

    // приватный, так как дети добавляются автоматически при указании у них родителей (родителя должно быть два!)
    private void addChild(Human child){
        children.add(child);
    }

    public Set<Human> getChildren(){
        return children;
    }

    @Override
    public String toString() {
        return name + " " + getAge() + " y.o. " + gender.toString();

    }

    // проверка на детей. т.к. гипотетически могут быть тёзки-близнецы, но с разным набором детей
    // они не будут равны
    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().getName().equals(this.getClass().getName()))
            return false;

        Human human = (Human) obj;
        Set<Human> tempThis = new HashSet<>(this.children);
        Set<Human> tempObj = new HashSet<>(human.children);
        tempThis.removeAll(tempObj);
        if (this.hashCode() != human.hashCode())
            return false;
        return this.name.equals(human.getName()) && this.birthDate.isEqual(human.birthDate)
                && tempThis.isEmpty();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthDate, mother, father, children.size());
    }

    // Следующие два метода статичные, т.к. для их работы требуется передавать объект
    // Ищут потомков и предков в n-ном поколении.
    // Потомки в поколении, превышающем допустимое значение, вернут пустой список
    // Поиск предков остановится на корне дерева. Пустого списка не получится,
    // так как родители у корневых эл-тов null
    // (как-то так пока что... пришлось поплясать с бубном, но я ещё подумаю над этой задачей)

    public static HashSet<Human> getDescendants(Human human, int generation){
        HashSet<Human> hs = new HashSet<>();
        if(generation == 0 || human.getChildren() == null){
            hs.addAll(human.getSiblings());
        }
        for (Human child : human.getChildren()){
            hs.addAll(getDescendants(child, generation - 1));
        }
        return hs;
    }

    public static HashSet<Human> getAncestors(Human human, int generation, HashSet<Human> hs){
        if (generation == 0){
            hs.add(human);
            return hs;
        }

        if (human.getFather() != null && human.getMother() != null){
            for (Human parent : human.getParents()) {
                hs.addAll(getAncestors(parent, generation - 1, hs));
            }
        } else {
            hs.add(human);
        }
        return hs;
    }
}
