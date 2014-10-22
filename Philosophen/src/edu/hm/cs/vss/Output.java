package edu.hm.cs.vss;

public class Output extends Thread {

    private final Table table;

    public Output(Table table){
        this.table=table;

    }

    @Override
    public void run(){
        System.out.println("++++++++++++++++++++++++++++ Waiter "+table.getWaiters());
        try {
            while(true){

            this.sleep(500);

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
            System.out.println("Eater  "+out+ sumEater);

            }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("problem outputter");
        }
    }

}
