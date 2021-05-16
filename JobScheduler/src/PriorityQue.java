import java.util.ArrayList;

public class PriorityQue {
    ArrayList<Job> que = new ArrayList<Job>();

    public PriorityQue() {

    }

    public void insert(Job job) {
        ///ArrayList<int[]> temp = new ArrayList<int[]>();

        que.add(job);
        int size = que.size();
        if (size > 1) {
            for (int x = size-1; x > 0; x--) {
                if(que.get(x).getPriority()>que.get(x-1).getPriority()) {
                    Job temp = new Job(0, 0, 0, 0);
                    temp = que.get(x-1);
                    que.set(x-1,que.get(x));
                    que.set(x,temp);

                }
            }
        }

    }

    public Job deleteMax(){
        Job temp = new Job(0, 0, 0, 0);
        temp=que.get(0);
        que.remove(0);
        return temp;

    }
    public Job get(int x){
        return que.get(x);
    }

    public int size(){
        return que.size();
    }

    public Job peek(){
        return que.get(0);
    }

    public boolean isEmpty(){
        if (que.size()>0){
            return false;
        }
        return true;
    }


    public void print() {
        for (int x = 0; x < que.size(); x++) {
            System.out.println(que.get(x).getNumber() + " " + que.get(x).getPriority() + " " + que.get(x).getAvailable() + " " + que.get(x).getTimeFinish());

        }
    }
}
