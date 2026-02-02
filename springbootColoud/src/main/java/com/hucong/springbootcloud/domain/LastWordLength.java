package com.hucong.springbootcloud.domain;

public class LastWordLength {
    public static int lengthOfLastWord(String s) {
        int length = 0;
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        while (end >= 0 && s.charAt(end) != ' ') {
            length++;
            end--;
        }
        return length;
    }
    public static void main(String[] args) {
        String s1 = "Hello World";
        System.out.println(lengthOfLastWord(s1));

        String s2 = "   fly me   to   the moon   ";
        System.out.println(lengthOfLastWord(s2));

        String s3 = "luffy is still joyboy";
        System.out.println(lengthOfLastWord(s3));
    }
}
