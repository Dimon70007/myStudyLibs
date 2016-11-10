package  homework;

import java.util.List;

/**
 * Created by OTBA}|{HbIu` on 16.09.16.
 */
public class CollectionsHelper{
//        // BEGIN (write your solution here)
//        <T extends Human> {
//    // END
//
//    public T findFirst(final List<T> humans, final String namePrefix, final Sex sex) {
//        // BEGIN (write your solution here)
//
//        for (T human : humans) {//going by list of humans
//            if (human.getName().startsWith(namePrefix)
//                    && human.getSex().equals(sex))
//                return human;//return hunam, whos name starts with namePrefix and has equals sex
//        }
//        return null;
//        // END
//
//    }
public <E extends Human> int findFirstIndex(
        // BEGIN (write your solution here)
        List<? extends Human> humans, String namePrefix
        // END
) {
    // BEGIN (write your solution here)
    for(int i=0;i<humans.size();i++){
        Human human= humans.get(i);
        if(human.getName().startsWith(namePrefix))
            return i;
    }
    return -1;
    // END
}


    public static <T> int findFirstIndex(List<T> list, T name){
            return list.indexOf(name);
        }
        // BEGIN (write your solution here)

        // END

        // findLastIndex
        // BEGIN (write your solution here)
        public static <T> int findLastIndex(List<T> list, T name){
            return list.lastIndexOf(name);
        }
        // END


}