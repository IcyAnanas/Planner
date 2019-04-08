import java.util.LinkedList;
import java.util.Queue;

public class RR extends Strategia
{
    private int q;

    public RR(int q)
    {
        super();
        this.q = q;
    }


    public void wykonaj(Proces[] procesy, int liczba_procesów)
    {
        resetuj(procesy, liczba_procesów); //RR wykonuje się zawsze po PS, a PS sortuje elementy i potem nie zmienia ich układu

        Queue<Proces> q = new LinkedList<Proces>();

        Proces aktualny;


        System.out.println("Strategia: RR-" + this.q);

        for(int i = 0; i < liczba_procesów;)
        {
            if(q.isEmpty() && procesy[i].getMoment_przybycia() > czas_obecny) czas_obecny = procesy[i].getMoment_przybycia();

            while(i < liczba_procesów && procesy[i].getMoment_przybycia() <= czas_obecny)   //dodajemy do oczekjących
            {
                q.add(procesy[i++]);
            }

            //wykonujemy procesy tak długo, aż napotkamy na kolejny proces, który trzeba dodać do kolejki oczekujących
            while(i < liczba_procesów && czas_obecny < procesy[i].getMoment_przybycia() && q.peek() != null)
            {
                aktualny = q.poll();

                if(this.q >= aktualny.getCzas_pozostały())  //skończymy ten proces przed upłynięciem kwantu czasu
                {
                    wykonajProces(aktualny);
                }
                else    //nie dokończymy procesu
                {
                    aktualny.zmniejszCzas_pozostały(this.q);
                    czas_obecny += this.q;

                    //do kolejki oczekujących wczytujemy wszystkie procesy, które przybyły przed końcem wykonywania aktualnego procesu
                    if(czas_obecny > procesy[i].getMoment_przybycia())
                    {
                        while(i < liczba_procesów && procesy[i].getMoment_przybycia() < czas_obecny)
                        {
                            q.add(procesy[i++]);
                        }
                    }

                    q.add(aktualny);    //dodajemy aktualny na koniec kolejki
                }
            }
        }

        while(q.peek() != null) //wszystkie procesy są już w kolejce oczekujących (lub zostały wykonane)
        {
            aktualny = q.poll();

            if(this.q >= aktualny.getCzas_pozostały())
            {
                wykonajProces(aktualny);
            }
            else
            {
                aktualny.zmniejszCzas_pozostały(this.q);
                q.add(aktualny);
                czas_obecny += this.q;
            }
        }

        System.out.format("\n");


        średni_czas_obrotu /= liczba_procesów;
        średni_czas_oczekiwania /= liczba_procesów;

        wypiszPodsumowanie();
    }
}
