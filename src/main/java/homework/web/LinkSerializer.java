package homework.web;
import homework.web.model.Link;
import com.google.gson.Gson;

public class LinkSerializer {

    public static String toJson(final Link link) {
        // BEGIN (write your solution here)
        Gson gson = new Gson();
        return gson.toJson(link);
        // END
    }

    public static void main(String[] args) {
        Link link = new Link("www.google.com","googl", new Link.ShortDate(1,0));
        System.out.println(toJson(link));

    }

}

