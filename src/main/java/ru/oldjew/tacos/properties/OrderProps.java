package ru.oldjew.tacos.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties(prefix = "taco.orders")
@Data
@Validated
public class OrderProps {

    int pageSize = 20;

    @Override
    public String toString(){
        return "Page size = " + pageSize;
    }

}
