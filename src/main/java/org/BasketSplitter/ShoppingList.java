package org.BasketSplitter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShoppingList {
    private JsonObject configDelivery;

    //delivery result map
    private Map<String, ArrayList<String>> map = new HashMap<>();

    //map for checking most used delivery
    private Map<String, Integer> ranking = new HashMap<>();

    public ShoppingList(JsonObject configDelivery) {
        this.configDelivery = configDelivery; //config file

        //adding map delivery values
        map.put("Pick-up point",new ArrayList<>());
        ranking.put("Pick-up point",0);
        map.put("Parcel locker",new ArrayList<>());
        ranking.put("Parcel locker",0);
        map.put("In-store pick-up",new ArrayList<>());
        ranking.put("In-store pick-up",0);
        map.put("Same day delivery",new ArrayList<>());
        ranking.put("Same day delivery",0);
        map.put("Next day shipping",new ArrayList<>());
        ranking.put("Next day shipping",0);
        map.put("Mailbox delivery",new ArrayList<>());
        ranking.put("Mailbox delivery",0);
        map.put("Courier",new ArrayList<>());
        ranking.put("Courier",0);
        map.put("Express Collection",new ArrayList<>());
        ranking.put("Express Collection",0);

    }

    public Map<String, ArrayList<String>> split(JsonArray basket){

        if(basket.isEmpty() || basket.size()>=999 ){ //checking if empty and if not larger than 1000
            return null;
        }

        for (ArrayList<String> list : map.values()) { //clear list
            list.clear();
        }

        for(Integer num : ranking.values()) { //clear ranking
            num=0;
        }

        //Setting all products delivery values and ranking
        for(int i = 0; i < basket.size(); i++){
            for(JsonElement deliveryMethod : configDelivery.get(basket.get(i).getAsString()).getAsJsonArray()) {
                map.get(deliveryMethod.getAsString()).add(basket.get(i).getAsString());
                ranking.put(deliveryMethod.getAsString(),ranking.get(deliveryMethod.getAsString())+1);
            }
        }


////        System.out.println("-------------------------------");
//        System.out.println(map);
////        System.out.println("-------------------------------");
//        System.out.println(ranking);
////        System.out.println("-------------------------------");


        while(true){
            int max = 0;
            String maxDelivery="x";

            //checking ranking for most used delivery
            //it was not written which deliveries should be favored
            //so the first ones are
            for (Map.Entry<String, Integer> entry : ranking.entrySet()) {
                int num = entry.getValue();
                if (num > max) {
                    max = num;
                    maxDelivery = entry.getKey();
                }
            }

            if(!Objects.equals(maxDelivery, "x")){
                ranking.put(maxDelivery,0);
            }

            //end if every product delivery selected
            if(max==0){
                break;
            }

            //For every product
            for(int i = 0; i < basket.size(); i++){
                String product=basket.get(i).getAsString();

                //If its maxDelivery entry don't do anything
                //If current entry contains product and maxDelivery contains product then delete from entry and decrease ranking accordingly
                for(Map.Entry<String, ArrayList<String>> entry : map.entrySet()){
//                    System.out.println(product+" : "  + !entry.getKey().equals(maxDelivery) + " : " + entry.getValue().contains(product) + " : " +map.get(maxDelivery).contains(product));
                    if(!entry.getKey().equals(maxDelivery) && entry.getValue().contains(product) && map.get(maxDelivery).contains(product)){
                        entry.getValue().remove(product);
                        ranking.put(entry.getKey(),ranking.get(entry.getKey())-1);
                    }
                }
//                System.out.println(map);
            }


        }

        return map;
    }
}
