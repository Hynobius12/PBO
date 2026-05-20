public class percobaan2 {
    public static void main(String[] args) {
        int i=0;
        String greetings[] ={
            "Hello world",
            "No,  Imean it!",
            "Hello World"
        };
        while(i<4)
        {
            try
            {
                System.out.println(greetings[i]);
                i++;
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Resetting index value");
                i=0;
            }
            // tidak berhenti
        };
    }
    
}