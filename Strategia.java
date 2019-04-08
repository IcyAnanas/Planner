import java.util.Arrays;

public abstract class Strategia
{
    protected double średni_czas_obrotu;
    protected double średni_czas_oczekiwania;
    protected double czas_obecny;

    public Strategia()
    {
        średni_czas_obrotu = 0;
        średni_czas_oczekiwania = 0;
        czas_obecny = 0;
    }

    public abstract void wykonaj(Proces[] procesy, int liczba_procesów);

    protected void ustawCzas()
    {
        średni_czas_obrotu = 0;
        średni_czas_oczekiwania = 0;
        czas_obecny = 0;
    }

    protected void resetuj(Proces[] p, int liczba_procesów)
    {
        for(int i = 0; i < liczba_procesów; i++)
        {
            p[i].setCzas_pozostały();
        }

        KomparatorPrzybycia komp = new KomparatorPrzybycia();
        Arrays.sort(p, komp);

        ustawCzas();
    }

    protected void wykonajProces(Proces p)
    {
        czas_obecny += p.getCzas_pozostały();

        p.wykonaj();

        średni_czas_obrotu += czas_obecny - p.getMoment_przybycia();
        średni_czas_oczekiwania += czas_obecny - p.getMoment_przybycia() - p.getZapotrzebowanie();

        wypiszWykonanyProces(p);
    }

    protected void wypiszWykonanyProces(Proces wykonany)
    {
        System.out.format("["
                + wykonany.toString() + " "
                + String.format(java.util.Locale.US,"%.2f", czas_obecny)
                + "]");
    }

    protected void wypiszPodsumowanie()
    {
        System.out.println("Średni czas obrotu: "
                + String.format(java.util.Locale.US,"%.2f", średni_czas_obrotu));

        System.out.println("Średni czas oczekiwania: "
                + String.format(java.util.Locale.US,"%.2f", średni_czas_oczekiwania));

        System.out.println();
    }
}
