package com.bybit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RefreshScope
@RestController
@RequestMapping("/api/")
public class ApiController<T> {
	
	@Value("${bybit.api.token}")
	String bybitApiToken;
	
	
    @RequestMapping(method = RequestMethod.GET, value = "getSample", 
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Test Sample", tags = "getSample")
    public ResponseEntity<Map<String, String>> sample(@RequestParam String param) {
    	System.out.println("bybitApiToken : " + bybitApiToken);
        return ResponseEntity.ok(Map.of("param",param));
    }
}
