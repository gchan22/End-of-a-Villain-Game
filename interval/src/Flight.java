public class Flight {
    //variables
    private String flightName;
    private Interval departure;
    private Interval duration;
    //constructor
    public Flight(String flightName, Interval departure, Interval duration) {
        this.flightName = flightName;
        this.departure = departure;
        this.duration = duration;
    }
    //Writes the flight information in format
    public String toString() {
        return String.format("%s departs @%s arrives @%s",
                flightName, getDeparture(), getArrival());}
    //Compares one departure time with another departure time
    public int compareTo(Flight other){
        return departure.compareTo(other.getDeparture());}
    //Assigns the variables too references
    public String getFlightName() {return flightName;}
    public Interval getDeparture() {return departure;}
    public Interval getDuration() {return duration;}
    //Adds departure and duration to get arrival
    public Interval getArrival() {return departure.getLater(duration);}
    //Checks true or false if a flight arrives before another flight or interval
    public boolean arrivesBefore(Interval then) {
        return this.getArrival().isEarlierThan(then);}
    public boolean arrivesBeforeDepartureOf(Flight next) {
        return this.getArrival().isEarlierThan(next.getDeparture());}
    //Checks true or false if a flight departs after another flight or interval
    public boolean departsAfter(Interval then) {
        return this.getDeparture().isLaterThan(then);}
    public boolean departsAfterArrivalOf(Flight prior) {
        return this.getDeparture().isLaterThan(prior.getArrival());}
    //Adds an interval to departure time to delay the flight departure
    public void delayFlight(Interval delay) {departure = departure.getLater(delay);}
    //Test code to see if correct
    public static void main(String[] args) {
        Interval before = new Interval(1, 2, 50);
        Interval later = new Interval(2,2,20);
        Interval bbefore = new Interval(1, 4, 20);
        Interval llater = new Interval(2,3,10);
        Flight now = new Flight("TOG", before, later);
        Flight after = new Flight("TOG", bbefore, llater);
        System.out.println(now);
        System.out.println(now.compareTo(after));
        System.out.println(now.getFlightName());
        System.out.println(now.getDuration());
        System.out.println(now.arrivesBefore(later));
        System.out.println(now.arrivesBeforeDepartureOf(after));
        System.out.println(now.departsAfter(later));
        System.out.println(now.departsAfterArrivalOf(after));
        System.out.println(now);
        now.delayFlight(before);
        System.out.println(now);
    }
}
