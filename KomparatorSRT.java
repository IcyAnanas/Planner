import java.util.Comparator;

/*
 * komparator porównuje procesy na podstawie
 * 1) momentu przybycia
 * 2) czasu potrzebnego do ich zakończenia
 * 3) ID
 * w podanej kolejności priorytetów
 */
public class KomparatorSRT implements Comparator<Proces>
{
    @Override
    public int compare(Proces p1, Proces p2)
    {
        if(p1.getMoment_przybycia() > p2.getMoment_przybycia()) return 1;
        else if(p1.getMoment_przybycia() < p2.getMoment_przybycia()) return -1;
        else return new KomparatorZapotrzebowania().compare(p1, p2);
    }
}
