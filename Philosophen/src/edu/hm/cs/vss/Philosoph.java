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

        while(true){
        //for(int runs=0;runs<5;runs++){

            int seatNr = -1;
            try {

                // Versuch einen Sitzplatz zu ergattern
                if((seatNr = myTable.getSeatOptimized())==-1){
                    System.out.println("Probleme bei der Sitzverteilung");
                }
                //System.out.println("Platz Nr "+seatNr+" PH "+number);
                // Sitzen
                Thread.sleep(40);
                while(!getForks()){
                    //Warten auf Gabeln
                    Thread.sleep(40);
                }
                myTable.incEater(seatNr);
                Thread.sleep(30);
                //System.out.println("essen auf platz nr " +seatNr+" PH "+number);
                myTable.returnFork(firstfork);
                myTable.returnFork(secondfork);
                //*/
                //Thread.sleep(30);

               myTable.standUp(seatNr);
               // Ausgabe ergibt keinen Sin!
               //System.out.println("PH "+number+" stand up");
                //Chillen
                //Thread.sleep(600);

            } catch (Exception e) {
            // TODO: handle exception
                System.out.println("Problem PH thread");
            }
        }
    }

    private boolean getForks() throws InterruptedException{
            //get all the forks!!!
            //first
            if(myTable.getFork(firstfork)){
                for(int runs = 0;runs<5;runs++){
                    Thread.sleep(10);
                    if(myTable.getFork(secondfork)) return true;
                }
                myTable.returnFork(firstfork);
                return false;
            }
            return false;
    }

}
