package Basis.Exceptions;

public class ExceptionTest {

    private int testTimes;

    public ExceptionTest(int testTimes) {
        this.testTimes = testTimes;
    }

    public void newObject() {
        long l = System.nanoTime();
        for (int i = 0; i < testTimes; i++) {
            new Object();
        }
        System.out.println("create Objects：" + (System.nanoTime() - l));
    }

    public void newException() {
        long l = System.nanoTime();
        for (int i = 0; i < testTimes; i++) {
            new Exception();
        }
        System.out.println("create Exceptions：" + (System.nanoTime() - l));
    }

    public void catchException() {
        long l = System.nanoTime();
        for (int i = 0; i < testTimes; i++) {
            try {
                throw new Exception();
            } catch (Exception e) {
            }
        }
        System.out.println("create,throw and catch Exceptions：" + (System.nanoTime() - l));
    }

    public static void main(String[] args) {
        ExceptionTest test = new ExceptionTest(10000);
        test.newObject();
        test.newException();
        test.catchException();
    }
}
