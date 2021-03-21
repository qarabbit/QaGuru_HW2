import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationForm {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void FillWithRequiredData() {
        open("https://demoqa.com/automation-practice-form");
        //Ручным тестированием было выявлено, что обязательны для
        //заполнения поля Имя, Фамилия, Пол и Номер телефона
        $("#firstName").setValue("Ekaterina");
        $("#lastName").setValue("Sherova");
        $(byText("Female")).click();
        $("#userNumber").setValue("0000000000");

        //Подтверждение отправки
        $("#submit").click();

        //Проверка полей после отправки
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(byText("Ekaterina" + " " + "Sherova")).shouldBe(visible);
        $(byText("Female")).shouldBe(visible);
        $(".modal-content").shouldHave(text("0000000000"));
    }

    @Test
    void FillWithAllData() {
        open("https://demoqa.com/automation-practice-form");
        //Ввод имени, фамилии, пола, телефона, email
        $("#firstName").setValue("Ekaterina");
        $("#lastName").setValue("Sherova");

        $(byText("Female")).click();

        $("#userNumber").setValue("0000000000");
        $("#userEmail").setValue("qarabbit19@gmail.com");

        //Ввод предмета изучения
        $("#subjectsContainer").click();
        $("#subjectsInput").setValue("History").pressEnter();

        //Выбор хобби, картинки, адреса
        $(byText("Reading")).click();
        $("#uploadPicture").uploadFromClasspath("rabbit.jpeg");
        $("#currentAddress").setValue("Saint Petersburg");

        //Выбор даты рождения с помощью виджета
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOptionByValue("1996");
        $(".react-datepicker__day--001").click();

        //Выбор штата и города через дропдаун
        $("#state").click();
        $("#react-select-3-input").setValue("NCR").pressEnter();
        $("#city").click();
        $("#react-select-4-input").setValue("Delhi").pressEnter();

        //Подтверждение отправки
        $("#submit").click();

        //Проверка полей после отправки
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(byText("Ekaterina" + " " + "Sherova")).shouldBe(visible);
        $(byText("Female")).shouldBe(visible);
        $(".modal-content").shouldHave(text("qarabbit19@gmail.com"),
                text("0000000000"), text("01 November,1996"), text("History"),
                text("Reading"), text("Saint Petersburg"), text("NCR Delhi"), text("rabbit.jpeg"));
    }
}
