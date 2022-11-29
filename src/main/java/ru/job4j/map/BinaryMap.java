package ru.job4j.map;

public class BinaryMap {
    public static String binary(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 32; i++) {
            sb.append(num % 2 == 0 ? 0 : 1);
            sb.append((i + 1) % 8 == 0 ? " " : "");
            num /= 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        int h = 123;
        System.out.println("До:    " + binary(h));
        System.out.println("После: " + binary(h >>> 4));
        h = 255;
        System.out.println("До:    " + binary(h));
        System.out.println("После: " + binary(h >>> 4));
        h = Integer.MAX_VALUE;
        System.out.println("До:    " + binary(h));
        System.out.println("После: " + binary(h >>> 16));
        h = 123456789;
        System.out.println("h        =" + binary(h));
        System.out.println("h >>> 16 =" + binary(h >>> 16));
        System.out.println("h        =" + binary(h ^ (h >>> 16)));
        h = 16 - 1;
        System.out.println("16 - 1   =" + binary(h));
        h = 32 - 1;
        System.out.println("32 - 1   =" + binary(h));
        h = 64 - 1;
        System.out.println("64 - 1   =" + binary(h));
    }
}
