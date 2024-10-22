package com.daehwapay.moneyaggregationservice.adapter.out.dynamodb;

import com.daehwapay.moneyaggregationservice.application.port.out.InsertMoneyIncreaseByAddressPort;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

@Repository
public class DynamoDbAdapter implements InsertMoneyIncreaseByAddressPort {

    private static final String TABLE_NAME = "MoneyIncreaseByAddress";
    private static final String ACCESS_KEY = "";
    private static final String PRIVATE_KEY = "";
    private final DynamoDbClient client;

    private final MoneySumByAddressMapper moneySumByAddressMapper;

    public DynamoDbAdapter() {
        moneySumByAddressMapper = new MoneySumByAddressMapper();
        AwsBasicCredentials credentials = AwsBasicCredentials.create(ACCESS_KEY, PRIVATE_KEY);
        this.client = DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

    @Override
    public void insertMoney(String address, int moneyIncrease) {
        System.out.println("Insert money to DynamoDB");

        /**
         * PK: 강남구#summary, SK: -1, balance: 5,000
         */
        String pk = address + "#summary";
        String sk = "-1";
        MoneySumByAddress moneySumByAddress = getItem(pk, sk);

        if (moneySumByAddress == null) {
            putItem(pk, sk, moneyIncrease);
        } else {
            int balance = (int) moneySumByAddress.getBalance();
            putItem(pk, sk, balance + moneyIncrease);
        }

        /**
         * PK: 강남구#2024-10-22, SK: 5,000, balance: 5,000
         */

        String pk2 = address + LocalDate.now().toString();
        String sk2 = String.valueOf(moneyIncrease);

        putItem(pk2, sk2, moneyIncrease);
    }

    private MoneySumByAddress getItem(String pk, String sk) {
        try {
            HashMap<String, AttributeValue> attrMap = new HashMap<>();
            attrMap.put("PK", AttributeValue.builder().s(pk).build());
            attrMap.put("SK", AttributeValue.builder().s(sk).build());

            GetItemRequest request = GetItemRequest.builder()
                    .tableName(TABLE_NAME)
                    .key(attrMap)
                    .build();

            GetItemResponse response = client.getItem(request);
            if (response.hasItem()) {
                return moneySumByAddressMapper.mapToMoneySumByAddress(response.item());
            } else {
                return null;
            }

        } catch (DynamoDbException e) {
            System.err.println("Error getting an item from the table: " + e.getMessage());
        }
        return null;
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

    @QueryHandler
    public Long query(QueryMoneySumByAddress query) {
        String pk = query.getAddress() + "#summary";
        String sk = "-1";

        return Objects.requireNonNull(getItem(pk, sk)).getBalance();
    }
}
