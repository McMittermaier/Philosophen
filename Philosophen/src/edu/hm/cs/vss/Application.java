/**
 *
 */
package edu.hm.cs.vss;

/**
 * @author Michael
 *
 */
public class Application {

    private static final int ANZ_PHILOSOPHS =5;
    private static final int ANZ_HUNGRY_PH = 5;
    private static final int ANZ_TABLESEATS = 7;

    private final static Thread[] philosophs = new Thread[ANZ_PHILOSOPHS+ANZ_HUNGRY_PH];

    /**
     * @param args
     *
     *
     */
    public static void main(String[] args) {
        Table dineTable = new Table(ANZ_TABLESEATS);
        //Philosophen erzeugen
        for(int i = 0;i<ANZ_PHILOSOPHS;i++){
            philosophs[i] = new Philosoph(dineTable,i,false);
        }
        for(int i = ANZ_PHILOSOPHS;i<ANZ_PHILOSOPHS+ANZ_HUNGRY_PH;i++){
            philosophs[i] = new Philosoph(dineTable,i,true);
        }
        //Philosophen starten

        for(Thread i : philosophs){
            i.start();
        }
        new Output(dineTable,philosophs).start();
        //new Tuersteher(dineTable,philosophs).start();

    }

}
