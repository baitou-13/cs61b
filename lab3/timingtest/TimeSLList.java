package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList nSLList = new AList();
        AList<Double> timeSLList = new AList<>();
        AList opCountsSLList = new AList();

        for (int n = 1000; n <= 128000; n *= 2) {
            nSLList.addLast(n);

            //Create an SLList.
            SLList tempList = new SLList();

            //Add N items to the SLList.
            for(int i = 0; i < n; i++) {
                tempList.addLast(i);
            }

            //Start the timer
            Stopwatch sw = new Stopwatch();

            //Perform M getLast operations on the SLList.
            int optiontimes = 10000;
            opCountsSLList.addLast(optiontimes);
            for (int i = 0; i < optiontimes; i++) {
                tempList.getLast();
            }
            double tempTimeInSeconds = sw.elapsedTime();
            timeSLList.addLast(tempTimeInSeconds);

        }

        printTimingTable(nSLList, timeSLList, opCountsSLList);
    }

}
