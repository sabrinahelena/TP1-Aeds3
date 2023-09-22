package Domain;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

public class SchoolShooting implements Serializable {

    private int id; // ID único para cada registro
    private String schoolName;
    private String locality;
    private String date;
    private int year;


    public SchoolShooting(int id, String schoolName, String locality, String date, int year) {
        this.id = id;
        this.schoolName = schoolName;
        this.locality = locality;
        this.date = date;
        this.year = year;
    }

    public SchoolShooting() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                ", locality='" + locality + '\'' +
                ", year='" + year + '\'' +
                ", date='" + date + '\'' +
                '}';
    }


}

