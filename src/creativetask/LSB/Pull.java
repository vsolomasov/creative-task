package creativetask.LSB;

import creativetask.Algorithms.BitByte;
import creativetask.IO.IO;
import creativetask.Param;
import sun.security.util.BitArray;

public class Pull {
    public Pull(String pathPic, int length){
        IO file = new IO();
        file.input(Param.PATH_PIC + pathPic);
        byte[] pic = file.getFile();
        byte[] pull = new byte[length];
        int k=0;
        for (int i=0; i<length; i++){
            BitArray text = new BitArray(8);
            for (int j=0; j<8; j++, k++){
                BitArray picture = BitByte.byteToBit(pic[Param.HEADER+k]);
                text.set(j, picture.get(0));
            }
            byte res = BitByte.bitToByte(text);
            pull[i] = res;
        }
        file.setFile(pull);
        file.output(Param.PATH + "pull.txt");
        System.out.println("\nСообщение извлечено из изображения");
    }
}
