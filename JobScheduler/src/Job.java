public class Job {
    int number;
    int priority;
    int available;
    int timeFinish;
    int timeWait=0;
    int timeWaited=0;
    int timeEval=0;
    boolean finished=false;
    boolean started=false;

    public Job(int number,int priority,int available,int timeFinish){
        this.number=number;
        this.priority=priority;
        this.available=available;
        this.timeFinish=timeFinish;
    }

    public void passTime(){
        timeWait +=1;
    }

    public int getAvailable() {
        return available;
    }

    public int getNumber() {
        return number;
    }

    public int getPriority() {
        return priority;
    }

    public int getTimeFinish() {
        return timeFinish;
    }

    public void setTimeWaited(int x){timeWaited=x;}

    public int getTimeWaited(){return timeWaited;}

    public int getTimeWait(){return timeWait;}

    public int getTimeEval(){return timeEval;}

    public void setTimeWait(int x){
        timeWait = x;
    }

    public void tickTimeEval(){
        timeEval+=1;
    }

    public String print(){
         String p=(getNumber()+" "+getPriority()+" "+getAvailable()+" "+getTimeFinish());
         return p;
    }


}
