import family.HumanBuilder;
import rw.FileHandler;
import rw.Writer;
import family.FamilyTree;
import family.Human;
import family.Gender;
import java.time.LocalDate;
import java.time.Month;

import java.io.IOException;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HumanBuilder builder = new HumanBuilder();
        Human eva = builder.createHuman("Eva", LocalDate.of(1987, Month.NOVEMBER, 10), Gender.Female);
        Human adam = builder.createHuman("Adam", LocalDate.of(1986, Month.APRIL, 26), Gender.Male);
        Human kain = builder.createHuman("Kain", LocalDate.of(2004, Month.JANUARY, 11), Gender.Male, eva, adam);
        Human sif = builder.createHuman("Sif", LocalDate.of(2010, Month.APRIL, 1), Gender.Male, eva, adam);
        Human avel = builder.createHuman("Avel", LocalDate.of(2000, Month.JUNE, 6), Gender.Male, eva, adam);
        Human avan = builder.createHuman("Avan", LocalDate.of(2005, Month.JULY, 16), Gender.Female, eva, adam);
        Human enoh = builder.createHuman("Enoh", LocalDate.of(2020, Month.DECEMBER, 3), Gender.Male, avan, kain);

//        Human eva = new Human("Eva", LocalDate.of(1987, Month.NOVEMBER, 10), Gender.Female);
//        Human adam = new Human("Adam", LocalDate.of(1986, Month.APRIL, 26), Gender.Male);
//        Human adam1 = new Human("Adam", LocalDate.of(1986, Month.APRIL, 26), Gender.Male);
//
//        Human kain = new Human("Kain", LocalDate.of(2004, Month.JANUARY, 11), Gender.Male, eva, adam);
//        Human sif = new Human("Sif", LocalDate.of(2010, Month.APRIL, 1), Gender.Male, eva, adam);
//        Human avel = new Human("Avel", LocalDate.of(2000, Month.JUNE, 6), Gender.Male, eva, adam);
//        Human avan = new Human("Avan", LocalDate.of(2005, Month.JULY, 16), Gender.Female, eva, adam);
//        Human enoh = new Human("Enoh", LocalDate.of(2020, Month.DECEMBER, 3), Gender.Male, avan, kain);

        FamilyTree tree = new FamilyTree(Set.of(adam, eva, kain, sif, avel, avan));
        Writer fileHandler = new FileHandler();
//        FamilyTree tree = fileHandler.read("tree.txt");

        System.out.println(tree.getByName("Eva").getAge());
        System.out.println(eva.getChildByName("Avan"));
        System.out.println("By ID: " + tree.getById(4));




        tree.add(enoh);

        tree.setAllRelationships();

        System.out.println(tree.getSiblings(sif));

        System.out.println(tree.getTree());

        Human enohsGrandma = enoh.getFather().getMother();
        System.out.println("\nEnoh's Grandma: " + enohsGrandma);
        System.out.println("\n----------------------");
        System.out.println(enohsGrandma.equals(eva));
//        System.out.println(adam1.equals(adam));

//
        System.out.println("\nБратия и сестры:");
        System.out.println(tree.getSiblings(sif));

        System.out.println("\nПотомки:");
        System.out.println(tree.getDescendants(adam, 1));
        System.out.println(tree.getDescendants(eva, 2));

        System.out.println("\nПредки:");
        System.out.println(tree.getAncestors(enoh, 1));
        System.out.println(tree.getAncestors(enoh, 2));



        fileHandler.write("tree.txt", tree);
        System.out.println("=================================");
        System.out.println(tree.getTree());

    }
}