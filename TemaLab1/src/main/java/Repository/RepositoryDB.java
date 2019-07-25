package Repository;

import Model.Pizza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepositoryDB {
    public Iterable<Pizza> findAll()
    {
        List<Pizza> pizzaList=new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\Pizzerie.db");
            try(Statement stmt=connection.createStatement()){
                try(ResultSet rs=stmt.executeQuery("select * from Pizza")){
                    while(rs.next())
                    {
                        Pizza pizza=new Pizza(Integer.parseInt( rs.getString(1)),rs.getString(2),Integer.parseInt( rs.getString(3)));
                        pizzaList.add(pizza);
                    }
                }
            }catch(SQLException ex){
                System.err.println(ex.getSQLState());
                System.err.println(ex.getErrorCode());
                System.err.println(ex.getMessage());

            }
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return pizzaList;
    }

    public void insert(Pizza pizza)
    {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\Pizzerie.db");
            try(PreparedStatement stmt=connection.prepareStatement("INSERT INTO Pizza(Denumire,Pret) values (?,?) ")){
                stmt.setString(1,pizza.getDenumire());
                stmt.setInt(2,pizza.getPret());
                stmt.executeUpdate();
            }catch(SQLException ex){
                System.err.println(ex.getSQLState());
                System.err.println(ex.getErrorCode());
                System.err.println(ex.getMessage());

            }
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
    public void delete(Integer id)
    {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite\\Pizzerie.db");
            try(PreparedStatement stmt=connection.prepareStatement("delete from Pizza where Pizza.ID=?")){

                stmt.setInt(1,id);
                stmt.executeUpdate();
            }catch(SQLException ex){
                System.err.println(ex.getSQLState());
                System.err.println(ex.getErrorCode());
                System.err.println(ex.getMessage());

            }
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
}
