package Lesson_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// реализация алгоритма поиска числа Фибоначчи с использование цикла
/*
прим: условие задания понял не до конца
 */
public class Solution_3 {
    public static void main(String[] args) throws IOException {
        System.out.println("Введите номер элемента числовой последовательности Фибоначчи");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine());
        int sum = 0;
        int first = 0;
        int second = 1;
        if (n < 47) {
            for (int i = 1; i < n; i++) {
                sum = first + second;
                first = second;
                second = sum;
            }
            System.out.printf("Этому номеру соответствует число: %d",sum);
        } else
            System.out.println("Сумма чисел последовательности превышает допустимые значения int, пожалуйста, введите число не больше 46");


    }
}
