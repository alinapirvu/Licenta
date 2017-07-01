package com.acme.internal;

import java.sql.*;
import java.text.ParseException;
import java.util.*;


public class Cal {
    public static List<Day> listOfDays;
    public int id;

    public final static int nrOfDaysInMonth[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    public Cal(){
        id = 0;
        Connection con;
        try {
            String url = "jdbc:oracle:thin:@//localhost:1521/XE";
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(url, "student", "STUDENT");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT MAX(ID_EVENT) FROM EVENTS");
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        id = 1;
        listOfDays = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);

        GregorianCalendar calendar = new GregorianCalendar(year, month, 1);

        // blankDays = cate spatii sa las in saptamana inainte de prima zi a lunii curente
        int blankDays = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        for (int i = 0; i < blankDays; i++) {
            Day currentDay = new Day(" ");
            listOfDays.add(currentDay);
        }

        int daysInMonth = nrOfDaysInMonth[month];
        if (calendar.isLeapYear(calendar.get(Calendar.YEAR)) && month == 1) {
            ++daysInMonth;
        }

        for (int i = 1; i <= daysInMonth; i++) {
            // adaug data (numarul zilei)
            Day currentDay = new Day(String.valueOf(i));
            listOfDays.add(currentDay);
        }
    }

    public void addEvent(Event event){
        Connection con = null;
        try{
            String url = "jdbc:oracle:thin:@//localhost:1521/XE";
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(url, "student", "STUDENT");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM EVENTS WHERE EVENT_NAME = ? AND EVENT_DATE = to_date(?, 'dd-mm-yyyy') AND EVENT_TIME = ? AND EVENT_PLACE = ? AND EVENT_INFO = ? AND EVENT_PEOPLE = ?");
            stmt.setString(1, event.getName());
            stmt.setString(2, event.getDate());
            stmt.setString(3, event.getTime());
            stmt.setString(4, event.getPlace());
            stmt.setString(5, event.getInfo());
            stmt.setString(6, event.getInvitedPeople());
            ResultSet rs = stmt.executeQuery();
            int count = 0;
            while (rs.next()){
                count++;
            }
            if (count == 0){
                String sql1 = "INSERT INTO Events VALUES (?, ?, to_date(?, 'dd-mm-yyyy'), ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql1);
                ps.setString(1, String.valueOf(id));
                id++;
                ps.setString(2, event.getName());
                ps.setString(3, event.getDate());
                ps.setString(4, event.getTime());
                ps.setString(5, event.getPlace());
                ps.setString(6, event.getInfo());
                ps.setString(7, event.getInvitedPeople());
                ps.executeUpdate();
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void editEvent(String name, String date, String time, Event newEvent){
        deleteEvent(name, date, time);
        addEvent(newEvent);
    }

    public String[] displayMonth() throws ParseException {
        String[] s = new String[listOfDays.size()];
        int index = 0;

        for (Day listOfDay : listOfDays) {
            // adaug data (numarul zilei)
            if (Objects.equals(listOfDay.getNumber(), " ")) {
                s[index] = " ";
            } else {
                s[index] = listOfDay.getNumber();

                // verific daca exista evenimente programate pe data respectiva
                Connection con = null;
                try {
                    String url = "jdbc:oracle:thin:@//localhost:1521/XE";
                    Class.forName("oracle.jdbc.OracleDriver");
                    con = DriverManager.getConnection(url, "student", "STUDENT");
                    String str;
                    if (Integer.parseInt(listOfDay.getNumber()) > 9) {
                        str = listOfDay.getNumber();
                    } else {
                        str = "0" + listOfDay.getNumber();
                    }
                    int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
                    String date = str + "-" + String.valueOf(month) + "-" + String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
                    PreparedStatement stmt = con.prepareStatement("SELECT * FROM EVENTS WHERE EVENT_DATE = to_date(?, 'dd-mm-yyyy')");
                    stmt.setString(1, date);
                    ResultSet rs = stmt.executeQuery();
                    while (rs.next()) {
                        s[index] += "***";
                        s[index] += rs.getString("event_name");
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (con != null) {
                        try {
                            con.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            index++;
        }
        return s;
    }

    public int getNumberOfEventsForToday(String eventDate){
        int count = 0;
        Connection con = null;
        try{
            String url = "jdbc:oracle:thin:@//localhost:1521/XE";
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(url, "student", "STUDENT");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM EVENTS WHERE EVENT_DATE = to_date(?, 'dd-mm-yyyy')");
            stmt.setString(1, eventDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                count++;
            }
            return count;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return count;
    }

    public List<String> getEventsForToday(String eventDate) {
        List<String> s = new ArrayList<>();
        int i = 0;
        Connection con = null;
        try{
            String url = "jdbc:oracle:thin:@//localhost:1521/XE";
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(url, "student", "STUDENT");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM EVENTS WHERE EVENT_DATE = to_date(?, 'dd-mm-yyyy')");
            stmt.setString(1, eventDate);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                s.add(i++, rs.getString("event_name"));
                String d = rs.getString("event_date");
                String df = d.substring(8, 10) + "-" + d.substring(5, 7) + "-" + d.substring(0, 4);
                s.add(i++, df);
                s.add(i++, rs.getString("event_time"));
                s.add(i++, rs.getString("event_place"));
                s.add(i++, rs.getString("event_info"));
                s.add(i++, rs.getString("event_people"));
            }
            if(s.size() > 0){
                return s;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        List<String> s1 = new ArrayList<>();
        s1.add(0, "no events for today");
        return s1;
    }

    public void deleteEvent(String name, String date, String time) {
        Connection con = null;
        try{
            String url = "jdbc:oracle:thin:@//localhost:1521/XE";
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(url, "student", "STUDENT");
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM EVENTS WHERE EVENT_NAME = ? AND EVENT_DATE = to_date(?, 'dd-mm-yyyy') AND EVENT_TIME = ?");
            stmt.setString(1, name);
            stmt.setString(2, date);
            stmt.setString(3, time);
            ResultSet rs = stmt.executeQuery();
            int count = 0;
            while (rs.next()){
                count++;
            }
            if (count != 0){
                stmt = con.prepareStatement("DELETE FROM EVENTS WHERE EVENT_NAME = ? AND EVENT_DATE = to_date(?, 'dd-mm-yyyy')AND EVENT_TIME = ?");
                stmt.setString(1, name);
                stmt.setString(2, date);
                stmt.setString(3, time);
                stmt.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
