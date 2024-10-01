import java.util.Arrays;

public class MultiThread extends Thread {

    private int[] splitInputArray;
    private int sum;
    private String threadName;

    MultiThread(String name, int[] splitInputArray) {
        this.threadName = name;
        this.splitInputArray = splitInputArray;
        System.out.println("Creating " + threadName);
        System.out.println("Creating " + threadName);
    }

    public void run() {
        System.out.println("Running " + threadName);
        sum = sumSplitArray(splitInputArray);
        System.out.println("Thread " + threadName + " completed with sum: " + sum);
    }

    public int sumSplitArray(int[] splitInputArray) {
        int sum = 0;
        for (int i : splitInputArray) {
            sum += i;
        }
        return sum;
    }

    public static int[][] splitArray(int[] inputArray) {
        int chunks = Runtime.getRuntime().availableProcessors();
        int baseChunkSize = inputArray.length / chunks;
        int remainder = inputArray.length % chunks;

        int start = 0;
        int[][] chunkedArray = new int[chunks][];

        for (int i = 0; i < chunks; i++) {
            int chunkSize = baseChunkSize + (i < remainder ? 1 : 0);
            int end = start + chunkSize;

            chunkedArray[i] = Arrays.copyOfRange(inputArray, start, end);
            start = end;
        }

        for (int i = 0; i < chunkedArray.length; i++) {
            System.out.println("Chunk " + (i + 1) + ": " + Arrays.toString(chunkedArray[i]));
        }
        return chunkedArray;
    }

    public static void initialiseMultiThread(int[][] splitArrays) throws InterruptedException {
        int n = splitArrays.length;
        MultiThread[] threads = new MultiThread[n];

        for (int i = 0; i < n; i++) {
            threads[i] = new MultiThread("Thread-" + (i + 1), splitArrays[i]);
            threads[i].start();
        }

        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            threads[i].join();
            totalSum += threads[i].getSum();
        }

        System.out.println("Total sum of all chunks: " + totalSum);
    }

    public int getSum() {
        return sum;
    }

}
