package com.example.productprice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    private Long currencyId;
    private Currencies fromCurrency;
    private Currencies toCurrency;
    private Integer exchangeRate;
}
