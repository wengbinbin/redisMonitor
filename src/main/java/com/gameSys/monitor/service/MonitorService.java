package com.gameSys.monitor.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameSys.monitor.pojo.OnlineMachineInfo;
import com.google.common.base.Optional;

@Service("monitorService")
public class MonitorService {
    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private StringRedisTemplate redisTemplate;

    public Optional<OnlineMachineInfo> get(String machinecode) throws IOException {
    	ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
    	String key = "machinecode:"+machinecode;
        String onlineMachineInfoAsJson = valueOps.get(key);

        if (onlineMachineInfoAsJson != null) {
            return Optional.of(MAPPER.readValue(onlineMachineInfoAsJson, OnlineMachineInfo.class));
        } else {
            return Optional.absent();
        }
    }

    public void add(String machinecode, OnlineMachineInfo onlineMachineInfo) throws IOException {
    	ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
    	String key = "machinecode:"+machinecode;
        String onlineMachineInfoAsJson = MAPPER.writeValueAsString(onlineMachineInfo);
        
        valueOps.set(key, onlineMachineInfoAsJson, 100, TimeUnit.SECONDS);
        
    }
    public Optional< List<OnlineMachineInfo> > getAll() throws IOException{
    	Set<String> keys = redisTemplate.keys(new String("machinecode:*"));
    	ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
    	List<String> onlineMachineInfoAsJson = valueOps.multiGet(keys);
    	
    	if(!onlineMachineInfoAsJson.isEmpty()){
    		List<OnlineMachineInfo> result = new ArrayList<OnlineMachineInfo>();
    		for (String onlineMachineInfo : onlineMachineInfoAsJson) {
				result.add(MAPPER.readValue(onlineMachineInfo, OnlineMachineInfo.class));
			}
    		return Optional.of(result);
    	}else{
    		return Optional.absent();
    	}
    	
    }
}
