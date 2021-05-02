


import javafx.scene.image.Image;


public  class Card {
    private String name,suit;
    private int value;
    private int valueSuit;
    private String cardImage;
    private Boolean status;
    javafx.scene.image.ImageView imageview;
 

    public Card(String name, String suit, int value, String cardImage) {
        this.status = true;
        this.name = name;
        this.suit = suit;
        this.value = value;
        this.cardImage = cardImage;
        Image image1 = new Image(cardImage);
        this.imageview = new javafx.scene.image.ImageView(image1);
        imageview.setFitWidth(100);
        imageview.setFitHeight(150);
        
        

        
        if(suit.equals("Spades")){
            this.valueSuit=4;
            
        }
        else if(suit.equals("Hearts")){
            this.valueSuit=3;
            
        }
        else if(suit.equals("Diamonds")){
            this.valueSuit=2;
            
        }
        else{
            this.valueSuit=1;
           
        }
    }

    public Card() {
        
    }


    

    @Override
    public String toString() {
        return "Card [cardImage=" + cardImage + ", name=" + name + ", suit=" + suit + ", value=" + value + "]";
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getSuit() {
        return suit;
    }



    public void setSuit(String suit) {
        this.suit = suit;
    }



    public int getValue() {
        return value;
    }



    public void setValue(int value) {
        this.value = value;
    }



    public String getCardImage() {
        return cardImage;
    }



    public void setCardImage(String cardImage) {
        this.cardImage = cardImage;
    }




    public javafx.scene.image.ImageView getImageview() {
        return imageview;
    }




    public int getValueSuit() {
        return valueSuit;
    }

    public Boolean getStatus() {
        return status;
    }


    public void setStatus(Boolean status) {
        this.status = status;
    }
    

    
}
