package com.blackjack.views;

import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import com.blackjack.BlackJack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.concurrent.TimeUnit;

public class PrimaryPresenter {
    
    //for the card position to be reveal
    public static int Xposition=-142;
    public static int Yposition=260; 
    public static int HitTimes=0;//if player hit more than 6 times then we change the yPosition
    private static Deck Player=new Deck();
    private static Deck Dealer=new Deck();
    private static Image img;
    private static int  PlayerHand,PlayerTotal;
    private static int  DealerHand, DealerTotal;
    private static boolean GameOn=false;
    

    @FXML
    private View primary;
    @FXML
    private Label DealerDisplay,guide,DealerBank,PlayerBank;
    @FXML
    private Label Winner;
    @FXML 
    ImageView HandCard;
    @FXML 
    ImageView CardOne,CardTwo,CardThree,CardFour,CardFive,CardSix,CardSeven,CardEight,CardNine,CardTen,CardEleven,
            CardTwelve;
    
     
    public void TransitionToOne()
    {
        if(GameOn){
        TranslateTransition trans= new TranslateTransition();
        trans.setDuration(Duration.seconds(.5));
        trans.setToX(Xposition);//remember each card is 55 in width
        trans.setToY(Yposition);
        trans.setAutoReverse(true);
        trans.setCycleCount(2);
        trans.setNode(HandCard);
        trans.play();
        Xposition=Xposition+55;
        if(HitTimes==5)
        {
            Xposition=-142;
            Yposition=357;
        } 
        else if (HitTimes>11)
        {
            trans.stop();
        }
    }
    }
    
