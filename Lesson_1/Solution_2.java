package Lesson_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Введите номер элемента числовой последовательности Фибоначчи");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int SIZEOFARRAY = Integer.parseInt(bufferedReader.readLine());
        if (SIZEOFARRAY >= 93){
            System.out.println("Сумма чисел последовательности превышает допустимые значения long, пожалуйста, введите число не больше 92");
        } else{
            long[] arrayWithFibonacci = new long[SIZEOFARRAY +2];
            arrayWithFibonacci[0] = 0;
            arrayWithFibonacci[1] = 1;
            for (int i = 2; i <= SIZEOFARRAY; i++) {
                arrayWithFibonacci[i] = arrayWithFibonacci[i - 1] + arrayWithFibonacci[i - 2];
            }
            System.out.printf("Этому номеру соответствует число: %d",arrayWithFibonacci[arrayWithFibonacci.length - 2]);
//            System.out.println("Этому номеру соответствует число: "+

        }

    }
}