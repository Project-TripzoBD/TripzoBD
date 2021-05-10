package tripzobd.com;

public class showbusfirebase {
    String bus_company_name, bus_id, journey_date;

    public showbusfirebase() {
    }

    public showbusfirebase(String bus_company_name, String bus_id, String journey_date) {
        this.bus_company_name = bus_company_name;
        this.bus_id = bus_id;
        this.journey_date = journey_date;
    }

    public String getBus_company_name() {
        return bus_company_name;
    }

    public void setBus_company_name(String bus_company_name) {
        this.bus_company_name = bus_company_name;
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
}
