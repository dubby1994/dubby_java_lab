import cn.dubby.id.generator.SnowFlakeGenerator;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by naonao on 2017/10/16.
 */
public class Test {

    public static void main(String[] args) {
        SnowFlakeGenerator generator;
        Set<Long> idSet;
        for (int index = 0; index < 1000; ++index) {
            generator = new SnowFlakeGenerator.Factory(1, 7).create(1, 1);
            idSet = new HashSet<Long>();
            for (int i = 0; i < 10000; ++i) {
                long id = generator.nextId();
//                System.out.println(System.currentTimeMillis() + "\t:\t" + id);
                idSet.add(id);
            }

            System.out.println();
            System.out.println(idSet.size());
//            System.out.println("------------------");
        }


//        long currentStamp = System.currentTimeMillis();
//        long newStamp = currentStamp << 22;
//
//        System.out.println(currentStamp);
//        System.out.println(newStamp);
//
//        System.out.println("----------------");
//        System.out.println(newStamp / currentStamp);
//        System.out.println(Math.pow(2, 22));

//        int a = 5;
//        System.out.println(-1 ^ -1 << a);
//        System.out.println(~(-1 << a));
//        System.out.println((1 << a) - 1);
    }

}
