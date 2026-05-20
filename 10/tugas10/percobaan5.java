public class percobaan5 {
    public static void main(String[] args) {
        int bil=10;
        try{
            System.out.println(bil/0);
        }
        catch(ArithmeticException e)
        {
            System.out.println("Pesan error: ");
            System.out.println(e.getMessage());
            System.out.println("info stack erase");
            e.printStackTrace();
            e.printStackTrace(System.out);
        }
        catch(Exception e)
        {
            System.out.println("ini menghandle error yang terjadi");
        }
    }
}
