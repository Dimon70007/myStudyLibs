package homework;

import java.util.List;

/**
 * Created by OTBA}|{HbIu` on 16.09.16.
 */
public class CollectionsHelper
        // BEGIN (write your solution here)
        <T extends Human> {
    // END

    public T findFirst(final List<T> humans, final String namePrefix, final Sex sex) {
        // BEGIN (write your solution here)

        for (T human : humans) {//going by list of humans
            if (human.getName().startsWith(namePrefix)
                    && human.getSex().equals(sex))
                return human;//return hunam, whos name starts with namePrefix and has equals sex
        }
        return null;
        // END

    }
}