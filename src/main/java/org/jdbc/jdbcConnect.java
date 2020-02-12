package org.jdbc;

import org.example.*;
import org.jdbc.dbSetting;

import java.sql.*;
import java.util.*;

//class to establish database connection
public class jdbcConnect implements dbSetting {


    public static void main(String[] args){
        //Object to get Run time input form End User
         Scanner sc = new Scanner(System.in);

         //Objects to store database query results
         ResultSet bookRS,studRS,bookDetailRS,authorRS, oneRS;


                try {
                    //To Load Driver
                    Class.forName(JDBC_DRIVER);

                    //Connect with Database by using username and password
                    Connection con = DriverManager.getConnection(DB_URL, USER, PASS);


                   // System.out.println("Connecting to Database............");

                    //TO initialize Statement object
                    Statement  stmt = con.createStatement();

                    //Creating Select query for each table
                   String selBook = "SELECT * FROM book";
                   String selAuthor = "SELECT * FROM author";
                   String selBDetails = "SELECT * FROM book_details";
                   String selStudent = "SELECT * FROM student";

                   //ArrayLists to Copy all data from ResultSet Of Respective Table
                   ArrayList<bookTableData> bAl = new ArrayList<bookTableData>();
                   ArrayList<studentTableData> sAl = new ArrayList<studentTableData>();
                   ArrayList<authorTableData> aAl = new ArrayList<authorTableData>();
                   ArrayList<bookDetailsTData> bdAl = new ArrayList<bookDetailsTData>();


                   //Copy All Book Table Data into  bookTableData Object then add into ArrayList bAl
                   bookRS = stmt.executeQuery(selBook);
                   while(bookRS.next()) { //For Books Details (book table)
                       bookTableData b = new bookTableData(bookRS.getString("book_id"),bookRS.getString("book_name"),bookRS.getString("author_id"),bookRS.getString("category"),bookRS.getInt("isAvail"));
                       bAl.add(b);
                   }


                    //Copy All Book Table Data into  authorTableData Object then add into ArrayList aAl
                    authorRS = stmt.executeQuery(selAuthor);
                    while(authorRS.next()) { //For Author Details (Author table)
                        authorTableData a = new authorTableData(authorRS.getString("author_id"),authorRS.getString("author_name"),authorRS.getString("ph_no"),authorRS.getString("city"));
                        aAl.add(a);
                    }

                    //Copy All Book Table Data into  studentTableData Object then add into ArrayList sAl
                    studRS = stmt.executeQuery(selStudent);
                    while(studRS.next()){//For Student Details
                        studentTableData s = new studentTableData(studRS.getString("stud_id"),studRS.getString("stud_name"),studRS.getString("class"));
                        sAl.add(s);
                    }


                    //Copy All Book Table Data into  bookDetailsTData Object then add into ArrayList bdAl
                    bookDetailRS = stmt.executeQuery(selBDetails);
                    while(bookDetailRS.next()){//FOR BOOKDATAILS Table
                        bookDetailsTData bd = new bookDetailsTData(bookDetailRS.getString("stud_id"),bookDetailRS.getString("book_id")); //,oneRS.getDate("b_date"),oneRS.getDate("r_date"));
                        bdAl.add(bd);
                    }


                   // System.out.println("Enter Your Choice :\n1.Check Book By Category\n2.Enter New Book");
                    //Get Choice from User
                    System.out.println("Enter Your Choice :\n1.Books By Category\n2.Books By Author");
                    int choice = sc.nextInt();

                    switch(choice){

                        case 1: //Case for Books By Category
                            System.out.println("Enter your Category\n1.Fiction\n2.History\n3.Education");
                            int chC = sc.nextInt();
                            findBooksOfCategory(bAl,chC);
                            break;
                        case 2 : //Case for Books By Author
                            System.out.println("Enter Your CHoice : \n1.J k rowling\n2.G R R Martin\n3.Jeffry Archer\n4.Vishwas Patil\n4.Yashwant Kanetkar\n5.Balguruswami");
                            int chA = sc.nextInt();
                            findBookByAuthor(bAl,aAl,chA);
                            break;
                        default:
                            System.out.println("Enter Valid Option Number......");
                            break;
                    }

                   // System.out.println("DataBase Created...............");
                }catch(ClassNotFoundException ex){
                    ex.printStackTrace();
                }catch (SQLException ex){
                    ex.printStackTrace();
                }
        }//main

    //Method to find books from ArrayList<Book>
    public static void findBooksOfCategory(ArrayList<bookTableData> bl, int userChoice){
        String ch;

        //switch case to get user desire category
        switch(userChoice){
            case 1 : // Case for Fiction
                findBooks( bl,"fiction"); //Method gives parameter category books
                break;
            case 2:
                findBooks( bl,"History");
                break;
            case 3:
                findBooks(bl,"education");
                break;
            default:
                System.out.println("Enter Valid Option Number......");
                }
        }//findBooksByCategory
    public static void findBooks(ArrayList<bookTableData> bl, String userChoice){
        Iterator btr = bl.iterator();
        while(btr.hasNext()){
            bookTableData b = (bookTableData) btr.next();
            if(b.category.equalsIgnoreCase(userChoice)){
                System.out.println(b.bname);
            }
        }
    }//findBooks

    //Method TO check Books By Author
    public static void findBookByAuthor(ArrayList<bookTableData> btl, ArrayList<authorTableData> atl, int choice){

        //1.J k rowling\n2.G R R Martin\n3.Jeffry Archer\n4.Vishwas Patil\n4.Yashwant Kanetkar\n5.Balguruswami");
        switch (choice){
            case 1:
                bookByAuthor(btl,atl,"J k rowling");
                break;
            case 2:
                bookByAuthor(btl,atl,"G R R Martin");
                break;
            case 3:
                bookByAuthor(btl,atl,"Jeffry Archer");
                break;
            case 4:
                bookByAuthor(btl,atl,"Yashwant Kanetkar");
                break;
            case 5:
                bookByAuthor(btl,atl,"Balguruswami");
                break;
            default:
                System.out.println("Enter Valid Author Number");
                break;
        }
    }//findBookByAuthor

    public static void bookByAuthor(ArrayList<bookTableData> btl, ArrayList<authorTableData> atl,String uChoice){
        //System.out.println(uChoice);
      /*  Iterator<bookTableData> bItr = btl.iterator();//for Book ArrayList
        Iterator<authorTableData> aItr = atl.iterator();//For AuthorList*/

        for(int i = 0; i < atl.size(); i++) {
            authorTableData author = (authorTableData) atl.get(i);
           // System.out.println(author.authorName);
            for(int j = 0; j < btl.size(); j++){

                //Iterator for Book ArrayList
                bookTableData book = (bookTableData) btl.get(j);
                // authorTableData a = (authorTableData) aItr.next();
                //System.out.println(book.bname);
                if (author.aid.equalsIgnoreCase(book.authorId) && author.authorName.equalsIgnoreCase(uChoice) ){
                    System.out.println(book.bname);
                }
                else {
                    continue;
                }
            }// for Book
        }//for Author

    }//bookByAuthor

    }//class


