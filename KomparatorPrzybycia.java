import java.util.Comparator;

/*
 *   komparator porównuje procesy na podstawie
 *   1) momentu przybycia
 *   2) ID
 *   w podanej kolejności priorytetów
 */
public class KomparatorPrzybycia implements Comparator<Proces>
{
    @Override
    public int compare(Proces p1, Proces p2)
    {
        if(p1.getMoment_przybycia() > p2.getMoment_przybycia()) return 1;
        else if(p1.getMoment_przybycia() < p2.getMoment_przybycia()) return -1;
        else return new KomparatorID().compare(p1, p2);
    }
}
