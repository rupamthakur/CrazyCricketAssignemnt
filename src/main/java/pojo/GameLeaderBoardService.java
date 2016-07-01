package pojo;

import dao.GameDAO;
import web.response.CountryPlayerPair;
import web.response.NameValuePair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by rupam on 26/6/16.
 */
public class GameLeaderBoardService {

    public List<NameValuePair> sort(Map<String, Integer> countryAndCount){
        //sort by score
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(countryAndCount.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {

                return (me2.getValue()).compareTo(me1.getValue());
            }
        });

        // Convert sorted map back to a Map
        List<NameValuePair> nameValuePairs = new LinkedList<NameValuePair>();
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Iterator<Map.Entry<String, Integer>> it = list.iterator(); it.hasNext();) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put(entry.getKey(), entry.getValue());
            NameValuePair nameValuePair = new NameValuePair(entry.getKey(), entry.getValue());
            nameValuePairs.add(nameValuePair);
        }
        return nameValuePairs;
    }


    public List<NameValuePair> getCountryLeaderBoard() {
        Map<String, Integer> countryAndCount = new HashMap<String, Integer>();
        List<Game> games = new GameDAO().getAllGames();
        Integer count = 0;
        if (games != null) {
            for (Game game : games) {
                if (countryAndCount.containsKey(game.getWinnerCountry())) {
                    count = countryAndCount.get(game.getWinnerCountry());
                    count++;
                } else {
                    count = 1;
                }
                countryAndCount.put(game.getWinnerCountry(), count);
            }
        }

        return sort(countryAndCount);

    }

    public List<NameValuePair> getCountryLeaderBoard(String start, String end) {
        Map<String, Integer> countryAndCount = new HashMap<String, Integer>();
        List<Game> games = new GameDAO().getAllGames();
        List<Game> gamesBetweenDates = new LinkedList<Game>();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");

        if (games != null) {
            for (Game game : games) {
                Long val = game.getDate();
                try {
                    Date startDate = ft.parse(start);
                    Date endDate = ft.parse(end);
                    Date gameDate = new Date(val);
                    if((startDate.before(gameDate))||(startDate.equals(gameDate)))
                    {
                        if((endDate.after(gameDate))||(endDate.equals(gameDate))){
                            gamesBetweenDates.add(game);
                        }
                    }
                }
                catch (ParseException e){

                }

            }
        }

        Integer count = 0;
        if (games != null) {
            for (Game game : gamesBetweenDates) {
                if (countryAndCount.containsKey(game.getWinnerCountry())) {
                    count = countryAndCount.get(game.getWinnerCountry());
                    count++;
                } else {
                    count = 1;
                }
                countryAndCount.put(game.getWinnerCountry(), count);
            }
        }

        return sort(countryAndCount);
    }

}
