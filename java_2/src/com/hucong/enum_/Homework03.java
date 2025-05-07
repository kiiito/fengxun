package com.hucong.enum_;


import java.util.Scanner;

public class Homework03 {
    public static void main(String[] args) {
//        System.out.println(Color.PED.show());
        Scanner scanner = new Scanner(System.in);
        String a = scanner.nextLine();
        System.out.println(a);
        switch(a){
            case "PED" :
                Color.PED.show();
                break;
            case "BLUE":
                Color.BLUE.show();
                break;
                case "GREEN":
                    Color.GREEN.show();
                    break;
            case "BLACK":
                Color.BLACK.show();
                break;
            case "YELLOW":
                Color.YELLOW.show();
                break;
            default:

        }
    }
}
interface IL{
    void show();
}
enum Color implements IL{
    PED(255,0,0),BLUE(0,0,255),
    BLACK(0,0,0),
    YELLOW(255,255,0),GREEN(0,255,0);
    private int redValue;
    private int greenValue;
    private int blueValue;

    Color(int redValue, int greenValue, int blueValue) {
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }
    @Override
    public void show() {
        System.out.println(" Ù–‘" + redValue + "," + greenValue + "," + blueValue);
    }
}