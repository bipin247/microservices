package com.example.currencyexchange;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CurrencyController {

    List<Currency> currencyExchangeList = new ArrayList<Currency>();

    @GetMapping("/currexg/from/{fromCurrId}/to/{toCurrId}")
    public Integer getCurrencyExchange(@PathVariable Currencies fromCurrId, @PathVariable Currencies  toCurrId){

        return getCurrencyRate(fromCurrId,toCurrId);
    }

    private Integer getCurrencyRate(Currencies fromCurrId, Currencies toCurrId) {
        populateExchangeRates();
        for(Currency currency : currencyExchangeList){
            if (currency.getFromCurrency().equals(fromCurrId) &&
                currency.getToCurrency().equals(toCurrId)){
                return currency.getExchangeRate();
            }
        }

        for(Currency currency : currencyExchangeList){
            if (currency.getFromCurrency().equals(toCurrId) &&
                    currency.getToCurrency().equals(fromCurrId)){
                return currency.getExchangeRate();
            }
        }
        return null;
    }

    private void populateExchangeRates() {
        currencyExchangeList.add(new Currency(5001L,Currencies.USD, Currencies.JPY,15));
        currencyExchangeList.add(new Currency(5002L,Currencies.USD, Currencies.GBP, 10));
        currencyExchangeList.add(new Currency(5003L,Currencies.USD, Currencies.EUR, 20));
    }
}
