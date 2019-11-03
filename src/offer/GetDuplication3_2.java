package offer;

/**
 * 复杂度
 * 时间复杂度说明：函数countRange()将被调用O(logn)次，每次需要O(n)的时间。
 * 时间复杂度：O(nlogn)  （while循环为O(logn)，coutRange()函数为O(n)）
 * 空间复杂度：O(1)
 * @author dengjiyun
 *
 */
public class GetDuplication3_2 {

	public static void main(String[] args) {
		int a = 6,b = 8;
		//不用乘除法计算平均值
		int middle = (a + b) / 2;
		int mid = ((b - a) >> 1) + a;
		System.out.println(middle + "," + mid);
	}

	/**
     * 找到数组中一个重复的数字
     * 返回-1代表无重复数字或者输入无效
     */
    public int getDuplicate(int[] arr) {
        if (arr == null || arr.length <= 0) {
            System.out.println("数组输入无效！");
            return -1;
        }
        for (int a : arr) {
            if (a < 1 || a > arr.length - 1) {
                System.out.println("数字大小超出范围！");
                return -1;
            }
        }
        int low = 1;
        int high = arr.length - 1; // high即为题目的n
        int mid, count;
        while (low <= high) {
            mid = ((high - low) >> 1) + low;
            count = countRange(arr, low, mid);
            if (low == high) {
                if (count > 1)
                    return low;
                else
                    break; // 必有重复，应该不会出现这种情况吧？
            }
            if (count > mid - low + 1) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
 
    /**
     * 返回在[low,high]范围中数字的个数
     */
    public int countRange(int[] arr, int low, int high) {
        if (arr == null)
            return 0;
 
        int count = 0;
        for (int a : arr) {
            if (a >= low && a <= high)
                count++;
        }
        return count;
    }
}
