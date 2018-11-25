package net.zaycev.careers;

//класс хранит только статичекие методы для изменения массива
final class ArrayHelper {

    private ArrayHelper() {
    }

    //вспомогательный метод поиска максимального элемента
    //прямым перебором всего массива находим первый максимальный элемент начиная с 0 и возвращаем его индекс.
    private static int findMaxElementIndex(final int [] inputArray){
        int index = 0;
        int max = inputArray[index];
        for(int i = index + 1; i < inputArray.length; i++){
            if(max < inputArray[i]){
                max = inputArray[i];
                index = i;
            }
        }
        return index;
    }

    //обнуление найденной первой максимальной ячейки и перераспределение её значения по всем ячейкам начиная
    // со следующей.
    // count - количество итераций
    public static void distributeMaxElement(final int count, int[] distributeArray) throws Exception {

        //задаем количество итераций
        for (int i = 0; i < count; i++) {

            //находим индекс максимального элемента
            int indexOfMaxElement = findMaxElementIndex(distributeArray);
            //сохраняем значение максимального элемента
            int maxValue = distributeArray[indexOfMaxElement];
            //если maxValue < 0, значит в массиве остались только отрицательные числа,  мы не знаем как их распределять
            if (maxValue < 0) {
                throw new Exception("Неверный ввод. Для отрицательных чисел не задана функция распределения.");
            }
            //обнуляем максимальный элемент
            distributeArray[indexOfMaxElement] = 0;

            // quotient - результат целочисленного деления на размер массива прибавляем каждому элементу
            int quotient;
            quotient = maxValue / distributeArray.length;
            if (quotient != 0)
                for (int qi = 0; qi < distributeArray.length; qi++) {
                    distributeArray[qi] += quotient;
                }

            // remainder - результат остатка от деления на размер массива распределяем по единице начиная со следующего
            // за максимальным элемента
            int remainder = maxValue % distributeArray.length;
            int index = indexOfMaxElement + 1;

            while (remainder > 0) {
                //дошли до конца массива?
                if ((index == distributeArray.length)) {
                    index = 0;
                }
                //инкриментируем значение ячейкм, переходим на следующий элемент, уменьшаем остаток
                distributeArray[index]++;
                index++;
                remainder--;
            }
        }
    }
}