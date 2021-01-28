package recursion;

/**
 * 常用递归算法
 *
 * @author tangjing
 * @date 2021/01/28 10:48
 */
public class CommonRecursion {
    // 二分查找,返回元素下标
    public static int binarySearch(int[] arr, int low, int high, int target) {
        if (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                // 由于array[mid]不是目标值，因此再次递归搜索时，可以将其排除
                return binarySearch(arr, low, mid - 1, target);
            } else {
                return binarySearch(arr, mid + 1, high, target);
            }

        }
        return -1;
    }

    // 阶乘
    public static long factorial(int n) {
        // 边界结束条件
        if (n == 1) {
            return 1;
        }
        if (n < 0) {
            return -1;
        }
        return factorial(n - 1) * n;
    }

    public static void main(String[] args) {
        int[] a = {1, 46, 47, 99, 175, 243};
        System.out.println(binarySearch(a, 0, a.length - 1, 243));
        System.out.println(factorial(4));
    }
}
