package net.zaycev.careers;

import java.util.Arrays;

class ArrayCycleComputation {

    private final ArrayState initialDataArray;
    private ArrayState cycleFirstElement;
    private int cycleLength = -1;

    public ArrayCycleComputation(ArrayState initialDataArray) {
        this.initialDataArray = initialDataArray;
    }

    //основной метод вычисления цикла, запускающий вспомогательные
    public boolean computeCycle () throws Exception{

        //Алгоритм двух указателей. В цикле совершаем над быстрым массивом  два шага, над медленным один, и сравниваем
        //значения. Когда значения массивов сравняются, значит они внутри цикла, и кол-во пройденных шагов кратно
        //длине цикла. Зная этот параметр и условия вычисляем начало и длину цикла
        //вычисляем i, чтобы Xi = X2i
        ArrayState meetPoint = findMeetPoint(initialDataArray);
        if (meetPoint == null)
            return false;
        //находим первый элемент цикла
        cycleFirstElement = findFirstCycleIteration(meetPoint, initialDataArray);
        //находим длину цикла
        cycleLength = findCycleLength(cycleFirstElement);
        return (cycleLength > -1);
     }

    //сообщаем результат
    public String getCycleInfo(){
        if ((cycleFirstElement != null) && (cycleLength > -1)) {
            return "Количество шагов до обнаружения цикла :" +
                    (cycleFirstElement.getIteration() + cycleLength) +
                    System.lineSeparator() +
                    "Длина цикла :" +
                    cycleLength +
                    System.lineSeparator();
        }
        else return null;
    }

    private static ArrayState findMeetPoint(ArrayState inputArray) throws Exception{

        //оба массива на начало
        int[] slowArray = Arrays.copyOf(inputArray.getDataArray(),inputArray.getDataArray().length);
        int[] fastArray = Arrays.copyOf(slowArray,slowArray.length);

        int iteration = 0;
        do {
            ArrayHelper.distributeMaxElement(1, slowArray);
            ArrayHelper.distributeMaxElement(2, fastArray);
            if (iteration < Integer.MAX_VALUE) {
                iteration++;
            } else {
                //если счетчик достиг максимального значения, считаем что в заданных ограничениях цикл найти не удается
                return null;
            }
            } while (!Arrays.equals(slowArray, fastArray));

        //возвращаем найденное состояние медленного массива
        return new ArrayState(iteration,slowArray);
    }

    //зная период повторения i, и учитывая что Xm = X(m+i), перебором множества с начала находим начало цикла m
    private static ArrayState findFirstCycleIteration(ArrayState meetPoint, ArrayState inputArray) throws Exception{

        //медленный массив - начальное состояние, быстрый - через i шагов в состояние Xi = X2i
        int[] slowArray = Arrays.copyOf(inputArray.getDataArray(),inputArray.getDataArray().length);
        int[] fastArray = Arrays.copyOf(meetPoint.getDataArray(),meetPoint.getDataArray().length);

        //преобразуем в цикле каждый массив по одному шагу, когда сравняются - в slowArray начало цикла m
        int iteration = 0;

        while (!Arrays.equals(slowArray, fastArray)) {
            ArrayHelper.distributeMaxElement(1, slowArray);
            ArrayHelper.distributeMaxElement(1, fastArray);
            iteration++;
        }
        //возвращаем количство шагов до начала цикла и значение массива на этом шаге,
        return new ArrayState(iteration, slowArray);
    }

    //зная начало цикла Xm выполняем над ним функцию распределения, пока значения не совпадут
    // количество шагов = длина цикла
    private static int findCycleLength (ArrayState cycleFirstElement) throws Exception {

        //в fastArray значение cycleFirstElement
        int[] fastArray = Arrays.copyOf(cycleFirstElement.getDataArray(),cycleFirstElement.getDataArray().length);
        //выполняем функцию distributeMaxElement, пока значение fastArray не станет равным началу цикла
        int iteration = 0;
        do {
            ArrayHelper.distributeMaxElement(1, fastArray);
            if (iteration < Integer.MAX_VALUE) {
                iteration++;
            } else {
                //если счетчик достиг максимального значения, считаем что в заданных ограничениях длину цикла найти не удается
                return -1;
            }
        } while (!Arrays.equals(cycleFirstElement.getDataArray(), fastArray));
        //возвращаем длину цикла
        return iteration;
    }
}