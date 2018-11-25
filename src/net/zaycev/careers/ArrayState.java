package net.zaycev.careers;

//класс хранит одно текущее состояния данных и количество шагов преобразований
class ArrayState {
    private final int iteration;
    private final int[] dataArray;

    public ArrayState(int iteration, int[] dataArray) {
        this.iteration = iteration;
        this.dataArray = dataArray;
    }

    public int getIteration() {
        return iteration;
    }

    public int[] getDataArray() {
        return dataArray;
    }
}
