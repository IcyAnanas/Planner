public class Proces
{
    private int id;
    private int zapotrzebowanie;
    private int moment_przybycia;
    private double czas_pozostały;  //czas potrzebny do zakończenia procesu (przy założeniu, że tylko on jest wykonywany)

    public void setCzas_pozostały() //resetuje proces do stanu sprzed wykonania
    {
        czas_pozostały = zapotrzebowanie;
    }

    public Proces(int id, int zapotrzebowanie, int moment_przybycia)
    {
        this.id = id;
        this.zapotrzebowanie = zapotrzebowanie;
        this.moment_przybycia = moment_przybycia;
        this.setCzas_pozostały();
    }

    @Override
    public String toString()
    {
        String wynik = this.id +  " " + this.moment_przybycia;
        return wynik;
    }

    public void zmniejszCzas_pozostały(double o_ile)    //wykonuje kawałek procesu
    {
        czas_pozostały -= o_ile;
    }

    public void wykonaj()   //wykonuje proces
    {
        czas_pozostały = 0;
    }

    public int getMoment_przybycia()
    {
        return moment_przybycia;
    }

    public int getId()
    {
        return id;
    }

    public int getZapotrzebowanie()
    {
        return zapotrzebowanie;
    }

    public double getCzas_pozostały()
    {
        return czas_pozostały;
    }
}
