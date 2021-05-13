package com.task.interview.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductOrder {

    long id;

    long productId;

    String productTitle;

    BigDecimal price;
}
