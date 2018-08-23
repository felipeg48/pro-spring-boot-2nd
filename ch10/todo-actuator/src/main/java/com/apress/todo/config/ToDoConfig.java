package com.apress.todo.config;

import com.apress.todo.interceptor.ToDoMetricInterceptor;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
public class ToDoConfig {

    @Bean
    public MappedInterceptor metricInterceptor(MeterRegistry registry) {
        return new MappedInterceptor(new String[]{"/**"}, new ToDoMetricInterceptor(registry));
    }

    /*
    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "todo-app");
    }
    */

//    @Bean
//    MeterFilter meterFilter() {
//        return MeterFilter.denyUnless( f -> f.getName().contains("api"));
//    }
}
