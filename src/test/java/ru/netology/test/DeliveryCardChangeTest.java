package ru.netology.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.test.DataGenerator.Registration.*;

public class DeliveryCardChangeTest {

    @Test
    void shouldSendDataForDelivery() {
        String date = getDate(4);
        String date1 = getDate(20);

        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue(getCityForCard());
        $(".calendar-input__custom-control input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $(".calendar-input__custom-control input").setValue(date);
        $("[name='name']").setValue(getNameForCard("ru"));
        $("[name='phone']").setValue(getPhoneForCard("ru"));
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

