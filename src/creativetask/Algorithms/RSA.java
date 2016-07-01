package creativetask.Algorithms;

import creativetask.IO.IOKey;
import creativetask.Param;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSA {

    private static IOKey key = new IOKey();

    public static void createKeyPair(){
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(Param.KEY_SIZE);
            KeyPair keyPair = keyGen.genKeyPair();
            System.out.println("Сгенерированы новые ключи!");
            System.out.println("-----------------------------------------------");
            System.out.println(keyPair.getPrivate());
            System.out.println(keyPair.getPublic());
            System.out.println("");
            IOKey saveKeys = new IOKey();
            saveKeys.outputKeys(keyPair);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Нет такого алгоритма");
            System.exit(3);
        }
    }

    public static void encode(String nameText, String nameFile){
        try {
            key.input(Param.PATH + nameText);
            byte[] file = key.getFile();
            Cipher cipher = Cipher.getInstance("RSA");
            key.inputPublicKey(Param.PATH_KEY + "public.key");
            cipher.init(Cipher.ENCRYPT_MODE, key.getPublicKey());
            byte[] new_file = cipher.doFinal(file);
            key.setFile(new_file);
            key.output(Param.PATH + nameFile);
            System.out.println("\nСообщение закодировано");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Нет такого алгоритма");
            System.exit(3);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            System.exit(3);
        } catch (InvalidKeyException e) {
            System.out.println("Неверный ключ");
            System.exit(3);
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    public static void decode(String nameEncode, String nameFile){
        try {
            key.input(Param.PATH + nameEncode);
            byte[] file = key.getFile();
            Cipher cipher = Cipher.getInstance("RSA");
            key.inputPrivateKey(Param.PATH_KEY + "private.key");
            cipher.init(Cipher.DECRYPT_MODE, key.getPrivateKey());
            byte[] new_file = cipher.doFinal(file);
            key.setFile(new_file);
            key.output(Param.PATH + nameFile);
            System.out.println("\nСообщение раскодировано\n");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Нет такого алгоритма");
            System.exit(3);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            System.exit(3);
        } catch (InvalidKeyException e) {
            System.out.println("Неверный ключ");
            System.exit(3);
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }
}
