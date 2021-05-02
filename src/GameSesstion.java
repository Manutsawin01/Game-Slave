import java.util.ArrayList;

public class GameSesstion {
    
    private Boolean []statusButton={true,true,true,true};
    private Boolean startStage = false;
    private Boolean playerCanPlay = true;
    private Boolean Com1CanPlay = true;
    private Boolean Com2CanPlay = true;
    private Boolean Com3CanPlay = true;
    private int lasttime = 0 ;
    private int selectStage ;
    private int limitSelectCards ;
    private int selectCards;
    private int valueCardsSellect;
    private int stageGame;
    private int numPlayerhand;
    private int numCom1hand;
    private int numCom2hand;
    private int numCom3hand;
    private int skip;
    private int []playerSelectIndex={0,0,0,0};
    private int []com1SelectIndex={0,0,0,0};
    private int []com2SelectIndex={0,0,0,0};
    private int []com3SelectIndex={0,0,0,0};
    private ComparableCard []CardsOnFieldPlayer={new ComparableCard(),new ComparableCard(),new ComparableCard(),new ComparableCard()};
    private ComparableCard []CardsOnFieldComOne={new ComparableCard(),new ComparableCard(),new ComparableCard(),new ComparableCard()};
    private ComparableCard []CardsOnFieldComTwo={new ComparableCard(),new ComparableCard(),new ComparableCard(),new ComparableCard()};
    private ComparableCard []CardsOnFieldComThree={new ComparableCard(),new ComparableCard(),new ComparableCard(),new ComparableCard()};
    private int turn ;
    private Boolean isfirstEnter;
    private int [] victory={-1,-1,-1,-1};
    private int  orderVictory=0;
    private Boolean exchangeCards ;

    public GameSesstion() {
        this.selectStage = 0;
        this.limitSelectCards = 1;
        this.selectCards=0;
        this.stageGame=0;
        this.numPlayerhand = 13;
        this.numCom1hand = 13;
        this.numCom2hand = 13;
        this.numCom3hand = 13;
        this.skip = 0 ;
        this.turn = 0;
        this.isfirstEnter = true ;
        this.exchangeCards = true;
    }

    public Boolean getStatusButton(int index) {
        return statusButton[index];
    }

    public void setStatusButtonfalse(int index) {
        this.statusButton[index] = false;
    }

    public void setStatusButtontrue(int index) {
        this.statusButton[index] = true;
    }

    public int getSelectStage() {
        return selectStage;
    }

    public void setSelectStage(int selectStage) {
        this.selectStage = selectStage;
    }

    public int getLimitSelectCards() {
        return limitSelectCards;
    }

    public void setLimitSelectCards(int limitSelectCards) {
        this.limitSelectCards = limitSelectCards;
    }

    public int getSelectCards() {
        return selectCards;
    }

    public void setSelectCards(int selectCards) {
        this.selectCards = selectCards;
    }

    
    public void plusSelectCards() {
        this.selectCards++;
    }
    

    public void decreaseSelectCards() {
        this.selectCards--;
    }

    public int getValueCardsSellect() {
        return valueCardsSellect;
    }

    public void setValueCardsSellect(int valueCardsSellect) {
        this.valueCardsSellect = valueCardsSellect;
    }

    public int getStageGame() {
        return stageGame;
    }

    public void setStageGame(int stageGame) {
        this.stageGame = stageGame;
    }

    public ComparableCard getCardsOnFieldPlayer(int index) {
        return CardsOnFieldPlayer[index];
    }

    public void setCardsOnFieldPlayer(ComparableCard cards,int index) {
        CardsOnFieldPlayer[index] = cards;
    }

    public ComparableCard getCardsOnFieldComThree(int index) {
        return CardsOnFieldComThree[index];
    }

    public void setCardsOnFieldComThree(ComparableCard cards,int index) {
        CardsOnFieldComThree[index] = cards;
    }

    public ComparableCard getCardsOnFieldComTwo(int index) {
        return CardsOnFieldComTwo[index];
    }

    public void setCardsOnFieldComTwo(ComparableCard cards,int index) {
        CardsOnFieldComTwo[index] = cards;
    }

    public ComparableCard getCardsOnFieldComOne(int index) {
        return CardsOnFieldComOne[index];
    }

    public void setCardsOnFieldComOne(ComparableCard cards,int index) {
        CardsOnFieldComOne[index] = cards;
    }

    public int getNumPlayerhand() {
        return numPlayerhand;
    }

    public void setNumPlayerhand(int numPlayerhand) {
        this.numPlayerhand = numPlayerhand;
    }

    public int getNumCom1hand() {
        return numCom1hand;
    }

    public void setNumCom1hand(int numCom1hand) {
        this.numCom1hand = numCom1hand;
    }

