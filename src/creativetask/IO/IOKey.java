package creativetask.IO;

import creativetask.Param;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class IOKey extends IO {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public void inputPublicKey(String path){
        try {
            input(Param.PATH_KEY + "public.key");
            byte[] encodedPublicKey = getFile();
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
            this.publicKey = keyFactory.generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Неверный алгоритм");
            System.exit(4);
        } catch (InvalidKeySpecException e) {
            System.out.println("Недопустимый ключ");
            System.exit(4);
        }
    }

    public void inputPrivateKey(String path){
        try {
            input(Param.PATH_KEY + "private.key");
            byte[] encodedPrivateKey = getFile();
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
            this.privateKey = keyFactory.generatePrivate(privateKeySpec);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Неверный алгоритм");
            System.exit(5);
        } catch (InvalidKeySpecException e) {
            System.out.println("Недопустимый ключ");
            System.exit(5);
        }
    }

    public void outputKeys(KeyPair keyPair){
        X509EncodedKeySpec encodedKeyPublic = new X509EncodedKeySpec(keyPair.getPublic().getEncoded());
        X509EncodedKeySpec encodedKeyPrivate = new X509EncodedKeySpec(keyPair.getPrivate().getEncoded());
        setFile(encodedKeyPublic.getEncoded());
        output(Param.PATH_KEY + "public.key");
        setFile(encodedKeyPrivate.getEncoded());
        output(Param.PATH_KEY + "private.key");
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

}