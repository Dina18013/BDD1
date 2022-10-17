package ru.netology.data;

import lombok.Value;

public class DataHelper {

    private DataHelper() {
    }
 //   private static final String[] cards = new String[]{"5559 0000 0000 0001", "5559 0000 0000 0002"};

 //   public static String getCard(int index) {
    //    String card = cards[index];
      //  return card;
   // }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

   // public static String getNumberOfFirstCard() {
    //   String s = "5559000000000001";
      // return s;
   // }

   // public static String getNumberOfSecondCard() {
      //  String s = "5559000000000002";
      //  return s;
   //}

    @Value
    public static class CardsInfo {
        String cardNumber;
    }
   public static CardsInfo getFirstCardNumber() {
       return new CardsInfo("5559000000000001");
   }

    public static CardsInfo getSecondCardNumber() {
        return new CardsInfo("5559000000000002");
    }
}
