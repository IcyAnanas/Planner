import java.util.Arrays;
import java.util.PriorityQueue;

public class SRT extends Strategia
{
    @Override
    /*
     *   sortuje procesy względem
     *   1) momentu przyjścia
     *   2) zapotrzebowania
     *   3) indeksu
     *   w podanej ważności
     *   (np. proces o przyjściu 4 zapotrzebowaniu 10 i indeksie 5 będzie 'większy' od procesu o przyjsciu 3, zapotrzebowaniu 12 i indeksie 125)
     */
    protected void resetuj(Proces[] p, int liczba_procesów)
    {
        for(int i = 0; i < liczba_procesów; i++)
        {
            p[i].setCzas_pozostały();
        }

        KomparatorSRT komp = new KomparatorSRT();
        Arrays.sort(p, komp);

        super.ustawCzas();
    }


    public void wykonaj(Proces[] procesy, int liczba_procesów)
    {
        this.resetuj(procesy, liczba_procesów);

        KomparatorZapotrzebowania komp = new KomparatorZapotrzebowania();


        PriorityQueue<Proces> pq = new PriorityQueue<Proces>(komp); //na kolejce trzymamy procesy oczekujące
                                                                   //posortowane wzlęgem czasu_pozostałego

        Proces aktualny = null;


        System.out.println("Strategia: SRT");

        for(int i = 0; i < liczba_procesów;)
        {
            if(pq.isEmpty() && procesy[i].getMoment_przybycia() > czas_obecny) czas_obecny = procesy[i].getMoment_przybycia();

            if(aktualny == null) aktualny = pq.poll();

            while(aktualny != null) //wykonujemy procesy aż do momentu, kiedy nowe procesy chcą dołączyć do kolejki oczekoujących
            {
                if(procesy[i].getMoment_przybycia() < aktualny.getCzas_pozostały() + czas_obecny)
                {
                    aktualny.zmniejszCzas_pozostały(procesy[i].getMoment_przybycia() - czas_obecny);
                    czas_obecny = procesy[i].getMoment_przybycia();
                    break;
                }
                else
                {
                    wykonajProces(aktualny);
                    if(czas_obecny < procesy[i].getMoment_przybycia()) aktualny = pq.poll();
                    else aktualny = null;   //kolejne procesy dochodzą idealnie w momencie wykonania aktualnego,
                                            //należy więc je dodać do kolejki oczekujących, dlatego przerywamy pętlę
                }
            }


            while(i < liczba_procesów && procesy[i].getMoment_przybycia() <= czas_obecny)   //dodajemy do oczekujących
            {
                pq.add(procesy[i++]);
            }

            if(aktualny != null && aktualny.getCzas_pozostały() > pq.peek().getCzas_pozostały())
            //w oczekujących jest proces, który wykona się szybciej, niż aktualny
            {
                pq.add(aktualny);
                aktualny = pq.poll();
            }
        }

        if(aktualny == null) aktualny = pq.poll();

        while(aktualny != null) //wszystkie procesy są już w kolejce oczekujących / zostały wykonane
        {
            wykonajProces(aktualny);
            aktualny = pq.poll();
        }

        System.out.format("\n");


        średni_czas_obrotu /= liczba_procesów;
        średni_czas_oczekiwania /= liczba_procesów;

        wypiszPodsumowanie();
    }
}
