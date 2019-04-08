import java.util.Comparator;

/*
 * komparator porównuje procesy na podstawie
 * 1) czasu potrzebnego do zakończenia procesu
 * 2) ID
 * w podanej kolejności
 */
public class KomparatorZapotrzebowania implements Comparator<Proces>
{
    @Override
    public int compare(Proces p1, Proces p2)
    {
        if(p1.getCzas_pozostały() > p2.getCzas_pozostały()) return 1;
        else if(p1.getCzas_pozostały() < p2.getCzas_pozostały()) return -1;
        else return new KomparatorID().compare(p1, p2);
    }
}
