package core;

import java.util.HashMap;

public class Camera
{
    // переменная
    public static HashMap<String, Integer> carsSpeed = new HashMap<>();

    public static Car getNextCar()
    {
        // переменная строка
        String randomNumber = Double.toString(Math.random()).substring(2, 5);
        // переменная целочисленная
        int randomHeight = (int) (1000 + 3500. * Math.random());
        // переменная число с плавающей точкой
        double randomWeight = 600 + 10000 * Math.random();
        // переменная класса Car
        Car car = new Car(randomNumber, randomHeight, randomWeight, Math.random() > 0.5);
        if(Math.random() < 0.15) {
            car.setIsSpecial();
        }
        Police.resetCalled();

        return car;
    }

    public static Integer getCarSpeed(Car car)
    {
        // Переменная строка
        String carNumber = car.getNumber();
        if(!carsSpeed.containsKey(carNumber)) {
            carsSpeed.put(carNumber, (int) (180 * Math.random()));
        }
        return carsSpeed.get(carNumber);
    }
}