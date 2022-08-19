package Basis.generalmethod;

import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        GeneralMethod generalMethod = new GeneralMethod();
        generalMethod.setName("aaaa");
        System.out.println(generalMethod);

        hashSet.add(generalMethod);
        generalMethod = new GeneralMethod();
        generalMethod.setName("aaaa");
        System.out.println(generalMethod);

        hashSet.add(generalMethod);
        System.out.println("hashset的大小：" + hashSet.size());
        hashSet.stream().forEach(addTel -> {
            System.out.println(addTel);
        });
    }
}
