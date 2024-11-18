package example.streamapi;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
       // example0();
       // System.out.println("------ example1: ------------");
       // example1();
      //  example2();
      //  exampleHuman();
        exampleHuman2();
    }

    private static void exampleHuman2() {
        List<Human> humans= List.of(new Human("Иванов", 19, "программист")
                ,new Human("Петров", 17, "школьник")
                ,new Human("Сидоров", 39, "препод")
                ,new Human("Баранов", 29, "программист")
                ,new Human("Петров",17,"школьник")
                ,new Human("Григорьева", 39, "журналист"));
        //humans.stream().sorted().forEach(System.out::println); //Обязательно нужно для элементов потока реализовать интерфейс Comparable
        humans.stream().sorted(Comparator.comparingInt(Human::getAge)).forEach(System.out::println);

        System.out.println("------------------------------------");
        humans.stream().distinct().forEach(System.out::println);
        Set<Human> set = new LinkedHashSet<>(humans);
        System.out.println("set = " + set);
        System.out.println("-------------------------------------");
        List<String> professions= humans.stream().distinct().map(Human::getProfession).sorted().toList();
        System.out.println("professions = " + professions);

        humans.stream()
                .distinct()
                .peek(human -> System.out.println("очередной человек = " + human))      //обычно для логирования и отладки
                .filter(h->h.getAge()<20)
                .peek(human -> System.out.println("фильтрованный человек = " + human))      
                .sorted()
                .forEach(System.out::println);

        //Количество уникальных объектов
        int kolvo = (int) humans.stream().distinct().count();
        System.out.println("kolvo = " + kolvo);

        //Возраст самого старого человечка
        OptionalInt a = humans.stream().distinct().mapToInt(Human::getAge).max();
        if (a.isPresent())
            System.out.println("максимальный возраст "+a.getAsInt());
        //Профессия самого старого человека
        Optional<Human> starik = humans.stream().distinct().max(Comparator.comparingInt(Human::getAge));
        System.out.println("профессия старика = " + starik.get().getProfession());

        Optional<Human> starik2 = humans.stream().sorted().max(Comparator.comparingInt(Human::getAge));
        System.out.println("профессия старика2 = " + starik2.get().getProfession());

        //Профессии всех людей с максимальным возрастом

    }

    private static void example2() {
        Stream<Rectangle> recstream = Stream.of(new Rectangle(4,3), new Rectangle(3,4), new Rectangle(3,4), new Rectangle(5, 6));
        List<Rectangle> rectangleList = recstream.toList();
        System.out.println("rectangleList = " + rectangleList);
        recstream.forEach(rectangle -> System.out.println("rectangle = " + rectangle)); //здесь будет Исключение: поток уже терминировался
        rectangleList.forEach(rectangle -> System.out.println("rectangle = " + rectangle));
    }

    private static void example1() {
        List<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle(3, 4));
        rectangles.add(new Rectangle(13, 2));
        rectangles.add(new Rectangle(8, 9));
        rectangles.add(new Rectangle(1, 9));

        rectangles.stream().filter(r -> r.getPerimeter() <= 30).forEach(r -> System.out.println("r = " + r));
        System.out.println("меньше 10 по габаритам:");
        rectangles.stream()
                    .filter(r -> r.getH() < 10 && r.getW() < 10)
                    .forEach(r -> System.out.println("r = " + r));
    }

    private static void example0() {
        List<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle(3, 4));
        rectangles.add(new Rectangle(13, 2));
        rectangles.add(new Rectangle(8, 9));
        rectangles.add(new Rectangle(1, 9));

        List<Rectangle> smallRectangles = filterRectangles(rectangles, r -> r.getPerimeter() <= 30);
        System.out.println("smallRectangles = " + smallRectangles);

        for (Rectangle r : smallRectangles) {
            System.out.println("r = " + r);
        }

        //сдедлать список прямоугольников, габариты которых не превышают 10
        List<Rectangle> less10Rectangles = filterRectangles(rectangles, r -> r.getH() < 10 && r.getW() < 10);
        System.out.println("less10Rectangles = " + less10Rectangles);
        for (Rectangle r : smallRectangles) {
            System.out.println("r = " + r);
        }

    }

    public static void exampleHuman(){
        List<Human> humans= List.of(new Human("Иванов", 19, "программист")
        ,new Human("Петров", 17, "школьник")
        ,new Human("Сидоров", 39, "препод"));
        List<Human> oldNonProgrammers =humans.stream()
                .filter(h -> h.getAge()>18) //отфильтровать тех, у кого возраст > 18
                .filter(h -> !h.getProfession().equals("программист"))//из того, что получилось, отфильтровать тех, у кого профессия не "программист"
                .toList();          //собрать то, что получилось, в список

        System.out.println("oldNonProgrammers = " + oldNonProgrammers);
        oldNonProgrammers.getFirst().setAge( oldNonProgrammers.getFirst().getAge()+1);
        System.out.println("oldNonProgrammers = " + oldNonProgrammers);
        
      //  oldNonProgrammers.add(new Human("Алоев", 15, "сварщик"));
        System.out.println("oldNonProgrammers = " + oldNonProgrammers.getClass());
        //oldNonProgrammers.sort(Comparator.comparing(Human::getName));
        List<Human> myStudents =new  ArrayList<>(oldNonProgrammers);
        myStudents.add(new Human("Алоев", 15, "школьник"));
        System.out.println("myStudents = " + myStudents);
    }

    private static List<Rectangle> filterRectangles(Collection<Rectangle> rectangles, Checker<Rectangle> checker) {
        List<Rectangle> smallRectangles = new ArrayList<>();
        for (Rectangle r : rectangles)
            if (checker.check(r))
                smallRectangles.add(r);
        return smallRectangles;
    }
}
