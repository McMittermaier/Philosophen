package edu.hm.cs.vss;

public class Output extends Thread {

    private final Table table;
    private final Thread[] philosophen;

    public Output(Table table,Thread[] philosophen){
        this.table=table;
        this.philosophen= philosophen;

    }

    @Override
    public void run(){
        long startTime = System.currentTimeMillis();
        try {
            while(true){

            Thread.sleep(100);

            //philosophen[3].wait(50);

            //System.out.println("Waiter "+table.getWaiters());

            String out = "";
            int[] eater = table.getAuslastung();
            int sumEater = 0;
            for( int valE : table.getAuslastung()){
                sumEater+=valE;
            }
            for( int valE : table.getAuslastung()){
                out = out + Math.round((valE/(double)sumEater)*100) + "%  | ";
            }
           //System.out.println("Eater  "+out+ sumEater);

            String outMeals = "";
            int sumMeals=0;
            out = "";
            for( Thread t :philosophen){
                int meals = ((Philosoph)t).getMeals();
                sumMeals += meals;
                out = out + meals + " | ";
            }

            System.out.println("Philosophen  "+out+Math.round(sumMeals/(double)philosophen.length)+" Runtime "+(System.currentTimeMillis()-startTime));


            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("problem outputter");
            System.out.println(e);
        }
    }

}
