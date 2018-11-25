package net.zaycev.careers;

class Main {

    public static void main(String[] args) {
        final int[] inputData = {0,5,10,0,11,14,13,4,11,8,8,7,1,4,12,11};
        ArrayCycleComputation aCC  = new ArrayCycleComputation(new ArrayState(0, inputData));
        //на некорректные вводные данные кидаем исключение
        try {
            if (aCC.computeCycle())
                System.out.println(aCC.getCycleInfo());
            else System.out.println("Не удалось найти цикл за " + Integer.MAX_VALUE + " итераций");
        } catch (Exception e) {
            System.out.print(e.getMessage());}
    }
}