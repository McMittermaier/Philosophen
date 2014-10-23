package edu.hm.cs.vss;

public class Tuersteher extends Thread {

    private final Thread[] philosophen;
    private final Table myTable;

    public Tuersteher(Table myTable,Thread[] philosophen){
        this.philosophen=philosophen;
        this.myTable = myTable;
    }


    @Override
    public void run(){
        while(true){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            int sumMeals = 0;
            for( Thread t :philosophen){
                sumMeals += ((Philosoph)t).getMeals();
            }
            for( Thread t :philosophen){
                if(((Philosoph)t).getMeals()>(sumMeals/(double)philosophen.length)+10){
                    //System.out.println("Blocke Thread"+ ((Philosoph)t).getId());
                    ((Philosoph)t).setBlocked();

                }
            }

        }
    }
}
