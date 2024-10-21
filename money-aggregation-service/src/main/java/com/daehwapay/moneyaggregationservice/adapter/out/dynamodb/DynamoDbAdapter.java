package com.daehwapay.moneyaggregationservice.adapter.out.dynamodb;

import com.daehwapay.moneyaggregationservice.application.port.out.InsertMoneyIncreaseByAddressPort;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;

@Repository
public class DynamoDbAdapter implements InsertMoneyIncreaseByAddressPort {

    private static final String TABLE_NAME = "MoneyIncreaseByAddress";
    private static final String ACCESS_KEY = "";
    private static final String PRIVATE_KEY = "";
    private final DynamoDbClient client;

    public DynamoDbAdapter() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(ACCESS_KEY, PRIVATE_KEY);
        this.client = DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

    @Override
    public void insertMoney(String address, int moneyIncrease) {
        System.out.println("Insert money to DynamoDB");

        String pk = address + "#summary";
        String sk = "-1";
        putItem(pk, sk, moneyIncrease);
    }

    private void putItem(String pk, String sk, int balance) {
        try {
            String balanceStr = String.valueOf(balance);
            HashMap<String, AttributeValue> attrMap = new HashMap<>();
            attrMap.put("PK", AttributeValue.builder().s(pk).build());
            attrMap.put("SK", AttributeValue.builder().s(sk).build());
            attrMap.put("balance", AttributeValue.builder().n(balanceStr).build());

            PutItemRequest request = PutItemRequest.builder()
                    .tableName(TABLE_NAME)
                    .item(attrMap)
                    .build();

            client.putItem(request);
        } catch (DynamoDbException e) {
            System.err.println("Error adding an item to the table: " + e.getMessage());
        }
    }
}
