package com.Adriana;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
    private final String name;
    private  TreeMap<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();
    }

    public int addToBasket(StockItem item, int quantity) {
        if (item != null && quantity > 0) {
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    public boolean removeFromBasket(StockItem item) {
        if (item != null) {
            int inBasket = list.getOrDefault(item, 0);
            if (inBasket != 0) {
                list.remove(item);
                return true;
            } else return false;
        }
        return false;
    }


    public Map<StockItem, Integer> Items() {
        return list;
    }

    @Override
    public String toString() {
        String s = "\nShopping basket \n" + name + " contains " + list.size() + ((list.size() == 1) ? " item" : " items") + "\n";
        double totalCost = 0.00;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {
            s = s + item.getKey() + ". " + item.getValue() + " purchased. \n";
            totalCost += item.getKey().getPrice() * item.getValue();

        }
        return s;
    }

    public double getPayment() {
        double payment = 0.00;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()) {

            payment += item.getKey().getPrice() * item.getValue();
        }
        return payment;
    }

    public void cleanBasket(){
        this.list.clear();

        }
    }
