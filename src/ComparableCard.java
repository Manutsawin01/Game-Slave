import java.util.ArrayList;


public class ComparableCard extends Card implements CheckCardValue{

    public ComparableCard(String suit, String name, int value, String cardImage) {
        super(name, suit, value, cardImage);
    }

    public ComparableCard() {
        
    }
    
    @Override
    public int checkValue1(ComparableCard card){
        if(getValue()==card.getValue())
        {
            if(getValueSuit()>card.getValueSuit())
            {
                return 1;
            }
            else{
                return -1;
            }
        }
        else if(getValue()>card.getValue())
        {
            return 1;
        }
        else{
            return -1;
        }
        
    }
    
    
    public static int checkValue2(ComparableCard card1,ComparableCard card2,ComparableCard card3,ComparableCard card4){
        if(card1.getValue()==card3.getValue())
        {
            int max1,max2;
            if(card1.getValueSuit()>card2.getValueSuit())
            {
                max1=card1.getValueSuit();
            }
            else{
                max1=card2.getValueSuit();
            }

            if(card3.getValueSuit()>card4.getValueSuit())
            {
                max2=card3.getValueSuit();
            }
            else{
                max2=card4.getValueSuit();
            }

            if(max1>max2)
            {
                return 1;
            }
            else{
                return -1;
            }
        }
        else if(card1.getValue()>card3.getValue())
        {
            return 1;
        }
        else{
            return -1;
        }
    }
    public static int checkValue3(ComparableCard card1,ComparableCard card2){
        if(card1.getValue()>card2.getValue()){
            return 1;
        }
        else{
            return -1;
        }
    }
    public static int checkValue4(ComparableCard card1,ComparableCard card2){
       if(card1.getValue()>card2.getValue()){
           return 1;
       }
       else{
           return -1;
       }
    }  

    public static Boolean twoCardsOnHand(ArrayList<ComparableCard> cardsOnHand){
        int value = 0;
        for(int loop=0;loop<13;loop++){
            if(cardsOnHand.get(loop).getStatus()){
                if(cardsOnHand.get(loop).getValue()==value)
                {
                    return true;
                }
                else{
                    value = cardsOnHand.get(loop).getValue() ;
                }
            }
        }
        return false;
    }

    public static Boolean threeCardsOnHand(ArrayList<ComparableCard> cardsOnHand){
        int value = 0;
        int stacx = 1;
        for(int loop=0;loop<13;loop++){
            if(cardsOnHand.get(loop).getStatus()){
                if(cardsOnHand.get(loop).getValue()==value)
                {
                    stacx++;
                }
                else{
                    value = cardsOnHand.get(loop).getValue() ;
                    stacx = 1;
                }
            }

            if(stacx==3)
            {
                return true;
            }

        }
        return false;
    }

    public static Boolean fourCardsOnHand(ArrayList<ComparableCard> cardsOnHand){
        int value = 0;
        int stacx = 1;
        for(int loop=0;loop<13;loop++){
            if(cardsOnHand.get(loop).getStatus()){
                if(cardsOnHand.get(loop).getValue()==value)
                {
                    stacx++;
                }
                else{
                    value = cardsOnHand.get(loop).getValue() ;
                    stacx = 1;
                }
            }

            if(stacx==4)
            {
                return true;
            }

        }
        return false;
    }

    public static Boolean isFourCard(ArrayList<ComparableCard> cardsOnHand,int value){
        int stacx = 0;
        for(int loop=0;loop<13;loop++){
            if(cardsOnHand.get(loop).getValue()==value&&cardsOnHand.get(loop).getStatus()==true){
                
                stacx++;
               
            }

        }
        if(stacx==4)
        {
            return true;
        }
        return false;
    }

    public static Boolean isThreeCard(ArrayList<ComparableCard> cardsOnHand,int value){
        int stacx = 0;
        for(int loop=0;loop<13;loop++){
            if(cardsOnHand.get(loop).getValue()==value&&cardsOnHand.get(loop).getStatus()==true){
                
                stacx++;
               
            }
        }
        if(stacx>=3)
        {
            return true;
        }
        return false;
    }

    public static Boolean isTwoCard(ArrayList<ComparableCard> cardsOnHand,int value){
        int stacx = 0;
        for(int loop=0;loop<13;loop++){

            if(cardsOnHand.get(loop).getValue()==value&&cardsOnHand.get(loop).getStatus()==true){
                stacx++;
            }
        }
        if(stacx>=2)
        {
            return true;
        }
       
        return false;
    }

