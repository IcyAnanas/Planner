public class DataException extends Exception
{
    int nr_wiersza;
    String message;

    public DataException(int nr_wiersza, String wiadomość)
    {
        super();
        this.nr_wiersza = nr_wiersza;
        this.message = "Błąd w wierszu " + nr_wiersza + ": " + wiadomość;
    }

}
