import org.junit.Test;

public class NewArrayTest {

    @Test
    public void arr1(){
        int [] arr1 = {1,2,3,4,};
        int [] arr2 = {
                111,
                222,
                333,
                444, // 末尾的逗号是可选的，这让长数组更好维护<thinking in java>
        };
    }

    @Test
    public void initArr(){
        int [] arr3 = new int[0];
        int [] arr4 = new int[]{};
        int [] arr5 = new int[]{1,2,3};
        int [] arr6 = new int[]{1,2,3,};
    }


}
