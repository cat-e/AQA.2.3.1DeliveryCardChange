package ru.netology.test;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardChangeTest {
    private Faker faker;
    @BeforeEach
    void setUpAll() {
        faker = new Faker(new Locale("ru"));
    }


    @Test
    void shouldSendDataForDelivery() {
        String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String date1 = LocalDate.now().plusDays(20).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String name = faker.name().fullName();
        String phone = faker.phoneNumber().phoneNumber();
        String city = faker.address().city();
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue(city);
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input__custom-control input").setValue(date);
        $("[name='name']").setValue(name);
        $("[name='phone']").setValue(phone);
        $("[data-test-id=agreement] .checkbox__box").click();
        $("button.button").click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000).shouldHave(text("Встреча успешно запланирована на " + date));
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input__custom-control input").setValue(date1);
        $("button.button").click();
        $("[data-test-id=replan-notification]").shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("[data-test-id=replan-notification] button.button").click();
        $("[data-test-id=success-notification]").waitUntil(visible, 15000).shouldHave(text("Встреча успешно запланирована на " + date1));


    }
}

