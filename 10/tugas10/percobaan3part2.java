public class percobaan3part2 {
    public static void main(String[] args) {
        int bil  =10;
        try{
            System.out.println(bil/0);
        }
        catch(ArithmeticException e)
        {
            System.out.println("terjadi arritmatika error ");
        }
        catch(Exception e)
        {
            System.out.println("ini menghandle error yang terjadi");
        }
    }
}
