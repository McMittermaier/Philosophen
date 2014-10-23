/**
 *
 */
package edu.hm.cs.vss;

/**
 * @author Michael
 *
 */
public class Philosoph extends Thread {

    private final static long EAT_TIME = 1;
    private long MEDIATE_TIME = 5;
    private final long SLEEP_TIME = 10;

    private final Table myTable;
    private final int number;

    private int firstfork;
    private int secondfork;

    private int seatNr;
    private final int tableSize;

    private int meals;
    private boolean blocked = false;

    private final boolean hungryOne;

    public Philosoph(Table myTable,int number, boolean hungryOne){
        this.myTable = myTable;
        this.number = number;
        this.hungryOne=hungryOne;
        this.tableSize=myTable.getTableSize();
        if(hungryOne){
            MEDIATE_TIME /=2;
        }

    }

    @Override

    public void run() {

        while(true){
        //for(int runs=0;runs<5;runs++){

            seatNr = -1;
            try {
                  for(int runs=0;runs<5;runs++){
                    // Versuch einen Sitzplatz zu ergattern

                     if(blocked){
                         blocked = false;
                         Thread.sleep(20);
                         //System.out.println("leg dich schlafen");

                     }

                     if((seatNr = myTable.getSeatOptimized())==-1){
                        System.out.println("Probleme bei der Sitzverteilung");
                    }
                    // Gabelzuteilung
                    if(seatNr%2==0){
                        firstfork = seatNr;
                        secondfork = (seatNr+1)%tableSize;
                    }else{
                        firstfork = (seatNr+1)%tableSize;
                        secondfork = seatNr;
                    }
                    //System.out.println("Platz Nr "+seatNr+" PH "+number + "linke gabel "+firstfork+" rechte gabel "+secondfork);
                    // Sitzen
                    while(!getForks()){
                        //Warten auf Gabeln
                        Thread.sleep(EAT_TIME);
                    }
                    //Essen
                    eat();

                    myTable.returnFork(firstfork);
                    myTable.returnFork(secondfork);
                    myTable.standUp(seatNr);
                    //Chillen
                    mediate();
                  }
              Thread.sleep(SLEEP_TIME);

            } catch (Exception e) {
            // TODO: handle exception
                System.out.println("Problem PH thread -- " + number );
                System.out.println(e);
            }
        }
    }

    private boolean getForks() throws InterruptedException{
            //get all the forks!!!


            if(myTable.getFork(firstfork)){
                for(int runs = 0;runs<5;runs++){
                    //Thread.sleep(10);
                    if(myTable.getFork(secondfork)) return true;
                }
                myTable.returnFork(firstfork);
                return false;
            }
            return false;
    }

    private void eat() throws InterruptedException{
        myTable.incEater(seatNr);
        meals++;
        //System.out.println("essen auf platz nr " +seatNr+" PH "+number);
        Thread.sleep(EAT_TIME);
    }

    private void mediate() throws InterruptedException{
        Thread.sleep(MEDIATE_TIME);
    }

    public int getMeals(){
        return meals;
    }

    public void setBlocked(){
        this.blocked = true;
    }

}
