package com.example.web.processor;

import com.example.web.util.DESedeUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.OriginTrackedMapPropertySource;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

public class SafetyEncryptProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        for (PropertySource<?> propertySource : environment.getPropertySources()) {
            System.out.println("propertySource = " + propertySource);
            if(propertySource instanceof OriginTrackedMapPropertySource){
                OriginTrackedMapPropertySource source = (OriginTrackedMapPropertySource) propertySource;
                for (String propertyName : source.getPropertyNames()) {
                    //System.out.println(propertyName + "=" + source.getProperty(propertyName));
                    if("spring.datasource.password".equals(propertyName)){
                        Map<String,Object> map = new HashMap<>();
                        // 做解密处理
                        String property = (String) source.getProperty(propertyName);
                        String s = DESedeUtil.decode3DES(DESedeUtil.KEY, property);
                        System.out.println("密文：" + property);
                        System.out.println("解密后的：" + s);
                        map.put(propertyName,s);

                        // 注意要添加到前面，覆盖
                        environment.getPropertySources().addFirst(new MapPropertySource(propertyName,map));
                    }
                }
            }
        }
    }
}