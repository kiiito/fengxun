package com.hucong.generic;

import org.junit.jupiter.api.Test;

import java.util.*;

public class GenericHomework {
    public static void main(String[] args) {

    }
    @Test
    public void testList() {
        DAO<User> userDAO = new DAO<>();
        userDAO.sava("001",new User(001,18,"eula"));
        userDAO.sava("002",new User(002,18,"ganYu"));
        userDAO.sava("003",new User(003,28,"keQin"));
        List<User> list = userDAO.list();
        System.out.println("list =" + list);
        userDAO.update("003",new User(003,19,"keQin"));
        list = userDAO.list();
        System.out.println(list);
    }
}
class DAO<T>{
    public Map<String,T> map = new HashMap<>();
    public void sava(String id,T entity) {
        map.put(id,entity);
    }
    public T get(String id) {
        return map.get(id);
    }
    public void update(String id,T entity) {
        map.put(id,entity);
    }
    public List<T>list() {
        List<T> list = new ArrayList<>();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            list.add(get(key));//µÈ¼ÛÓÚlist.add(map.get(key));
        }
        return list;
    }
    public void delete(String id) {
        map.remove(id);
    }
}
class User {
    private int id,age;
    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
