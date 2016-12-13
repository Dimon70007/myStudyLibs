
import java.util.Date;
import java.util.List;

/**
 * Created by OTBA}|{HbIu` on 06.09.16.
 */
public class Course {


    private final String uuid;

    private final String name;

    private final List<Session> sessions;

    public Course(final String uuid, final String name, final List<Session> sessions) {
        this.uuid = uuid;
        this.name = name;
        this.sessions = sessions;
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    @Override
    public boolean equals(final Object object) {
        // BEGIN (write your solution here)
        if(!(object instanceof Course)) return false;

        return this.uuid.hashCode()== ((Course)object).uuid.hashCode();
        // END
    }

    //some code

    @Override
    public int hashCode() {
        // BEGIN (write your solution here)
        if(this.uuid==null || this.uuid.equals("")) return 0;
        char[] charUuid=uuid.toCharArray();
        int result = 0;
        for (char ch:charUuid){
            result+=ch;
        }
        return result;
        // END
    }


    public class Session {

        private final Date startDate;

        public Session(final Date startDate) {
            this.startDate = startDate;
        }

        public Date getStartDate() {
            return this.startDate;
        }

        public Course getCourse() {
            return Course.this;
        }

        @Override
        public boolean equals(final Object object) {
            // BEGIN (write your solution here)
            if (!(object instanceof Session)) return false;

            return this.getStartDate().equals(((Session) object).getStartDate())
                    && this.getCourse().equals(((Session) object).getCourse());
            // END
        }

        @Override
        public int hashCode() {
            // BEGIN (write your solution here)

            return startDate.hashCode();
            // END
        }

    }
}

