package edu.hm.cs.vss;

import java.util.ArrayList;

public class Table {

    private final int size;

    private final ArrayList<Boolean> seats;
    private final ArrayList<Boolean> forks;

    public Table(int size){
        this.size = size;
        seats = new ArrayList<Boolean>();
        forks = new ArrayList<Boolean>();

        for(int i = 0 ; i<size;i++){
            seats.add(true);
            forks.add(true);
        }

     }

    public int getSeat(){
        int seatNr = seats.lastIndexOf(true);
        // Tisch voll
        if (seatNr == -1) return -1;
        else  {
            //Setz dich auf nächsten freien Platz
            synchronized(seats.get(seatNr)){
                if(seats.get(seatNr)==true)
                seats.set(seatNr, false);
                else seatNr = -1;
            }
            return  seatNr;
        }
    }

    public void standUp(int seatNr){
        synchronized(seats.get(seatNr)){
            seats.set(seatNr, true);
        }
    }

    public boolean getFork(int forknr){
        boolean ret = true;
        synchronized(forks.get(forknr)){
            if(forks.get(forknr)==true) forks.set(forknr, false);
            else ret = false;
        }
        return ret;
    }

    public void returnFork(int forknr){
        synchronized(forks.get(forknr)){
            if(forks.get(forknr)==false) forks.set(forknr, true);
            else throw new RuntimeException("HoloFrok");
        }
    }
}
