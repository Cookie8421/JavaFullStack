package Basis.Java8;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

public class OptionalTest {



    public static void main(String[] args) {
        //调用工厂方法创建Optional实例
        Optional<String> name = Optional.of("Sanaulla");

        //isPresent方法用来检查Optional实例中是否包含值
        if (name.isPresent()) {
            //在Optional实例内调用get()返回已存在的值
            System.out.println(name.get());//输出Sanaulla
        }

        //传入参数为null，抛出NullPointerException.
        Optional<String> someNull = Optional.of(null);

        //下面创建了一个不包含任何值的Optional实例
        //例如，值为'null'
        Optional empty = Optional.ofNullable(null);

        //执行下面的代码会输出: No value present
        try {
            //在空的Optional实例上调用get()，抛出NoSuchElementException
            System.out.println(empty.get());
        } catch (NoSuchElementException ex) {
            System.out.println(ex.getMessage());
        }


        //ifPresent方法接受lambda表达式作为参数。
        //lambda表达式对Optional的值调用consumer进行处理。
        name.ifPresent((value) -> {
            System.out.println("The length of the value is: " + value.length());
        });


        //如果值不为null，orElse方法返回Optional实例的值。
        //如果为null，返回传入的消息。
        //输出: There is no value present!
        System.out.println(empty.orElse("There is no value present!"));
        //输出: Sanaulla
        System.out.println(name.orElse("There is some value!"));


        //orElseGet与orElse方法类似，区别在于orElse传入的是默认值，
        //orElseGet可以接受一个lambda表达式生成默认值。
        //输出: Default Value
        System.out.println(empty.orElseGet(() -> "Default Value"));
        //输出: Sanaulla
        System.out.println(name.orElseGet(() -> "Default Value"));


        try {
            //orElseThrow与orElse方法类似。与返回默认值不同，
            //orElseThrow会抛出lambda表达式或方法生成的异常
            empty.orElseThrow(ValueAbsentException::new);
        } catch (Throwable ex) {
            //输出: No value present in the Optional instance
            System.out.println(ex.getMessage());
        }


        //map方法执行传入的lambda表达式参数对Optional实例的值进行修改。
        //为lambda表达式的返回值创建新的Optional实例作为map方法的返回值。
        Optional<String> upperName = name.map((value) -> value.toUpperCase());
        System.out.println(upperName.orElse("No value found"));


        //flatMap与map(Function)非常类似，区别在于传入方法的lambda表达式的返回类型。
        //map方法中的lambda表达式返回值可以是任意类型，在map函数返回之前会包装为Optional。
        //但flatMap方法中的lambda表达式返回值必须是Optionl实例。
        upperName = name.flatMap((value) -> Optional.of(value.toUpperCase()));
        System.out.println(upperName.orElse("No value found"));//输出SANAULLA


        //解决这种结构的深层嵌套路径是有点麻烦的。我们必须编写一堆 null 检查来确保不会导致一个
        Outer outer = new Outer();
        if (outer != null && outer.nested != null && outer.nested.inner != null) {
            System.out.println(outer.nested.inner.foo);
        }
        //我们可以通过利用 Java 8 的 Optional 类型来摆脱所有这些 null 检查。
        // map 方法接收一个 Function 类型的 lambda 表达式，并自动将每个 function 的结果包装成一个 Optional 对象。
        // 这使我们能够在一行中进行多个 map 操作。Null 检查是在底层自动处理的。
        Optional.of(new Outer())
                .map(Outer::getNested)
                .map(Nested::getInner)
                .map(Inner::getFoo)
                .ifPresent(System.out::println);
        //还有一种实现相同作用的方式就是通过利用一个 supplier 函数来解决嵌套路径的问题:
        Outer obj = new Outer();
        resolve(() -> obj.getNested().getInner().getFoo())
    .ifPresent(System.out::println);
    }

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        }
        catch (NullPointerException e) {
            return Optional.empty();
        }
    }
}

class Outer {
    Nested nested;
    Nested getNested() {
        return nested;
    }
}
class Nested {
    Inner inner;
    Inner getInner() {
        return inner;
    }
}
class Inner {
    String foo;
    String getFoo() {
        return foo;
    }
}
