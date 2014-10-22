/**
 *
 */
package edu.hm.cs.vss;

/**
 * @author Michael
 *
 */
public class Application {

    private static final int ANZ_PHILOSOPHS = 244;
    private static final int ANZ_TABLESEATS = 8;

    private final static Thread[] philosophs = new Thread[ANZ_PHILOSOPHS];

    /**
     * @param args
     *
     *
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Table dineTable = new Table(ANZ_TABLESEATS);
        //Philosophen erzeugen
        for(int i = 0;i<philosophs.length;i++){
            philosophs[i] = new Thread(new Philosoph(dineTable,i));
        }
        //Philosophen starten
        try {
            for(Thread i : philosophs){

                i.start();
               // i.wait();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        new Output(dineTable).start();



    }

}
