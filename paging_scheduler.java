
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication31;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class process{
    String name;
    int burstTime;
    int priority;
    int arrivalTime;
    int responseTime;
    int waitingTime =0;
    ArrayList<Integer> runningTime;
    public process(String name,int arrivalTime,int burstTime,int priority){
        this.name = name;
        this.burstTime = burstTime;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        runningTime = new ArrayList<Integer>();
    } 
    public void CalculateResponseTime(){
        responseTime = runningTime.get(0) - arrivalTime;
    }
    public void CalculateWaitingTime(){
        waitingTime = runningTime.get(0) - arrivalTime;
        int checker = runningTime.get(0);
        for(int i = 0;i<runningTime.size();i++){
            if(runningTime.get(i)>checker+1){
                waitingTime += runningTime.get(i) - (checker);
            }
            checker = runningTime.get(i);
        }
    }
    public String toString(){
        String runningTimes="";
        for(int i = 0;i<runningTime.size();i++){
            runningTimes += ","+runningTime.get(i);
        }
        return "" + name + " at: "+ arrivalTime + " bt" + burstTime + " pri:"+ priority 
                + "ResponseTime : "+ responseTime
                + "\n WaitingTime : "+ waitingTime
                + "\n Running Times : \n"+ runningTimes;
        
    }
}

/**
 *
 * @author sadievrenseker
 */
public class Scheduler {
    int TQ; // time quadrant
    int contextSwitchCost;
    ArrayList<process> readyQueue ;
    ArrayList<process> finishedProcesses ;
    void readFile() throws Exception{
        FileReader fr = new FileReader(new File("input.txt"));
        BufferedReader br = new BufferedReader(fr);
        int lineCount =0;
        String line;
        while((line = br.readLine())!=null){
            lineCount++;
            if(lineCount == 1){
                TQ = Integer.parseInt(line);
            }
            else if(lineCount == 2){
                contextSwitchCost = Integer.parseInt(line);
            }
            else{
                StringTokenizer st = new StringTokenizer(line," ");
                String token;
                String name;
                int values [] = new int[3];
                name = st.nextToken();
                int i = 0;
                
                while(st.hasMoreTokens()){
                   values[i++] = Integer.parseInt(st.nextToken());
                }
                readyQueue.add(new process(name,values[0],values[1],values[2]));
                
               
                
            }
            
        }
    }
    void PrintQueue(ArrayList<process> al){
        
        for(process p : al){
            System.out.println(p);
           
        }
    }
    void calculateResponseTimes(ArrayList<process> al){
        int twt=0,trt=0;// total wait time and total responseTime
         for(process p : al){
            p.CalculateResponseTime(); 
            trt+= p.responseTime;
            p.CalculateWaitingTime();
            twt += p.waitingTime;
        }
         System.out.println(" Average Waiting Time : "+ (float)((float)twt/(float)al.size()) 
                 + " Average Response Time "+ (float)((float)trt/(float)al.size()));
    }
    public Scheduler(){
         readyQueue = new ArrayList<process>();
         finishedProcesses = new ArrayList<process>();
         try{
            readFile();
         }catch(Exception e){
             e.printStackTrace();
         }
         PrintQueue(readyQueue);
         int timeTick = 0;
         String runningProcessName="";
         while(!readyQueue.isEmpty()){
                // SJF preemptive 
                int i = 0;
               
                // search for the first process, which is arrived
                while(readyQueue.size()>i && readyQueue.get(i).arrivalTime > timeTick){
                    i++;
                }
                // if there is no arrived process, than increase timeTick
                if(i==readyQueue.size()){
                    System.out.println("TimeTick: "+timeTick+" There is no process to run");
                    timeTick++;
                    continue;
                    // we dont have an arrived process yet, so go for next iteration
                }
                int sj = readyQueue.get(i).burstTime;
               
                int addr = 0;
                // we have at least 1 arrived process
                // so search for shorter jobs 
                for(int j = i ; j< readyQueue.size();j++){
                    if(sj>readyQueue.get(j).burstTime && timeTick>=readyQueue.get(j).arrivalTime){
                        sj = readyQueue.get(j).burstTime;
                        addr = j;
                    }
                }
               //  System.out.println(" sj "+sj);
                // run the process
                if(!runningProcessName.equals(readyQueue.get(addr).name)){
                    timeTick += contextSwitchCost;
                    runningProcessName = readyQueue.get(addr).name;
                }
                readyQueue.get(addr).burstTime--;
                readyQueue.get(addr).runningTime.add(timeTick);
                System.out.println("TimeTick: "+timeTick + " Running "+readyQueue.get(addr).name);
                if(readyQueue.get(addr).burstTime==0){
                    finishedProcesses.add(readyQueue.get(addr));
                    readyQueue.remove(addr);
                    
                }
                timeTick++;
            }
            calculateResponseTimes(finishedProcesses);
            PrintQueue(finishedProcesses);
         }
  
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       Scheduler s = new Scheduler();
    }
    
}
