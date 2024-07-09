import family.human.Gender;
import family.HumanService;
import family.tree.FamilyTree;

import java.time.LocalDate;
import java.time.Month;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HumanService humanService = new HumanService();

        humanService.addHumanToTree("Anna", LocalDate.of(2021, Month.MARCH, 3), Gender.Female);
        humanService.addHumanToTree("Egor", LocalDate.of(2000, Month.JANUARY, 25), Gender.Male);
        humanService.addHumanToTree("Kirill", LocalDate.of(1999, Month.DECEMBER, 31), Gender.Male);
        humanService.addHumanToTree("Alex", LocalDate.of(2011, Month.MAY, 11), Gender.Male);
        humanService.addHumanToTree("Paul", LocalDate.of(2005, Month.JUNE, 6), Gender.Male);
        humanService.addHumanToTree("Max", LocalDate.of(2002, Month.JULY, 16), Gender.Female);
        humanService.addHumanToTree("Li", LocalDate.of(2004, Month.DECEMBER, 3), Gender.Male);


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