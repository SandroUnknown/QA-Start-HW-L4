import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class HomeWorkLesson4 {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "800x600";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void checkJUnit5CodeOnPage() {
        open("https://github.com/selenide/selenide");

        $("a#wiki-tab").click();

         $("input#wiki-pages-filter").setValue("SoftAssertions");
        $$("ul.filterable-active a").findBy(text("SoftAssertions")).click();

        // Какая из проверок правильней?
        $("div.markdown-body").shouldHave(text("3. Using JUnit5 extend test class:"));                          //нахожу "общий" div и в нем ищу по тексту
        $$("h4.heading-element").shouldHave(itemWithText("3. Using JUnit5 extend test class:"));    //нахожу коллекцию h4 с классом heading-element и ищу в ней элемент по тексту
        $$(".heading-element").shouldHave(itemWithText("3. Using JUnit5 extend test class:"));      //нахожу коллекцию элементов с классом heading-element (без жесткого указания искать среди h4) и ищу в ней элемент по тексту
    }
}
