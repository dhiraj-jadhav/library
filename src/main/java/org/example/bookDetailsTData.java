package org.example;

public class bookDetailsTData { //FOR BOOKDETAILS Table
    String studId,bookId,bDate,reDate;
    public bookDetailsTData(String sid, String bid){//, String bDate, String reDate){
        this.studId = sid;
        this.bookId = bid;
        this.bDate = bDate;
        this.reDate = reDate;
    }

        @Override
        public String toString() {
            return "BookDetails{" +
                    "studId='" + studId + '\'' +
                    ", bookId='" + bookId + '\'' +
                    ", bDate='" + bDate + '\'' +
                    ", reDate='" + reDate + '\'' +
                    '}';
        }
    }
