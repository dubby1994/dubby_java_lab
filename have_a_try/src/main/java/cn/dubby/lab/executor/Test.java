package cn.dubby.lab.executor;

/**
 * Created by yangzheng03 on 2018/1/18.
 */
public class Test {

    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 99, 100, 101, 9999};
        int target = 99991;

        System.out.println(data[search(data, target, 0, data.length - 1)] == target);
    }

    /**
     * @param data   从小到大
     * @param target
     * @param left
     * @param right
     * @return
     */
    public static int search(int[] data, int target, int left, int right) {
        if (right == left) {
            if (data[right] == target)
                return right;
            else
                return -1;
        }
        int middleIndex = (left + right) / 2;
        if (data[middleIndex] == target) {
            return middleIndex;
        } else if (data[middleIndex] > target) {
            return search(data, target, left, middleIndex);
        } else {
            return search(data, target, middleIndex + 1, right);
        }
    }


}
