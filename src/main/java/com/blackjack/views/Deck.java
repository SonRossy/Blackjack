
package com.blackjack.views;

import java.util.Random;

public class Deck {
    public static int  CardCount;
    public static int TotalCard;
    Random rand = new Random();
    public int Bank=500;
    
    public int GetBank()
    {
        return Bank;
    }
    
    public void SetBank(int money)
    {
        Bank=money;
    }
    
    public int Hand()
    {   
        CardCount = rand.nextInt(10) + 1;
        return CardCount;
        
    }
    
    
}
