import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @author arhaiyun
 * @version 1.0
 * @date 2020/4/25 14:33
 */

public class ForkJoinTest {

    public static void main(String... args) throws InterruptedException, ExecutionException {
        //模拟一个“很大”的List，这里用直接用数组代替了（题目里其实也没说明白“很大”到底是什么概念，实际上太大了会OOM，
        // 但我觉得这道题主要考查的是对并发编程的基本思路吧，应该不会考察太深的，
        // 所以不用在意了，要是确实是要把怎么样避免OOM也考虑到的话暂时还没想到应该怎样解决）
        int[] array = IntStream.rangeClosed(0, 100_000_000).toArray();

        //简单粗暴的做法
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        System.out.println(sum);

        //Fork/Join的做法
        ForkJoinPool forkJoinPool = new ForkJoinPool(); //起一个数量等于可用CPU核数的池子（对应题目中“充分利用多核”）
        Task task = new Task(0, array.length, 10_000, array);

        Future<Integer> future = forkJoinPool.submit(task); //提交Task
        System.out.println(future.get()); //获得返回值

        forkJoinPool.shutdown(); //关闭池子
    }

    static class Task extends RecursiveTask<Integer> {

        public static final int DEFAULT_THRESHOLD = 1000;
        private int high, low;
        private int threshold;
        private int[] array;

        Task(int low, int high, int threshold, int[] array) {
            this.array = array;
            this.low = low;
            this.high = high;
            this.threshold = threshold; //任务划分的最小值（若为1000则含义是Fork到1000大小时就不再继续Fork了）
        }

        @Override
        protected Integer compute() {
            //System.out.println("low: " + low + "  high: " + high);
            if (high - low <= threshold) { //到了不能再Fork的阈值后直接循环累加返回
                int sum = 0;
                for (int i = low; i < high; i++) {
                    sum += array[i];
                }
                //System.out.println("sum: " + sum);
                return sum;
            } else { //没有到阈值的话，继续递归拆分任务为左任务和右任务（分治法的思想）
                int middle = (high - low) / 2 + low;
                //System.out.println("middle: " + middle);
                Task leftHandTask = new Task(low, middle, threshold, array); //左任务
                Task rightHandTask = new Task(middle, high, threshold, array); //右任务
                leftHandTask.fork(); //左任务还要继续拆，直到满足上边if里的阈值条件
                rightHandTask.fork(); //右任务也要继续拆，直到满足上边if里的阈值条件
                return leftHandTask.join() + rightHandTask.join(); //最后Join得到结果
            }
        }
    }
}
