package com.budget.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "budget.prop")
@Data
public class BudgetProperties {

    private boolean initUsersData;
    
    private boolean initCategoriesData;
}
