package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;


import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.data.DataHelper.*;
import static ru.netology.page.DashboardPage.addToFirstCard;
import static ru.netology.page.DashboardPage.addToSecondCard;

public class TransferTest {

    @Test
    void shouldTransferMoneyFromSecondCardToFirst() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int amount = 300;
        val balanceFirstCard = dashboardPage.getFirstCardBalance();
        val balanceSecondCard = dashboardPage.getSecondCardBalance();
        val transferPage = addToFirstCard();
        transferPage.transferMoney(amount, getSecondCardNumber());
        val expectBalanceFirstCard = balanceFirstCard + amount;
        val expectBalanceSecondCard = balanceSecondCard - amount;
        assertEquals(expectBalanceFirstCard, dashboardPage.getFirstCardBalance());
        assertEquals(expectBalanceSecondCard, dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyFromFirstCardToSecond() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int amount = 300;
        val balanceFirstCard = dashboardPage.getFirstCardBalance();
        val balanceSecondCard = dashboardPage.getSecondCardBalance();
        val transferPage = addToSecondCard();
        transferPage.transferMoney(amount, getFirstCardNumber());
        val expectBalanceFirstCard = balanceFirstCard - amount;
        val expectBalanceSecondCard = balanceSecondCard + amount;
        assertEquals(expectBalanceFirstCard, dashboardPage.getFirstCardBalance());
        assertEquals(expectBalanceSecondCard, dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldNotTransferMoneyMoreLimitFromSecondCardToFirst() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int amount = 15000;
        val balanceFirstCard = dashboardPage.getFirstCardBalance();
        val balanceSecondCard = dashboardPage.getSecondCardBalance();
        val transferPage = addToFirstCard();
        transferPage.transferMoney(amount, getSecondCardNumber());
        val expectBalanceFirstCard = balanceFirstCard + amount;
        val expectBalanceSecondCard = balanceSecondCard - amount;
        assertEquals(expectBalanceFirstCard, dashboardPage.getFirstCardBalance());
        assertEquals(expectBalanceSecondCard, dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldNotTransferMoneyMoreLimitFromFirstCardToSecond() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        int amount = 15000;
        val balanceFirstCard = dashboardPage.getFirstCardBalance();
        val balanceSecondCard = dashboardPage.getSecondCardBalance();
        val transferPage = addToSecondCard();
        transferPage.transferMoney(amount, getFirstCardNumber());
        val expectBalanceFirstCard = balanceFirstCard - amount;
        val expectBalanceSecondCard = balanceSecondCard + amount;
        assertEquals(expectBalanceFirstCard, dashboardPage.getFirstCardBalance());
        assertEquals(expectBalanceSecondCard, dashboardPage.getSecondCardBalance());
    }
}


