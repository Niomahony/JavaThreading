public class Main {
    public static void main(String[] args) throws InterruptedException {
        int[] array = new int[100024];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 10);
        }
        final long start = System.currentTimeMillis();
        int[][] splitArrays = MultiThread.splitArray(array);
        MultiThread.initialiseMultiThread(splitArrays);
        final long end = System.currentTimeMillis();
        System.out.println("The program was running: " + (end - start) + "ms.");


    }

}
