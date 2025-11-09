package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        //create 3 new alist
        AList nAlist = new AList();
        AList<Double> timeInSecondsAlist = new AList<>();
        AList opCountsAlist = new AList();
        //operation
        for (int n = 1000; n <= 128000; n *= 2) {
            //new sw
            Stopwatch sw = new Stopwatch();
            //operation
            AList tempAlist = new AList();
            for (int i = 1; i <= n; i += 1) {
                tempAlist.addLast(1);
            }
            double tempTimeInSeconds = sw.elapsedTime();
            //load in
            nAlist.addLast(n);
            timeInSecondsAlist.addLast(tempTimeInSeconds);
            opCountsAlist.addLast(n);
        }
        printTimingTable(nAlist, timeInSecondsAlist, opCountsAlist);
    }
}
