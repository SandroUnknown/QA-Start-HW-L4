import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class HomeWorkLesson4 {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://github.com";
        Configuration.browserSize = "800x600";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void checkJUnit5CodeOnPageTest() {
        open("/selenide/selenide");

        $("a#wiki-tab").click();

         $("input#wiki-pages-filter").setValue("SoftAssertions");
        $$("ul.filterable-active a").findBy(text("SoftAssertions")).click();

        // Такой проверки будет достаточно? Вроде как "@ExtendWith" кроме как в JUnit5 нигде более не применяется.
        $(".markdown-body").shouldHave(text("@ExtendWith"));
        // Или нужна проверка на полный текст кода? А что если его хоть чуть-чуть поменяют? - Тест уже не пройдет, но это всё-равно будет пример на JUnit5
        $(".markdown-body").shouldHave(text("""
                        @ExtendWith({SoftAssertsExtension.class})
                        class Tests {
                          @Test
                          void test() {
                            Configuration.assertionMode = SOFT;
                            open("page.html");
                        
                            $("#first").should(visible).click();
                            $("#second").should(visible).click();
                          }
                        }"""));
    }
}