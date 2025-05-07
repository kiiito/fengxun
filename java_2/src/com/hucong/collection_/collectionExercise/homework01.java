package com.hucong.collection_.collectionExercise;

import java.util.ArrayList;
@SuppressWarnings({"all"})
public class homework01 {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add(new news("新闻一:所容纳之物；内容；含量；主题，" +
                "主要内容；目录，目次；所含之物；（网站或只读光盘上的）内容，目录"));
        list.add(new news("新闻二:检测到您的页面展示可能受到浏览器插件影响，" +
                "建议您将当前页面加入插件白名单，以保障您的浏览体验"));
        int size = list.size();
        for (int i = size - 1;i>= 0;i--){
            news News = (news) list.get(i);
            System.out.println(processTitle(News.getTitle()));
        }
    }
    public static String processTitle(String title){
        if (title == null){
            return "";
        }
        if (title.length() >= 15){
            return title.substring(0,15) + "...";
        }else{
            return title;
        }
    }

}
class news{
    private String title;
    private String content;

    public news(String title) {
        this.title = title;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "news{" +
                "title='" + title + '\''  + '\'' +
                '}';
    }

}