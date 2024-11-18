package example.streamapi;

import java.util.Objects;

public class Human implements Comparable<Human>{
    String name;
    int age;
    String profession;

    public Human(String name, int age, String profession) {
        this.name = name;
        this.age = age;
        this.profession = profession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", profession='" + profession + '\'' +
                '}';
    }

//    public int compare(Human other)
//    {
//        return name.compareTo(other.name);
//    }

  //  @Override
    public int compareTo(Human other) {
        return name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age && Objects.equals(name, human.name) && Objects.equals(profession, human.profession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, profession);
    }
}
