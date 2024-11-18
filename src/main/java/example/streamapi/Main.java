package example.streamapi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        example0();
        System.out.println("------ example1: ------------");
        example1();
        example2();
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

    private static List<Rectangle> filterRectangles(Collection<Rectangle> rectangles, Checker<Rectangle> checker) {
        List<Rectangle> smallRectangles = new ArrayList<>();
        for (Rectangle r : rectangles)
            if (checker.check(r))
                smallRectangles.add(r);
        return smallRectangles;
    }
}
