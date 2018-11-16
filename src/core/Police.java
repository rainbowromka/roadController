package core;

import java.util.HashSet;

/**
 * Created by Danya on 24.08.2015.
 */
public class Police
{
    // переменная булева
    private static boolean called = false;
    // переменная
    private static HashSet<String> criminalNumbers;

    public static HashSet<String> getCriminalNumbers()
    {
        if(criminalNumbers != null) {
            return criminalNumbers;
        }
        criminalNumbers = new HashSet<>();
        // переменная целочисленая
        int count = (int) (200 * Math.random());
        for(int i = 0; i < count; i++)
        {
            // переменная строка
            String randomNumber = Double.toString(Math.random()).substring(2, 5);
            criminalNumbers.add(randomNumber);
        }
        return criminalNumbers;
    }

    public static void call(String message)
    {
        called = true;
        System.out.println("Вызов полиции... Причина: " + message);
    }

    public static boolean wasCalled()
    {
        return called;
    }

    //=======================================

    static void resetCalled()
    {
        called = false;
    }
}
