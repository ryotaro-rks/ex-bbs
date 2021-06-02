package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bbs")
public class PushGoodApiController {
	@RequestMapping("/pushGood")
	public Map<String, Integer> pushGood(Integer goodNumbers) {
		Map<String, Integer> goodNumbersMap = new HashMap<>();
		goodNumbersMap.put("goodNumbers", goodNumbers + 1);
		return goodNumbersMap;
	};
}
