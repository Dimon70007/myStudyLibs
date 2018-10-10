package homework.web.model;

public class Link {

    private final String fullUrl;
    private final String code;
    private final ShortDate expiryDate;

    public Link(final String fullUrl, final String code, final ShortDate expiryDate) {
        this.fullUrl = fullUrl;
        this.code = code;
        this.expiryDate = expiryDate;
    }

    public String getFullUrl() {
        return fullUrl;
    }

    public String getCode() {
        return code;
    }

    public ShortDate getExpiryDate() {
        return expiryDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Link link = (Link) o;

        if (fullUrl != null ? !fullUrl.equals(link.fullUrl) : link.fullUrl != null) return false;
        if (code != null ? !code.equals(link.code) : link.code != null) return false;
        return expiryDate != null ? expiryDate.equals(link.expiryDate) : link.expiryDate == null;
    }

    @Override
    public String toString() {
        return "Link{" +
                "fullUrl='" + fullUrl + '\'' +
                ", code='" + code + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }

    public static class ShortDate {

        private final int month;
        private final int year;

        public ShortDate(int month, int year) {
            this.month = month;
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final ShortDate shortDate = (ShortDate) o;

            if (month != shortDate.month) return false;
            return year == shortDate.year;
        }

        @Override
        public String toString() {
            return "ShortDate{" +
                    "month=" + month +
                    ", year=" + year +
                    '}';
        }
    }
}
