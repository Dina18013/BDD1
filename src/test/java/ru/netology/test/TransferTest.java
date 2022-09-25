package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.getFirstCardNumber;
import static ru.netology.data.DataHelper.getSecondCardNumber;
import static ru.netology.page.DashboardPage.pushFirstCardButton;
import static ru.netology.page.DashboardPage.pushSecondCardButton;

public class TransferTest {

   // DashboardPage dashboardPage;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
       // verificationPage.validVerify(verificationCode);
       // dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldTransferFromFirstToSecond() {
        int amount = 3000;
        val dashboardPage = new DashboardPage();
        val firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        val secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        val transferPage = pushSecondCardButton();
        transferPage.transferMoney(amount, getFirstCardNumber());
        val firstCardBalanceFinish = firstCardBalanceStart - amount;
        val secondCardBalanceFinish = secondCardBalanceStart + amount;
        assertEquals(firstCardBalanceFinish, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardBalanceFinish, dashboardPage.getSecondCardBalance());
    }

    @Test
    public void shouldTransferFromSecondToFirst() {
        int amount = 3000;
        val dashboardPage = new DashboardPage();
        val firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        val secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        val transferPage = pushFirstCardButton();
        transferPage.transferMoney(amount, getSecondCardNumber());
        val firstCardBalanceFinish = firstCardBalanceStart + amount;
        val secondCardBalanceFinish = secondCardBalanceStart - amount;
        assertEquals(firstCardBalanceFinish, dashboardPage.getFirstCardBalance());
        assertEquals(secondCardBalanceFinish, dashboardPage.getSecondCardBalance());
    }

    @Test
    public void shouldTransferMoneyMoreBalance() {
        int amount = 15000;
        val dashboardPage = new DashboardPage();
        val firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        val secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        val transferPage = pushSecondCardButton();
        transferPage.transferMoney(amount, getFirstCardNumber());
        transferPage.errorLimit();
    }

    @Test
    public void shouldTransferFromFirstToFirstCard() {
        int amount = 2000;
        val dashboardPage = new DashboardPage();
        val firstCardBalanceStart = dashboardPage.getFirstCardBalance();
        val secondCardBalanceStart = dashboardPage.getSecondCardBalance();
        val transferPage = pushFirstCardButton();
        transferPage.transferMoney(amount, getFirstCardNumber());
        transferPage.invalidCard();
    }
}

