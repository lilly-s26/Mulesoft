import java.sql.*;

public class Mule_soft {
    Connection con = null;

    public Mule_soft() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3308/movies", "", "");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void make_table() {
        try {
            Statement stmt = con.createStatement();
            String query = "CREATE TABLE movie(id INT AUTO_INCREMENT,name VARCHAR(50),actor varchar(20),actress varchar(20),director varchar(20), year INT, PRIMARY KEY(id))";
            stmt.executeUpdate(query);
            System.out.println("Table created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void insert(String name, String actor, String actress, String director, int year) {
        try {
            Statement stmt = con.createStatement();
            String query = String.format(
                    "insert into movies.movie(name,actor,actress,director,year) values('%s','%s','%s','%s','%d')", name,
                    actor, actress, director, year);
            stmt.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void fetch() {
        try {
            Statement stmt = con.createStatement();
            String query;

            query = "select * from  movies.movie";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
                        + " " + rs.getString(5) + " " + rs.getInt(6));
            }

        }

        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String args[]) {
        Mule_soft mule = new Mule_soft();
        mule.make_table();
        mule.insert("abcd", "abcd", "abcd", "abcd", 2020);
        mule.fetch();
    }
}