package com.hucong.jdbc.dao_.test;

import com.hucong.jdbc.dao_.dao.ActorDAO;
import com.hucong.jdbc.dao_.domain.Actor;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestDAO {
    @Test
    //测试ActorDAO 对 ACTOR表的curd操作
    public void testActorDAO(){
        ActorDAO actorDAO = new ActorDAO();
        List<Actor> actors =
                actorDAO.queryMulti("select * from actor where id >= ?", Actor.class, 1);
        for (Actor actor : actors) {
            System.out.println(actor);
        }

        Actor actor =
                actorDAO.querySingle("select * from actor where id = ?", Actor.class, 1);
        System.out.println(actor);
        Object actor1 = actorDAO.queryScalar("select name from actor where id = ? ", 1);
        System.out.println(actor1);

        int update = actorDAO.Update
                ("insert into actor values(?,?,?,?,?)", 4, "周星驰", "男", "1981-12-08", "112");
        System.out.println(update>0 ? "执行成功":"执行并没有影响表");
    }
}
