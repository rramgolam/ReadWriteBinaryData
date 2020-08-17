package com.company.readwritebinary;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        // Inner class for a Person
        class Person {
            private String name;
            private int age;
            private char sex;

            public Person(String name, int age, char sex) {
                this.name = name;
                this.age = age;
                this.sex = sex;
            }
        }

	    // Writing Data
        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("data.dat")))) {

            locFile.writeUTF("Jeff");
            locFile.writeInt(79);
            locFile.writeChar('M');

            locFile.writeUTF("Billie");
            locFile.writeInt(60);
            locFile.writeChar('F');

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading Data
        String name = null;
        int age = 0;
        char sex = 0;
        List<Person> people = new ArrayList<>();


        try (DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("data.dat")))) {

            boolean eof = false;
            try {
                while(!eof) {
                    name = locFile.readUTF();
                    age = locFile.readInt();
                    sex = locFile.readChar();
                    people.add(new Person(name,age,sex));
                }
            } catch (EOFException e) {
                eof = true;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Person person : people) {
            System.out.println("Name : " + person.name + " age: " + person.age + " sex: " + person.sex);

        }

    }
}
