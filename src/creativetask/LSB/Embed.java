package creativetask.LSB;

import creativetask.Algorithms.BitByte;
import creativetask.IO.IO;
import creativetask.Param;
import sun.security.util.BitArray;

public class Embed {

    public int length;

    public Embed(String pathPic, String pathText){
        IO input = new IO();
        input.input(Param.PATH_PIC + pathPic);
        byte[] pic = input.getFile();
        input.input(Param.PATH + pathText);
        byte[] text = input.getFile();
        length = text.length;
        System.out.println("\nОбъем сообщение: " + length + " байт");
        System.out.println("Объем изображение: " + (pic.length-54) + " байт");
        System.out.println("Максимальный объем сообщения: " + (pic.length-54)/8 + " байт");
        if((pic.length - 54)/8 > text.length){
            int k=0;
            for (int i=0; i<text.length; i++){
                BitArray bits = BitByte.byteToBit(text[i]);
                for (int j=0; j<8; j++, k++){
                    BitArray picture = BitByte.byteToBit(pic[Param.HEADER + k]);
                    picture.set(0, bits.get(j));
                    byte res = BitByte.bitToByte(picture);
                    pic[Param.HEADER + k] = res;
                }
            }
            IO file = new IO();
            file.setFile(pic);
            file.output(Param.PATH_PIC + "embed_pic.bmp");
            System.out.println("Сообщение встроено в изображение");
        } else {
            System.out.println("Сообщение слишком длинное");
            System.exit(6);
        }
    }
}