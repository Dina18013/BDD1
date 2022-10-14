package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;


import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Selenide.*;

public class TransferTest{

    @Test
    void shouldTransferMoneyFromFirstCardToSecond() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCard = dashboardPage.getFirstCardBalance();
        val balanceSecondCard = dashboardPage.getSecondCardBalance();
        Integer transfer = 300;
        //выбираем первую карту
        val transferPage = dashboardPage.addToFirstCard();
        transferPage.addToFirstCard(transfer);
        val expectBalanceFirstCard = balanceFirstCard + transfer;
        val expectBalanceSecondCard = balanceSecondCard - transfer;
        assertEquals(expectBalanceFirstCard, dashboardPage.getFirstCardBalance());
        assertEquals(expectBalanceSecondCard, dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldTransferMoneyFromSecondCardToFirst() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCard = dashboardPage.getFirstCardBalance();
        val balanceSecondCard = dashboardPage.getSecondCardBalance();
        Integer transfer = 300;
        val transferPage = dashboardPage.addToSecondCard();
        transferPage.addToSecondCard(transfer);
        val expectBalanceFirstCard = balanceFirstCard - transfer;
        val expectBalanceSecondCard = balanceSecondCard + transfer;
        assertEquals(expectBalanceFirstCard, dashboardPage.getFirstCardBalance());
        assertEquals(expectBalanceSecondCard, dashboardPage.getSecondCardBalance());
    }


    @Test
    void shouldNotTransferMoreLimitFromFirstToSecond() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCard = dashboardPage.getFirstCardBalance();
        val balanceSecondCard = dashboardPage.getSecondCardBalance();
        Integer transfer = 15000;
        val transferPage = dashboardPage.addToFirstCard();
        transferPage.addToFirstCard(transfer);
        val expectBalanceFirstCard = balanceFirstCard;
        val expectBalanceSecondCard = balanceSecondCard;
        assertEquals(expectBalanceFirstCard, dashboardPage.getFirstCardBalance());
        assertEquals(expectBalanceSecondCard, dashboardPage.getSecondCardBalance());
    }

    @Test
    void shouldNotTransferMoreLimitFromSecondToFirst() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        val balanceFirstCard = dashboardPage.getFirstCardBalance();
        val balanceSecondCard = dashboardPage.getSecondCardBalance();
        Integer transfer = 15000;
        val transferPage = dashboardPage.addToSecondCard();
        transferPage.addToSecondCard(transfer);
        val expectBalanceFirstCard = balanceFirstCard;
        val expectBalanceSecondCard = balanceSecondCard;
        assertEquals(expectBalanceFirstCard, dashboardPage.getFirstCardBalance());
        assertEquals(expectBalanceSecondCard, dashboardPage.getSecondCardBalance());
    }
}