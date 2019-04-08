import java.util.Arrays;
import java.util.PriorityQueue;

public class PS extends Strategia
{

    public void wykonaj(Proces[] procesy, int liczba_procesów)
    {
        resetuj(procesy, liczba_procesów);

        KomparatorZapotrzebowania komp = new KomparatorZapotrzebowania();

        //kolejka procesów obecnie wykonywanych
        //w head jest zawsze proces, któremu pozostało najmniej czasu do wykonania
        PriorityQueue<Proces> pq = new PriorityQueue<Proces>(komp);

        int pq_size = 0;

        double kwant;


        System.out.println("Strategia: PS");

        for(int i = 0; i < liczba_procesów;)
        {
            if(pq.isEmpty() && czas_obecny < procesy[i].getMoment_przybycia()) czas_obecny = procesy[i].getMoment_przybycia();

            //dopóki najkrótszy z czasów_pozostałych procesów bieżacych
            //jest na tyle mały, że skończymy wykonywać ten proces
            //przed przyjściem nowych procesów
            //wykonujemy ten 'najkrótszy' proces (i po kawałku wszystkich pozostałych)
            while(pq.peek() != null && czas_obecny + (kwant = pq.peek().getCzas_pozostały()) * pq_size <= procesy[i].getMoment_przybycia())
            {
                czas_obecny += kwant * pq_size;

                for(int k = 0; k < i; k++)  //wykonujemy po kawałku każdego procesu
                {
                    procesy[k].zmniejszCzas_pozostały(kwant);

                    if(procesy[k].getCzas_pozostały() == 0)
                    {
                        wykonajProces(procesy[k]);
                        pq.remove(procesy[k]);
                        pq_size--;
                    }
                }
            }

            if(pq.peek() != null)   //jest proces, który wejdzie do oczekujących, zanim skończymy wykonywać którykolwiek z bieżących procesów
            {
                for(int k = 0; k < i; k++)  //wykonujemy kawałek wszystkich procesów
                {
                    procesy[k].zmniejszCzas_pozostały((procesy[i].getMoment_przybycia() - czas_obecny) / pq_size);
                }
                czas_obecny = procesy[i].getMoment_przybycia();
            }

            //dodajemy procesy do oczekujących
            while(i < liczba_procesów && procesy[i].getMoment_przybycia() <= czas_obecny)
            {
                pq.add(procesy[i++]);
                pq_size++;
            }
        }


        while(pq.peek() != null)    //wszystkie procesy są już wykonywane / zostały już wykonane
        {
            kwant = pq.peek().getCzas_pozostały();

            for(int k = 0; k < liczba_procesów; k++)
            {
                procesy[k].zmniejszCzas_pozostały(kwant);
            }
            czas_obecny += pq_size * kwant;

            while(pq.peek() != null && pq.peek().getCzas_pozostały() == 0)  //z kolejki wykonywanych zdejmujemy wszystkie wykonane
            {
                wykonajProces(pq.poll());
                pq_size--;
            }
        }

        System.out.format("\n");



        średni_czas_obrotu /= liczba_procesów;
        średni_czas_oczekiwania = 0;

        wypiszPodsumowanie();
    }
}
