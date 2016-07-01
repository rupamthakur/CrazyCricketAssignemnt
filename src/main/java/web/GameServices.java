package web;

/**
 * Created by rupam on 26/6/16.
 */

import dao.GameDAO;
import pojo.Game;
import pojo.GameLeaderBoardService;
import web.response.CountryPlayerPair;
import web.response.NameValuePair;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/api")
public class GameServices {

    GameDAO gameDao = new GameDAO();
    private GameLeaderBoardService gameLeaderBoardService = new GameLeaderBoardService();

    @GET
    @Path("/games")
    @Produces(MediaType.APPLICATION_XML)
    public List<Game> getGames(){
        return new GameDAO().getAllGames();
    }

    /*
    @GET
    @Path("/leaderboard")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NameValuePair> getCountryLeaderBoard(){
        return gameLeaderBoardService.getCountryLeaderBoard();
    }
    */

    @GET
    @Path("/leaderboard")
    @Produces(MediaType.APPLICATION_JSON)
    public List<NameValuePair> getCountryLeaderBoard(@DefaultValue("null")@QueryParam("start") String start, @DefaultValue("null")@QueryParam("end") String end){
        if(start.equals("null") || end.equals("null"))
            return gameLeaderBoardService.getCountryLeaderBoard();
        else
            return gameLeaderBoardService.getCountryLeaderBoard(start,end);
    }

    @GET
    @Path("/ping")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping(){
        return "pong";
    }


}
