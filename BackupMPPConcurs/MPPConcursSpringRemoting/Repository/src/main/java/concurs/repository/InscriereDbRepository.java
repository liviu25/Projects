package concurs.repository;

import concurs.model.Inscriere;
import concurs.model.Participant;
import concurs.model.Proba;
import concurs.model.TipProba;
import javafx.util.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InscriereDbRepository implements JRepository<Inscriere, Pair<Integer,Integer>> {
    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public InscriereDbRepository(Properties props) {
        this.dbUtils =new JdbcUtils(props);
    }



    @Override
    public Inscriere findOne(Pair<Integer,Integer> id) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        //List<concurs.model.Inscriere> all=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Inscrieri where Inscrieri.IdParticipant=? and Inscrieri.IdProba=?")) {
            preStmt.setInt(1,id.getKey());
            preStmt.setInt(2,id.getValue());
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    //Integer id=result.getInt(1);
                    Inscriere inscriere = getInscriere(con, result);

                    return inscriere;

                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private Inscriere getInscriere(Connection con, ResultSet result) throws SQLException {
        //participant
        PreparedStatement preparedStatement = con.prepareStatement("select * from Participanti where ID=?");
        preparedStatement.setInt(1, result.getInt(1));
        ResultSet participantiSet = preparedStatement.executeQuery();

        Participant participant = getParticipant(participantiSet);


        //proba
        PreparedStatement preparedStatement2 = con.prepareStatement("select * from Probe where ID=?");
        preparedStatement2.setInt(1, result.getInt(2));
        ResultSet probeSet = preparedStatement2.executeQuery();

        Proba proba = getProba(probeSet);

        //incriere
        return new Inscriere(participant, proba);
    }

    @Override
    public Inscriere save(Inscriere inscriere) {
        logger.traceEntry("salvare inscriere {} ",inscriere);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Inscrieri values (?,?)")){
            preStmt.setInt(1,inscriere.getParticipant().getID());
            preStmt.setInt(2,inscriere.getProba().getID());

            preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.traceExit();
        return null;
    }



    @Override
    public void delete(Pair<Integer,Integer> pair) {
        logger.traceEntry("stergere inscriere  {}",pair);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Inscrieri where IdParticipant=? and IdProba=?")){
            preStmt.setInt(1,pair.getKey());
            preStmt.setInt(2,pair.getValue());
            preStmt.executeUpdate();
        }catch (SQLException ex){
            logger.error(ex);
            System.out.println("Error DB "+ex);
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.traceExit();
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        //List<concurs.model.Participant> all=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) from Inscrieri")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    return result.getInt(1);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.traceExit();
        return 0;
    }

    @Override
    public Iterable<Inscriere> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Inscriere> all=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Inscrieri")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    //Integer id=result.getInt(1);

                    //participant
                    PreparedStatement preparedStatement=con.prepareStatement("select * from Participanti where ID=?");
                    preparedStatement.setInt(1,result.getInt(1));
                    ResultSet participantiSet=preparedStatement.executeQuery();

                    Participant participant=getParticipant(participantiSet);

                    //proba
                    PreparedStatement preparedStatement2=con.prepareStatement("select * from Probe where ID=?");
                    preparedStatement2.setInt(1,result.getInt(2));
                    ResultSet probeSet=preparedStatement2.executeQuery();

                    Proba proba=getProba(probeSet);

                    //incriere
                    Inscriere inscriere=new Inscriere(participant,proba);
                    all.add(inscriere);

                }
            }
        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.traceExit(all);
        return all;
    }

    private Proba getProba(ResultSet result) throws SQLException {
        Integer id = result.getInt(1);
        TipProba tip = TipProba.valueOf(result.getString(2));
        Integer varstaMin = result.getInt(3);
        Integer varstaMax = result.getInt(4);
        return new Proba(id, tip, varstaMin, varstaMax);
    }

    private Participant getParticipant(ResultSet result) throws SQLException {
        Integer id = result.getInt(1);
        String nume = result.getString(2);
        String prenume = result.getString(3);
        Integer varsta = result.getInt(4);
        return new Participant(id, nume, prenume, varsta);
    }
}
