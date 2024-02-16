public class Main {
    public static void main(String[] args) {

        try{
            AES aes = new AES();
            aes.init();
            String enc = aes.encrypt("Hello world!");
            System.out.println(enc);
            System.out.println("______");
            String dec = aes.decrypt(enc);
            System.out.println(dec);

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}