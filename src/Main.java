import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Human eva = new Human("Eva", LocalDate.of(1987, Month.NOVEMBER, 10), Gender.Female);
        Human adam = new Human("Adam", LocalDate.of(1986, Month.APRIL, 26), Gender.Male);

        Human kain = new Human("Kain", LocalDate.of(2004, Month.JANUARY, 11), Gender.Male, eva, adam);
        Human sif = new Human("Sif", LocalDate.of(2010, Month.APRIL, 1), Gender.Male, eva, adam);
        Human avel = new Human("Avel", LocalDate.of(2000, Month.JUNE, 6), Gender.Male, eva, adam);
        Human avan = new Human("Avan", LocalDate.of(2005, Month.JULY, 16), Gender.Female, eva, adam);
        Human enoh = new Human("Enoh", LocalDate.of(2020, Month.DECEMBER, 3), Gender.Male, avan, kain);

        System.out.println(eva.getAge());
        System.out.println(eva.getChildByName("Avan"));

        System.out.println("\n------------------");

        System.out.println(eva.getChildren());
        System.out.println(adam.getChildren());
        System.out.println(enoh.getParents());


        Human enohsGrandma = enoh.getFather().getMother();
        System.out.println("\nEnoh's Grandma: " + enohsGrandma);
        System.out.println("\n----------------------");
        System.out.println(enohsGrandma.equals(eva));

        System.out.println("\nБратия и сестры:");
        System.out.println(sif.getSiblings());

        System.out.println("\nПотомки в n-ном поколении:");
        System.out.println(Human.getDescendants(adam, 1));
        System.out.println(Human.getDescendants(eva, 2));

        System.out.println("\nПредки в n-ном поколении:");
        System.out.println(Human.getAncestors(enoh, 1, new HashSet<>()));
        System.out.println(Human.getAncestors(enoh, 2, new HashSet<>()));
    }
}