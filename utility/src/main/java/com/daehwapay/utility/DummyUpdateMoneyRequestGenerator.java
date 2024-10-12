package com.daehwapay.utility;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DummyUpdateMoneyRequestGenerator {
    private static final String DECREASE_API_ENDPOINT = "http://localhost:9092/money/decrease-eda";
    private static final String INCREASE_API_ENDPOINT = "http://localhost:9092/money/increase-eda";

    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> readyMemberList = new ArrayList<>();

        while (true) {
            int amount = random.nextInt(20001) - 10000; // Random number between -100000 and 100000
            int targetMembershipId = random.nextInt(10000) + 1; // Random number between 1 and 100000

            readyMemberList.add(targetMembershipId);

            updateMemberMoneySimulator(INCREASE_API_ENDPOINT, amount, targetMembershipId);

            amount = random.nextInt(20001) - 10000; // Random number between -100000 and 100000
            Integer decreaseTargetMembershipId = readyMemberList.get(random.nextInt(readyMemberList.size()));
            updateMemberMoneySimulator(DECREASE_API_ENDPOINT, amount, decreaseTargetMembershipId);

            try {
                Thread.sleep(100); // Wait for 1 second before making the next API call
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void updateMemberMoneySimulator(String apiEndpoint, int amount, int targetMembershipId) {
        try {
            URL url = new URL(apiEndpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject jsonRequestBody = new JSONObject();
            jsonRequestBody.put("amount", amount);
            jsonRequestBody.put("targetMembershipId", targetMembershipId);

            call(apiEndpoint, conn, jsonRequestBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void call(String apiEndpoint, HttpURLConnection conn, JSONObject jsonRequestBody) throws IOException {
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(jsonRequestBody.toString().getBytes());
        outputStream.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        System.out.println("API Response from " + apiEndpoint + ": " + response.toString());
    }
}
