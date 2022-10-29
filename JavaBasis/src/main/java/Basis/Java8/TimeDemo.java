package Basis.Java8;

import java.util.Calendar;

public class TimeDemo {


    public static void main(String[] args) {

        // 计算两个日期之间的天数
        Calendar birth = Calendar.getInstance();
        birth.set(1975, Calendar.MAY, 26);
        Calendar now = Calendar.getInstance();
        System.out.println(daysBetween(birth, now));
        System.out.println(daysBetween(birth, now)); // 显示 0?

        // Java 8仍然延用了ISO的日历体系，并且与它的前辈们不同，java.time包中的类是不可变且线程安全的。
        // 新的时间及日期API位于java.time包中


    }

    public static long daysBetween(Calendar begin, Calendar end) {
        long daysBetween = 0;
        while(begin.before(end)) {
            begin.add(Calendar.DAY_OF_MONTH, 1);
            daysBetween++;
        }
        return daysBetween;
    }

}
