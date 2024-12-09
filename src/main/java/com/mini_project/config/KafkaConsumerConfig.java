package com.mini_project.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Bean
    public NewTopic createTopic(){
        return new NewTopic("product-response-topic-new", 1, (short) 1);
    }

//    // Consumer configuration for ProductRequest
//    @Bean
//    public ConsumerFactory<String, ProductRequest> productRequestConsumerFactory() {
//        Map<String, Object> config = new HashMap<>();
//        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        config.put(ConsumerConfig.GROUP_ID_CONFIG, "product-service-group-5");
//        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
//
//        JsonDeserializer<ProductRequest> jsonDeserializer = new JsonDeserializer<>(ProductRequest.class);
//        jsonDeserializer.addTrustedPackages("com.mini_project.orderservice.model.dto");
//
//
//
//        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), jsonDeserializer);
//    }
//
//    // Kafka Listener Factory for ProductRequest
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, ProductRequest> kafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, ProductRequest> factory =
//                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(productRequestConsumerFactory());
//        return factory;
//    }
//
//    // Producer configuration for ProductResponse
//    @Bean
//    public Map<String, Object> producerConfig() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//
//        return props;
//    }
//
//    // KafkaTemplate for ProductResponse
//    @Bean
//    public KafkaTemplate<String, ProductResponse> productResponseKafkaTemplate() {
//        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfig()));
//    }
}
