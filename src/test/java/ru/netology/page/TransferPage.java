package ru.netology.page;

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

   // public DashboardPage addToFirstCard(Integer transfer) {
   //     amount.setValue(transfer.toString());
     //   fromCard.setValue(DataHelper.getNumberOfSecondCard());
    //    addButton.click();
   //     return new DashboardPage();
   // }

   // public DashboardPage addToSecondCard(Integer transfer) {
   //     amount.setValue(transfer.toString());
   //     fromCard.setValue(DataHelper.getNumberOfFirstCard());
   //     addButton.click();
   //     return new DashboardPage();
   // }

    public void transferMoney(int amount, DataHelper. CardsInfo from) {
        sumAmount.setValue(valueOf(amount));
        fromCard.setValue(valueOf(from));
        addButton.click();
        new DashboardPage();
    }
}
