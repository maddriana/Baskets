package com.Adriana;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StockList {
   private final Map<String, StockItem> list;

    public StockList() {
        this.list = new HashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) {
            StockItem inStock = list.getOrDefault(item.getName(), item);
            if(inStock!=item){
                item.adjustStock(inStock.getQuantityInStock());
            }
            list.put(item.getName(), item);
            return item.getQuantityInStock();
        }
        return 0;
    }

    public boolean reserveStock(StockItem item, int quantity) {

        if (item != null) {

            StockItem inStock = list.getOrDefault(item.getName(), null);

            if ((inStock != null) && inStock.getQuantityInStock() >= quantity && quantity > 0) {

                return  inStock.setReserved(quantity);

            }
            return false;
        }
        return false;
    }

    public int sellStock(String item, int quantity){
        StockItem inStock = list.getOrDefault(item, null);
        if((inStock!=null) && inStock.getQuantityInStock() >= quantity && quantity>0){
            inStock.adjustStock(-quantity);
            inStock.setReserved(-quantity);
            return quantity;
        }
        return 0;
    }

     public StockItem get(String key){
        return list.get(key);
     }

     public Map<String, StockItem> Items(){
        return Collections.unmodifiableMap(list);
     }

     public void clean(StockItem item){
        list.get(item.getName()).cleanReserved();
     }

    @Override
    public String toString() {
        String s="\n Stock List \n";
        double totalCost =0.0;
        for(Map.Entry<String, StockItem> item: list.entrySet()){
            StockItem stockItem= item.getValue();
            double itemValue = stockItem.getPrice()*stockItem.getQuantityInStock();
            s= s + stockItem + ". There are "+ stockItem.getQuantityInStock() + " in stock. Value of items: "+ String.format("%.02f", itemValue) + ". Reserved: " + stockItem.getReserved();
            s =s+"\n";
            totalCost+=itemValue;
        }
        return s+ "Total cost value:" + String.format("%.02f", totalCost);
    }
}
