package com.digisky.liuwei2.tinyexample.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.digisky.liuwei2.tinyexample.util.Util.print;

/**
 * @author liuwei2
 */
public class MyWorld {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house = new House();
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Basco the dog", house));
        animals.add(new Animal("Ralph the hamster", house));
        animals.add(new Animal("Molly the cat", house));

        print("animals:\n" + animals);
        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buf1);
        o1.writeObject(animals);
        o1.writeObject(animals);

        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animals);

        ObjectInputStream in1 = new ObjectInputStream(new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(new ByteArrayInputStream(buf2.toByteArray()));


        @SuppressWarnings({"unchecked", "RedundantSuppression"}) List<Animal>
                animals1 = (List<Animal>) in1.readObject(),
                animals2 = (List<Animal>) in1.readObject(),
                animals3 = (List<Animal>) in2.readObject();

        print(animals1);
        print(animals2);
        print(animals3);

    }
}

class House implements Serializable {}

class Animal implements Serializable {
    private String name;
    private House preferredHouse;

    public Animal(String name, House preferredHouse) {
        this.name = name;
        this.preferredHouse = preferredHouse;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", preferredHouse=" + preferredHouse +
                '}';
    }
}
