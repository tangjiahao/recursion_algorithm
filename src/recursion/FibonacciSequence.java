package recursion;

/**
 * 斐波那契数列（包含常规解法和优化解法）
 *
 * @author tangjing
 * @date 2021/01/28 11:13
 */
public class FibonacciSequence {
    /**
     * @param n
     * @return
     * @description 经典递归法求解
     * <p>
     * 斐波那契数列如下：
     * <p>
     * 1,1,2,3,5,8,13,21,34,...
     * <p>
     * *那么，计算fib(5)时，需要计算1次fib(4),2次fib(3),3次fib(2)，调用了2次fib(1)*，即：
     * <p>
     * fib(5) = fib(4) + fib(3)
     * <p>
     * fib(4) = fib(3) + fib(2) ；fib(3) = fib(2) + fib(1)
     * <p>
     * fib(3) = fib(2) + fib(1)
     * <p>
     * 这里面包含了许多重复计算，而实际上我们只需计算fib(4)、fib(3)、fib(2)和fib(1)各一次即可，
     * 后面的optimizeFibonacci函数进行了优化，使时间复杂度降到了O(n).
     * @author rico
     * @created 2017年5月10日 下午12:00:42
     */
    public static int fibonacci(int n) {
        if (n == 1 || n == 2) {     // 递归终止条件
            return 1;       // 简单情景
        }
        return fibonacci(n - 1) + fibonacci(n - 2); // 相同重复逻辑，缩小问题的规模
    }

    /**
     * @param first  数列的第一项
     * @param second 数列的第二项
     * @param n      目标项
     * @return
     * @description 对经典递归法的优化
     * <p>
     * 斐波那契数列如下：
     * <p>
     * 1,1,2,3,5,8,13,21,34,...
     * <p>
     * 那么，我们可以这样看：fib(1,1,5) = fib(1,2,4) = fib(2,3,3) = 5
     * <p>
     * 也就是说，以1,1开头的斐波那契数列的第五项正是以1,2开头的斐波那契数列的第四项，
     * 而以1,2开头的斐波那契数列的第四项也正是以2,3开头的斐波那契数列的第三项，
     * 更直接地，我们就可以一步到位：fib(2,3,3) = 2 + 3 = 5,计算结束。
     * <p>
     * 注意，前两个参数是数列的开头两项，第三个参数是我们想求的以前两个参数开头的数列的第几项。
     * <p>
     * 时间复杂度：O(n)
     * @author rico
     */
    public static int optimizeFibonacci(int first, int second, int n) {
        if (n > 0) {
            if (n == 1) {    // 递归终止条件
                return first;       // 简单情景
            } else if (n == 2) {            // 递归终止条件
                return second;      // 简单情景
            } else if (n == 3) {         // 递归终止条件
                return first + second;      // 简单情景
            }
            return optimizeFibonacci(second, first + second, n - 1);  // 相同重复逻辑，缩小问题规模
        }
        return -1;
    }

    /**
     * @param n
     * @return
     * @description 非递归解法：有去无回
     * @author rico
     * @created 2017年5月10日 下午12:03:04
     */
    public static int fibonacciLoop(int n) {

        if (n == 1 || n == 2) {
            return 1;
        }

        int result = -1;
        int first = 1;      // 自己维护的"栈",以便状态回溯
        int second = 1;     // 自己维护的"栈",以便状态回溯

        for (int i = 3; i <= n; i++) { // 循环
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }

    /**
     * @param n
     * @return
     * @description 使用数组存储斐波那契数列
     * @author rico
     */
    public static int fibonacciArray(int n) {
        if (n > 0) {
            int[] arr = new int[n];   // 使用临时数组存储斐波纳契数列
            arr[0] = arr[1] = 1;

            for (int i = 2; i < n; i++) {   // 为临时数组赋值
                arr[i] = arr[i - 1] + arr[i - 2];
            }
            return arr[n - 1];
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println("常见递归方式 ：" + fibonacci(10));
        System.out.println("优化递归方式：" + optimizeFibonacci(1, 2, 9));
        System.out.println("循环栈方式：" + fibonacciLoop(10));
        System.out.println("数组循环方式：" + fibonacciArray(10));


    }
}



