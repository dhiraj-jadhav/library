package org.example;

import org.jdbc.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class librarySystem extends jdbcCommonConnection {
    static Scanner sc = new Scanner(System.in);
    static Connection con = getJdbcConnect();
    static  Statement stmt = getStmt();
    public static void main(String[] args) throws SQLException {


        fixDisplayContent();

        //getting User Option


    }//Main


    //Method to Show Options on Console Start Page
    public static void  fixDisplayContent() throws SQLException {
        System.out.println("Enter Your Option :\n1. Check Available Books\n2. Grant Book\n3. Book Return\n4. Enter Student Details\n5.Enter Book Details");
        int choice = sc.nextInt();

        switch (choice){
            case 1: //1. Check Available Books
                System.out.println("Enter Your Choice : \n1. Books By Category\n2. Books By Author");
                int bSort = sc.nextInt();
                switch (bSort){
                    case  1:
                        getBookCategory();
                        break;
                    case 2:
                        getBookAuthor();
                        break;
                    default:
                        System.out.println("Enter Valid Option.....");
                        break;
                }

                fixDisplayContent();
                break;
            case 2:  //2. Grant Book
                grantBook();
                fixDisplayContent();
                break;
            case 3:  //3. Book Return
                fixDisplayContent();
                break;
            case 4:  //4. Enter Student Details
                insertStudentData();
                fixDisplayContent();
                break;
            case 5:  //5.Enter Book Details
                insertBookData();
                fixDisplayContent();
                break;
            default:  //If user enters Wrong Option
                System.out.println("Enter Valid Option Number..............");
                fixDisplayContent();
                break;
        }

    }//fixDisplayContent

    //Method to get books By using Author name
    public static void getBookAuthor() throws SQLException {


        //To get Author Names At Time of Execution of Program
        String selAuthor = "SELECT author_name from author ;";
        ResultSet authorSet = stmt.executeQuery(selAuthor);

        //To get Choice of User For Author
        int i = 1;
        System.out.println("Please Enter Author : ");
        ArrayList<String> authorNames = new ArrayList<String>();
        while(authorSet.next()){
            System.out.println(i++ + ". "+authorSet.getString(1));
            authorNames.add(authorSet.getString(1));
        }

        //To Get Books Of User Entered Author in Database
        int choiceAuthor = sc.nextInt();
        PreparedStatement fBook = con.prepareStatement("SELECT book_name FROM book b, author a WHERE b.author_id = a.author_id AND author_name = ?");
        fBook.setString(1,authorNames.get(choiceAuthor - 1));
        ResultSet bookData = fBook.executeQuery();

        while (bookData.next()){
            System.out.println(bookData.getString(1));
        }
    }//getBookAuthor

    //Method to get Books By Using Book Category
    public static void getBookCategory() throws SQLException {

        //Get Names of Categories Present into Database
        String selCategory = "SELECT DISTINCT category FROM book;";
        ResultSet bookSet = stmt.executeQuery(selCategory);

        //Copy All Values Into ArrayList of BookNames
        ArrayList<String> bookNames = new ArrayList<String>();
        while(bookSet.next()){
            bookNames.add(bookSet.getString(1));
        }

        //TO Get Category From User
        bookSet = stmt.executeQuery(selCategory);
        System.out.println("Enter Book Category : ");
        int i = 1;
        while (bookSet.next()){
            System.out.println(i++ + ". "+bookSet.getString(1));
        }
        int catId = sc.nextInt();

        //To Get Books Of User Entered Category
        PreparedStatement pst = con.prepareStatement("SELECT book_name FROM book  WHERE category = ?");
        pst.setString(1,bookNames.get(catId - 1));
        bookSet = pst.executeQuery();

        while ((bookSet.next())){
            System.out.println(bookSet.getString(1));
        }
    }//getBookCategory

    //Method to Insert New Book Details
    public static void insertBookData() throws SQLException {
        //Query TO Insert Data Into Author Table
        PreparedStatement psAuthor = con.prepareStatement("INSERT INTO author VALUES (?,?,?,?);");
        System.out.println("Enter Author Details AuthorID(Starts with A(5 Digits)),Author Name,PhoneNo and City");
        authorTableData authorData = new authorTableData(sc.next(),sc.next(),sc.nextLine(),sc.next());
        psAuthor.setString(1,authorData.aid);
        psAuthor.setString(2,authorData.authorName);
        psAuthor.setString(3,authorData.phNo);
        psAuthor.setString(4,authorData.city);
        psAuthor.execute();

    }//insertBookData

    //Method TO Insert New Student Data
    public static void insertStudentData() throws SQLException {

        //Query to Insert Data into Student Table
        PreparedStatement psStudent = con.prepareStatement("INSERT INTO student VALUES(?,?,?)");
        System.out.println("Enter Student Details StudentID(Starts with Capital Letter(5 Digit),Student Name,Class");
        studentTableData studData = new studentTableData(sc.next(),sc.nextLine(),sc.next());
        psStudent.setString(1,studData.sid);
        psStudent.setString(2,studData.sName);
        psStudent.setString(3,studData.classIn);
        psStudent.execute();
    }//insertStudentData

    //Method to Grant Book to Student
    public static void grantBook() throws SQLException {
        PreparedStatement psGrantBk = con.prepareStatement("INSERT INTO book_details(stud_id,book_id) VALUES(?,?);");
        System.out.println("Enter Student Id, Book Id from List");

        System.out.println("\nStudent ID :");
        ResultSet studId = stmt.executeQuery("SELECT stud_id FROM student");
        while (studId.next()){
            System.out.print(studId.getString(1)+", ");
        }
        System.out.println("Book ID :");
        ResultSet bookId = stmt.executeQuery("SELECT book_id FROM book");
        while (bookId.next()){
            System.out.print(bookId.getString(1)+", ");
        }

        bookDetailsTData grantBook = new bookDetailsTData(sc.next(),sc.next());
        psGrantBk.setString(1,grantBook.studId);
        psGrantBk.setString(2,grantBook.bookId);
        psGrantBk.execute();
    }


}//CLass


