package ru.netology.utils;

import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataGenerator {
    private DataGenerator() {}

    public static String generateCity() {
        List<String> sitylist = Arrays.asList(
                "Москва",
                "Санкт-Петербург",
                "Калуга",
                "Великий Новгород",
                "Казань",
                "Салехард",
                "Владивосток",
                "Астрахань",
                "Калининград",
                "Рязань",
                "Самара",
                "Краснодар",
                "Саратов",
                "Архангельск",
                "Хабаровск",
                "Вологда",
                "Владикавказ",
                "Владимир",
                "Барнаул",
                "Махачкала",
                "Саранск"
        );
        Random rand = new Random();
        String city = sitylist.get(rand.nextInt(sitylist.size()));
        return city;
    }

    public static String generateDate(int days) {
        LocalDate date = LocalDate.now().plusDays(days);
        String newDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return newDate;
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String name = faker.name().fullName();
        return name;
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.numerify("+7##########");
        return phone;
    }
}