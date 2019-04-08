import java.io.FileNotFoundException;
import java.io.File;

public class Main
{
    public static void main(String[] args)
    {
        String pathToFile = null;
        File f = null;

        if(args.length > 0)
        {
            pathToFile = args[0];
            f = new File(pathToFile);
        }

        Reader r;

        if(pathToFile == null || pathToFile.isEmpty())
        {
            r = new Reader();
        }
        else try
        {
            r = new Reader(f);
        }
        catch(FileNotFoundException e)
        {
            r = null;
            System.out.println("Brak dostępu do pliku");
        }


        if(r != null)   //mamy dostęp do pliku albo czytamy dane z konsoli
        {
            int liczba_procesów;
            Proces[] procesy;

            int liczba_kwantów_RR;
            int[] kwantyRR;

            Strategia s;

            try {
                r.wczytaj();

                liczba_procesów = r.getLiczba_procesów();
                procesy = r.getProcesy();

                liczba_kwantów_RR = r.getLiczba_kwantów_RR();
                kwantyRR = r.getKwantyRR();

                s = new FCFS();
                s.wykonaj(procesy, liczba_procesów);

                s = new SJF();
                s.wykonaj(procesy, liczba_procesów);

                s = new SRT();
                s.wykonaj(procesy, liczba_procesów);

                s = new PS();
                s.wykonaj(procesy, liczba_procesów);

                for (int i = 0; i < liczba_kwantów_RR; i++) {
                    s = new RR(kwantyRR[i]);
                    s.wykonaj(procesy, liczba_procesów);
                }

            } catch (DataException e) {
                System.out.println(e.message);
            }
        }
    }
}
