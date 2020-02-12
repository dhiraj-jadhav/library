package org.example;

public class studentTableData {  //FOR STUDENT TABLE
   public String sid;
   public String sName;
   public String classIn;

        @Override
        public String toString() {
            return "Student{" +
                    "sid='" + sid + '\'' +
                    ", sName='" + sName + '\'' +
                    ", classIn='" + classIn + '\'' +
                    '}';
        }

        public studentTableData(String sid, String sName, String c){
        this.sid = sid;
        this.sName = sName;
        this.classIn = c;
    }
    }
