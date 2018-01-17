
package si.fri.rso.katalogdogodkov;

import java.util.ArrayList;
import java.util.List;


public class Database {
    private static List<Dogodek> dogodeks = new ArrayList<>();

    public static List<Dogodek> getDogodeks() {
        /*Dogodek cus = new Dogodek();
        cus.setId("1");
        cus.setTitle("Novoletno praznovanje");
        cus.setAbout("Praznovanje novega leta na križarki čez Ljubljanico.");
        dogodeks.add(cus);*/

        return dogodeks;
    }

    public static Dogodek getDogodek(String dogodekId) {
        for (Dogodek dogodek : dogodeks) {
            if (dogodek.getId().equals(dogodekId))
                return dogodek;
        }

        return null;
    }

    public static void addDogodek(Dogodek dogodek) {
        dogodeks.add(dogodek);
    }

    public static void deleteDogodek(String dogodekId) {
        for (Dogodek dogodek : dogodeks) {
            if (dogodek.getId().equals(dogodekId)) {
                dogodeks.remove(dogodek);
                break;
            }
        }
    }
}
