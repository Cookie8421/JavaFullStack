package Basis.DataType;

public class DataType {
    public static void main(String[] args) {

        Integer x = new Integer(123);
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
        System.out.println(s4 == s5);  // true


        /*the following code is correct:
        short x = 3;
        x += 4.6;*/

        /*will not compile fine
        short x = 3;
        x = x + 4.6;*/

        /*and results in x having the value 7 because it is equivalent to:
        short x = 3;
        x = (short)(x + 4.6);*/


    }
}
