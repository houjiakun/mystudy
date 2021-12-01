package com.study.arithmetic;

public class Sort {

    //冒泡排序
    public static void bubbleSort(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    //选择排序
    public static void selectSort(int[] numbers) {
        //数组长度
        int size = numbers.length;
        //中间变量
        int temp = 0;

        for (int i = 0; i < size; i++) {
            //待确定的位置
            int k = i;
            //选择出应该在第i个位置的数
            for (int j = size - 1; j > i; j--) {
                if (numbers[j] < numbers[k]) {
                    k = j;
                }
            }
            //交换两个数
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
    }

    //插入排序
    public static void insertSort(int[] numbers) {
        int size = numbers.length;
        int temp = 0;
        int j = 0;

        for (int i = 0; i < size; i++) {
            temp = numbers[i];
            //假如temp比前面的值小，则将前面的值后移
            for (j = i; j > 0 && temp < numbers[j - 1]; j--) {
                numbers[j] = numbers[j - 1];
            }
            numbers[j] = temp;
        }
    }

    /**
     * 希尔排序
     * 第一步：取一个小于length的整数作为增量 Increment，将数据分成Increment组
     * 第二步：在各组内对其进行直接插入排序
     * 第三步 缩小Increment，在分成若干组，再对其进行直接插入排序
     * 第四步：直至增量为1，再对其进行直接插入排序
     */
    public static void shellSort(int[] data) {
        int j = 0;
        int temp = 0;
        //每次将步长缩短为原来的一半
        for (int increment = data.length / 2; increment > 0; increment /= 2) {
            for (int i = increment; i < data.length; i++) {
                temp = data[i];
                for (j = i; j >= increment; j -= increment) {
                    //如想从小到大排只需修改这里
                    if (temp > data[j - increment]) {
                        data[j] = data[j - increment];
                    } else {
                        break;
                    }

                }
                data[j] = temp;
            }

        }
    }

    public static void main(String[] args) {
        int[] array = {2, 5, 7, 9, 4, 2, 3, 5};
        quickSort(array, 0, array.length - 1);
        shellSort(array);
        // heapSort(array);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
     /*   int[] arr = {49, 38, 65, 97, 23, 22, 76, 1, 5, 8, 2, 0, -1, 22};
        insertSort1(arr);
        System.out.println("排序后:");
        for (int i : arr) {
            System.out.println(i);
        }*/
        int[] array1 = {4, 1, 5, 6, 2, 7, 3};
        getIndex(array1, 0, 6);
    }

    /**
     * 堆排序
     * 堆的属性：1.父节点大于两个子节点  2.左子节点为2i，右子节点为2i+1
     * 第一步：构造最大堆，堆第一个元素最大
     * 第二步：将堆中第一个元素与最后的元素对调，那么数组中最大的元素移动到最后面
     * 第三步：递归进行选择最大的依次放入数组末尾
     * 堆排序属于选择排序，选择最大的放入最末尾
     * @param array
     */
    public static void heapSort(int[] array) {
        int arrayLength = array.length;
        //循环建堆
        for (int i = 0; i < arrayLength - 1; i++) {
            //建堆
            buildMaxHeap(array, arrayLength - 1 - i);
            //交换堆顶和最后一个元素
            swap(array, 0, arrayLength - 1 - i);
        }
    }

    //对data数组从0到lastIndex建大顶堆
    public static void buildMaxHeap(int[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //k保存正在判断的节点
            int k = i;
            //如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                //k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    //若果右子节点的值较大
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    //交换他们
                    swap(data, k, biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    //交换
    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    /**
     * 快速排序
     * 第一步: 把第一个当基准数，找到基准数的位置，将数组分成两个数组，左边的数组小于基准数，右边的数组大于基准数
     * 第二步:再对对一个数组和第二个数组做递归快排
     *
     * @param arr
     * @param low
     * @param high
     */
    public static void quickSort(int[] arr, int low, int high) {

        if (low < high) {
            // 找寻基准数据的正确索引
            int index = getIndex(arr, low, high);
            // 进行迭代对index之前和之后的数组进行相同的操作使整个数组变成有序
            //quickSort(arr, 0, index - 1); 之前的版本，这种姿势有很大的性能问题，谢谢大家的建议
            quickSort(arr, low, index - 1);
            quickSort(arr, index + 1, high);
        }
    }

    private static int getIndex(int[] arr, int low, int high) {
        // 基准数据
        int tmp = arr[low];
        while (low < high) {
            // 当队尾的元素大于等于基准数据时,向前挪动high指针
            while (low < high && arr[high] >= tmp) {
                high--;
            }
            // 如果队尾元素小于tmp了,需要将其赋值给low
            arr[low] = arr[high];
            // 当队首元素小于等于tmp时,向前挪动low指针
            while (low < high && arr[low] <= tmp) {
                low++;
            }
            // 当队首元素大于tmp时,需要将其赋值给high
            arr[high] = arr[low];

        }
        // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
        // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
        arr[low] = tmp;
        // 返回tmp的正确位置
        return low;
    }


}
