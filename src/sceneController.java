
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class sceneController {

    private String path = "sound/1.mp3";
    private Media media = new Media(new File(path).toURI().toString());
    private MediaPlayer mediaPlayer = new MediaPlayer(media);


    private ArrayList<ComparableCard> playerHand = new ArrayList<ComparableCard>();
    private ArrayList<ComparableCard> Com1Hand = new ArrayList<ComparableCard>();
    private ArrayList<ComparableCard> Com2Hand = new ArrayList<ComparableCard>();
    private ArrayList<ComparableCard> Com3Hand = new ArrayList<ComparableCard>(); 

    private ArrayList<javafx.scene.image.ImageView> imageviewButtonOn = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewButtonOff = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewButtonPush = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewButtonTurnMark = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewNumberHands0 = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewNumberHands1 = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewNumberHands2 = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewNumberHands3 = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewSt = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewStatus = new ArrayList<javafx.scene.image.ImageView>();
    private ArrayList<javafx.scene.image.ImageView> imageviewStatusStart = new ArrayList<javafx.scene.image.ImageView>();
   
    private GameSesstion game = new GameSesstion();

    private boolean start = true;

    @FXML
    private ArrayList<Pane> paneList ;

    @FXML
    private ArrayList<Pane> paneTopList ;

    @FXML
    private ArrayList<Pane> BList ;

    @FXML
    private ArrayList<Pane> CardOnFieldPlayerList ;

    @FXML
    private ArrayList<Pane> CardOnFieldComthreeList ;
    
    @FXML
    private ArrayList<Pane> CardOnFieldComtwoList ;

    @FXML
    private ArrayList<Pane> CardOnFieldComoneList ;

    @FXML
    private ArrayList<Pane> cardOnHandsList ;
    
    @FXML
    private ArrayList<Pane> statusList;

    @FXML
    private Button skip;

    @FXML
    private Button next; 
    
    @FXML
    private Button exBT;

    @FXML
    private Pane turnPlayer;

    @FXML
    private Pane turnBot1;

    @FXML
    private Pane turnBot2;

    @FXML
    private Pane turnBot3;

    ComparableCard CardsOnField;
 
    @FXML
    private Button enter;

    @FXML
    private Button toMenu;
    
    @FXML
    private Pane bg;
  
    @FXML
    private Pane picStange;

    private int [] indexCom1,indexCom2,indexCom3;

    private TimeCount time = new TimeCount();

    private int[] victory = {0,0,0,0};

    private int roundGame = 0; 
    
    @FXML
    void initialize() {
        mediaPlayer.play();
        bg.getChildren().add(SetpicMainPages.setpicBgGame());
        setUp();
    }

    @FXML
    public void setUp(){
       
        if(start==true){
            start=false;
            RandomHand rand = new RandomHand();
            this.playerHand = rand.getPlayerHand();
            this.Com1Hand = rand.getCom1Hand();
            this.Com2Hand = rand.getCom2Hand();
            this.Com3Hand = rand.getCom3Hand();
            CardOnFieldComthreeList.get(0).getChildren().add(Com3Hand.get(0).getImageview());
            CardOnFieldComtwoList.get(0).getChildren().add(Com2Hand.get(0).getImageview());
            CardOnFieldComoneList.get(0).getChildren().add(Com1Hand.get(0).getImageview());
            CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(0).getImageview());
            for(int loop=0;loop<13;loop++)
            {
                paneTopList.get(loop).getChildren().clear();
                paneList.get(loop).getChildren().add(playerHand.get(loop).imageview);
            }
            
            game = new GameSesstion();
    
            imageviewButtonOn = SetpicMainPages.setpicOn();
            imageviewButtonOff = SetpicMainPages.setpicOff();
            imageviewButtonPush = SetpicMainPages.setpicPush();
            imageviewButtonTurnMark = SetpicMainPages.setpicTurnMark();
            imageviewNumberHands0 = SetpicMainPages.setpicNumberHands();
            imageviewNumberHands1 = SetpicMainPages.setpicNumberHands();
            imageviewNumberHands2 = SetpicMainPages.setpicNumberHands();
            imageviewNumberHands3 = SetpicMainPages.setpicNumberHands();
            imageviewSt = SetpicMainPages.setpicSt();
            imageviewStatus = SetpicMainPages.setpicStatus();
            imageviewStatusStart = SetpicMainPages.setpicStatusStart();

            statusList.get(0).getChildren().add(imageviewStatusStart.get(0));
            statusList.get(1).getChildren().add(imageviewStatusStart.get(1));
            statusList.get(2).getChildren().add(imageviewStatusStart.get(2));
            statusList.get(3).getChildren().add(imageviewStatusStart.get(3));

            picStange.getChildren().add(imageviewSt.get(0));
    
            game.setSelectStage(game.getStageGame());
            for(int loop=0;loop<4;loop++)
            {
                BList.get(loop).getChildren().add(imageviewButtonOn.get(loop));
            }

            

            fethButton();
            checkLimitCards();
            game.setSelectCards(0);
            System.out.println(time.getTimecount());  
            setNumberHands();
            firstPlay();  
        }
    }

   

    private void fethButton(){
        if(!ComparableCard.twoCardsOnHand(playerHand)){
            BList.get(1).getChildren().clear();
            BList.get(1).getChildren().add(imageviewButtonOff.get(1));
            game.setStatusButtonfalse(1);
        } 
        if(!ComparableCard.threeCardsOnHand(playerHand)){
            BList.get(2).getChildren().clear();
            BList.get(2).getChildren().add(imageviewButtonOff.get(2));
            game.setStatusButtonfalse(2);
        }
        if(!ComparableCard.fourCardsOnHand(playerHand)){
            BList.get(3).getChildren().clear();
            BList.get(3).getChildren().add(imageviewButtonOff.get(3));
            game.setStatusButtonfalse(3);
        } 

        if(game.getStageGame()==0){
            game.setStatusButtonfalse(1);
            BList.get(1).getChildren().clear();
            BList.get(1).getChildren().add(imageviewButtonOff.get(1));
            game.setStatusButtonfalse(3);
            BList.get(3).getChildren().clear();
            BList.get(3).getChildren().add(imageviewButtonOff.get(3));
        }
        else if(game.getStageGame()==1){
            game.setStatusButtonfalse(0);
            BList.get(0).getChildren().clear();
            BList.get(0).getChildren().add(imageviewButtonOff.get(0));
            game.setStatusButtonfalse(2);
            BList.get(2).getChildren().clear();
            BList.get(2).getChildren().add(imageviewButtonOff.get(2));
        }
        else if(game.getStageGame()==2){
            game.setStatusButtonfalse(0);
            BList.get(0).getChildren().clear();
            BList.get(0).getChildren().add(imageviewButtonOff.get(0));
            game.setStatusButtonfalse(1);
            BList.get(1).getChildren().clear();
            BList.get(1).getChildren().add(imageviewButtonOff.get(1));
            game.setStatusButtonfalse(3);
            BList.get(3).getChildren().clear();
            BList.get(3).getChildren().add(imageviewButtonOff.get(3));
        }

    }

    private void refeshBT(){
        for(int loop=0;loop<4;loop++){
            if(game.getStatusButton(loop)==true){
                BList.get(loop).getChildren().clear();
                BList.get(loop).getChildren().add(imageviewButtonOn.get(loop));
            }
            else{
                BList.get(loop).getChildren().clear();
                BList.get(loop).getChildren().add(imageviewButtonOff.get(loop));
            }
        }
    }

    private void setNumberHands(){
        for(int loop=13;loop>=0;loop--){
            if(game.getNumPlayerhand()==loop){
                cardOnHandsList.get(0).getChildren().clear();
                cardOnHandsList.get(0).getChildren().add(imageviewNumberHands0.get(loop));
            }
            if(game.getNumCom1hand()==loop){
                cardOnHandsList.get(1).getChildren().clear();
                cardOnHandsList.get(1).getChildren().add(imageviewNumberHands1.get(loop));
            }
            if(game.getNumCom2hand()==loop){
                cardOnHandsList.get(2).getChildren().clear();
                cardOnHandsList.get(2).getChildren().add(imageviewNumberHands2.get(loop));
            }
            if(game.getNumCom3hand()==loop){
                cardOnHandsList.get(3).getChildren().clear();
                cardOnHandsList.get(3).getChildren().add(imageviewNumberHands3.get(loop));
            }
        }
        
        
    }

    private void checkLimitCards(){ 
        game.setLimitSelectCards(game.getStageGame()+1);
    }

    @FXML
    private void clickonCards(MouseEvent event)
    {   
        
        
        int value = Integer.parseInt(((Pane)event.getSource()).getId());
        System.out.println("Time :"+time.getTimecount());
        System.out.println("Stage : "+game.getStageGame());
        System.out.println("Skip : "+game.getSkip());
        System.out.println(CardsOnField);
        System.out.println("Select : "+game.getSelectCards()+" Limit : "+game.getLimitSelectCards());
        System.out.println("Player : "+game.getNumPlayerhand()+"||"+" COM1 : "+game.getNumCom1hand()+"||"+" COM2 : "+game.getNumCom2hand()+"||"+" COM3 : "+game.getNumCom3hand());
        System.out.println("Exchange : "+game.getExchangeCards());
        if(game.getExchangeCards()){

            if(game.getVictory(0)==0){
                if(game.getSelectCards()<2){
                    paneList.get(value).getChildren().clear();
                    paneTopList.get(value).getChildren().add(playerHand.get(value).imageview);
                    game.setPlayerSelectIndex(game.getSelectCards(),value);
                    game.plusSelectCards();
                }
            }
            else if(game.getVictory(1)==0){
                if(game.getSelectCards()<1){
                    paneList.get(value).getChildren().clear();
                    paneTopList.get(value).getChildren().add(playerHand.get(value).imageview);
                    game.setPlayerSelectIndex(game.getSelectCards(),value);
                    game.plusSelectCards();
                }
            }
        }
        else if(game.getSelectCards()<game.getLimitSelectCards()&&playerHand.get(value).getStatus()==true) {
            if(game.getSelectStage()==0)
            {
                if(playerHand.get(value).checkValue1(CardsOnField)==1)
                {
                    paneList.get(value).getChildren().clear();
                    paneTopList.get(value).getChildren().add(playerHand.get(value).imageview);
                    game.setPlayerSelectIndex(game.getSelectCards(),value);
                    game.plusSelectCards();
                }
            }
            else if(game.getSelectStage()==1)
            {
               
                if(ComparableCard.isTwoCard(playerHand, playerHand.get(value).getValue())){
                    if((game.getSelectCards()==0&&playerHand.get(value).checkValue1(CardsOnField)==1)||game.getValueCardsSellect()==playerHand.get(value).getValue())
                    {
                        
                        paneList.get(value).getChildren().clear();
                        paneTopList.get(value).getChildren().add(playerHand.get(value).imageview);
                        game.setPlayerSelectIndex(game.getSelectCards(),value);
                        game.plusSelectCards();
                        game.setValueCardsSellect(playerHand.get(value).getValue());
                    } 
                }
            }
            else if(game.getSelectStage()==2)
            {
               
                if(ComparableCard.isThreeCard(playerHand, playerHand.get(value).getValue())){
  
                    if((game.getSelectCards()==0&&playerHand.get(value).checkValue1(CardsOnField)==1)||game.getValueCardsSellect()==playerHand.get(value).getValue()||game.getStageGame()==0){
                        paneList.get(value).getChildren().clear();
                        paneTopList.get(value).getChildren().add(playerHand.get(value).imageview);
                        game.setPlayerSelectIndex(game.getSelectCards(),value);
                        game.plusSelectCards();
                        game.setValueCardsSellect(playerHand.get(value).getValue());
                    }
                }
            }
            else if(game.getSelectStage()==3)
            {
                if(ComparableCard.isFourCard(playerHand, playerHand.get(value).getValue())){
                    if((game.getSelectCards()==0&&playerHand.get(value).checkValue1(CardsOnField)==1)||game.getValueCardsSellect()==playerHand.get(value).getValue()||game.getStageGame()==1){
                        paneList.get(value).getChildren().clear();
                        paneTopList.get(value).getChildren().add(playerHand.get(value).imageview);
                        game.setPlayerSelectIndex(game.getSelectCards(),value);
                        game.plusSelectCards();
                        game.setValueCardsSellect(playerHand.get(value).getValue());
                    }
                }
            }
       }
        
        
    }

    @FXML
    private void clickonCardsTop(MouseEvent event)
    {
        int value = Integer.parseInt(((Pane)event.getSource()).getId());
        if(playerHand.get(value).getStatus()==true){
            paneTopList.get(value).getChildren().clear();
            paneList.get(value).getChildren().add(playerHand.get(value).imageview);
            game.decreaseSelectCards();
        }
        
       
    }

    @FXML
    private void selectStange(MouseEvent event)
    {
        
        
        for(int loop=0;loop<13;loop++){
            if(playerHand.get(loop).getStatus()==true)
            {
                paneTopList.get(loop).getChildren().clear();
                paneList.get(loop).getChildren().clear();
                paneList.get(loop).getChildren().add(playerHand.get(loop).imageview);
            }
        }
        
        int value = Integer.parseInt(((Pane)event.getSource()).getId());


        if(game.getStatusButton(value)||game.getStartStage()==true)
        {
            if(value!=0||game.getStageGame()!=1){
                BList.get(game.getSelectStage()).getChildren().clear();
                BList.get(game.getSelectStage()).getChildren().add(imageviewButtonOn.get(game.getSelectStage()));
            }
           
            BList.get(value).getChildren().clear();
            BList.get(value).getChildren().add(imageviewButtonPush.get(value));

            game.setSelectStage(value);
            game.setLimitSelectCards(value+1);
            game.setSelectCards(0);
            System.out.println(game.getSelectStage());
            picStange.getChildren().clear();
            picStange.getChildren().add(imageviewSt.get(value));
            
        }
        
    }

    @FXML
    private void playerEnterCard(ActionEvent event) throws IOException{

        if(game.getExchangeCards()){
            
        }
        else if(game.getTurn()==0){

            if(game.getSelectCards()==game.getLimitSelectCards()){
                
                game.setStageGame(game.getSelectStage());
                if(game.getStageGame()==0){
                    paneTopList.get(game.getPlayerSelectIndex(0)).getChildren().clear();
                    CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(game.getPlayerSelectIndex(0)).imageview);
                    CardsOnField = playerHand.get(game.getPlayerSelectIndex(0));
                    playerHand.get(game.getPlayerSelectIndex(0)).setStatus(false);
                    game.decreasePlayerhand(1);
                }
                else if(game.getStageGame()==1){
                    paneTopList.get(game.getPlayerSelectIndex(0)).getChildren().clear();
                    paneTopList.get(game.getPlayerSelectIndex(1)).getChildren().clear();
                    CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(game.getPlayerSelectIndex(0)).imageview);
                    CardOnFieldPlayerList.get(1).getChildren().add(playerHand.get(game.getPlayerSelectIndex(1)).imageview);
                    if(playerHand.get(game.getPlayerSelectIndex(0)).checkValue1(playerHand.get(game.getPlayerSelectIndex(0)))==1){
                        CardsOnField = playerHand.get(game.getPlayerSelectIndex(0));
                    }
                    else{
                        CardsOnField = playerHand.get(game.getPlayerSelectIndex(1));
                    }
                    playerHand.get(game.getPlayerSelectIndex(0)).setStatus(false);  
                    playerHand.get(game.getPlayerSelectIndex(1)).setStatus(false);  
                    game.decreasePlayerhand(2);
                }
                else if(game.getStageGame()==2){
                    paneTopList.get(game.getPlayerSelectIndex(0)).getChildren().clear();
                    paneTopList.get(game.getPlayerSelectIndex(1)).getChildren().clear();
                    paneTopList.get(game.getPlayerSelectIndex(2)).getChildren().clear();
    
                    System.out.println(game.getPlayerSelectIndex(0));
                    System.out.println(game.getPlayerSelectIndex(1));
                    System.out.println(game.getPlayerSelectIndex(2));
    
                    CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(game.getPlayerSelectIndex(0)).imageview);
                    CardOnFieldPlayerList.get(1).getChildren().add(playerHand.get(game.getPlayerSelectIndex(1)).imageview);
                    CardOnFieldPlayerList.get(2).getChildren().add(playerHand.get(game.getPlayerSelectIndex(2)).imageview);
                    if(ComparableCard.findMaxSuit3(playerHand.get(game.getPlayerSelectIndex(0)), playerHand.get(game.getPlayerSelectIndex(1)), playerHand.get(game.getPlayerSelectIndex(2)))==1){
                        CardsOnField = playerHand.get(game.getPlayerSelectIndex(0));
                    }
                    else if(ComparableCard.findMaxSuit3(playerHand.get(game.getPlayerSelectIndex(0)), playerHand.get(game.getPlayerSelectIndex(1)), playerHand.get(game.getPlayerSelectIndex(2)))==2){
                        CardsOnField = playerHand.get(game.getPlayerSelectIndex(1));
                    }
                    else if(ComparableCard.findMaxSuit3(playerHand.get(game.getPlayerSelectIndex(0)), playerHand.get(game.getPlayerSelectIndex(1)), playerHand.get(game.getPlayerSelectIndex(2)))==3){
                        CardsOnField = playerHand.get(game.getPlayerSelectIndex(2));
                    }
                    playerHand.get(game.getPlayerSelectIndex(0)).setStatus(false);  
                    playerHand.get(game.getPlayerSelectIndex(1)).setStatus(false); 
                    playerHand.get(game.getPlayerSelectIndex(2)).setStatus(false);
                    game.decreasePlayerhand(3);   
                
                }
                else if(game.getStageGame()==3){
                    paneTopList.get(game.getPlayerSelectIndex(0)).getChildren().clear();
                    paneTopList.get(game.getPlayerSelectIndex(1)).getChildren().clear();
                    paneTopList.get(game.getPlayerSelectIndex(2)).getChildren().clear();
                    paneTopList.get(game.getPlayerSelectIndex(3)).getChildren().clear();
                    CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(game.getPlayerSelectIndex(0)).imageview);
                    CardOnFieldPlayerList.get(1).getChildren().add(playerHand.get(game.getPlayerSelectIndex(1)).imageview);
                    CardOnFieldPlayerList.get(2).getChildren().add(playerHand.get(game.getPlayerSelectIndex(2)).imageview);
                    CardOnFieldPlayerList.get(3).getChildren().add(playerHand.get(game.getPlayerSelectIndex(3)).imageview);
                    if(ComparableCard.findMaxSuit4(playerHand.get(game.getPlayerSelectIndex(0)), playerHand.get(game.getPlayerSelectIndex(1)), playerHand.get(game.getPlayerSelectIndex(2)),playerHand.get(game.getPlayerSelectIndex(3)))==1){
                        CardsOnField = playerHand.get(game.getPlayerSelectIndex(0));
                    }
                    else if(ComparableCard.findMaxSuit4(playerHand.get(game.getPlayerSelectIndex(0)), playerHand.get(game.getPlayerSelectIndex(1)), playerHand.get(game.getPlayerSelectIndex(2)),playerHand.get(game.getPlayerSelectIndex(3)))==2){
                        CardsOnField = playerHand.get(game.getPlayerSelectIndex(1));
                    }
                    else if(ComparableCard.findMaxSuit4(playerHand.get(game.getPlayerSelectIndex(0)), playerHand.get(game.getPlayerSelectIndex(1)), playerHand.get(game.getPlayerSelectIndex(2)),playerHand.get(game.getPlayerSelectIndex(3)))==3){
                        CardsOnField = playerHand.get(game.getPlayerSelectIndex(2));
                    }
                    else if(ComparableCard.findMaxSuit4(playerHand.get(game.getPlayerSelectIndex(0)), playerHand.get(game.getPlayerSelectIndex(1)), playerHand.get(game.getPlayerSelectIndex(2)),playerHand.get(game.getPlayerSelectIndex(3)))==4){
                        CardsOnField = playerHand.get(game.getPlayerSelectIndex(3));
                    }
                    playerHand.get(game.getPlayerSelectIndex(0)).setStatus(false);  
                    playerHand.get(game.getPlayerSelectIndex(1)).setStatus(false); 
                    playerHand.get(game.getPlayerSelectIndex(2)).setStatus(false);
                    playerHand.get(game.getPlayerSelectIndex(3)).setStatus(false);
                    game.decreasePlayerhand(4);   
    
                }
                game.setSelectCards(0);
                fethButton(); 
                endTurn();
            }

           
        }
    }

    private void firstPlay(){

        game.setExchangeCards(false);
        game.setVictory(0, -1);
        game.setVictory(1, -1);
        game.setVictory(2, -1);
        game.setVictory(3, -1);
        try {
            turnPlayer.getChildren().clear();
            turnBot1.getChildren().clear();
            turnBot2.getChildren().clear();
            turnBot3.getChildren().clear();
        }
        catch (Exception ex) {
            System.out.println("clear pic turn on firstPlay");
            
        }
        CardOnFieldComthreeList.get(0).getChildren().clear();
        CardOnFieldComtwoList.get(0).getChildren().clear();
        CardOnFieldComoneList.get(0).getChildren().clear();
        CardOnFieldPlayerList.get(0).getChildren().clear();
        int turn = GameSesstion.findFirsTurn(playerHand, Com1Hand, Com2Hand, Com3Hand);
        game.setTurn(turn);
        if(turn==0){
            
            int count = 0 ;
            int[] index={-1,-1,-1,-1};
            
            for(int loop=0;loop<4;loop++){
                if(playerHand.get(loop).getValue()==3){
                    index[count]= loop;
                    count++;
                }
            }

            if(count==1||count==4){
                paneList.get(0).getChildren().clear();
                playerHand.get(0).setStatus(false);
                game.decreasePlayerhand(1);
                CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(0).getImageview());
                CardsOnField=playerHand.get(0);
            }
            else if(count==2){
                paneList.get(0).getChildren().clear();
                playerHand.get(0).setStatus(false);
                paneList.get(1).getChildren().clear();
                playerHand.get(1).setStatus(false);
                game.decreasePlayerhand(2);
                game.setStageGame(1);
                game.setSelectStage(1);
                CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(index[0]).getImageview());
                CardOnFieldPlayerList.get(1).getChildren().add(playerHand.get(index[1]).getImageview());
                CardsOnField=playerHand.get(index[1]);
            }
            else if(count==3){
                paneList.get(0).getChildren().clear();
                playerHand.get(0).setStatus(false);
                paneList.get(1).getChildren().clear();
                playerHand.get(1).setStatus(false);
                paneList.get(2).getChildren().clear();
                playerHand.get(2).setStatus(false);
                game.decreasePlayerhand(3);
                game.setStageGame(2);
                game.setSelectStage(2);
                CardOnFieldPlayerList.get(0).getChildren().add(playerHand.get(index[0]).getImageview());
                CardOnFieldPlayerList.get(1).getChildren().add(playerHand.get(index[1]).getImageview());
                CardOnFieldPlayerList.get(2).getChildren().add(playerHand.get(index[2]).getImageview());
                CardsOnField=playerHand.get(index[2]);
            }

            game.plusTurn(1);
            turnBot1.getChildren().add(imageviewButtonTurnMark.get(0));
            // bot1Play();
            // bot2Play();
            // bot3Play();
            // game.setTurn(0);
            
           
        }
        else if(turn==1){
           
            Com1Hand.get(0).setStatus(false);
            game.decreaseCom1hand(1);
            CardOnFieldComoneList.get(0).getChildren().add(Com1Hand.get(0).getImageview());
            CardsOnField=Com1Hand.get(0);
            turnBot2.getChildren().add(imageviewButtonTurnMark.get(0));
            game.plusTurn(1);
           
            // bot2Play();
            // bot3Play();
            // game.setTurn(0);
        }
        else if(turn==2){
            Com2Hand.get(0).setStatus(false);
            game.decreaseCom2hand(1);
            CardOnFieldComtwoList.get(0).getChildren().add(Com2Hand.get(0).getImageview());
            CardsOnField=Com2Hand.get(0);
            turnBot3.getChildren().add(imageviewButtonTurnMark.get(0));
            game.plusTurn(1);
            
            // bot3Play();
            // game.setTurn(0);
        }
        else if(turn==3){
            Com3Hand.get(0).setStatus(false);
            game.decreaseCom3hand(1);
            CardOnFieldComthreeList.get(0).getChildren().add(Com3Hand.get(0).getImageview());
            CardsOnField=Com3Hand.get(0);
            turnPlayer.getChildren().add(imageviewButtonTurnMark.get(0));
            game.setTurn(0);
           
            // game.setTurn(0);
        }

        setNumberHands();
        
    }

    @FXML
    private void next() throws IOException{
        
        if((game.getTurn()!=0||game.getPlayerCanPlay()==false)&&game.getExchangeCards()==false){
            endTurn();
        }
        
    }


    
    private void endTurn() throws IOException{
        if(game.getTurn()==1){
            
            fethButton();
            checkLimitCards();
            if(game.getCom1CanPlay()==true){
                bot1Play();
            }
        }
        else if(game.getTurn()==2){
           
            fethButton();
            checkLimitCards();
            System.out.println(CardsOnField);
            if(game.getCom2CanPlay()==true){
                bot2Play();
            }
        }
        else if(game.getTurn()==3){
           
            fethButton();
            checkLimitCards();
            System.out.println(CardsOnField);
            if(game.getCom3CanPlay()==true){
                bot3Play();
            }

            System.out.println(CardsOnField);
            fethButton();
            checkLimitCards();
        
            if(game.getSkip()==3&&game.getPlayerCanPlay()==true){
                
                for(int loop=0;loop<4;loop++){
                    CardOnFieldPlayerList.get(loop).getChildren().clear();
                    CardOnFieldComoneList.get(loop).getChildren().clear();
                    CardOnFieldComtwoList.get(loop).getChildren().clear();
                    CardOnFieldComthreeList.get(loop).getChildren().clear();
                }
                game.setStartStage(true);
                game.setSkip(0);
            }

            if(game.getStartStage()==true){
                System.out.println("new game");
                
                game.setStageGame(0);
                game.setSelectStage(0);
                game.setLimitSelectCards(1);

                game.setCom1CanPlay(true);
                game.setCom2CanPlay(true);
                game.setCom3CanPlay(true);
                game.setPlayerCanPlay(true);
                CardsOnField.setValue(0);
                game.setStartStage(false);
                
                try {
                   
                    for(int loop=0;loop<4;loop++)
                    {
                        game.setStatusButtontrue(loop);
                    }
                    for(int loop=0;loop<4;loop++)
                    {
                        BList.get(loop).getChildren().add(imageviewButtonOn.get(loop));
                    }
                    if(!ComparableCard.twoCardsOnHand(playerHand)){
                        BList.get(1).getChildren().clear();
                        BList.get(1).getChildren().add(imageviewButtonOff.get(1));
                        game.setStatusButtonfalse(1);
                    } 
                    if(!ComparableCard.threeCardsOnHand(playerHand)){
                        BList.get(2).getChildren().clear();
                        BList.get(2).getChildren().add(imageviewButtonOff.get(2));
                        game.setStatusButtonfalse(2);
                    }
                    if(!ComparableCard.fourCardsOnHand(playerHand)){
                        BList.get(3).getChildren().clear();
                        BList.get(3).getChildren().add(imageviewButtonOff.get(3));
                        game.setStatusButtonfalse(3);
                    } 
                }
                catch (Exception ex) {
                    System.out.println("set and claer pic on methods endturn");
                    
                }

                
            
            }
           
        }

        
        
        
        if(game.getTurn()==3){
            game.setTurn(0);
        }
        else{
            game.plusTurn(1);
        }

        checkVictory();

        if(game.getTurn()==0){
            turnBot3.getChildren().clear();
            turnPlayer.getChildren().add(imageviewButtonTurnMark.get(0));
        }
        else if(game.getTurn()==1){
            turnPlayer.getChildren().clear();
            turnBot1.getChildren().add(imageviewButtonTurnMark.get(0));
        }
        else if(game.getTurn()==2){
            turnBot1.getChildren().clear();
            turnBot2.getChildren().add(imageviewButtonTurnMark.get(0));
        }
        else if(game.getTurn()==3){
            turnBot2.getChildren().clear();
            turnBot3.getChildren().add(imageviewButtonTurnMark.get(0));
        }
        
        setNumberHands();
        try {
            refeshBT();
        }
        catch (Exception ex) {
            System.out.println("refeshBT()");
            
        }

        if(game.getTurn()==0&&game.getPlayerCanPlay()==false){
            next();
        }

        if(game.getTurn()==1&&game.getCom1CanPlay()==false){
            next();
        }

        if(game.getTurn()==2&&game.getCom2CanPlay()==false){
            next();
        }

        if(game.getTurn()==3&&game.getCom3CanPlay()==false){
            next();
        }

        if(game.getNumPlayerhand()==0){
            playerSkip();
        }

        if(game.getTurn()==1&&game.getNumCom1hand()==0){
            next();
        }

        if(game.getTurn()==2&&game.getNumCom2hand()==0){
            next();
        }

        if(game.getTurn()==3&&game.getNumCom3hand()==0){
            next();
        }


    }

    @FXML
    private void playerSkip() throws IOException{
        if(game.getExchangeCards()){
            
        }
        else if(game.getTurn()==0){
            game.plusSkip(1);
            System.out.println("skip : "+game.getSkip());
            game.setPlayerCanPlay(false);
            for(int loop =0 ;loop<4;loop++){
                CardOnFieldPlayerList.get(loop).getChildren().clear();
            }
            endTurn();
        }
       
    }

    private void bot1Play(){
        
        System.out.println("turn Bot 1");

        if(game.getSkip()==3&&game.getCom1CanPlay()==true){
            for(int loop=0;loop<4;loop++){
                CardOnFieldPlayerList.get(loop).getChildren().clear();
                CardOnFieldComoneList.get(loop).getChildren().clear();
                CardOnFieldComtwoList.get(loop).getChildren().clear();
                CardOnFieldComthreeList.get(loop).getChildren().clear();
            }
            game.setStartStage(true);
        }
        
        indexCom1 = Bot.botCalculate(Com1Hand,CardsOnField, game);
            if(indexCom1[4]==4){
                for(int loop=0;loop<4;loop++){
                    CardOnFieldComoneList.get(loop).getChildren().clear();
                }
                game.setCom1CanPlay(false);
                game.plusSkip(1);
                System.out.println("skip : "+game.getSkip());
            }
            else if(indexCom1[4]==0){
                CardOnFieldComoneList.get(0).getChildren().add(Com1Hand.get(indexCom1[0]).imageview);
                Com1Hand.get(indexCom1[0]).setStatus(false);
                game.decreaseCom1hand(1);
                CardsOnField=Com1Hand.get(indexCom1[0]);
                game.setStageGame(0);
                System.out.println("COM1 Enter Card");
            }
            else if(indexCom1[4]==1){
                CardOnFieldComoneList.get(0).getChildren().add(Com1Hand.get(indexCom1[0]).imageview);
                CardOnFieldComoneList.get(1).getChildren().add(Com1Hand.get(indexCom1[1]).imageview);
                Com1Hand.get(indexCom1[0]).setStatus(false);
                Com1Hand.get(indexCom1[1]).setStatus(false);
                game.decreaseCom1hand(2);
                CardsOnField=Com1Hand.get(indexCom1[1]);
                game.setStageGame(1);
                System.out.println("COM1 Enter Card");

            }
            else if(indexCom1[4]==2){
                CardOnFieldComoneList.get(0).getChildren().add(Com1Hand.get(indexCom1[0]).imageview);
                CardOnFieldComoneList.get(1).getChildren().add(Com1Hand.get(indexCom1[1]).imageview);
                CardOnFieldComoneList.get(2).getChildren().add(Com1Hand.get(indexCom1[2]).imageview);
                Com1Hand.get(indexCom1[0]).setStatus(false);
                Com1Hand.get(indexCom1[1]).setStatus(false);
                Com1Hand.get(indexCom1[2]).setStatus(false);
                game.decreaseCom1hand(3);
                CardsOnField=Com1Hand.get(indexCom1[2]);
                game.setStageGame(2);
                System.out.println("COM1 Enter Card");

            }
            else if(indexCom1[4]==3){
                CardOnFieldComoneList.get(0).getChildren().add(Com1Hand.get(indexCom1[0]).imageview);
                CardOnFieldComoneList.get(1).getChildren().add(Com1Hand.get(indexCom1[1]).imageview);
                CardOnFieldComoneList.get(2).getChildren().add(Com1Hand.get(indexCom1[2]).imageview);
                CardOnFieldComoneList.get(3).getChildren().add(Com1Hand.get(indexCom1[3]).imageview);
                Com1Hand.get(indexCom1[0]).setStatus(false);
                Com1Hand.get(indexCom1[1]).setStatus(false);
                Com1Hand.get(indexCom1[2]).setStatus(false);
                Com1Hand.get(indexCom1[3]).setStatus(false);
                game.decreaseCom1hand(4);
                CardsOnField=Com1Hand.get(indexCom1[3]);
                game.setStageGame(3);
                System.out.println("COM1 Enter Card");
            }

    }

    private void bot2Play(){

        System.out.println("turn Bot 2");
       
        if(game.getSkip()==3&&game.getCom2CanPlay()==true){
            for(int loop=0;loop<4;loop++){
                CardOnFieldPlayerList.get(loop).getChildren().clear();
                CardOnFieldComoneList.get(loop).getChildren().clear();
                CardOnFieldComtwoList.get(loop).getChildren().clear();
                CardOnFieldComthreeList.get(loop).getChildren().clear();
            }
            game.setStartStage(true);
            game.setSkip(0);
        }

        indexCom2 = Bot.botCalculate(Com2Hand,CardsOnField, game);
            if(indexCom2[4]==4){
                for(int loop=0;loop<4;loop++){
                    CardOnFieldComtwoList.get(loop).getChildren().clear();
                }
                game.setCom2CanPlay(false);
                game.plusSkip(1);
                System.out.println("skip : "+game.getSkip());
            }
            else if(indexCom2[4]==0){
                CardOnFieldComtwoList.get(0).getChildren().add(Com2Hand.get(indexCom2[0]).imageview);
                Com2Hand.get(indexCom2[0]).setStatus(false);
                game.decreaseCom2hand(1);
                CardsOnField=Com2Hand.get(indexCom2[0]);
                game.setStageGame(0);
                System.out.println("COM2 Enter Card");
            }
            else if(indexCom2[4]==1){
                CardOnFieldComtwoList.get(0).getChildren().add(Com2Hand.get(indexCom2[0]).imageview);
                CardOnFieldComtwoList.get(1).getChildren().add(Com2Hand.get(indexCom2[1]).imageview);
                Com2Hand.get(indexCom2[0]).setStatus(false);
                Com2Hand.get(indexCom2[1]).setStatus(false);
                game.decreaseCom2hand(2);
                CardsOnField=Com2Hand.get(indexCom2[1]);
                game.setStageGame(1);
                System.out.println("COM2 Enter Card");

            }
            else if(indexCom2[4]==2){
                CardOnFieldComtwoList.get(0).getChildren().add(Com2Hand.get(indexCom2[0]).imageview);
                CardOnFieldComtwoList.get(1).getChildren().add(Com2Hand.get(indexCom2[1]).imageview);
                CardOnFieldComtwoList.get(2).getChildren().add(Com2Hand.get(indexCom2[2]).imageview);
                Com2Hand.get(indexCom2[0]).setStatus(false);
                Com2Hand.get(indexCom2[1]).setStatus(false);
                Com2Hand.get(indexCom2[2]).setStatus(false);
                game.decreaseCom2hand(3);
                CardsOnField=Com2Hand.get(indexCom2[2]);
                game.setStageGame(2);
                System.out.println("COM2 Enter Card");

            }
            else if(indexCom2[4]==3){
                CardOnFieldComtwoList.get(0).getChildren().add(Com2Hand.get(indexCom2[0]).imageview);
                CardOnFieldComtwoList.get(1).getChildren().add(Com2Hand.get(indexCom2[1]).imageview);
                CardOnFieldComtwoList.get(2).getChildren().add(Com2Hand.get(indexCom2[2]).imageview);
                CardOnFieldComtwoList.get(3).getChildren().add(Com2Hand.get(indexCom2[3]).imageview);
                Com2Hand.get(indexCom2[0]).setStatus(false);
                Com2Hand.get(indexCom2[1]).setStatus(false);
                Com2Hand.get(indexCom2[2]).setStatus(false);
                Com2Hand.get(indexCom2[3]).setStatus(false);
                CardsOnField=Com2Hand.get(indexCom2[3]);
                game.decreaseCom2hand(4);
                game.setStageGame(3);
                System.out.println("COM2 Enter Card");
            }

    }

    private void bot3Play(){

        System.out.println("turn Bot 3" );
            
            if(game.getSkip()==3&&game.getCom3CanPlay()==true){
                for(int loop=0;loop<4;loop++){
                    
                    CardOnFieldPlayerList.get(loop).getChildren().clear();
                    CardOnFieldComoneList.get(loop).getChildren().clear();
                    CardOnFieldComtwoList.get(loop).getChildren().clear();
                    CardOnFieldComthreeList.get(loop).getChildren().clear();
                }
                game.setStartStage(true);
                game.setSkip(0);
            }

            indexCom3 = Bot.botCalculate(Com3Hand,CardsOnField, game);
            if(indexCom3[4]==4){
                for(int loop=0;loop<4;loop++){
                    CardOnFieldComthreeList.get(loop).getChildren().clear();
                }
                game.setCom3CanPlay(false);
                game.plusSkip(1);
                System.out.println("skip : "+game.getSkip());
            }
            else if(indexCom3[4]==0){
                CardOnFieldComthreeList.get(0).getChildren().add(Com3Hand.get(indexCom3[0]).imageview);
                Com3Hand.get(indexCom3[0]).setStatus(false);
                game.decreaseCom3hand(1);
                CardsOnField=Com3Hand.get(indexCom3[0]);
                game.setStageGame(0);
                System.out.println("COM3 Enter Card");
            }
            else if(indexCom3[4]==1){
                CardOnFieldComthreeList.get(0).getChildren().add(Com3Hand.get(indexCom3[0]).imageview);
                CardOnFieldComthreeList.get(1).getChildren().add(Com3Hand.get(indexCom3[1]).imageview);
                Com3Hand.get(indexCom3[0]).setStatus(false);
                Com3Hand.get(indexCom3[1]).setStatus(false);
                game.decreaseCom3hand(2);
                CardsOnField=Com3Hand.get(indexCom3[1]);
                game.setStageGame(1);
                System.out.println("COM3 Enter Card");

            }
            else if(indexCom3[4]==2){
                CardOnFieldComthreeList.get(0).getChildren().add(Com3Hand.get(indexCom3[0]).imageview);
                CardOnFieldComthreeList.get(1).getChildren().add(Com3Hand.get(indexCom3[1]).imageview);
                CardOnFieldComthreeList.get(2).getChildren().add(Com3Hand.get(indexCom3[2]).imageview);
                Com3Hand.get(indexCom3[0]).setStatus(false);
                Com3Hand.get(indexCom3[1]).setStatus(false);
                Com3Hand.get(indexCom3[2]).setStatus(false);
                game.decreaseCom3hand(3);
                CardsOnField=Com3Hand.get(indexCom3[2]);
                game.setStageGame(2);
                System.out.println("COM3 Enter Card");

            }
            else if(indexCom3[4]==3){
                CardOnFieldComthreeList.get(0).getChildren().add(Com3Hand.get(indexCom3[0]).imageview);
                CardOnFieldComthreeList.get(1).getChildren().add(Com3Hand.get(indexCom3[1]).imageview);
                CardOnFieldComthreeList.get(2).getChildren().add(Com3Hand.get(indexCom3[2]).imageview);
                CardOnFieldComthreeList.get(3).getChildren().add(Com3Hand.get(indexCom3[3]).imageview);
                Com3Hand.get(indexCom3[0]).setStatus(false);
                Com3Hand.get(indexCom3[1]).setStatus(false);
                Com3Hand.get(indexCom3[2]).setStatus(false);
                Com3Hand.get(indexCom3[3]).setStatus(false);
                CardsOnField=Com3Hand.get(indexCom3[3]);
                game.decreaseCom3hand(4);
                game.setStageGame(3);
                System.out.println("COM3 Enter Card");
            }


    }

    private void checkVictory() throws IOException{
        if(game.getNumPlayerhand()==0)
        {   
            int count = 0;
            for(int loop=0;loop<4;loop++){
                if(game.getVictory(loop)==0){
                    count++;
                }
            }
            if(count==0){
                game.setVictory(game.getOrderVictory(),0);
                game.plusOrderVictory(1);
            }
            
        }

        if(game.getNumCom1hand()==0)
        {   
            int count = 0;
            for(int loop=0;loop<4;loop++){
                if(game.getVictory(loop)==1){
                    count++;
                }
            }
            if(count==0){
                game.setVictory(game.getOrderVictory(),1);
                game.plusOrderVictory(1);
            }
            
        }

        if(game.getNumCom2hand()==0)
        {   
            int count = 0;
            for(int loop=0;loop<4;loop++){
                if(game.getVictory(loop)==2){
                    count++;
                }
            }
            if(count==0){
                game.setVictory(game.getOrderVictory(),2);
                game.plusOrderVictory(1);
            }
            
        }

        if(game.getNumCom3hand()==0)
        {   
            int count = 0;
            for(int loop=0;loop<4;loop++){
                if(game.getVictory(loop)==3){
                    count++;
                }
            }
            if(count==0){
                game.setVictory(game.getOrderVictory(),3);
                game.plusOrderVictory(1);
            }
            
        }

        if(game.getOrderVictory()==3){
            int count0=0;
            int count1=0;
            int count2=0;
            int count3=0;
            for(int loop=0;loop<4;loop++){
                if(game.getVictory(loop)==0){
                    count0++;
                }
                if(game.getVictory(loop)==1){
                    count1++;
                }
                if(game.getVictory(loop)==2){
                    count2++;
                }
                if(game.getVictory(loop)==3){
                    count3++;
                }
            }
            if(count0==0){
                game.setVictory(3, 0);
            }
            else  if(count1==0){
                game.setVictory(3, 1);
            }
            else  if(count2==0){
                game.setVictory(3, 2);
            }
            else  if(count3==0){
                game.setVictory(3, 3);
            }
            
            nextRound();
        }
        
    }

    private void nextRound() throws IOException{
        
        

        if(roundGame==1){
            System.out.println("End Game");
            gotoEndScene();
        }
        roundGame++;
        victory[0]=game.getVictory(0);
        victory[1]=game.getVictory(1);
        victory[2]=game.getVictory(2);
        victory[3]=game.getVictory(3);
        
        
        try {
            for(int loop=0;loop<4;loop++){
                statusList.get(game.getVictory(loop)).getChildren().add(imageviewStatus.get(loop));
            }
        }
        catch (Exception ex) {
            System.out.println("status in  nextRound");
        }
        
 
        RandomHand rand = new RandomHand();
        this.playerHand = rand.getPlayerHand();
        this.Com1Hand = rand.getCom1Hand();
        this.Com2Hand = rand.getCom2Hand();
        this.Com3Hand = rand.getCom3Hand();
        for(int loop=0;loop<13;loop++)
        {
            paneTopList.get(loop).getChildren().clear();
            paneList.get(loop).getChildren().add(playerHand.get(loop).imageview);
        }
        
        game = new GameSesstion();
        
        game.setVictory(0, victory[0]);
        game.setVictory(1, victory[1]);
        game.setVictory(2, victory[2]);
        game.setVictory(3, victory[3]);

        imageviewButtonOn = SetpicMainPages.setpicOn();
        imageviewButtonOff = SetpicMainPages.setpicOff();
        imageviewButtonPush = SetpicMainPages.setpicPush();
        imageviewButtonTurnMark = SetpicMainPages.setpicTurnMark();
        imageviewNumberHands0 = SetpicMainPages.setpicNumberHands();
        imageviewNumberHands1 = SetpicMainPages.setpicNumberHands();
        imageviewNumberHands2 = SetpicMainPages.setpicNumberHands();
        imageviewNumberHands3 = SetpicMainPages.setpicNumberHands();

        game.setSelectStage(game.getStageGame());
        for(int loop=0;loop<4;loop++)
        {
            BList.get(loop).getChildren().add(imageviewButtonOn.get(loop));
        }
        BList.get(game.getSelectStage()).getChildren().clear();
        BList.get(game.getSelectStage()).getChildren().add(imageviewButtonPush.get(game.getSelectStage()));
        fethButton();
        checkLimitCards();
        game.setSelectCards(0);
        System.out.println(time.getTimecount());  
        setNumberHands();
        for(int loop=0;loop<4;loop++){
            CardOnFieldPlayerList.get(loop).getChildren().clear();
            CardOnFieldComoneList.get(loop).getChildren().clear();
            CardOnFieldComtwoList.get(loop).getChildren().clear();
            CardOnFieldComthreeList.get(loop).getChildren().clear();
        }
        try {
            turnPlayer.getChildren().clear();
            turnBot1.getChildren().clear();
            turnBot2.getChildren().clear();
            turnBot3.getChildren().clear();
        }
        catch (Exception ex) {
            System.out.println("clear pic turn on nextRound");
            
        }
    }

    @FXML
    private void exchange(){
        if(game.getExchangeCards()==true){
            game.setSelectCards(0);
            if(game.getVictory(0)==0){
                ArrayList<ComparableCard> playerHand2 = new ArrayList<ComparableCard>();
                playerHand2=playerHand;
                if(game.getVictory(3)==1){
                    playerHand = ComparableCard.exchange2Card(playerHand, game.getPlayerSelectIndex(0), game.getPlayerSelectIndex(1), Com1Hand.get(11), Com1Hand.get(12));
                    Com1Hand = ComparableCard.exchange2Card(Com1Hand, 11, 12, playerHand2.get(game.getPlayerSelectIndex(0)), playerHand2.get(game.getPlayerSelectIndex(0)));
                }
                else if(game.getVictory(3)==2){
                    playerHand = ComparableCard.exchange2Card(playerHand, game.getPlayerSelectIndex(0), game.getPlayerSelectIndex(1), Com2Hand.get(11), Com2Hand.get(12));
                    Com2Hand = ComparableCard.exchange2Card(Com2Hand, 11, 12, playerHand2.get(game.getPlayerSelectIndex(0)), playerHand2.get(game.getPlayerSelectIndex(0)));
                }
                else if(game.getVictory(3)==3){
                    playerHand = ComparableCard.exchange2Card(playerHand, game.getPlayerSelectIndex(0), game.getPlayerSelectIndex(1), Com3Hand.get(11), Com3Hand.get(12));
                    Com3Hand = ComparableCard.exchange2Card(Com3Hand, 11, 12, playerHand2.get(game.getPlayerSelectIndex(0)), playerHand2.get(game.getPlayerSelectIndex(0)));
                }
            }
            else  if(game.getVictory(0)==1){
                ArrayList<ComparableCard> Com1Hand2 = new ArrayList<ComparableCard>();
                Com1Hand2=Com1Hand;
                if(game.getVictory(3)==0){
                    Com1Hand = ComparableCard.exchange2Card(Com1Hand, 0, 1, playerHand.get(11), playerHand.get(12));
                    playerHand = ComparableCard.exchange2Card(playerHand, 11, 12, Com1Hand2.get(0), Com1Hand2.get(1));
                }
                else if(game.getVictory(3)==2){
                    Com1Hand = ComparableCard.exchange2Card(Com1Hand, 0, 1, Com2Hand.get(11), Com2Hand.get(12));
                    Com2Hand = ComparableCard.exchange2Card(Com2Hand, 11, 12, Com1Hand2.get(0), Com1Hand2.get(1));
                }
                else if(game.getVictory(3)==3){
                    Com1Hand = ComparableCard.exchange2Card(Com1Hand, 0, 1, Com3Hand.get(11), Com3Hand.get(12));
                    Com3Hand = ComparableCard.exchange2Card(Com3Hand, 11, 12, Com1Hand2.get(0), Com1Hand2.get(1));
                }
            }
            else  if(game.getVictory(0)==2){
                ArrayList<ComparableCard> Com2Hand2 = new ArrayList<ComparableCard>();
                Com2Hand2=Com2Hand;
                if(game.getVictory(3)==0){
                    Com2Hand = ComparableCard.exchange2Card(Com2Hand, 0, 1, playerHand.get(11), playerHand.get(12));
                    playerHand = ComparableCard.exchange2Card(playerHand, 11, 12, Com2Hand2.get(0), Com2Hand2.get(1));
                }
                else if(game.getVictory(3)==1){
                    Com2Hand = ComparableCard.exchange2Card(Com2Hand, 0, 1, Com1Hand.get(11), Com1Hand.get(12));
                    Com1Hand = ComparableCard.exchange2Card(Com1Hand, 11, 12, Com2Hand2.get(0), Com2Hand2.get(1));
                }
                else if(game.getVictory(3)==3){
                    Com2Hand = ComparableCard.exchange2Card(Com2Hand, 0, 1, Com3Hand.get(11), Com3Hand.get(12));
                    Com3Hand = ComparableCard.exchange2Card(Com3Hand, 11, 12, Com2Hand2.get(0), Com2Hand2.get(1));
                }
            }
            else  if(game.getVictory(0)==3){
                ArrayList<ComparableCard> Com3Hand2 = new ArrayList<ComparableCard>();
                Com3Hand2=Com3Hand;
                if(game.getVictory(3)==0){
                    Com3Hand = ComparableCard.exchange2Card(Com3Hand, 0, 1, playerHand.get(11), playerHand.get(12));
                    playerHand = ComparableCard.exchange2Card(playerHand, 11, 12, Com3Hand2.get(0), Com3Hand2.get(1));
                }
                else if(game.getVictory(3)==1){
                    Com3Hand = ComparableCard.exchange2Card(Com3Hand, 0, 1, Com1Hand.get(11), Com1Hand.get(12));
                    Com1Hand = ComparableCard.exchange2Card(Com1Hand, 11, 12, Com3Hand2.get(0), Com3Hand2.get(1));
                }
                else if(game.getVictory(3)==2){
                    Com3Hand = ComparableCard.exchange2Card(Com3Hand, 0, 1, Com2Hand.get(11), Com2Hand.get(12));
                    Com2Hand = ComparableCard.exchange2Card(Com2Hand, 11, 12, Com3Hand2.get(0), Com3Hand2.get(1));
                }
            }

            if(game.getVictory(1)==0){
                ArrayList<ComparableCard> playerHand2 = new ArrayList<ComparableCard>();
                playerHand2=playerHand;
                if(game.getVictory(2)==1){
                    playerHand = ComparableCard.exchange1Card(playerHand, game.getPlayerSelectIndex(0), Com1Hand.get(12));
                    Com1Hand = ComparableCard.exchange1Card(Com1Hand, 12, playerHand2.get(game.getPlayerSelectIndex(0)));
                }
                else if(game.getVictory(2)==2){
                    playerHand = ComparableCard.exchange1Card(playerHand, game.getPlayerSelectIndex(0), Com2Hand.get(12));
                    Com2Hand = ComparableCard.exchange1Card(Com2Hand, 12, playerHand2.get(game.getPlayerSelectIndex(0)));
                }
                else if(game.getVictory(2)==3){
                    playerHand = ComparableCard.exchange1Card(playerHand, game.getPlayerSelectIndex(0), Com3Hand.get(12));
                    Com3Hand = ComparableCard.exchange1Card(Com3Hand, 12, playerHand2.get(game.getPlayerSelectIndex(0)));
                }
            }
            else  if(game.getVictory(1)==1){
                ArrayList<ComparableCard> Com1Hand2 = new ArrayList<ComparableCard>();
                Com1Hand2 = Com1Hand;
                if(game.getVictory(2)==0){
                    Com1Hand = ComparableCard.exchange1Card(Com1Hand, 0, playerHand.get(12));
                    playerHand = ComparableCard.exchange1Card(playerHand, 12, Com1Hand2.get(0));
                }
                else if(game.getVictory(2)==2){
                    Com1Hand = ComparableCard.exchange1Card(Com1Hand, 0, Com2Hand.get(12));
                    Com2Hand = ComparableCard.exchange1Card(Com2Hand, 12, Com1Hand2.get(0));
                }
                else if(game.getVictory(2)==3){
                    Com1Hand = ComparableCard.exchange1Card(Com1Hand, 0, Com3Hand.get(12));
                    Com3Hand = ComparableCard.exchange1Card(Com3Hand, 12, Com1Hand2.get(0));
                }
            }
            else  if(game.getVictory(1)==2){
                ArrayList<ComparableCard> Com2Hand2 = new ArrayList<ComparableCard>();
                Com2Hand2 = Com2Hand;
                if(game.getVictory(2)==0){
                    Com2Hand = ComparableCard.exchange1Card(Com2Hand, 0, playerHand.get(12));
                    playerHand = ComparableCard.exchange1Card(playerHand, 12, Com2Hand2.get(0));
                }
                else if(game.getVictory(2)==1){
                    Com2Hand = ComparableCard.exchange1Card(Com2Hand, 0, Com1Hand.get(12));
                    Com1Hand = ComparableCard.exchange1Card(Com1Hand, 12, Com2Hand2.get(0));
                }
                else if(game.getVictory(2)==3){
                    Com2Hand = ComparableCard.exchange1Card(Com2Hand, 0, Com3Hand.get(12));
                    Com3Hand = ComparableCard.exchange1Card(Com3Hand, 12, Com2Hand2.get(0));
                }
            }
            else  if(game.getVictory(1)==3){
                ArrayList<ComparableCard> Com3Hand2 = new ArrayList<ComparableCard>();
                Com3Hand2 = Com3Hand;
                if(game.getVictory(2)==0){
                    Com3Hand = ComparableCard.exchange1Card(Com3Hand, 0, playerHand.get(12));
                    playerHand = ComparableCard.exchange1Card(playerHand, 12, Com3Hand2.get(0));
                }
                else if(game.getVictory(2)==1){
                    Com3Hand = ComparableCard.exchange1Card(Com3Hand, 0, Com1Hand.get(12));
                    Com1Hand = ComparableCard.exchange1Card(Com1Hand, 12, Com3Hand2.get(0));
                }
                else if(game.getVictory(2)==2){
                    Com3Hand = ComparableCard.exchange1Card(Com3Hand, 0, Com2Hand.get(12));
                    Com2Hand = ComparableCard.exchange1Card(Com2Hand, 12, Com3Hand2.get(0));
                }
            }
            try {
            
                paneTopList.get(game.getPlayerSelectIndex(0)).getChildren().clear();
                paneTopList.get(game.getPlayerSelectIndex(1)).getChildren().clear();
            }
            catch (Exception ex) {
                System.out.println("clear card select to Top Exchange");
            }
            try {
                for(int loop=0;loop<13;loop++)
                {
                    paneList.get(loop).getChildren().clear();
                    paneList.get(loop).getChildren().add(playerHand.get(loop).imageview);

                }
            }
            catch (Exception ex) {
                System.out.println("exchange");
                
            }
            firstPlay();
            fethButton();
        } 
    }
    
    @FXML
    void gotoEndScene() throws IOException {
        mediaPlayer.pause();
        saveFile ();
        Parent EndParent = FXMLLoader.load(getClass().getResource("EndGame.fxml"));
        Scene EndScene = new Scene(EndParent);
        Stage window =  (Stage)next.getScene().getWindow();
        window.setScene(EndScene);
        window.setTitle("End");
        window.show();
    }

    @FXML
    void gotoMenuButtonAction(ActionEvent event) throws IOException {

        mediaPlayer.pause();
        Parent menuParent = FXMLLoader.load(getClass().getResource("MenuScene.fxml"));
        Scene menuScene = new Scene(menuParent);
        Stage window =  (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(menuScene);
        window.setTitle("Slave Menu");
        window.show();
    }

    private void saveFile () {
        try( DataOutputStream file = new DataOutputStream(new FileOutputStream("src/order.dat"))) {
            
            file.flush();
            file.writeInt(game.getVictory(0));
            file.writeInt(game.getVictory(1));
            file.writeInt(game.getVictory(2));
            file.writeInt(game.getVictory(3));
            // file.writeUTF(tfNumberOfYears.getText());
            // file.writeUTF(tfLoanAmount.getText());
        
        } catch (Exception ex) {
            
            ex.printStackTrace();
        }
        
    }
}
