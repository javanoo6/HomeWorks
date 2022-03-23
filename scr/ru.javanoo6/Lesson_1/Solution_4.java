package ru.javanoo6.Lesson_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Solution_4 {
    public static void main(String[] args) throws IOException {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println("Введите номер элемента числовой последовательности Фибоначчи");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Integer n = Integer.parseInt(bufferedReader.readLine());
        System.out.printf("Этому номеру соответствует число: %d", fibonacci.calculation(n));
    }

    private static class Fibonacci {
        public static Map<Integer, BigInteger> cachedFibonacci = new HashMap<Integer, BigInteger>();

        static {
            cachedFibonacci.put(0, BigInteger.valueOf(0));
            cachedFibonacci.put(1, BigInteger.valueOf(1));
        }

        public BigInteger calculation(Integer n) {
            if (n <= 1) {
                return BigInteger.valueOf(n);
            } else if (cachedFibonacci.containsKey(n)) {
                return cachedFibonacci.get(n);
            } else {
                BigInteger result = calculation(n - 1).add((calculation(n - 2)));
                cachedFibonacci.put(n, result);
                return result;
            }
        }
    }

}
