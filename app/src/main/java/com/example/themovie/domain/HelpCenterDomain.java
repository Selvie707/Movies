package com.example.themovie.domain;

import com.example.themovie.adapter.HelpCenterAdapter;
import com.example.themovie.data.DataHelpDetail;
import com.example.themovie.detail.HelpCenter;

import java.util.ArrayList;

public class HelpCenterDomain {

    private String title, pic;
    private String pos;

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