    //this function check which card is generated and set the appropriate image
    public void SetUpImage()
    {
        //Player = new Deck();
        //Dealer = new Deck();
        if(GameOn){
        PlayerHand=Player.Hand();
        DealerHand=Dealer.Hand();
        PlayerTotal+=PlayerHand;
        if(DealerTotal<17)
        {
            DealerTotal+=DealerHand;
        }
        
       if (PlayerHand==1)
       {
           img=new Image("IMG/CardOne.png");
           
       }
       else if(PlayerHand==2)
       {
           img=new Image("IMG/card2.png");
          
       }
       else if(PlayerHand==3)
       {
           img=new Image("IMG/card3.png");
         
       }
       else if(PlayerHand==4)
       {
           img=new Image("IMG/card4.png");
           
       }
       else if(PlayerHand==5)
       {
           img=new Image("IMG/card5.png");
          
       }
       else if(PlayerHand==6)
       {
           img=new Image("IMG/card6.png");
           
       }
       else if(PlayerHand==7)
       {
           img=new Image("IMG/card7.png");
           
       }
       else if(PlayerHand==8)
       {
           img=new Image("IMG/card8.png");
           
       }
       else if(PlayerHand==9)
       {
           img=new Image("IMG/card9.png");
           
       }
       else if(PlayerHand==10)
       {
           img=new Image("IMG/card10.png");
           
       }
       
      }
    }
    public void CardReveal()
    {
        if(GameOn){
        //so the card is shown after one second
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(PrimaryPresenter.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        //checking how which card to set the image to
        if(HitTimes==0)
        {
           SetUpImage();
           CardOne.setImage(img);
        }
        else if (HitTimes==1)
        {
            SetUpImage();
            CardTwo.setImage(img);
        }
         else if (HitTimes==2)
        {
           SetUpImage();
           CardThree.setImage(img);
        }
         else if (HitTimes==3)
        {
            SetUpImage();
            CardFour.setImage(img);
        }
         else if (HitTimes==4)
        {
           SetUpImage();
           CardFive.setImage(img);
        }
         else if (HitTimes==5)
        {
           SetUpImage();
           CardSix.setImage(img);
        }
         else if (HitTimes==6)
        {
            SetUpImage();
            CardSeven.setImage(img);
        }
         else if (HitTimes==7)
        {
            SetUpImage();
            CardEight.setImage(img);
        }
         else if (HitTimes==8)
        {
            SetUpImage();
            CardNine.setImage(img);
        }
         else if (HitTimes==9)
        {
            SetUpImage();
            CardTen.setImage(img);
        }
         else if (HitTimes==10)
        {
           SetUpImage();
           CardEleven.setImage(img);
        }
         else if (HitTimes==11)
        {
            SetUpImage();
            CardTwelve.setImage(img);
        }
    }
    }
    
    public void GameisOn()
    {
         //check to see who wins.
        if(GameOn){
        if(PlayerTotal>DealerTotal&&PlayerTotal<22)
        {
            Winner.setText("PLAYER WINS ROUND");
            DealerDisplay.setText("Dealer has: "+DealerTotal);  
            Dealer.SetBank(Dealer.GetBank()-50);
           Player.SetBank(Player.GetBank()+50);
        }
        else if (PlayerTotal==21&&DealerTotal!=21)
        {
            Winner.setText("PLAYER WINS ROUND");
            DealerDisplay.setText("Dealer has: "+DealerTotal); 
           Dealer.SetBank(Dealer.GetBank()-50);
           Player.SetBank(Player.GetBank()+50);
        }
        else if (PlayerTotal!=21&&DealerTotal==21)
        {
            Winner.setText("DEALER WINS ROUND");
            DealerDisplay.setText("Dealer has: "+DealerTotal); 
            Dealer.SetBank(Dealer.GetBank()+50);
            Player.SetBank(Player.GetBank()-50);
        }
        else if(PlayerTotal<DealerTotal&&DealerTotal<22)
        {
            Winner.setText("DEALER WINS ROUND");
            DealerDisplay.setText("Dealer has: "+DealerTotal);
            Dealer.SetBank(Dealer.GetBank()+50);
            Player.SetBank(Player.GetBank()-50);
        }
        else if(PlayerTotal==DealerTotal)
        {
            Winner.setText("No Winner it's a Tie");
            DealerDisplay.setText("Dealer has: "+DealerTotal);  
           
        }
        else if (PlayerTotal>21&&DealerTotal>21)
        {
            Winner.setText("No winner Try Again!");
            DealerDisplay.setText("Dealer has: "+DealerTotal); 
        }
        else if(DealerTotal<22&&PlayerTotal>22)
        {
            Winner.setText("DEALER WINS ROUND");
            DealerDisplay.setText("Dealer has: "+DealerTotal);  
            Dealer.SetBank(Dealer.GetBank()+50);
           Player.SetBank(Player.GetBank()-50);
        }
       else if(DealerTotal>22&&PlayerTotal<22)
        {
            Winner.setText("PLAYER WINS ROUND");
            DealerDisplay.setText("Dealer has: "+DealerTotal);  
           Dealer.SetBank(Dealer.GetBank()-50);
           Player.SetBank(Player.GetBank()+50);
        }
       else if(PlayerTotal>21&&DealerTotal<21)
       {
           Winner.setText("DEALER WINS ROUND");
           DealerDisplay.setText("Dealer has: "+DealerTotal);  
           Dealer.SetBank(Dealer.GetBank()+50);
           Player.SetBank(Player.GetBank()-50);
       }
        else if(PlayerTotal<21&&DealerTotal>21)
       {
           Winner.setText("PLAYER WINS ROUND");
           DealerDisplay.setText("Dealer has: "+DealerTotal); 
           Dealer.SetBank(Dealer.GetBank()-50);
           Player.SetBank(Player.GetBank()+50);
           
       }
        
        }
    }
    
    @FXML
    void StandButton() {
       GameisOn();
       if(GameOn)
       {
        DealerDisplay.setText("Dealer has: "+DealerTotal); 
       }
       GameOn=false;
        
    }
    
    @FXML
    void HitButton() {
        TransitionToOne(); 
        CardReveal();
        HitTimes+=1;
         if(PlayerTotal>21||DealerTotal>21)
        {
            GameisOn();
            GameOn=false;
        }
    }
    
    @FXML
    void QuitButton() {
       Platform.exit();
       System.exit(0);
    }
    
    @FXML
    void StartButton(ActionEvent event){
       GameOn=true;
       DealerBank.setText("Dealer has :$ "+Dealer.GetBank());
       PlayerBank.setText("Player has :$ "+Player.GetBank());
       
        //display who wins the whole game
       if(Dealer.GetBank()==0)
       {
           PlayerBank.setText("Dealer Won");
       }
       else if(Player.GetBank()==0)
       {
           DealerBank.setText("Player Won");
       }
       
       //if someone won then we fill both bank up
       if(Dealer.GetBank()==0||Player.GetBank()==0)
       {
           Player.SetBank(500);
           Dealer.SetBank(500);
           
       }
      
       guide.setText("");
       Xposition=-142;
       Yposition=260; 
       HitTimes=0;
       PlayerTotal=0;
       DealerTotal=0;
       Winner.setText("");
       DealerDisplay.setText("");
       img=new Image("IMG/card.png");
       CardOne.setImage(img);
       CardTwo.setImage(img);
       CardThree.setImage(img);
       CardFour.setImage(img);
       CardFive.setImage(img);
       CardSix.setImage(img);
       CardSeven.setImage(img);
       CardEight.setImage(img);
       CardNine.setImage(img);
       CardTen.setImage(img);
       CardEleven.setImage(img);
       CardTwelve.setImage(img);
       HandCard.setImage(img);
        
    }
}
