package Basis.OOP;

import java.util.ArrayList;
import java.util.List;

public class OOP {

    public static void main(String[] args) {

    }



}
class Instrument {
    public void play() {
        System.out.println("Instrument is playing...");
    }
}

class Wind extends Instrument {
    public void play() {
        System.out.println("Wind is playing...");
    }
}

class Percussion extends Instrument {
    public void play() {
        System.out.println("Percussion is playing...");
    }
}

class Music {
    public static void main(String[] args) {
        List<Instrument> instruments = new ArrayList<>();
        instruments.add(new Wind());
        instruments.add(new Percussion());
        for(Instrument instrument : instruments) {
            instrument.play();
        }
    }
}
