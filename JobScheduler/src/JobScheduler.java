
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class JobScheduler {
    ArrayList<Job> schedule = new ArrayList<Job>();
    PriorityQue que = new PriorityQue();
    ArrayList<Job> finished=new ArrayList<Job>();
    Job current;
    int clock = 0;

    public static void main(String args[]) {

        JobScheduler job = new JobScheduler();
        try {
            Scanner scan = new Scanner(args[0]);
            job.readJobs(scan.nextLine());
        } catch (ArrayIndexOutOfBoundsException e) {
            Scanner scan = new Scanner(System.in);
            job.readJobs(scan.nextLine());
        }
        job.runJob();
    }

    public void readJobs(String file) {
        try {
            Scanner scan = new Scanner(new FileReader(file));
            for (int y = 0; scan.hasNext(); y++) {
                int[] input = new int[4];
                for (int x = 0; x < 4; x++) {
                    input[x] = scan.nextInt();
                }
                Job in = new Job(input[0], input[1], input[2], input[3]);
                schedule.add(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Jobs to run:");
        for (int x = 0; x < schedule.size(); x++) {
            System.out.println(schedule.get(x).getNumber() + " " + schedule.get(x).getPriority() + " " + schedule.get(x).getAvailable() + " " + schedule.get(x).getTimeFinish());
        }
    }

    public void setSchedule() {

        for (int x = 0; x < schedule.size(); x++) {
            if (clock >= schedule.get(x).getAvailable()) {
                que.insert(schedule.get(x));
                que.get(x).setTimeWait(clock);
               // System.out.println("Arrived:" + schedule.get(x).print()+" clock:"+clock);
                schedule.remove(x);
                x--;
            }
        }

        //System.out.println();
        // que.print();

    }

    public boolean jobAvailable() {
        if (que.peek().getAvailable() >= clock) {
            return true;
        }
        return false;
    }

    public void runJob() {
        while (clock < 104) {
            clock++;
            if (!que.isEmpty()) {
                current = que.peek();
                if(current.started==false){
                    current.setTimeWaited(clock-current.getTimeWait()-1);
                }
                if(current!=null) {
                    current.tickTimeEval();
                    current.started=true;
                    System.out.println("Time:"+clock+" Running Job:"+ current.getNumber());
                }
                if (current!=null&&current.getTimeEval() >= current.getTimeFinish()) {
                    current.finished = true;
                    finished.add(current);

                   // System.out.println("Time:"+clock+" "+current.getNumber()+ " finished");
                    finishJob();
                }

            }else {
                System.out.println("Time:" + clock + " No Job Running");
            }
            setSchedule();

        }
        System.out.println("Job Number  Waiting Time    Execution Time");
        for (int x = 0; x < finished.size(); x++) {
            System.out.println(finished.get(x).getNumber()+"            "+finished.get(x).getTimeWaited()+"            "+ finished.get(x).getTimeEval());
        }


    }

    public void finishJob() {
        que.deleteMax();
    }

    public void tickClock() {
        clock++;
    }



}
