package br.com.euandresimoes.auth_service.shared.utils;

import java.util.Random;

public class DataGenerator {

    public static String genUsername() {
        String username = "user";
        Random rdm = new Random();

        for (int i = 0; i < 11; i++) {
            username += rdm.nextInt(10);
        }

        return username;
    }

}