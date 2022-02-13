package com.bybit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponseSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RefreshScope
@RestController
@RequestMapping("/api/")
public class ApiController<T> {

	@Autowired
	VaultTemplate template;
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	@RequestMapping(method = RequestMethod.GET, value = "receiveBybitData", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "receiveBybitData", tags = "receiveBybitData")
	public ResponseEntity<HttpStatus> receiveBybitData(
			@RequestParam(defaultValue = "BTCUSDT") String symbol) {
		
		this.redisTemplate.convertAndSend("receive.bybit.symbol",symbol);
		
		return ResponseEntity.ok().build();
	} 

	@RequestMapping(method = RequestMethod.GET, value = "getBybitApiKey", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get ApiKey", tags = "getBybitApiKey")
	public ResponseEntity<Map<String, String>> getBybitApiKey(
			@RequestParam(defaultValue = "bybit-rest/bybit") String path) {

		VaultResponseSupport<Map> res = template.read(path, Map.class);
		System.out.println(res.getData());
		return ResponseEntity.ok(res.getData());
	}

	@RequestMapping(method = RequestMethod.POST, value = "putBybitApiKey", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "put ApiKey", tags = "putBybitApiKey")
	public ResponseEntity<Map<String, String>> postBybitApiKey(
			@RequestParam(defaultValue = "bybit-rest/bybit") String path, @RequestParam String apiKey,
			@RequestParam String secret) {

		template.write(path, Map.of("api_key", apiKey, "secret", secret));

		VaultResponseSupport<Map> res = template.read(path, Map.class);
		System.out.println(res.getData());
		return ResponseEntity.ok(res.getData());
	}

	@RequestMapping(method = RequestMethod.GET, value = "getGitToken", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "get GitToken", tags = "getGitToken")
	public ResponseEntity<Map<String, String>> getGitToken(@RequestParam(defaultValue = "bybit-rest/git") String path) {

		VaultResponseSupport<Map> res = template.read(path, Map.class);
		System.out.println(res.getData());
		return ResponseEntity.ok(res.getData());
	}

	@RequestMapping(method = RequestMethod.POST, value = "putGitToken", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "put GitToken", tags = "putGitToken")
	public ResponseEntity<Map<String, String>> putGitToken(@RequestParam(defaultValue = "bybit-rest/git") String path,
			@RequestParam String username, @RequestParam String password) {

		template.write(path, Map.of("username", username, "password", password));

		VaultResponseSupport<Map> res = template.read(path, Map.class);
		System.out.println(res.getData());
		return ResponseEntity.ok(res.getData());
	}
}
