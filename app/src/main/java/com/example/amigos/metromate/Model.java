package com.example.amigos.metromate;

import java.io.Serializable;
import java.util.ArrayList;

public class Model implements Serializable {
    String status;
    ArrayList<String> line1;
    ArrayList<String> line2;
    ArrayList<String> interchange;
    ArrayList<String> lineEnds;
    ArrayList<String> path;
    private String time;

    public String getStatus() {

        return status;
    }

    public void setStatus(String status) {

        this.status = status;
    }

    public ArrayList<String> getLine1() {

        return line1;
    }

    public void setLine1(ArrayList<String> line1) {

        this.line1 = line1;
    }

    public ArrayList<String> getLine2() {

        return line2;
    }

    public void setLine2(ArrayList<String> line2) {

        this.line2 = line2;
    }

    public ArrayList<String> getInterchange() {

        return interchange;
    }

    public void setInterchange(ArrayList<String> interchange) {

        this.interchange = interchange;
    }

    public ArrayList<String> getLineEnds() {

        return lineEnds;
    }

    public void setLineEnds(ArrayList<String> lineEnds) {

        this.lineEnds = lineEnds;
    }

    public ArrayList<String> getPath() {

        return path;
    }

    public void setPath(ArrayList<String> path) {

        this.path = path;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {

        this.time = time;
    }
}
