package Basis.Java8.lambda;

import Basis.oop.Person;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.forEach(n -> System.out.println(n));
        list.forEach(System.out::println);  // 使用方法引用
        list.forEach((String s) -> System.out.println("*" + s + "*"));

        List<Integer> primes = Arrays.asList(new Integer[]{2, 3,5,7});
        int factor = 2;
        primes.forEach(element -> { System.out.println(factor*element); });
        // 以下代码会报错：从lambda 表达式引用的本地变量必须是最终变量或实际上的最终变量
        /*primes.forEach(element -> { factor++; });*/

        // 这行代码并未做什么实际性的工作，filter只是描述了Stream，没有产生新的集合。
        list.stream().filter(f -> f.equals("p1"));

        // collect最终会从Stream产生新值，拥有终止操作。
        List<String> list2 = list.stream().filter(f -> f.equals("p1")).collect(Collectors.toList());

        // 理想方式是形成一个惰性求值的链，最后用一个及早求值的操作返回想要的结果。
        // 与建造者模式相似，建造者模式先是使用一系列操作设置属性和配置，最后调用build方法，创建对象。

        //每个Stream都有两种模式: 顺序执行和并行执行。
        List <String> str1 = list.stream().collect(Collectors.toList());
        List <String> str2 = list.stream().parallel().collect(Collectors.toList());

        //parallelStream原理:
        /*List originalList = new ArrayList();
        List split1 = originalList(0, originalList.size()/2);//将数据分小部分
        List split2 = originalList(originalList.size()/2,originalList.size());
        new Runnable(split1.process());//小部分执行操作
        new Runnable(split2.process());
        List revisedList = split1 + split2;//将结果合并*/


        // forEach
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));

        // 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
        features.forEach(System.out::println);

        // Supplier<String> s = () -> new String();
        Supplier<String> s = String::new;

        // set.forEach(t -> System.out.println(t));
        // set.forEach(System.out::println);

        // Stream<Double> stream = Stream.generate(() -> Math.random());
        Stream<Double> stream = Stream.generate(Math::random);

        //  TreeSet<String> set = new TreeSet<>((s1,s2) -> s1.compareTo(s2));
        TreeSet<String> set = new TreeSet<>(String::compareTo);

        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        filter(languages, (str)->str.toString().startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str)->str.toString().endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str)->str.toString().length() > 4);

        // 可以用and()、or()和xor()逻辑函数来合并Predicate，
        // 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        languages.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));


        List<Double> costBeforeTax = Arrays.asList(100.00, 200.00, 300.00, 400.00, 500.00);
        double bill = costBeforeTax.stream().map((cost) -> cost + .12*cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);

        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);
        //Collectors.joining(", ")
        // Collectors.toList()
        // Collectors.toSet() ，生成set集合
        // Collectors.toMap(MemberModel::getUid, Function.identity())
        // Collectors.toMap(ImageModel::getAid, o -> IMAGE_ADDRESS_PREFIX + o.getUrl())  flatMap


        //将多个Stream连接成一个Stream
        List<Integer> result= Stream.of(Arrays.asList(1,3),Arrays.asList(5,6)).flatMap(a->a.stream()).collect(Collectors.toList());

        //去重
        List<String> likeDOs=new ArrayList<>();
        List<byte[]> likeTidList = likeDOs.stream().map(String::getBytes)
                .distinct().collect(Collectors.toList());


        //计总数
        /*int countOfAdult=persons.stream()
                .filter(p -> p.getAge() > 18)
                .map(person -> new Adult(person))
                .count();*/


        //匹配
        /*boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);      // true*/

        //最大值最小值
        /*List<Person> lists = new ArrayList<Person>();
        lists.add(new Person(1L, "p1"));
        lists.add(new Person(2L, "p2"));
        lists.add(new Person(3L, "p3"));
        lists.add(new Person(4L, "p4"));
        Person a = lists.stream().max(Comparator.comparing(t -> t.getId())).get();
        System.out.println(a.getId());*/

        //如果比较器涉及多个条件，比较复杂，可以定制
        /*
        Person a = lists.stream().min(new Comparator<Person>() {

              @Override
              public int compare(Person o1, Person o2) {
                   if (o1.getId() > o2.getId()) return -1;
                   if (o1.getId() < o2.getId()) return 1;
                   return 0;
               }
         }).get();*/

        //获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes1 = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes1.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());

        //自定义函数接口
        //
        //@FunctionalInterface
        //public interface IMyInterface {
        //    void study();
        //}
        //
        //package com.isea.java;
        //public class TestIMyInterface {
        //    public static void main(String[] args) {
        //        IMyInterface iMyInterface = () -> System.out.println("I like study");
        //        iMyInterface.study();
        //    }
        //}

        //内置四大函数接口

        //消费型接口: Consumer< T> void accept(T t)有参数，无返回值的抽象方法；
        /*Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));*/

        //供给型接口: Supplier < T> T get() 无参有返回值的抽象方法；
        /*Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person*/

        /*
        // 调用方法
        <R, A> R collect(Collector<? super T, A, R> collector)

        // Collectors.toSet
        public static <T>
            Collector<T, ?, Set<T>> toSet() {
                return new CollectorImpl<>((Supplier<Set<T>>) HashSet::new, Set::add,
                                       (left, right) -> { left.addAll(right); return left; },
                                       CH_UNORDERED_ID);
        }

        // CollectorImpl
        private final Supplier<A> supplier;
        private final BiConsumer<A, T> accumulator;
        private final BinaryOperator<A> combiner;
        private final Function<A, R> finisher;
        private final Set<Characteristics> characteristics;

        CollectorImpl(Supplier<A> supplier,
                      BiConsumer<A, T> accumulator,
                      BinaryOperator<A> combiner,
                      Function<A,R> finisher,
                      Set<Characteristics> characteristics) {
            this.supplier = supplier;
            this.accumulator = accumulator;
            this.combiner = combiner;
            this.finisher = finisher;
            this.characteristics = characteristics;
        }

        CollectorImpl(Supplier<A> supplier,
                      BiConsumer<A, T> accumulator,
                      BinaryOperator<A> combiner,
                      Set<Characteristics> characteristics) {
            this(supplier, accumulator, combiner, castingIdentity(), characteristics);
        }

        // collect()方法实现
        public final <R, A> R collect(Collector<? super P_OUT, A, R> collector) {
            A container;
            if (isParallel()
                    && (collector.characteristics().contains(Collector.Characteristics.CONCURRENT))
                    && (!isOrdered() || collector.characteristics().contains(Collector.Characteristics.UNORDERED))) {
                container = collector.supplier().get();
                BiConsumer<A, ? super P_OUT> accumulator = collector.accumulator();
                forEach(u -> accumulator.accept(container, u));
            }
            else {
                container = evaluate(ReduceOps.makeRef(collector));
            }
            return collector.characteristics().contains(Collector.Characteristics.IDENTITY_FINISH)
                   ? (R) container
                   : collector.finisher().apply(container);
        }
        */


        //断定型接口: Predicate<T> boolean test(T t):有参，但是返回值类型是固定的boolean
        /*
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();
         */


        //函数型接口: Function<T,R> R apply(T t)有参有返回值的抽象方法；
        //比如:  steam().map() 中参数就是Function<? super T, ? extends R>；reduce()中参数BinaryOperator<T> (ps: BinaryOperator<T> extends BiFunction<T,T,T>)
        /*
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");     // "123"
         */


        //输出 年龄>25的女程序员中名字排名前3位的姓名 javaProgrammers.stream()
        /*
          .filter((p) -> (p.getAge() > 25))
          .filter((p) -> ("female".equals(p.getGender())))
          .sorted((p, p2) -> (p.getFirstName().compareTo(p2.getFirstName())))
          .limit(3)
          //.forEach(e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary()))//涨工资
          .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));
         */

        //著作权归https://pdai.tech所有。
        //工资最高的 Java programmer Person person = javaProgrammers
        //          .stream()
        //          .max((p, p2) -> (p.getSalary() - p2.getSalary()))
        //          .get()


        //将 Java programmers 的 first name 存放到 TreeSet TreeSet<String> javaDevLastName = javaProgrammers
        //          .stream()
        //          .map(Person::getLastName)
        //          .collect(toCollection(TreeSet::new))


        //计算付给 Java programmers 的所有money int totalSalary = javaProgrammers
        //          .parallelStream()
        //          .mapToInt(p -> p.getSalary())
        //          .sum();


        //Comparator多属性排序: 先按名字不分大小写排，再按GID倒序排，最后按年龄正序排 public static void main(String[] args) {
        //	List<Person> personList = getTestList();
        //	personList.sort(Comparator.comparing(Person::getName, String.CASE_INSENSITIVE_ORDER)
        //			.thenComparing(Person::getGid, (a, b) -> b.compareTo(a))
        //			.thenComparingInt(Person::getAge));
        //	personList.stream().forEach(System.out::println);
        //}
        //
        //public static List<Person> getTestList() {
        //	return Lists.newArrayList(new Person("dai", "301", 10), new Person("dai", "303", 10),
        //			new Person("dai", "303", 8), new Person("dai", "303", 6), new Person("dai", "303", 11),
        //			new Person("dai", "302", 9), new Person("zhang", "302", 9), new Person("zhang", "301", 9),
        //			new Person("Li", "301", 8));
        //}


        //处理字符串 两个新的方法可在字符串类上使用: join和chars。第一个方法使用指定的分隔符，将任何数量的字符串连接为一个字符串。 String.join(":", "foobar", "foo", "bar");
        // => foobar:foo:bar


        //第二个方法chars从字符串所有字符创建数据流，所以你可以在这些字符上使用流式操作。 "foobar:foo:bar"
        //    .chars()
        //    .distinct()
        //    .mapToObj(c -> String.valueOf((char)c))
        //    .sorted()
        //    .collect(Collectors.joining());
        //=> :abfor


        //不仅仅是字符串，正则表达式模式串也能受益于数据流。我们可以分割任何模式串，并创建数据流来处理它们，而不是将字符串分割为单个字符的数据流，像下面这样: Pattern.compile(":")
        //    .splitAsStream("foobar:foo:bar")
        //    .filter(s -> s.contains("bar"))
        //    .sorted()
        //    .collect(Collectors.joining(":"));
        //// => bar:foobar



        //此外，正则模式串可以转换为谓词。这些谓词可以像下面那样用于过滤字符串流: Pattern pattern = Pattern.compile(".*@gmail\\.com");
        //Stream.of("bob@gmail.com", "alice@hotmail.com")
        //    .filter(pattern.asPredicate())
        //    .count();
        //// => 1
        //上面的模式串接受任何以@gmail.com结尾的字符串，并且之后用作Java8的Predicate来过滤电子邮件地址流。



        //Local Cache实现 public class TestLocalCache {
        //
        //	private static ConcurrentHashMap<Integer, Long> cache = new ConcurrentHashMap<>();
        //
        //	static long fibonacci(int i) {
        //		if (i == 0)
        //			return i;
        //
        //		if (i == 1)
        //			return 1;
        //
        //		return cache.computeIfAbsent(i, (key) -> {
        //			System.out.println("Slow calculation of " + key);
        //
        //			return fibonacci(i - 2) + fibonacci(i - 1);
        //		});
        //	}
        //
        //	public static void main(String[] args) {
        //		// warm up
        //		for (int i = 0; i < 101; i++)
        //	        System.out.println(
        //	            "f(" + i + ") = " + fibonacci(i));
        //
        //		// read -> cal
        //		long current = System.currentTimeMillis();
        //		System.out.println(fibonacci(100));
        //		System.out.println(System.currentTimeMillis()-current);
        //	}
        //}


    }


    public static void filter(List names, Predicate condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }

}
