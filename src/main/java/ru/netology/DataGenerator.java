package ru.netology;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        public static String nameForCard(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new String(
                    faker.name().lastName() + " " + faker.name().firstName()
            );
        }

        public static String phoneForCard(String locale) {
            Faker faker = new Faker(new Locale("ru"));
            return new String(
                    faker.phoneNumber().phoneNumber()
            );
        }

        public static String cityForCard(String ru) {
            Faker faker = new Faker(new Locale("ru"));
            return new String(
                    faker.address().city()
            );
        }


        private Registration() {
        }

    }
}
