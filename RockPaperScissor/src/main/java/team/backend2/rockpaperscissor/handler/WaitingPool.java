package team.backend2.rockpaperscissor.handler;
import team.backend2.rockpaperscissor.model.Room;

import java.util.HashMap;
import java.util.Map;

public class WaitingPool {
    private static WaitingPool instance;
    private static Map<String, Room> pool;
    private WaitingPool(){};

    public static WaitingPool getInstance(){
        if(instance == null){
            instance = new WaitingPool();
            pool = new HashMap<>();
        }
        return instance;
    }
    public String findRoom(String uid){
        if(pool.get(uid) != null){
            return "-"+uid;
        }
        for (String roomId : pool.keySet()) {
            Room room = pool.get(roomId);
            if(room != null && room.getUid_2().equals(uid)) {
                return "-" + roomId;
            }

            // Nếu phòng chưa chơi và room đó của người khác
            if (pool.get(roomId) == null && !roomId.equals(uid)) {
                updateRoom(roomId,uid);
                return "-"+roomId;
            }
        }
        //Náº¿u khÃ´ng cÃ²n room nÃ o thÃ¬ sáº½ tá»± táº¡o room
        if(!pool.containsKey(uid))
            newRoom(uid);
        return uid;
    }
    
    public Room findById(String uid1){
        return pool.get(uid1);
    }
    private String newRoom(String uid1){
        pool.put(uid1,null);
        return uid1;
    }
    public Room updateRoom(String uid1,String uid2){
        Room newRoom = new Room(uid2);
        pool.put(uid1,newRoom);
        return newRoom;
    }
}
