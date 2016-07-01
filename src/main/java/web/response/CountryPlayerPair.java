package web.response;

import java.util.List;

/**
 * Created by zorya on 1/7/16.
 */
public class CountryPlayerPair {

    String country;
    List<NameValuePair> players;

    public CountryPlayerPair() {
    }

    public CountryPlayerPair(String country, List<NameValuePair> players) {
        this.country = country;
        this.players = players;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<NameValuePair> getPlayers() {
        return players;
    }

    public void setPlayers(List<NameValuePair> players) {
        this.players = players;
    }



}
