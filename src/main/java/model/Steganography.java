package model;

import model.exceptions.InsufficientTextSizeException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class Steganography {
    public static final String END_OF_MSG = " END OF MSG";

    public static void encode(String secretMsg, String fileName, String outputFile, String charsetName) throws IOException, InsufficientTextSizeException {
        List<String> bezdna = Files.readAllLines(Path.of(fileName), Charset.forName(charsetName));
        StringJoiner stringJoiner = new StringJoiner("\n");
        bezdna.forEach(stringJoiner::add);
        char[] txt = stringJoiner.toString().toCharArray();

        String[] stringBinaryArr = getBinaryStringArr(secretMsg + END_OF_MSG);
        StringBuilder sb2 = new StringBuilder();
        Arrays.stream(stringBinaryArr).forEach(sb2::append);
        char[] digits = sb2.toString().toCharArray();

        int i = 0;
        int j = 0;
        while (i < txt.length && j < digits.length) {
            if (Character.isLetter(txt[i])) {
                if (digits[j] == '1') {
                    txt[i] = Character.toUpperCase(txt[i]);
                } else {
                    txt[i] = Character.toLowerCase(txt[i]);
                }
                j++;
            }
            i++;
        }
        if (i == txt.length && j < digits.length) {
            throw new InsufficientTextSizeException();
        }
        String editedText = new String(txt);
        Files.writeString(Path.of(outputFile), editedText, Charset.forName(charsetName));
    }

    public static String decode(String fileName, String charsetName) throws IOException {
        List<String> strings = Files.readAllLines(Path.of(fileName), Charset.forName(charsetName));
        StringBuilder sb1 = new StringBuilder();
        strings.forEach(sb1::append);
        char[] txt = sb1.toString().toCharArray();

        StringBuilder binary = new StringBuilder();
        for (char ch : txt){
            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {
                    binary.append("1");
                } else {
                    binary.append("0");
                }
            }
        }

        String s = binary.toString();
        List<String> list = new ArrayList<>();
        for (int j = 0; j < s.length(); j += 8) {
            list.add(s.substring(j, Math.min((j + 8), s.length())));
        }
        return getSecretMsgFromStringArray(list.toArray(new String[0]), charsetName);
    }

    private static String[] getBinaryStringArr(String secretMsg) throws UnsupportedEncodingException {
        byte[] bytes = secretMsg.getBytes();
        String[] result = new String[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            result[i] = getBinaryString(bytes[i]);
        }
        return result;
    }

    private static String getSecretMsgFromStringArray(String[] arr, String charsetName) throws UnsupportedEncodingException {
        byte[] res = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = (byte) Integer.parseInt(arr[i], 2);
        }
        String secretMsg = new String(res, StandardCharsets.UTF_8);
        return !secretMsg.contains(END_OF_MSG) ? secretMsg : secretMsg.substring(0, secretMsg.indexOf(" END OF MSG"));
    }

    private static String getBinaryString(int b) {
        return Integer.toBinaryString(0b100000000 | (b & 0xff)).substring(1);
    }

    public static String decodedMsgFromFile(String fileName, String charsetName) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(fileName), Charset.forName(charsetName));
        StringBuilder sb = new StringBuilder();
        lines.forEach(sb::append);
        char[] txt = sb.toString().toCharArray();

        StringBuilder binary = new StringBuilder();
        for (char ch : txt){
            if (Character.isLetter(ch) && ch != '¸' && ch !='¨' && ch !='÷'&& ch != '×') {
                if (Character.isUpperCase(ch)) {
                    binary.append("1");
                } else {
                    binary.append("0");
                }
            }
        }

        String s = binary.toString();
        List<String> list = new ArrayList<>();
        for (int j = 0; j < s.length(); j += 8) {
            list.add(s.substring(j, Math.min((j + 8), s.length())));
        }
        byte[] arr = getMsgFromStringArray(list.toArray(new String[0]));
        return new String(arr, Charset.forName(charsetName));
    }

    private static byte[] getMsgFromStringArray(String[] arr){
        byte[] res = new byte[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = (byte) Integer.parseInt(arr[i], 2);
        }
        return res;
    }
}
