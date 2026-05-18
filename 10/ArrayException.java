public class ArrayException {
    public static void main(String[] args) {
        try{
            String data [] = {"1","2","3"};
            int i0 = Integer.parseInt(data[0]);
            int i1 = Integer.parseInt(data[1]);
            int hasil = 10 + i1;
            System.out.println(data[3] + "=" + hasil);
        }
        catch(ArrayIndexOutOfBoundsException arr){
            System.out.println("Index melebihi batas");
        }
    }
}