    public int getNumCom2hand() {
        return numCom2hand;
    }

    public void setNumCom2hand(int numCom2hand) {
        this.numCom2hand = numCom2hand;
    }

    public int getNumCom3hand() {
        return numCom3hand;
    }

    public void setNumCom3hand(int numCom3hand) {
        this.numCom3hand = numCom3hand;
    }

    public int getPlayerSelectIndex(int index) {
        return playerSelectIndex[index];
    }

    public void setPlayerSelectIndex(int index,int indexOfcard) {
        this.playerSelectIndex[index] = indexOfcard;
    }

    public int getCom1SelectIndex(int index) {
        return com1SelectIndex[index];
    }

    public void setCom1SelectIndex(int index,int indexOfcard) {
        this.com1SelectIndex[index] = indexOfcard;
    }

    public int getCom2SelectIndex(int index) {
        return com2SelectIndex[index];
    }

    public void setCom2SelectIndex(int index,int indexOfcard) {
        this.com2SelectIndex[index] = indexOfcard;
    }

    public int getCom3SelectIndex(int index) {
        return com3SelectIndex[index];
    }

    public void setCom3SelectIndex(int index,int indexOfcard) {
        this.com3SelectIndex[index] = indexOfcard;
    }

    public void decreasePlayerhand(int numDecrease) {
        this.numPlayerhand -=numDecrease;
    }

    public void decreaseCom1hand(int numDecrease) {
        this.numCom1hand -=numDecrease;
    }

    public void decreaseCom2hand(int numDecrease) {
        this.numCom2hand -=numDecrease;
    }

    public void decreaseCom3hand(int numDecrease) {
        this.numCom3hand -=numDecrease;
    }


    public static int findFirsTurn(ArrayList<ComparableCard> playerHand,ArrayList<ComparableCard> Com1Hand,ArrayList<ComparableCard> Com2Hand,ArrayList<ComparableCard> Com3Hand){
        for(int loop=0;loop<13;loop++){
            if(playerHand.get(loop).getValue()==3&&playerHand.get(loop).getValueSuit()==1){
                return 0;
            }
            else if(Com1Hand.get(loop).getValue()==3&&Com1Hand.get(loop).getValueSuit()==1){
                return 1;
            }
            else if(Com2Hand.get(loop).getValue()==3&&Com2Hand.get(loop).getValueSuit()==1){
                return 2;
            }
            else if(Com3Hand.get(loop).getValue()==3&&Com3Hand.get(loop).getValueSuit()==1){
                return 3;
            }
            
        }
        return 0;
    }

    public Boolean getStartStage() {
        return startStage;
    }

    public void setStartStage(Boolean startStage) {
        this.startStage = startStage;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public void plusSkip(int num) {
        this.skip += num;
    }

    public Boolean getCom1CanPlay() {
        return Com1CanPlay;
    }

    public void setCom1CanPlay(Boolean com1CanPlay) {
        Com1CanPlay = com1CanPlay;
        System.out.println("Com1Canplay :"+com1CanPlay);
    }

    public Boolean getCom2CanPlay() {
        return Com2CanPlay;
    }

    public void setCom2CanPlay(Boolean com2CanPlay) {
        Com2CanPlay = com2CanPlay;
        System.out.println("Com2Canplay :"+com2CanPlay);
    }

    public Boolean getCom3CanPlay() {
        return Com3CanPlay;
    }

    public void setCom3CanPlay(Boolean com3CanPlay) {
        Com3CanPlay = com3CanPlay;
        System.out.println("Com3Canplay :"+com3CanPlay);
    }

    public Boolean getPlayerCanPlay() {
        return playerCanPlay;
    }

    public void setPlayerCanPlay(Boolean playerCanPlay) {
        this.playerCanPlay = playerCanPlay;
    }

    public int getLasttime() {
        return lasttime;
    }

    public void setLasttime(int lasttime) {
        this.lasttime = lasttime;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void plusTurn(int num) {
        this.turn += num;
    }

    public Boolean getIsfirstEnter() {
        return isfirstEnter;
    }

    public void setIsfirstEnter(Boolean isfirstEnter) {
        this.isfirstEnter = isfirstEnter;
    }

    public int getVictory(int index) {
        return victory[index];
    }

    public void setVictory(int index,int num) {
        this.victory[index] = num;
    }

    public int getOrderVictory() {
        return orderVictory;
    }
    
    public void plusOrderVictory(int num) {
        this.orderVictory += num;
    }

    public void setOrderVictory(int orderVictory) {
        this.orderVictory = orderVictory;
    }

    public Boolean getExchangeCards() {
        return exchangeCards;
    }

    public void setExchangeCards(Boolean exchangeCards) {
        this.exchangeCards = exchangeCards;
    }

    
    

    
    

    
    
    
    
    
}
