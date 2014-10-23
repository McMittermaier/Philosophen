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
        System.out.println("++++++++++++++++++++++++++++ Waiter "+table.getWaiters());
        try {
            while(true){

            Thread.sleep(1000);

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
           System.out.println("Philosophen  "+out);
            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("problem outputter");
        }
    }

}
