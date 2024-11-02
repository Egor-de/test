package proj;
import java.util.Arrays;
import java.util.List;
public class Rasshifrovka {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е','ё', 'ж', 'з',
            'и', 'й','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'ю', 'я'};
    private static final char[] Znaki = {'.', ',', '«', '»', ':', '!', '?', ' '};
    public static String[] ras(List list, int key){
        key = key % 33;
        String[] new_list = (String[]) list.toArray(new String[0]);
        char[][] charArray = new char[new_list.length][];
        for (int i = 0; i < list.size(); i++){
            charArray[i] = new_list[i].toCharArray();
        }
        for(int i = 0; i < charArray.length; i++){
            for (int j = 0; j < charArray[i].length; j++) {
                boolean p = false;
                for(int k = 0; k < Znaki.length; k ++){
                    if(String.valueOf(Znaki[k]).equals(String.valueOf(charArray[i][j]))){
                        p = true;
                        break;
                    }
                }
                if (p){
                    continue;
                }
                p = false;
                for(int k = 0; k < ALPHABET.length; k ++){
                    if(String.valueOf(ALPHABET[k]).equals(String.valueOf(charArray[i][j]).toLowerCase())){
                        p = true;
                        break;
                    }
                }
                if (!p){
                    return new String[]{"-1"};
                }
                boolean t = Character.isUpperCase(charArray[i][j]);
                if (t) {
                    charArray[i][j] = String.valueOf(charArray[i][j]).toLowerCase().charAt(0);
                }
                for (int g = 0; g < ALPHABET.length; g++) {
                    if (String.valueOf(ALPHABET[g]).equals(String.valueOf(charArray[i][j]))) {
                        if (t) {
                            if (g - key < 0){
                                charArray[i][j] = String.valueOf(ALPHABET[33 + (g - key) % 33]).toUpperCase().charAt(0);
                            }
                            else {
                                charArray[i][j] = String.valueOf(ALPHABET[(g - key) % 33]).toUpperCase().charAt(0);
                            }
                            break;

                        }
                        else {
                            if (g - key < 0) {
                                charArray[i][j] = ALPHABET[33 + (g - key) % 33];
                            } else {
                                charArray[i][j] = ALPHABET[(g - key) % 33];
                            }
                            break;
                        }

                    }
                }
            }
        }
        String[] new_str = new String[charArray.length];
        for(int i = 0; i < charArray.length; i++) {
            new_str[i] = new String(charArray[i]);
        }
        return new_str;
    }
}

