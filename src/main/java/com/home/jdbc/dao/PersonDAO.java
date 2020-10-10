package com.home.jdbc.dao;

import com.home.jdbc.entity.Person;
import com.home.jdbc.helpers.JDBCHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PersonDAO {
    private static final String INSERT_QUERY = "INSERT INTO PERSON(ID,FIRST_NAME,LAST_NAME,EMAIL,JOINED_DATE) VALUES(?,?,?,?,?)";
    private static final String NAME_QUERY = "UPDATE PERSON SET FIRST_NAME=%s WHERE ID=0";

    public static void insert(Person p) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JDBCHelper.getConnection();
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return;
            }
            con.setAutoCommit(false);
            ps = con.prepareStatement(PersonDAO.INSERT_QUERY);
            ps.setLong(1, p.getId());
            ps.setString(2, p.getFirstName());
            ps.setString(3, p.getLastName());
            ps.setString(4, p.getEmail());

            ps.execute();
            System.out.println("insertPerson => " + ps.toString());
            con.commit();

        } catch (SQLException e) {
            if (con != null) {
                con.rollback();
            }
            throw e;
        } finally {
            JDBCHelper.closePreparedStatement(ps);
            JDBCHelper.closeConnection(con);
        }

    }

    public static void updateName(String name) {
        Connection con = null;
        try {
            con = JDBCHelper.getConnection();
            if (con == null) {
                System.out.println("Error getting the connection. Please check if the DB server is running");
                return;
            }
            con.setAutoCommit(false);
            con.createStatement().execute(String.format(NAME_QUERY, name));
            con.commit();
        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                    e.printStackTrace();
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        } finally {
            try {
                JDBCHelper.closeConnection(con);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
