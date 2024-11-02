package proj;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.nio.file.Path;

import static java.nio.file.Path.of;

public class Main {
    public static int key = 5;
    public static Scanner str = new Scanner(System.in);
    public static int count_file_shifr = 0;
    public static int count_file_Rasshifr = 0;
    public static int count_file_Brute_force_rasshif = 0;
    public static int count_dir_Brute_force_rasshif = 0;


    public static void main(String[] args) throws IOException {
        System.out.println("Здравствуйте. Чтобы вы хотели бы сделать?");
        while (true) {
            str = new Scanner(System.in);
            System.out.println("Введите соответсвующие цифры чтобы продолжить:");
            System.out.println("1. Зашифровать текст в файле");
            System.out.println("2. Расшифровать текст в файле с помощью ключа");
            System.out.println("3. Расшифровать текст в файле с помощью 'Brute force'");
            System.out.println("4. Выйти из программы");
            int a = 0;
            while (true) {
                try {
                    str = new Scanner(System.in);
                    a = str.nextInt();
                    break;
                } catch (Exception e) {
                    System.out.println("Вы ввели не то значение, введите другое");
                }
            }
            if (a > 4) {
                System.out.println("Вы ввели не то значение, введите другое");
            }
            else if (a == 1) {
                while(true) {
                    List<String> list = put(a);
                    String[] v = Shifr.Shifrovanie(list, narushitel());
                    if (Arrays.equals(v, new String[]{"-1"})) {
                        System.out.println("Данный файл не может быть зашифрован, выберите другой");
                        continue;
                    }
                    fail(v, a);
                    break;
                }
            }
            else if (a == 2) {
                while(true) {
                    List<String> list = put(a);
                    String[] v = Rasshifrovka.ras(list, narushitel());
                    if (Arrays.equals(v, new String[]{"-1"})) {
                        System.out.println("Данный файл не может быть расшифрован, выберите другой");
                        continue;
                    }
                    fail(v, a);
                    break;
                }
            }
            else if (a == 3){
                while (true) {
                    List<String> list = put(a);
                    String[] v = Rasshifrovka.ras(list, 1);
                    if (Arrays.equals(v, new String[]{"-1"})) {
                        System.out.println("Данный файл не может быть расшифрован, выберите другой");
                        Files.delete(of("Brute_force_rasshif_" + String.valueOf(count_dir_Brute_force_rasshif)));
                        continue;
                    }
                    for (int i = 0; i < 33; i++) {
                        v = Rasshifrovka.ras(list, i);
                        fail(v, a);
                    }
                    break;
                }
            }
            else{
                break;
            }
        }
    }


    public static int narushitel(){
        System.out.println("Теперь введите, ключ, по которому будет расшифровываться сообщение");
        while (true){
            try {
                str = new Scanner(System.in);
                key = str.nextInt();
                break;
            }
            catch (Exception e) {
                System.out.println("Вы ввели неправильное значение ключа, выберите другое значение");
            }
        }
        return key;
    }


    public static void fail(String[] res, int a) throws IOException{
        if (a == 1) {
            count_file_shifr = 0;
            String r = String.valueOf('\\');
            while (Files.exists(Path.of("Shifr" + r + "res_" + String.valueOf(count_file_shifr) + ".txt"))) {
                count_file_shifr += 1;
            }
            Files.createFile(Path.of("Shifr" + r + "res_" + String.valueOf(count_file_shifr) + ".txt"));
            FileOutputStream outputStream = new FileOutputStream("Shifr" + r + "res_" + String.valueOf(count_file_shifr) + ".txt");
            for (int i = 0; i < res.length; i++) {
                byte[] buffer = (res[i] + '\n').getBytes();
                outputStream.write(buffer);
            }
            outputStream.close();
        }
        else if (a == 2){
            count_file_Rasshifr = 0;
            String r = String.valueOf('\\');
            while (Files.exists(Path.of("Rasshifr" + r + "res_" + String.valueOf(count_file_Rasshifr) + ".txt"))) {
                count_file_Rasshifr += 1;
            }
            Files.createFile(Path.of("Rasshifr" + r + "res_" + String.valueOf(count_file_Rasshifr) + ".txt"));
            FileOutputStream outputStream = new FileOutputStream("Rasshifr" + r + "res_" + String.valueOf(count_file_Rasshifr) + ".txt");
            for (int i = 0; i < res.length; i++) {
                byte[] buffer = (res[i] + '\n').getBytes();
                outputStream.write(buffer);
            }
            outputStream.close();
        }
        else{
            count_file_Brute_force_rasshif = 0;
            String r = String.valueOf('\\');
            while (Files.exists(Path.of("Brute_force_rasshif_" + String.valueOf(count_dir_Brute_force_rasshif) + r + "res_" + String.valueOf(count_file_Brute_force_rasshif) + ".txt"))) {
                count_file_Brute_force_rasshif += 1;
            }
            Files.createFile(Path.of("Brute_force_rasshif_" + String.valueOf(count_dir_Brute_force_rasshif) + r + "res_" + String.valueOf(count_file_Brute_force_rasshif) + ".txt"));
            FileOutputStream outputStream = new FileOutputStream("Brute_force_rasshif_" + String.valueOf(count_dir_Brute_force_rasshif) + r + "res_" + String.valueOf(count_file_Brute_force_rasshif) + ".txt");
            for (int i = 0; i < res.length; i++) {
                byte[] buffer = (res[i] + '\n').getBytes();
                outputStream.write(buffer);
            }
            outputStream.close();
        }
    }


    public static List put(int a) throws IOException {
        System.out.println("Введите путь к файлу");
        String s = "c:";
        List<String> list = new ArrayList<String>();
        Path path = of(s);
        while (true) {
            try {
                str = new Scanner(System.in);
                s = str.nextLine();
                path = of(s);
                list = Files.readAllLines(path);

                break;
            } catch (Exception e) {
                System.out.println("Файла с таким названием в этом месте нет, выберите другой путь");
            }
        }
        if (a == 1){
            if (!Files.exists(Path.of("Shifr"))) {
                Files.createDirectory(Path.of("Shifr"));
            }
        }
        else if (a == 2){
            if (!Files.exists(Path.of("Rasshifr"))) {
                Files.createDirectory(Path.of("Rasshifr"));
            }
        }
        else{
            while (true) {
                if (Files.exists(Path.of("Brute_force_rasshif_" + String.valueOf(count_dir_Brute_force_rasshif)))){
                    count_dir_Brute_force_rasshif += 1;
                }
                else{
                    Files.createDirectory(of("Brute_force_rasshif_" + String.valueOf(count_dir_Brute_force_rasshif)));
                    break;
                }
            }
        }
        return list;
    }
}