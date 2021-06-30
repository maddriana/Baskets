package com.Adriana;


import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();
    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 1.00);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10,7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.10,2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 61.50,200);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50,2);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95,4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.34,36);
        stockList.addStock(temp);

        temp = new StockItem("vase", 2.40,35);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 13);
        stockList.addStock(temp);

        System.out.println(stockList);



        Basket myBasket = new Basket("Adriana's basket");
        addToBasket(myBasket, "car", 1 );
        addToBasket(myBasket, "car", 1 );
        addToBasket(myBasket, "tape", 1 );
        addToBasket(myBasket,  "cup", 3);
        addToBasket(myBasket, "door", 1);
        System.out.println(myBasket);
        System.out.println("----------------------------------\n");
        System.out.println(stockList);
        System.out.println("----------------------------------\n");
        CheckOut(myBasket);
        System.out.println("----------------------------------\n");
        System.out.println(stockList);
        System.out.println("----------------------------------\n");
        System.out.println(myBasket);
    }

    public static void addToBasket(Basket basket, String item, int quantity){
        StockItem stockItem = stockList.get(item);
        if(stockItem==null){
            System.out.println("We don't sell "+ item);
            //return 0;
        }
        if(stockList.reserveStock(stockItem, quantity)){
            System.out.println("Reserved");
            basket.addToBasket(stockItem, quantity);
            //return quantity;
        }

        //return 0;
    }

    public static void removeItem(Basket basket, String item){
        StockItem newItem = stockList.get(item);
        if(newItem ==null){
            System.out.println("No such item");
            return;
        }
        else if (basket.removeFromBasket(newItem)){
            System.out.println("Item removed");
            stockList.clean(newItem);
            return;
        }else {
            System.out.println("No such item in the basket");
        }
    }

    public static void CheckOut(Basket basket){
           double payment= basket.getPayment();
           String name=null;
        for (Map.Entry<StockItem, Integer> item : basket.Items().entrySet()) {
            name=item.getKey().getName();
            stockList.sellStock(name, item.getValue());
            payment += item.getKey().getPrice() * item.getValue();
        }
        basket.cleanBasket();
            System.out.println("Basket is empty. Your total is: " + payment);
    }
}
