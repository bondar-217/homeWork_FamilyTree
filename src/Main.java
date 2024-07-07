import family.human.Gender;
import family.HumanService;
import family.tree.FamilyTree;

import java.time.LocalDate;
import java.time.Month;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HumanService humanService = new HumanService();

        humanService.addHumanToTree("Eva", LocalDate.of(1987, Month.NOVEMBER, 10), Gender.Female);
        humanService.addHumanToTree("Adam", LocalDate.of(1986, Month.APRIL, 26), Gender.Male);
        humanService.addHumanToTree("Kain", LocalDate.of(2004, Month.JANUARY, 11), Gender.Male);
        humanService.addHumanToTree("Sif", LocalDate.of(2010, Month.APRIL, 1), Gender.Male);
        humanService.addHumanToTree("Avel", LocalDate.of(2000, Month.JUNE, 6), Gender.Male);
        humanService.addHumanToTree("Avan", LocalDate.of(2005, Month.JULY, 16), Gender.Female);
        humanService.addHumanToTree("Enoh", LocalDate.of(2020, Month.DECEMBER, 3), Gender.Male);

//        Human eva = builder.createHuman("Eva", LocalDate.of(1987, Month.NOVEMBER, 10), Gender.Female);
//        Human adam = builder.createHuman("Adam", LocalDate.of(1986, Month.APRIL, 26), Gender.Male);
//        Human kain = builder.createHuman("Kain", LocalDate.of(2004, Month.JANUARY, 11), Gender.Male, eva, adam);
//        Human sif = builder.createHuman("Sif", LocalDate.of(2010, Month.APRIL, 1), Gender.Male, eva, adam);
//        Human avel = builder.createHuman("Avel", LocalDate.of(2000, Month.JUNE, 6), Gender.Male, eva, adam);
//        Human avan = builder.createHuman("Avan", LocalDate.of(2005, Month.JULY, 16), Gender.Female, eva, adam);
//        Human enoh = builder.createHuman("Enoh", LocalDate.of(2020, Month.DECEMBER, 3), Gender.Male, avan, kain);

        System.out.println(humanService.printTreeInfo());
        humanService.setParent(2,0);
        humanService.setParent(2, 1);
        humanService.setParent(4,0);
        humanService.setParent(4,1);
        humanService.setParent(5,0);
        humanService.setParent(6,5);
        humanService.setParent(6,2);
        humanService.setRelationships();
        System.out.println(humanService.getById(2).getParents());
        System.out.println(humanService.getSiblings(2));
        System.out.println(humanService.getAncestors(6,2));
        System.out.println(humanService.getDescendants(0,2));
        humanService.sortByName();
        System.out.println(humanService.printTreeInfo());
        humanService.sortByAge();
        System.out.println(humanService.printTreeInfo());
        humanService.sortById();
        System.out.println(humanService.printTreeInfo());

        humanService.save();

        FamilyTree tree = humanService.load();

        System.out.println("=========================");
        System.out.println(tree.getById(0));

    }
}