package sort;

public class SortCheck {
    public static void main(String[] args) {
        sortValidation(10000, 30,1000);
    }

    private static void sortValidation(int validationCount, int maxLen, int maxValue) {
        for (int i = 0; i < validationCount; i++) {
            int[] ints = SortUtil.lenRandomValueRandom(20, 1000);
            InsertSort.sort(ints);

            if (!SortUtil.isSorted(ints)) {
                System.out.println("拉胯");
                SortUtil.printArrInLine(ints);
                return;
            }
        }
        System.out.println("New Bee!");
    }
}
