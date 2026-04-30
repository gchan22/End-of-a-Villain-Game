
import java.util.ArrayList;
public class Itinerary {
    ArrayList<Flight> flights;
    public Itinerary() {
        flights = new ArrayList<>();}
    //public String toString() {
        //return ;}
    public boolean addFlight(Flight segment) {
        for (int i = 0; i < flights.size(); i++) {
            Flight found = flights.get(i);
            if (!flights.get(i).equals(segment)) {
                addFlight(segment);
            }
        }
        return false;
    }

    public boolean removeFlight(Flight segment) {
            for(int i = 0; i<flights.size(); i++ ) {
                Flight found = flights.get(i);
                if (flights.get(i).equals(segment)) {
                    removeFlight(segment);}
                //public int removeFlights(Interval beginning, Interval until) {}
                //public Interval itineraryLength() {}
                //public Interval longestLayover() {}
                //public static void main(String[] args) {}
            }
        }
