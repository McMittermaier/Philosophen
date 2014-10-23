/**
 *
 */
package edu.hm.cs.vss;

/**
 * @author Michael
 *
 */
public class Application {

    private static final int ANZ_PHILOSOPHS =6;
    private static final int ANZ_HUNGRY_PH = 1;
    private static final int ANZ_TABLESEATS = 7;

    private final static Thread[] philosophs = new Thread[ANZ_PHILOSOPHS+ANZ_HUNGRY_PH];

    /**
     * @param args
     *
     *
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Table dineTable = new Table(ANZ_TABLESEATS);
        //Philosophen erzeugen
        for(int i = 0;i<ANZ_PHILOSOPHS;i++){
            philosophs[i] = new Philosoph(dineTable,i,false);
        }
        for(int i = ANZ_PHILOSOPHS;i<ANZ_PHILOSOPHS+ANZ_HUNGRY_PH;i++){
            philosophs[i] = new Philosoph(dineTable,i,true);
        }
        //Philosophen starten
        try {
            for(Thread i : philosophs){
                i.start();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        new Output(dineTable,philosophs).start();



    }

}
