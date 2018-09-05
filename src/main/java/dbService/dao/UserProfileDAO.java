package dbService.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbService.entity.UserProfile;

public class UserProfileDAO {
    private Connection connection;

    public UserProfileDAO(Connection connection) {
        this.connection = connection;
    }

    public UserProfile get(long id) {
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("select * from users where id=" + id);
            if (rs.next()) {
                return new UserProfile(rs.getString(2), rs.getString(3));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UserProfile> getAll() {
        List<UserProfile> UserProfileList = new ArrayList<>();
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("select * from users");
            while (rs.next())
                UserProfileList.add(new UserProfile(rs.getString(2), rs.getString(3)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return UserProfileList;
    }

    public UserProfile getUserByLogin(String name) {
        try (Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery("select * from users where user_name='" + name + "'");
            if (rs.next()) {
                return new UserProfile(rs.getString(2), rs.getString(3));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertUser(String name, String pass) {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("insert into users (user_name,user_pass) values ('" + name + "','" + pass + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate(
                    "create table if not exists users (id bigint auto_increment, user_name varchar(256), user_pass varchar(256), primary key (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
