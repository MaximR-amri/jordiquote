package be.syntra.daoimpl;

import be.syntra.dao.QuoteDao;
import be.syntra.model.Quote;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class QuoteDaoImpl implements QuoteDao {
    private static String url;
    private static String usr;
    private static String pass;

    static {
        try(InputStream inputStream = QuoteDaoImpl.class.getResourceAsStream("/app.properties")){
            Properties props = new Properties();
            props.load(inputStream);
            url = props.getProperty("url");
            usr = props.getProperty("usr");
            pass = props.getProperty("pass");
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public Quote getRandomQuote() {
        String sql = "SELECT * FROM jordiquotes ORDER BY RAND() LIMIT 1";

        try(Connection con = DriverManager.getConnection(url, usr, pass);
            PreparedStatement ps = con.prepareStatement(sql)){

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Quote quote = new Quote();
                    quote.setId(rs.getInt("id"));
                    quote.setAuthor(rs.getString("author"));
                    quote.setQuote(rs.getString("quote"));
                    quote.setLikes(rs.getInt("likes"));
                    quote.setDislikes(rs.getInt("dislikes"));

                    return quote;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void likeQuote(Quote quote) {
        String sql = "UPDATE jordiquotes SET likes = likes +1 WHERE ID= ?";

        try(Connection con = DriverManager.getConnection(url, usr, pass);
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, quote.getId());
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    @Override
    public void dislikeQuote(Quote quote) {
        String sql = "UPDATE jordiquotes SET dislikes = dislikes +1 WHERE ID= ?";

        try(Connection con = DriverManager.getConnection(url, usr, pass);
            PreparedStatement ps = con.prepareStatement(sql)){
            ps.setInt(1, quote.getId());
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public void debug() {
        String sql = "SELECT * FROM jordiquotes";

        try(Connection con = DriverManager.getConnection(url, usr, pass);
            PreparedStatement ps = con.prepareStatement(sql)){

            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    System.out.print(rs.getInt("ID"));
                    System.out.print(": ");
                    System.out.print(rs.getString("author"));
                    System.out.print(" : ");
                    System.out.println(rs.getString("quote"));
                    System.out.print(" : ");
                    System.out.println(rs.getInt("likes"));
                    System.out.print(" : ");
                    System.out.println(rs.getInt("dislikes"));
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
    }

