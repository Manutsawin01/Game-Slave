import java.util.ArrayList;
public class Bot {
    
    
    public static int[] botCalculate(ArrayList<ComparableCard> comHand,ComparableCard cardOnField,GameSesstion game){
        int[] indexCard={0,0,0,0,4};
            
            if(game.getStartStage()==true){
                
                game.setSkip(0);
                for(int loop=0;loop<4;loop++)
                {
                    game.setStatusButtontrue(loop);
                }
    
                cardOnField.setValue(0);
                for(int loop=0;loop<13;loop++){
                    if(comHand.get(loop).getStatus()==true){
                        indexCard[0]=loop;
                        indexCard[4]=0;
                        break;
                    }
                }

                if(indexCard[4]==4){
                    game.setStartStage(true);
                }
                else{
                    game.setStartStage(false);
                }

                game.setCom1CanPlay(true);
                game.setCom2CanPlay(true);
                game.setCom3CanPlay(true);
                game.setPlayerCanPlay(true);
                
                
            }
            else{
                if(game.getStageGame()==0){
                    for(int loop=0;loop<13;loop++){
                        if(comHand.get(loop).checkValue1(cardOnField)==1&&comHand.get(loop).getStatus()==true){
                            indexCard[0]=loop;
                            indexCard[4]=0;
                           
                            break;
                        }
                    }
                    
                    if(indexCard[4]==4){
                        if(ComparableCard.threeCardsOnHand(comHand)==true){
                            int index = Bot.findIndexThreeCard(comHand);
                            if(comHand.get(index).getValue()>cardOnField.getValue()){
                                indexCard[0]=index;
                                indexCard[1]=index+1;
                                indexCard[2]=index+2;
                                indexCard[4]=2;
                            }
                            
                           
                        }
                    }
                }
                else if(game.getStageGame()==1){
                    if(ComparableCard.twoCardsOnHand(comHand)==true){
                        int index = Bot.findIndexTwoCard(comHand);
                        if(comHand.get(index).getValue()>cardOnField.getValue()){
                            indexCard[0]=index;
                            indexCard[1]=index+1;
                            indexCard[4]=1;
                        }
                        
                        
                    }

                    if(indexCard[4]==4){
                        if(ComparableCard.fourCardsOnHand(comHand)==true){
                            int index = Bot.findIndexFourCard(comHand);
                            if(comHand.get(index).getValue()>cardOnField.getValue()){
                                indexCard[0]=index;
                                indexCard[1]=index+1;
                                indexCard[2]=index+2;
                                indexCard[3]=index+3;
                                indexCard[4]=3;
                            }
                        }
                    }
    
                }
                else if(game.getStageGame()==2){
                    if(ComparableCard.threeCardsOnHand(comHand)==true){
                        int index = Bot.findIndexThreeCard(comHand);
                        if(comHand.get(index).getValue()>cardOnField.getValue()){
                            indexCard[0]=index;
                            indexCard[1]=index+1;
                            indexCard[2]=index+2;
                            indexCard[4]=2;
                        }
                    }
                }
                else if(game.getStageGame()==3){
                    if(ComparableCard.fourCardsOnHand(comHand)==true){
                        int index = Bot.findIndexFourCard(comHand);
                        if(comHand.get(index).getValue()>cardOnField.getValue()){
                            indexCard[0]=index;
                            indexCard[1]=index+1;
                            indexCard[2]=index+2;
                            indexCard[3]=index+3;
                            indexCard[4]=3;
                        }
                    }
                }
            }
        return indexCard;
    }

    public static int findIndexFourCard(ArrayList<ComparableCard> comHand){
        int count=0;
        int sameValue=0;
        for(int loop=0;loop<13;loop++){
            if(sameValue==comHand.get(loop).getValue()&&comHand.get(loop).getStatus()==true){
                count++;
            }
            else if(comHand.get(loop).getStatus()==true){
                sameValue=comHand.get(loop).getValue();
                count=0;
            }
            if(count==3)
            {
                return loop-3;
            }
        }
        
        return 0;
    }

    
    public static int findIndexThreeCard(ArrayList<ComparableCard> comHand){
        int count=0;
        int sameValue=0;
        for(int loop=0;loop<13;loop++){
            if(sameValue==comHand.get(loop).getValue()&&comHand.get(loop).getStatus()==true){
                count++;
            }
            else if(comHand.get(loop).getStatus()==true){
                sameValue=comHand.get(loop).getValue();
                count=0;
            }
            if(count==2)
            {
                return loop-2;
            }
        }
        
        return 0;
    }

    public static int findIndexTwoCard(ArrayList<ComparableCard> comHand){
        int count=0;
        int sameValue=0;
        for(int loop=0;loop<13;loop++){
            if(sameValue==comHand.get(loop).getValue()&&comHand.get(loop).getStatus()==true){
                count++;
            }
            else if(comHand.get(loop).getStatus()==true){
                sameValue=comHand.get(loop).getValue();
                count=0;
            }
            if(count==1)
            {
                return loop-1;
            }
        }
        
        return 0;
    }
}
