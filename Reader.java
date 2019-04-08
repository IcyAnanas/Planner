import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Reader
{
    private int liczba_procesów;
    private Proces[] procesy;
    private Scanner sc;
    private int liczba_kwantów_RR;
    private int[] kwantyRR;

    public Reader()
    {
        this.sc = new Scanner(System.in);
    }
    public Reader(File f) throws FileNotFoundException
    {
        this.sc = new Scanner(f);
    }

    private int wczytajLiczbeZLinii(String linia, int nr_wiersza) throws DataException
    {
        Scanner kontrolny = new Scanner(linia);

        if(!kontrolny.hasNextInt())
        {
            sc.close();
            throw new DataException(nr_wiersza, "brak liczby");
        }

        int liczba = kontrolny.nextInt();

        if(kontrolny.hasNext())
        {
            sc.close();
            throw new DataException(nr_wiersza, "za dużo danych w linii");
        }

        kontrolny.close();

        return liczba;
    }



    private void wczytajLiczbeProcesów() throws DataException
    {
        if(!sc.hasNextLine())
        {
            sc.close();
            throw new DataException(1, "za mało danych");
        }

        liczba_procesów = wczytajLiczbeZLinii(sc.nextLine(), 1);
    }



    private void wczytajProcesZLinii(int nr_wiersza, String linia) throws DataException
    {
        Scanner kontrolny = new Scanner(linia);

        if(!kontrolny.hasNextInt())
        {
            sc.close();
            throw new DataException(nr_wiersza, "brak kolejnej liczby");
        }

        int moment_przybycia = kontrolny.nextInt();

        if(!kontrolny.hasNextInt())
        {
            sc.close();
            throw new DataException(nr_wiersza, "brak kolejnej liczby");
        }

        int zapotrzebowanie = kontrolny.nextInt();

        procesy[nr_wiersza - 2] = new Proces(nr_wiersza - 1, zapotrzebowanie, moment_przybycia);

        if(kontrolny.hasNext())
        {
            sc.close();
            throw new DataException(nr_wiersza, "za dużo danych w linii");
        }

        kontrolny.close();
    }



    private void wczytajProces(int nr_wiersza) throws DataException
    {
        if(!sc.hasNextLine())
        {
            sc.close();
            throw new DataException(nr_wiersza, "za mało danych");
        }

        wczytajProcesZLinii(nr_wiersza, sc.nextLine());
    }



    private void wczytajProcesy() throws DataException
    {
        procesy = new Proces[liczba_procesów];

        for(int i = 0; i < liczba_procesów; i++)
        {
            wczytajProces(i + 2);
        }
    }



    private void wczytajLiczbeKwantów() throws DataException
    {
        if(!sc.hasNextLine())
        {
            sc.close();
            throw new DataException(liczba_procesów + 2, "za mało danych");
        }

        liczba_kwantów_RR = wczytajLiczbeZLinii(sc.nextLine(), liczba_procesów + 2);
    }

    private void wczytajKwantyZLinii(String linia) throws DataException
    {
        Scanner kontrolny = new Scanner(linia);

        for(int i = 0; i < liczba_kwantów_RR; i++)
        {
            if(!kontrolny.hasNextInt())
            {
                sc.close();
                throw new DataException(liczba_procesów + 3, "brak kolejnej liczby");
            }

            kwantyRR[i] = kontrolny.nextInt();
        }

        if(kontrolny.hasNext())
        {
            sc.close();
            throw new DataException(liczba_procesów + 3, "za dużo danych w linii");
        }

        kontrolny.close();
    }

    private void wczytajKwanty() throws DataException
    {
        kwantyRR = new int[liczba_kwantów_RR];

        if(!sc.hasNextLine())
        {
            sc.close();
            throw new DataException(liczba_procesów + 3, "za mało danych");
        }

        wczytajKwantyZLinii(sc.nextLine());
    }


    public void wczytaj() throws DataException
    {
        wczytajLiczbeProcesów();
        wczytajProcesy();
        wczytajLiczbeKwantów();
        wczytajKwanty();

        if(sc.hasNextLine() && sc.nextLine() != null)
        {
            sc.close();
            throw new DataException(liczba_procesów + 1 + liczba_kwantów_RR + 2, "Za długie wejście");
        }

        sc.close();
    }

    public int getLiczba_procesów()
    {
        return liczba_procesów;
    }

    public Proces[] getProcesy()
    {
        return procesy;
    }

    public int getLiczba_kwantów_RR()
    {
        return liczba_kwantów_RR;
    }

    public int[] getKwantyRR()
    {
        return kwantyRR;
    }
}
