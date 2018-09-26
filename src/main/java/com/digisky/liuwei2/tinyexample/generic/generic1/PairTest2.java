package com.digisky.liuwei2.tinyexample.generic.generic1;



/**
 * @author liuwei2
 */
public class PairTest2 {
    public static void printBuddies(Pair<? extends Employee> p) {
        Employee first = p.getFirst();
        Employee second = p.getSecond();

        System.out.println(first.getName() + " and " + second.getName() + " are buddies!");
    }

    public static void minmaxBonus(Manager[] m, Pair<? super Manager> result) {
        if (m.length == 0) {
            return;
        }

        Manager min = m[0];
        Manager max = m[0];

        for (int i = 0; i < m.length; i++) {
            if (min.getBonus() > m[i].getBonus()) { min = m[i]; }
            if (max.getBonus() < m[i].getBonus()) { max = m[i]; }
        }

        result.setFirst(min);
        result.setSecond(max);
    }

    public static void maxminBonus(Manager[] m, Pair<? super Manager> p) {
        minmaxBonus(m, p);
        PairAlg.swap(p);
    }

    public static void main(String[] args) {
        Manager ceo = new Manager("VV", 800000);
        Manager cfo = new Manager("YY", 60000);

        Pair<Manager> buddies = new Pair<>(ceo, cfo);
        printBuddies(buddies);

        ceo.setBonus(1000000);
        cfo.setBonus(500000);

        Manager[] managers = new Manager[] {ceo, cfo};
        Pair<Employee> result = new Pair<>();
        minmaxBonus(managers, result);
        printBuddies(result);
        maxminBonus(managers, result);
        printBuddies(result);
    }
}
