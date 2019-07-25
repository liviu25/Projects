import domain.Player;
import domain.Team;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Exercises {
    
    static Team getTeamForPlayer(int playerID)
    {
        Team team=new Team();
        try(
                Connection connection=DBUtils.getConnection();
                PreparedStatement preStmt=connection.prepareStatement("select * from teams where team_id = ? ");

        ){
            preStmt.setInt(1,playerID);
            try(ResultSet resultSet=preStmt.executeQuery()) {
                if (resultSet.next()) {
                    team = new Team(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3)
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return team;
    }
    
    static Player getPlayer(ResultSet resultSet) throws SQLException {
        Player player=new Player(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getDate(4),
                resultSet.getString(5),
                resultSet.getBigDecimal(6),
                resultSet.getDate(7)
        );

        int playerID=resultSet.getInt(8);
        Team team=getTeamForPlayer(playerID);
        player.setTeam(team);
        
        return player;
    }
    
    static List<Player> getAllPlayers(){
        List<Player> players=new ArrayList<>();
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select * from players ");
            ResultSet resultSet=preStmt.executeQuery();
            )
        {

            while (resultSet.next())
            {

                Player player=getPlayer(resultSet);
                
                
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    static void getSalaryofAllPlayers(){
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select salary from players ");
            ResultSet resultSet=preStmt.executeQuery();
        )
        {
            while (resultSet.next())
            {
                System.out.println(resultSet.getBigDecimal(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void getNameAndSalaryIncreased(){
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select player_name,salary from players ");
            ResultSet resultSet=preStmt.executeQuery();
        )
        {
            while (resultSet.next())
            {
                String name = resultSet.getString(1);
                BigDecimal salary = resultSet.getBigDecimal(2);
                salary=salary.add(salary.multiply(BigDecimal.valueOf(15)).divide(BigDecimal.valueOf(100))) ;
                System.out.println("name="+name+", salary="+salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static List<Player> getAllPlayersWithExpiresDate(){

        List<Player> players=new ArrayList<>();
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select player_id,player_name,position,birth_date,nationality," +
                    " salary,expires_date,team_id from players ");
            ResultSet resultSet=preStmt.executeQuery();
        )
        {

            while (resultSet.next())
            {
                Player player=getPlayer(resultSet);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    static List<Player> getAllPlayersWhoDoNotBelongToSteaua(){

        List<Player> players=new ArrayList<>();
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select * from players " +
                    " inner join teams on teams.team_id=players.team_id " +
                    " where teams.team_name <> 'Steaua' ");
            ResultSet resultSet=preStmt.executeQuery();
        )
        {

            while (resultSet.next())
            {
                Player player=getPlayer(resultSet);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    static List<Player> getPlayersWhoBornBefore1991AndPlayAtDinamo(){

        List<Player> players=new ArrayList<>();
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select * from players " +
                    " inner join teams on teams.team_id=players.team_id " +
                    " where teams.team_name ='Dinamo' and YEAR(players.birth_date) <= 1991  ");
            ResultSet resultSet=preStmt.executeQuery();
        )
        {

            while (resultSet.next())
            {
                Player player=getPlayer(resultSet);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    static int getAverageSalaryForGoalkeepers(){

        int salary=0;
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select avg (salary) from players");
            ResultSet resultSet=preStmt.executeQuery();
        )
        {

            if (resultSet.next())
            {
                salary = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salary;
    }

    static List<Player> getDetailsOfPlayer(){

        List<Player> players=new ArrayList<>();
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select * from players where player_name='Messi' ");
            ResultSet resultSet=preStmt.executeQuery();
        )
        {

            while (resultSet.next())
            {
                Player player=getPlayer(resultSet);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    static List<Player> getPlayersWithContactExpiresInJune(){

        List<Player> players=new ArrayList<>();
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select * from players where MONTH(expires_date)='June' ");
            ResultSet resultSet=preStmt.executeQuery();
        )
        {

            while (resultSet.next())
            {
                Player player=getPlayer(resultSet);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    static List<Player> getUnemployedPlayers(){

        List<Player> players=new ArrayList<>();
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select * from players " +
                    " left join teams on teams.team_id=players.team_id ");
            ResultSet resultSet=preStmt.executeQuery();
        )
        {

            while (resultSet.next())
            {
                Player player=getPlayer(resultSet);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    static List<Player> getGoalkeepersWithExpiresDataIn1YearsAndPlayAtRapid(){

        List<Player> players=new ArrayList<>();
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select * from players " +
                    " left join teams on teams.team_id=players.team_id " +
                    " where team.team_name='Madrid' and player.position='Goalkeeper' ");
            ResultSet resultSet=preStmt.executeQuery();
        )
        {

            while (resultSet.next())
            {
                Player player=getPlayer(resultSet);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }


    static List<Player> getPlayersThatEarn60000AndNotPLayForMadrid(){

        List<Player> players=new ArrayList<>();
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select * from players " +
                    " left join teams on teams.team_id=players.team_id " +
                    " where team.team_name<>'Real Madrid' and player.salary>60000 ");
            ResultSet resultSet=preStmt.executeQuery();
        )
        {

            while (resultSet.next())
            {
                Player player=getPlayer(resultSet);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    static List<Player> getGradeAndPlayerName(){

        List<Player> players=new ArrayList<>();
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select * from players " +
                    " left join teams on teams.team_id=players.team_id " +
                    " where team.team_name<>'Real Madrid' and player.salary>6000 ");
            ResultSet resultSet=preStmt.executeQuery();
        )
        {

            while (resultSet.next())
            {
                Player player=getPlayer(resultSet);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    static List<Player> getTeamNameAscending(){

        List<Player> players=new ArrayList<>();
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select * from players " +
                    " left join teams on teams.team_id=players.team_id " +
                    " order by teams.team_name ASC ");
            ResultSet resultSet=preStmt.executeQuery();
        )
        {

            while (resultSet.next())
            {
                Player player=getPlayer(resultSet);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    static List<Player> getAverageSalaryForTeamAndPosition(){

        List<Player> players=new ArrayList<>();
        try(Connection connection=DBUtils.getConnection();
            PreparedStatement preStmt=connection.prepareStatement("select avg(salary),team.team_name,players.position from players " +
                    " left join teams on teams.team_id=players.team_id ");
            ResultSet resultSet=preStmt.executeQuery();
        )
        {

            while (resultSet.next())
            {
                Player player=getPlayer(resultSet);
                players.add(player);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

}
