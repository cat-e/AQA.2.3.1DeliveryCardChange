package ru.netology.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardChangeTest {
    @Test
    void shouldSendDataForDelivery() {
        String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String date1 = LocalDate.now().plusDays(20).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Самара");
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input__custom-control input").setValue(date);
        $("[name='name']").sendKeys("Василий Кот");
        $("[name='phone']").sendKeys("+79171249001");
        $("[data-test-id=agreement] .checkbox__box").click();
        $("button.button").click();
        $("[data-test-id=success-notification]").shouldHave(text("Встреча успешно запланирована на " + date));
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input__custom-control input").setValue(date1);
        $("button.button").click();
        $("[data-test-id=replan-notification]").shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $("button").shouldHave(text("Перепланировать")).click();
        $("[data-test-id=success-notification]").shouldHave(text("Встреча успешно запланирована на " + date1));


    }
}

