public class Interval implements Comparable<Interval> {
    private final int totalMinutes;     // final makes totalMinutes immutable!
    private int getTotalMinutes() {     // complete encapsulation--totalMinutes
        return totalMinutes;            //    is never accessed directly
    }

    // these getters are all for "derived" fields (values computed on demand)
    // By normalized we mean an interval described in days, hours and minutes
    // where hours and minutes are in the range 0..59. E.g., 2 days, 19 hours
    // and 15 minutes is normalized, but 1 day, 33 hours, 75 minutes is not.

    // normalized days in totalMinutes
    public int getDay() {
        return getTotalMinutes() / (60 * 24);
    }
    // normalized hours in totalMinutes
    public int getHour() {
        return getTotalMinutes() % (60 * 24) / 60;
    }
    // normalized minutes in totalMinutes
    public int getMinute() {
        return getTotalMinutes() % 60;
    }

    // required implementation since Interval implements Comparable<Interval>
    public int compareTo(Interval other) {
        return getTotalMinutes() - other.getTotalMinutes();
    }

    // other < this: other "comes before" this ??
    public boolean isEarlierThan(Interval other) {
        return compareTo(other) < 0;
    }
    // other > this; other "comes after" this ??
    public boolean isLaterThan(Interval other) {
        return compareTo(other) > 0;
    }

    // provided--use it!
    public String toString() {
        if (getDay() > 0)
            return String.format("%dd+%02d:%02d", getDay(), getHour(), getMinute());
        return String.format("%02d:%02d", getHour(), getMinute());
    }

    public Interval(int day, int hour, int minute) {
        // If any argument is negative, the program will "crash" and the
        // error msg with a traceback will be provided.
        //
        if (day < 0 || hour < 0 || minute < 0)
            throw new IllegalArgumentException("negative argument value(s) passed to Interval constructor");

        totalMinutes = (day * 24 + hour) * 60 + minute;
    }

    public Interval(int hour, int minute) {
        this(0, hour, minute);
    }
    private Interval(int minutes) {
        this(0, minutes);
    }

    // howLong after this
    public Interval getLater(Interval howLong) {
        return new Interval(getTotalMinutes() + howLong.getTotalMinutes());
    }
    // howLong before this
    public Interval getEarlier(Interval howLong) {
        return new Interval(getTotalMinutes() - howLong.getTotalMinutes());
    }
    // how long from then until this; then < this
    public Interval getSince(Interval then) {
        return new Interval(getTotalMinutes() - then.getTotalMinutes());
    }
    // how long from this until then; this < then
    public Interval getUntil(Interval then) {
        return new Interval(then.getTotalMinutes() - getTotalMinutes());
    }

    public static void main(String[] args) {
        Interval now = new Interval(0, 6, 45);
        System.out.println(now);
        Interval then = now.getLater(new Interval(30, 65));
        System.out.println(then);
        System.out.println(then.isLaterThan(now));
        System.out.println(then.getSince(now));

        // this should NOT work (becomes negative)
        Interval tooEarly = now.getEarlier(new Interval(3, 0, 0));
    }
}