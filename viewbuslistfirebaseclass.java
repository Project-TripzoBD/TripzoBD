package tripzobd.com;

public class viewbuslistfirebaseclass {
    String bus_company_name, starting_point, ending_point,
            pickup_point_1, pickup_point_2, ticket_price, bus_id, journey_date,
            starting_time, ending_time, pickup_point_time_1, pickup_point_time_2;

    public viewbuslistfirebaseclass() {
    }

    public viewbuslistfirebaseclass(String bus_company_name, String starting_point, String ending_point, String pickup_point_1, String pickup_point_2, String ticket_price, String bus_id, String journey_date, String starting_time, String ending_time, String pickup_point_time_1, String pickup_point_time_2) {
        this.bus_company_name = bus_company_name;
        this.starting_point = starting_point;
        this.ending_point = ending_point;
        this.pickup_point_1 = pickup_point_1;
        this.pickup_point_2 = pickup_point_2;
        this.ticket_price = ticket_price;
        this.bus_id = bus_id;
        this.journey_date = journey_date;
        this.starting_time = starting_time;
        this.ending_time = ending_time;
        this.pickup_point_time_1 = pickup_point_time_1;
        this.pickup_point_time_2 = pickup_point_time_2;
    }


    public String getBus_company_name() {
        return bus_company_name;
    }

    public void setBus_company_name(String bus_company_name) {
        this.bus_company_name = bus_company_name;
    }

    public String getStarting_point() {
        return starting_point;
    }

    public void setStarting_point(String starting_point) {
        this.starting_point = starting_point;
    }

    public String getEnding_point() {
        return ending_point;
    }

    public void setEnding_point(String ending_point) {
        this.ending_point = ending_point;
    }

    public String getPickup_point_1() {
        return pickup_point_1;
    }

    public void setPickup_point_1(String pickup_point_1) {
        this.pickup_point_1 = pickup_point_1;
    }

    public String getPickup_point_2() {
        return pickup_point_2;
    }

    public void setPickup_point_2(String pickup_point_2) {
        this.pickup_point_2 = pickup_point_2;
    }

    public String getTicket_price() {
        return ticket_price;
    }

    public void setTicket_price(String ticket_price) {
        this.ticket_price = ticket_price;
    }

    public String getBus_id() {
        return bus_id;
    }

    public void setBus_id(String bus_id) {
        this.bus_id = bus_id;
    }

    public String getJourney_date() {
        return journey_date;
    }

    public void setJourney_date(String journey_date) {
        this.journey_date = journey_date;
    }

    public String getStarting_time() {
        return starting_time;
    }

    public void setStarting_time(String starting_time) {
        this.starting_time = starting_time;
    }

    public String getEnding_time() {
        return ending_time;
    }

    public void setEnding_time(String ending_time) {
        this.ending_time = ending_time;
    }

    public String getPickup_point_time_1() {
        return pickup_point_time_1;
    }

    public void setPickup_point_time_1(String pickup_point_time_1) {
        this.pickup_point_time_1 = pickup_point_time_1;
    }

    public String getPickup_point_time_2() {
        return pickup_point_time_2;
    }

    public void setPickup_point_time_2(String pickup_point_time_2) {
        this.pickup_point_time_2 = pickup_point_time_2;
    }
}

