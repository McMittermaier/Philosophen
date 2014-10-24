package edu.hm.cs.vss;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Table {

    private final int tableSize;
    //ArrayListe mit den Sitzplätzen repräsentiert durch jeweils eine semaphore mit nur einem Element
    private final ArrayList<Semaphore> seats;
    //Arrayliste mit den Gabeln hier mit booleans dargestellt
    private final ArrayList<Semaphore> forks;
    // Array wie oft an welchem platz gegessen wurde (statistik)
    private final int[] eater;


    public Table(int size){
        //Initialisierung der Variablen und Arrays
        this.tableSize = size;
        this.eater = new int[size];
        seats = new ArrayList<Semaphore>();
        forks = new ArrayList<Semaphore>();
        for(int i = 0 ; i<size;i++){
            seats.add(new Semaphore(1,true));
            forks.add(new Semaphore(1,true));
        }

     }

    //Veraltet siehe getSeatOptimized
    public int getSeat(){
        //Zufällige start position bei der begonnen wird um den Tisch zu laufen
        int startPos = (int)  (Math.random()*tableSize);
        // Gehe 2 mal um den tisch und schaue, ob plätze frei sind, wenn ja setze dich hin (return sitzpaltz)
        for(int round=0;round < 2*tableSize;round++){
            if(seats.get((round+startPos) % tableSize).tryAcquire()) return (round+startPos) % tableSize;
        }
        try {
            seats.get(startPos).acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
        return startPos;
    }

    public int getSeatOptimized(){
        //Zufällige start position bei der begonnen wird um den Tisch zu laufen
        int startPos = (int)  (Math.random()*tableSize);
     // Gehe 2 mal um den tisch und schaue, ob plätze frei sind, wenn ja setze dich hin (return sitzpaltz)
        for(int round=0;round < 2*tableSize;round++){
            if(seats.get((round+startPos) % tableSize).tryAcquire()) return (round+startPos) % tableSize;
        }
        // keine plätze mehr frei also reihe dich an der Kürzesten schlange ein
        int shortestQueue = Integer.MAX_VALUE;
        try {
            for(int round=0;round < tableSize;round++){
                if( seats.get(round).getQueueLength()<shortestQueue){
                    shortestQueue = seats.get(round).getQueueLength();
                    startPos = round;
                }
            }
            // einreihen in schlange
            seats.get(startPos).acquire();

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return -1;
        }
        // Endlich einen platz bekommen!
        return startPos;
    }

    public void standUp(int seatNr){
        //Rückgabe der Stuhl an die Semaphore
        seats.get(seatNr).release();
    }

    public boolean getFork(int forknr){
        /*boolean ret = true;
        //Versucht die Gabel zu bekommen

        if(forks.get(forknr).tryAcquire()){

        }

        synchronized(forks.get(forknr)){
            //Erfolgreich
            if(forks.get(forknr)==true)forks.set(forknr, false);
            // nicht erfolgreich
            else ret = false;
        }
        return ret;*/
        return forks.get(forknr).tryAcquire();
    }
    public void returnFork(int forknr){
        forks.get(forknr).release();

        /*//Zurücklegen der Gabel
        synchronized(forks.get(forknr)){
            if(forks.get(forknr)==false) forks.set(forknr, true);
            //else throw new RuntimeException("HoloFrok");
            else System.out.println("holoFork");
        }/**/
    }

    public String getWaiters(){
        // Gibt einen String über die pro Platz warten Ph zurück
        String waiters = "";
        for( Semaphore sem : seats ){
            waiters = waiters + sem.getQueueLength()+" | ";
        }
        return waiters;
    }

    public int incEater(int seatNr){
        //Zählt wie viel Ph an dem Platz gegessen haben
        eater[seatNr]++;
        return eater[seatNr];
    }

    public int[] getAuslastung(){
        return eater;
    }

    public int getTableSize(){
        return tableSize;
    }
}
