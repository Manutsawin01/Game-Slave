import java.util.ArrayList;
import java.util.Random;

public class RandomHand {
    
    private ArrayList<ComparableCard> playerHand = new ArrayList<ComparableCard>();
    private ArrayList<ComparableCard> Com1Hand = new ArrayList<ComparableCard>();
    private ArrayList<ComparableCard> Com2Hand = new ArrayList<ComparableCard>();
    private ArrayList<ComparableCard> Com3Hand = new ArrayList<ComparableCard>();
    
    public RandomHand() {
        ArrayList<ComparableCard> card = SetPicCard.setPic();
        Random rand = new Random();
        int numRand;
        for(int loop=0;loop<52;loop++)
        {
            numRand = rand.nextInt(4);
            if(numRand==0)
            {
                if(playerHand.size()<13)
                {
                    playerHand.add(card.get(loop));
                }
                else if(Com1Hand.size()<13)
                {
                    Com1Hand.add(card.get(loop));
                }
                else if(Com2Hand.size()<13)
                {
                    Com2Hand.add(card.get(loop));
                }
                else if(Com3Hand.size()<13)
                {
                    Com3Hand.add(card.get(loop));
                }
                
            }
            else if(numRand==1)
            {
               
                if(Com1Hand.size()<13)
                {
                    Com1Hand.add(card.get(loop));
                }
                else if(Com2Hand.size()<13)
                {
                    Com2Hand.add(card.get(loop));
                }
                else if(Com3Hand.size()<13)
                {
                    Com3Hand.add(card.get(loop));
                }
                else if(playerHand.size()<13)
                {
                    playerHand.add(card.get(loop));
                }
            }
            else if(numRand==2)
            {
                
                if(Com2Hand.size()<13)
                {
                    Com2Hand.add(card.get(loop));
                }
                else if(Com3Hand.size()<13)
                {
                    Com3Hand.add(card.get(loop));
                }
                else if(playerHand.size()<13)
                {
                    playerHand.add(card.get(loop));
                }
                else if(Com1Hand.size()<13)
                {
                    Com1Hand.add(card.get(loop));
                }
            }
            else if(numRand==3)
            {
                
                if(Com3Hand.size()<13)
                {
                    Com3Hand.add(card.get(loop));
                }
                else if(playerHand.size()<13)
                {
                    playerHand.add(card.get(loop));
                }
                else if(Com1Hand.size()<13)
                {
                    Com1Hand.add(card.get(loop));
                }
                else if(Com2Hand.size()<13)
                {
                    Com2Hand.add(card.get(loop));
                }
            }

        }
    }

    public ArrayList<ComparableCard> getPlayerHand() {
        return playerHand;
    }

    public ArrayList<ComparableCard> getCom1Hand() {
        return Com1Hand;
    }


    public ArrayList<ComparableCard> getCom2Hand() {
        return Com2Hand;
    }
 
    public ArrayList<ComparableCard> getCom3Hand() {
        return Com3Hand;
    }

    
    
}
