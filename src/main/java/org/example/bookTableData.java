package org.example;

public class bookTableData {  //FOR BOOK TABLE
    public String bid;
    public String bname;
    public String authorId;
    public String category;
int isAvl;
public bookTableData(String bid, String nm, String authorId, String category, int av){
    this.bid = bid;
    this.bname = nm;
    this.authorId = authorId;
    this.category = category;
    this.isAvl = av;
}

    @Override
    public String toString() {
        return "Book{" +
                "bid='" + bid + '\'' +
                ", bname='" + bname + '\'' +
                ", authorId='" + authorId + '\'' +
                ", category='" + category + '\'' +
                ", isAvl=" + isAvl +
                '}';
    }
}
