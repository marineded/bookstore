package com.spring.henallux.bookStore.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.spring.henallux.bookStore.Model.CommandLine;
import com.spring.henallux.bookStore.Model.Promotion;

//calcule le prix total du panier
@Service
public class OrderCustomerService {

    public OrderCustomerService ()
    {}

    public double getTotalOrder (HashMap<Integer, CommandLine> cart, ArrayList<Promotion> promotions)
    {
        double totalPrice = 0;
        double price = 0;

        for (HashMap.Entry<Integer, CommandLine> commandLine : cart.entrySet()) {

            price = commandLine.getValue().getBook().getPrice();

            for (Promotion promo : promotions)
            {
                if (promo.getBook_id().getIsbn() == (int)commandLine.getValue().getBook().getIsbn())
                    price *= 1- promo.getPercentage();
            }

            totalPrice += price * commandLine.getValue().getQuantity();

        }

        totalPrice = (Math.floor(totalPrice * 100) / 100);

        return totalPrice;
    }
}