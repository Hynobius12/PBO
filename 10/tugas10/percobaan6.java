public class percobaan6 {
    static void demo()
    {
        NullPointerException t;
        t=new NullPointerException("Coba Throw");
        throw t;
        System.out.println("ini tidak lagi dicetak");
    }

    public static void main(String[] args) {
        try
        {
            demo();
            System.out.println("selesai");
        }
        catch(NullPointerException e)
        {
            System.out.println("ada pesan error: "+e);
        }
    }
}
