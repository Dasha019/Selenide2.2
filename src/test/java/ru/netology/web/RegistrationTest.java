package ru.netology.web;

import org.junit.jupiter.api.Test;

import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
        @Test
        public void shouldBeSuccesComleted () {
            open("http://localhost:9999");
            $("[data-test-id='city'] input").setValue("Тюмень");
            String currentDate = generateDate(3,"dd.MM.yyyy");
            $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
            $("[data-test-id='date'] input").sendKeys(currentDate);
            $("[data-test-id='name'] input").setValue("Южакова Дарья");
            $("[data-test-id='phone'] input").setValue("+79224725363");
            $("[data-test-id='agreement']").click();
            $("button.button").click();
            $("[data-test-id='notification']")
                    .shouldBe(Condition.visible, Duration.ofSeconds(20))
                    .shouldHave(Condition.exactText("Успешно! Встреча успешно забронирована на " + currentDate));
        }
    }

