package Lesson_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// рекурсивная реализация алгоритма поиска числа Фибоначчи
public class Solution_1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Введите номер элемента числовой последовательности Фибоначчи");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());

        if (n < 47) {
            fibonacci(n);
            System.out.printf("Этому номеру соответствует число: %d",fibonacci(n));
        } else
            System.out.println("Сумма чисел последовательности превышает допустимые значения int, пожалуйста, введите число не больше 46");
    }

    private static int fibonacci(int n) {
        if (n <= 1) return n;

        else return fibonacci(n - 1) + fibonacci(n - 2);

    }

}
