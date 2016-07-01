package creativetask;

import creativetask.Algorithms.RSA;
import creativetask.LSB.Embed;
import creativetask.LSB.Pull;
import java.util.Date;

public class Launcher {
    public static void main(String[] args){
        Date start = new Date();

//        RSA.createKeyPair(); // Создать пару ключей
//        RSA.encode("secret.txt", "encode.txt"); // Кодируем с помощью RSA сообщение
//        Embed embed = new Embed("orig.bmp", "encode.txt"); // Прячем закодированную последовательность в изображение
//        Pull pull = new Pull("embed_pic.bmp", 64); // Достаем спрятанную последовательность из изображения
        RSA.decode("pull.txt", "decode.txt"); // Декодируем последовательность

        Date finish = new Date();
        long ms = finish.getTime() - start.getTime();
        System.out.println("Время выполнения программы: " + ms + " мс");
    }
}
