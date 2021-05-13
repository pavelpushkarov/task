package com.task.interview.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Order {

    long id;

    long productId;

    BigDecimal price;
}