    public static ComparableCard findmax(ComparableCard card1,ComparableCard card2){
        if(card1.getValue()==card2.getValue())
        {
            if(card1.getValueSuit()>card2.getValueSuit())
            {
                return card1;
            }
            else{
                return card2;
            }
        }
        else if(card1.getValue()>card2.getValue()){
            return card1;
        }
        else{
            return card2;
        }
    }

    public static int findMaxSuit3(ComparableCard card1,ComparableCard card2,ComparableCard card3){
        if(card1.getValueSuit()>card2.getValueSuit()&&card1.getValueSuit()>card3.getValueSuit()){
            return 1;
        }
        else if(card2.getValueSuit()>card1.getValueSuit()&&card2.getValueSuit()>card3.getValueSuit()){
            return 2;
        }
        else if(card3.getValueSuit()>card1.getValueSuit()&&card3.getValueSuit()>card2.getValueSuit()){
            return 3;
        }

        return 0 ;
    }

    public static int findMaxSuit4(ComparableCard card1,ComparableCard card2,ComparableCard card3,ComparableCard card4){
        if(card1.getValueSuit()>card2.getValueSuit()&&card1.getValueSuit()>card3.getValueSuit()&&card1.getValueSuit()>card4.getValueSuit()){
            return 1;
        }
        else if(card2.getValueSuit()>card1.getValueSuit()&&card2.getValueSuit()>card3.getValueSuit()&&card2.getValueSuit()>card4.getValueSuit()){
            return 2;
        }
        else if(card3.getValueSuit()>card1.getValueSuit()&&card3.getValueSuit()>card2.getValueSuit()&&card3.getValueSuit()>card4.getValueSuit()){
            return 3;
        }
        else if(card4.getValueSuit()>card1.getValueSuit()&&card4.getValueSuit()>card2.getValueSuit()&&card4.getValueSuit()>card3.getValueSuit()){
            return 4;
        }

        return 0 ;
    }


    public static ArrayList<ComparableCard> exchange2Card(ArrayList<ComparableCard> list,int index1,int index2,ComparableCard card1,ComparableCard card2){
        ArrayList<ComparableCard> listNew = new ArrayList<ComparableCard>();
        for(int loop=0;loop<13;loop++){
            if(loop!=index1&&loop!=index2){
                listNew.add(list.get(loop));
            }
            
        }
        for(int loop=0;loop<11;loop++){
            if(card1.checkValue1(listNew.get(loop))==-1||loop==10){
                listNew.add(list.get(loop));
                for(int loop2=10;loop2>=loop;loop2--){
                    listNew.set(loop2+1,listNew.get(loop2));
                }
                if(loop==10){
                    listNew.set(loop+1,card1);
                }
                else{
                    listNew.set(loop,card1);
                }
                
                break;
            }
        }
        listNew.add(list.get(0));
        for(int loop=0;loop<12;loop++){
            if(card2.checkValue1(listNew.get(loop))==-1||loop==11){
               
                for(int loop2=11;loop2>=loop;loop2--){
                    listNew.set(loop2+1,listNew.get(loop2));
                }
                if(loop==11){
                    listNew.set(loop+1,card2);
                }
                else{
                    listNew.set(loop,card2);
                }
                
                break;
            }
        }
        return listNew;
    } 
    
    
    public static ArrayList<ComparableCard> exchange1Card(ArrayList<ComparableCard> list,int index ,ComparableCard card){
        ArrayList<ComparableCard> listNew = new ArrayList<ComparableCard>();
        for(int loop=0;loop<13;loop++){
            if(loop!=index){
                listNew.add(list.get(loop));
            }
            
        }
        for(int loop=0;loop<12;loop++){
            if(card.checkValue1(listNew.get(loop))==-1||loop==11){
                listNew.add(list.get(loop));
                for(int loop2=11;loop2>=loop;loop2--){
                    listNew.set(loop2+1,listNew.get(loop2));
                }
                if(loop==11){
                    listNew.set(loop+1,card);
                }
                else{
                    listNew.set(loop,card);
                }
                
                break;
            }
        }
        listNew.add(list.get(0));
        return listNew;
    }  
    
    
    
    


}
