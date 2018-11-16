import core.*;
import core.Camera;

public class RoadController
{
    // переменная цисло с плавающей точкой
    public static double passengerCarMaxWeight = 3500.0; // kg
    // переменная целочисленая
    public static int passengerCarMaxHeight = 2000; // mm
    // переменная целочисленая
    public static int controllerMaxHeight = 3500; // mm

    // переменная целочисленая
    public static int passengerCarPrice = 100; // RUB
    // переменная целочисленая
    public static int cargoCarPrice = 250; // RUB
    // переменная целочисленая
    public static int vehicleAdditionalPrice = 200; // RUB

    // переменная целочисленая
    public static int maxOncomingSpeed = 60; // km/h
    // переменная целочисленая
    public static int speedFineGrade = 20; // km/h
    // переменная целочисленая
    public static int finePerGrade = 500; // RUB
    // переменная целочисленая
    public static int criminalSpeed = 160; // km/h

    public static void main(String[] args)
    {

        for(int i = 0; i < 10; i++)
        {
            // переменная класса Car
            Car car = Camera.getNextCar();
            System.out.println(car);
            System.out.println("Скорость: " + Camera.getCarSpeed(car) + " км/ч");

            /**
             * Проверка на наличие номера в списке номеров нарушителей
             */
            // переменная булева
            boolean policeCalled = false;
            for(String criminalNumber : Police.getCriminalNumbers())
            {
                // переменная строки
                String carNumber = car.getNumber();
                if(carNumber.equals(criminalNumber)) {
                    Police.call("автомобиль нарушителя с номером " + carNumber);
                    blockWay("не двигайтесь с места! За вами уже выехали!");
                    break;
                }
            }
            if(Police.wasCalled()) {
                continue;
            }

            /**
             * Пропускаем автомобили спецтранспорта
             */
            if(car.isSpecial()) {
                openWay();
                continue;
            }


            /**
             * Проверяем высоту и массу автомобиля, вычисляем стоимость проезда
             */
            // переменная целочисленная
            int carHeight = car.getHeight();
            // переменная целочисленная
            int price = 0;
            if(carHeight > controllerMaxHeight)
            {
                blockWay("высота вашего ТС превышает высоту пропускного пункта!");
                continue;
            }
            else if(carHeight > passengerCarMaxHeight)
            {
                // переменная число с плавающей точкой
                double weight = WeightMeter.getWeight(car);
                //Грузовой автомобиль
                if(weight > passengerCarMaxWeight)
                {
                    price = cargoCarPrice;
                    if(car.hasVehicle()) {
                        price = price + vehicleAdditionalPrice;
                    }
                }
                //Легковой автомобиль
                else {
                    price = passengerCarPrice;
                }
            }
            else {
                price = passengerCarPrice;
            }

            /**
             * Проверка скорости подъезда и выставление штрафа
             */
            // переменная целочисленная
            int carSpeed = Camera.getCarSpeed(car);
            if(carSpeed > criminalSpeed)
            {
                Police.call("cкорость автомобиля - " + carSpeed + " км/ч, номер - " + car.getNumber());
                blockWay("вы значительно превысили скорость. Ожидайте полицию!");
                continue;
            }
            else if(carSpeed > maxOncomingSpeed)
            {
                // переменная целочисленная
                int overSpeed = carSpeed - maxOncomingSpeed;
                // переменная целочисленная
                int totalFine = finePerGrade * (1 + overSpeed / speedFineGrade);
                System.out.println("Вы превысили скорость! Штраф: " + totalFine + " руб.");
                price = price + totalFine;
            }

            /**
             * Отображение суммы к оплате
             */
            System.out.println("Общая сумма к оплате: " + price + " руб.");
        }

    }



    /**
     * Открытие шлагбаума
     */
    public static void openWay()
    {
        System.out.println("Шлагбаум открывается... Счастливого пути!");
    }

    public static void blockWay(String reason)
    {
        System.out.println("Проезд невозможен: " + reason);
    }
}