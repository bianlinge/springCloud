import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class GuavaLists {
    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(0);
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);
        integers.add(7);
        List<List<Integer>> partition = Lists.partition(integers, 3);
        partition.forEach((list)-> System.out.println(list.size()));

    }
}
