import java.util.Arrays;

public class SJF extends Strategia
{
    public void wykonaj(Proces procesy[], int liczba_procesów)
    {
        resetuj(procesy, liczba_procesów);

        KomparatorZapotrzebowania komp = new KomparatorZapotrzebowania();

        int idx = 0;


        System.out.println("Strategia: SJF");

        //niezmiennik: wszystkie procesy po lewej od i zostały już wykonane
        for(int i = 0; i < liczba_procesów; i++)
        {
            if(czas_obecny < procesy[i].getMoment_przybycia()) czas_obecny = procesy[i].getMoment_przybycia();

            //znajdujemy pierwszy proces, który jeszcze się nie pojawił
            //(pozostałe powinny dołączyć do kolejki oczekujących)
            while(idx < liczba_procesów && procesy[idx].getMoment_przybycia() <= czas_obecny)
            {
                idx++;
            }

            Arrays.sort(procesy, i, idx, komp); //sortujemy oczekujące procesy po czasie_pozostałym

            wykonajProces(procesy[i]);
        }

        System.out.format("\n");


        średni_czas_obrotu /= liczba_procesów;
        średni_czas_oczekiwania /= liczba_procesów;

        wypiszPodsumowanie();
    }
}
