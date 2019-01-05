package com.dokang.center;

import com.dokang.lib.keyGenerator.KeyGenerator;
import com.dokang.lib.keyGenerator.TimeFormatKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: YwT
 * @create: 2019-01-04 16:21
 **/
@Configuration
public class SpringCommonConfigure {

    @Bean
    public KeyGenerator<String> stringKeyGenerator() {
        return new TimeFormatKeyGenerator();
    }
}
