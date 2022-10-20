package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.valueOf;

public class TransferPage {

    private SelenideElement codeField = $(withText("Пополнение карты"));
    private SelenideElement sumAmount = $$(".input__control[type=text]").first();
    private SelenideElement fromCard = $(".input__control[type=tel]");
    private SelenideElement addButton = $(withText("Пополнить"));

    public TransferPage() {
        codeField.shouldBe(visible);
    }

    public void transferMoney(int amount, DataHelper. CardsInfo from) {
        sumAmount.setValue(valueOf(amount));
        fromCard.setValue(String.valueOf(from));
        addButton.click();
    }

    public void errorLimit() {
        $(".notification_content").should(Condition.exactText("Ошибка"));

    }
}
