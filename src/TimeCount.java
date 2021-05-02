
import java.util.Timer;
import java.util.TimerTask;


public class TimeCount {
      
      Timer myTimer;
      int timecount = 0;   
      

      public TimeCount() {
         this.myTimer = new Timer();
         myTimer.schedule(new TimerTask() {
            

            public void run() {
            
               timecount+=1;
            }
         }, 0, 1000);
      }


      public int getTimecount() {
         return timecount;
      }


      public void setTimecount(int timecount) {
         this.timecount = timecount;
      }

      

     

      
    

}
