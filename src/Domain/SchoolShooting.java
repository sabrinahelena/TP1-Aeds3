package Domain;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class SchoolShooting implements Serializable {

    private int id; // ID único para cada registro
    private String schoolName;
    private String locality;
    private Date date;
    private int year;
    private String[] weapons;
    private String weaponComplete;
    private Boolean exists;


    /*
    Construtor para CSV e DB
     */

    public SchoolShooting(int id, String schoolName, String locality, Date date, int year, String weaponComplete, Boolean exists) {
        this.id = id;
        this.schoolName = schoolName;
        this.locality = locality;
        this.date = date;
        this.year = year;
        this.weaponComplete = weaponComplete;
        this.exists = exists;
    }

    /*
    Construtor para CRUD, com o vetor que armazena as armas separado usando split.
     */

    public SchoolShooting(int id, String schoolName, String locality, Date date, int year, String[] weapons, Boolean exists) {
        this.id = id;
        this.schoolName = schoolName;
        this.locality = locality;
        this.date = date;
        this.year = year;
        this.weapons = weapons;
        this.exists = exists;
    }

    public Boolean getExists() {
        return exists;
    }

    public void setExists(Boolean exists) {
        this.exists = exists;
    }

    public String getWeaponComplete() {
        return weaponComplete;
    }

    public void setWeaponComplete(String weaponComplete) {
        this.weaponComplete = weaponComplete;
    }

    public String[] getWeapons() {
        if(getWeaponComplete() == null){
            return weapons;
        }
        return getWeaponComplete().split(",");
    }


    public void setWeapons(String[] weapons) {
        this.weapons = weapons;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public SchoolShooting() {

    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String formattedDate = dateFormat.format(date);

        /*
        StringBuilder weaponsStr = new StringBuilder();
        for (String weapon : weapons) {
            weaponsStr.append(weapon).append(", ");
        }

        if (weaponsStr.length() > 0) {
            weaponsStr.setLength(weaponsStr.length() - 2);  // isso e pra remover a última vírgula e espaço em branco

        }

        return "Record{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                ", locality='" + locality + '\'' +
                ", year='" + year + '\'' +
                ", date='" + formattedDate + '\'' +
                ", weapons='" + weaponsStr.toString() + '\'' +
                '}';
       */
        return "Record{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                ", locality='" + locality + '\'' +
                ", year='" + year + '\'' +
                ", date='" + formattedDate + '\'' +
                ", weapons='" + weaponComplete + '\'' +
                ", exists='" + exists + '\'' +
                '}';
    }




}

