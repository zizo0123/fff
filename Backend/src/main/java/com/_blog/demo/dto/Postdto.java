
package com._blog.demo.dto;
import java.util.List;

public class Postdto {
    private Integer userid;
    private String title;
    private String content;
    private List<Userdto> userslike;

    public Integer getUserId(){
        return userid;
    }
}