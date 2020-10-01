package team.backend2.rockpaperscissor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import team.backend2.rockpaperscissor.handler.GamePool;
import team.backend2.rockpaperscissor.model.Game;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

@RestController
//@RequestMapping("/turn")
public class GamePlayController {
//        @RequestMapping(method = RequestMethod.POST)
    @PostMapping("/turn")
        public String handle(HttpServletRequest request, @RequestBody Map<String, Object> payload) {
            //System.out.println("AAAAAAAAAAAAAAAAAAAAA");
            //Cookie player = WebUtils.getCookie(request, "player");
            //Cookie room_id = WebUtils.getCookie(request, "room_id");
            String player = payload.get("uid").toString();
            //String player = "1234";
            String room_id = payload.get("room_id").toString();
            //String room_id = "12345";
            //Integer choose = Integer.parseInt(request.getParameter("choose"));
            Integer choose = Integer.parseInt(payload.get("choose").toString());
            //Integer choose = 1;
            if (player == null)
                return "signup";

            GamePool gamePool = GamePool.getInstance();
            Game curGame = gamePool.findById(room_id.toString());

            if(curGame == null) return "You are not in any game";

            curGame.update(player.toString(), choose);
            if(curGame.isfinish())
                if(curGame.getWinner().equals(player.toString()))
                    return "You Win";
                else return "You Lose";
            return "Waiting";
        }
    }
