/**
 *
 */
package edu.hm.cs.vss;

/**
 * @author Michael
 *
 */
public class Philosoph implements Runnable {

    private final Table myTable;
    private final int number;

    private int firstfork;
    private int secondfork;

    private int seatNr;

    public Philosoph(Table myTable,int number){
        this.myTable = myTable;
        this.number = number;
        if(seatNr%2==0){
            firstfork = seatNr;
            secondfork = seatNr+1;
        }else{
            firstfork = seatNr+1;
            secondfork = seatNr;
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true){
        //for(int runs=0;runs<5;runs++){

            int seatNr = -1;
            try {
                //Thread.sleep(50);

                while((seatNr = myTable.getSeat())==-1){
                    Thread.sleep(10);
                }
                System.out.println("Platz Nr "+seatNr+" PH "+number);
                // Sitzen
                while(!getForks()){
                    //Warten auf Gabeln
                    Thread.sleep(50);
                }
                System.out.println("essen auf platz nr " +seatNr+" PH "+number);
                myTable.returnFork(firstfork);
                myTable.returnFork(secondfork);

               myTable.standUp(seatNr);
               // Ausgabe ergibt keinen Sin!
               // System.out.println("PH "+number+" stand up");
                //Chillen
                Thread.sleep(600);

            } catch (Exception e) {
            // TODO: handle exception
            }
        }
    }

    private boolean getForks(){
            //get all the forks!!!
            //first
            if(myTable.getFork(firstfork)){
                for(int runs = 0;runs<5;runs++){
                    if(myTable.getFork(secondfork)) return true;
                }
                myTable.returnFork(firstfork);
                return false;
            }
            return false;
    }

}
