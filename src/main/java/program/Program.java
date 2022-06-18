package program;

import model.Steganography;
import model.exceptions.InsufficientTextSizeException;

import java.io.IOException;

public class Program {

    private static final String WINDOWS_1251 = "windows-1251";
    private static final String UTF8 = "UTF-8";

    public static void main(String[] args) throws IOException, InsufficientTextSizeException {

        System.out.println("#1 windows-1251");
        String str = "Бабушка приехала. windows-1251";
        Steganography.encode(str, "bezdna-WINDOWS1251.txt", "output-" + WINDOWS_1251 + ".txt", WINDOWS_1251);
        String msg = Steganography.decode("output-" + WINDOWS_1251, WINDOWS_1251);
        System.out.println(msg);

        System.out.println("#2 UTF-8");
        String secretMsg = "Бабушка приехала. UTF-8";
        String sourceFile = "bezdna-UTF8.txt";
        String outputFile = "output-UTF-8.txt";
        Steganography.encode(secretMsg, sourceFile, outputFile, UTF8);
        String receivedMsg = Steganography.decode(outputFile, UTF8);
        System.out.println(receivedMsg);

        for (int i = 1; i <= 20; i++) {
            System.out.println(Steganography.decodedMsgFromFile("messages/message" + String.format("%02d", i) + ".txt", WINDOWS_1251));
        }
    }
}
