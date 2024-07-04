import family.human.Gender;
import family.Service;
import family.tree.FamilyTree;

import java.time.LocalDate;
import java.time.Month;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Service service = new Service();

        service.addHumanToTree("Eva", LocalDate.of(1987, Month.NOVEMBER, 10), Gender.Female);
        service.addHumanToTree("Adam", LocalDate.of(1986, Month.APRIL, 26), Gender.Male);
        service.addHumanToTree("Kain", LocalDate.of(2004, Month.JANUARY, 11), Gender.Male);
        service.addHumanToTree("Sif", LocalDate.of(2010, Month.APRIL, 1), Gender.Male);
        service.addHumanToTree("Avel", LocalDate.of(2000, Month.JUNE, 6), Gender.Male);
        service.addHumanToTree("Avan", LocalDate.of(2005, Month.JULY, 16), Gender.Female);
        service.addHumanToTree("Enoh", LocalDate.of(2020, Month.DECEMBER, 3), Gender.Male);

//        Human eva = builder.createHuman("Eva", LocalDate.of(1987, Month.NOVEMBER, 10), Gender.Female);
//        Human adam = builder.createHuman("Adam", LocalDate.of(1986, Month.APRIL, 26), Gender.Male);
//        Human kain = builder.createHuman("Kain", LocalDate.of(2004, Month.JANUARY, 11), Gender.Male, eva, adam);
//        Human sif = builder.createHuman("Sif", LocalDate.of(2010, Month.APRIL, 1), Gender.Male, eva, adam);
//        Human avel = builder.createHuman("Avel", LocalDate.of(2000, Month.JUNE, 6), Gender.Male, eva, adam);
//        Human avan = builder.createHuman("Avan", LocalDate.of(2005, Month.JULY, 16), Gender.Female, eva, adam);
//        Human enoh = builder.createHuman("Enoh", LocalDate.of(2020, Month.DECEMBER, 3), Gender.Male, avan, kain);

        System.out.println(service.printTreeInfo());
        service.setParent(2,0);
        service.setParent(2, 1);
        service.setParent(4,0);
        service.setParent(4,1);
        service.setParent(5,0);
        service.setParent(6,5);
        service.setParent(6,2);
        service.setRelationships();
        System.out.println(service.getById(2).getParents());
        System.out.println(service.getSiblings(2));
        System.out.println(service.getAncestors(6,2));
        System.out.println(service.getDescendants(0,2));
        service.sortByName();
        System.out.println(service.printTreeInfo());
        service.sortByAge();
        System.out.println(service.printTreeInfo());
        service.sortById();
        System.out.println(service.printTreeInfo());

        service.save();

        FamilyTree tree = service.load();

        System.out.println("=========================");
        System.out.println(tree.getById(0));

    }
}