package org.example;
public class authorTableData { //FOR AUTHOR TABLE

    //Variables Same as Table Column
   public String aid,authorName,phNo,city;

    public authorTableData(String aid, String name, String phno, String city){
        this.aid = aid;
        this.authorName = name;
        this.phNo = phno;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Author{" +
                "aid='" + aid + '\'' +
                ", authorName='" + authorName + '\'' +
                ", phNo='" + phNo + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
