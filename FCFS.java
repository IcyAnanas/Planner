import java.util.Arrays;

public class FCFS extends Strategia
{
    public void wykonaj(Proces[] procesy, int liczba_procesów)
    {
        resetuj(procesy, liczba_procesów);


        System.out.println("Strategia: FCFS");

        for(int i = 0; i < liczba_procesów; i++)
        {
            //jeśli procesor 'nic nie robi', a nie ma żadnych procesów oczekujących, zwiększamy czas do momentu ich przybycia
            if(czas_obecny < procesy[i].getMoment_przybycia()) czas_obecny = procesy[i].getMoment_przybycia();

            wykonajProces(procesy[i]);
        }

        System.out.format("\n");


        średni_czas_obrotu /= liczba_procesów;
        średni_czas_oczekiwania /= liczba_procesów;

        wypiszPodsumowanie();
    }
}
