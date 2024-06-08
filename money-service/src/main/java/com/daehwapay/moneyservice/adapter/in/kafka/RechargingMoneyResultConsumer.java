package com.daehwapay.moneyservice.adapter.in.kafka;

import com.daehwapay.common.CountDownLatchManager;
import com.daehwapay.common.LoggingProducer;
import com.daehwapay.common.RechargingMoneyTask;
import com.daehwapay.common.SubTask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.constraints.NotNull;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
public class RechargingMoneyResultConsumer {
    private final KafkaConsumer<String, String> consumer;
    private final LoggingProducer loggingProducer;
    @NotNull
    private final CountDownLatchManager countDownLatchManager;

    public RechargingMoneyResultConsumer(@Value("${kafka.clusters.bootstrapservers}")String bootstrapServers,
                                         @Value("${task.result.topic}")String resultTopic,
                                         LoggingProducer loggingProducer,
                                         CountDownLatchManager countDownLatchManager) {
        this.loggingProducer = loggingProducer;
        this.countDownLatchManager = countDownLatchManager;

        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", "my-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(resultTopic));

        Thread consumerThread = new Thread(() -> {
            try {
                ObjectMapper mapper = new ObjectMapper();
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("Received message: " + record.key() + "/" + record.value());

                        RechargingMoneyTask task;
                        try {
                            task = mapper.readValue(record.value(), RechargingMoneyTask.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }

                        List<SubTask> subTasks = task.getSubTasks();

                        boolean taskResult = true;
                        for (SubTask subTask : subTasks) {
                            if (subTask.getStatus().equals("fail")) {
                                taskResult = false;
                                break;
                            }
                        }

                        if (taskResult) {
                            this.loggingProducer.sendMessage(task.getTaskId(), "task success");
                            this.countDownLatchManager.setDataForKey(task.getTaskId(), "success");
                        } else {
                            this.loggingProducer.sendMessage(task.getTaskId(), "task failed");
                            this.countDownLatchManager.setDataForKey(task.getTaskId(), "failed");
                        }

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                        this.countDownLatchManager.getCountDownLatch(task.getTaskId()).countDown();
                    }
                }
            } finally {
                consumer.close();
            }
        });
        consumerThread.start();
    }
}
