package ru.netology.test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import ru.netology.utils.DataGenerator;
import java.time.Duration;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class ChangeDateTest {

    @BeforeEach
    void setUP() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999/");
    }

    @AfterEach
    void tearDown() {
        closeWindow();
    }

    @Test
    void shouldSuccessPlanAndReplanMeeting() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=city] input")
                .setValue(DataGenerator.generateCity());
        form.$("[data-test-id=date] input")
                .doubleClick()
                .sendKeys(DataGenerator.generateDate(3));
        form.$("[data-test-id=name] input")
                .setValue(DataGenerator.generateName("ru"));
        form.$("[data-test-id=phone] input")
                .setValue(DataGenerator.generatePhone("ru"));
        form.$("[data-test-id=agreement]")
                .click();
        form.$(".button")
                .click();
        $(".notification_status_ok")
                .shouldBe(visible);
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + DataGenerator.generateDate(3)));
        form.$("[data-test-id=date] input")
                .doubleClick().
                sendKeys(DataGenerator.generateDate(7));
        form.$(".button")
                .click();
        $("[data-test-id=replan-notification]")
                .waitUntil(visible, 50000);
        $(withText("Перепланировать"))
                .click();
        $(".notification_status_ok")
                .shouldBe(visible);
        $(".notification__content")
                .shouldHave(text("Встреча успешно запланирована на " + DataGenerator.generateDate(7)), Duration.ofSeconds(10))
                .shouldBe(Condition.visible);
    }
}