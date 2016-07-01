package creativetask.IO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class IO {

    private byte[] file;

    public void input(String path){
        try {
            FileInputStream fis = new FileInputStream(path);
            byte[] file = new byte[fis.available()];
            fis.read(file);
            fis.close();
            this.file = file;
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + path);
            System.exit(2);
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
            System.exit(2);
        }
    }

    public void output(String path){
        try {
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(file, 0, file.length);
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + path);
            System.exit(3);
        } catch (IOException e) {
            System.out.println("Ошибка записи");
            System.exit(3);
        }
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
