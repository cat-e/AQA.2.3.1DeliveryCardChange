package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static class Registration {
        private Registration() {
        }

        public static String getDate(int shift) {
            return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }

        public static String getNameForCard(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return faker.name().lastName() + " " + faker.name().firstName();
        }

        public static String getPhoneForCard(String locale) {
            Faker faker = new Faker(new Locale(locale));
            return faker.phoneNumber().phoneNumber();
        }

        public static String getCityForCard() {
            String[] cities = {"Москва", "Казань", "Волгоград", "Санкт-Петербург", "Ростов-на-Дону", "Нижний Новгород"};
            Random random = new Random();
            int index = random.nextInt(cities.length);
            return cities[index];
        }




    }
}
