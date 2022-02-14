package com.bybit.config;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

@Service
public class RedisMessageSubscriber implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisMessageSubscriber.class);

	Jackson2JsonRedisSerializer<Map<String, String>> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Map.class);
	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		String topic = new String(pattern);
		LOGGER.info(topic);
		
		Map<String, String> m = jackson2JsonRedisSerializer.deserialize(message.getBody());
		String symbol = m.get("symbol");
		LOGGER.info(symbol);
	}
}
