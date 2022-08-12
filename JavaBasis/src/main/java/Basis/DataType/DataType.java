package Basis.DataType;

import java.nio.charset.StandardCharsets;

public class DataType {
    public static void main(String[] args) {

        // value or quote ?

        /*Integer x = new Integer(123);
        Integer y = new Integer(123);
        System.out.println(x == y);    // false (buffer pool:unable)
        Integer z = Integer.valueOf(123);
        Integer k = Integer.valueOf(123);
        System.out.println(z == k);   // true (buffer pool:preferentially)


        String s1 = new String("aaa");
        String s2 = new String("aaa");
        System.out.println(s1 == s2);           // false
        String s3 = s1.intern();                // put s1 into String Pool
        System.out.println(s1.intern() == s3);  // true


        String s4 = "bbb";
        String s5 = "bbb";
        System.out.println(s4 == s5);  // true*/




        // type convert:

        /*the following code is correct:
        short x = 3;
        x += 4.6;

        will not compile:
        short x = 3;
        x = x + 4.6;

        and results in x having the value 7 because it is equivalent to:
        short x = 3;
        x = (short)(x + 4.6);*/




        // double or float?

        /*double a = 1.1;
        float b = 1.1f;*/





        //  convert byte[] to String:

        /*String a = new String(new byte[]{});*/

        // convert String to byte[]:

        /*byte[] a = new String().getBytes(StandardCharsets.UTF_8);*/




    }
}
