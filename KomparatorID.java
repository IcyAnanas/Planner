import java.util.Comparator;

public class KomparatorID implements Comparator<Proces>
{
    @Override
    public int compare(Proces p1, Proces p2)
    {
        if (p1.getId() > p2.getId()) return 1;
        else if (p1.getId() < p2.getId()) return -1;
        else return 0;  //teoretycznie, to niemożliwe (id jest zawsze różne)
    }
}
