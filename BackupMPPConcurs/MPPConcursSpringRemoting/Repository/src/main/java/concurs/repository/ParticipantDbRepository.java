package concurs.repository;


import concurs.model.Participant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ParticipantDbRepository implements IParticipantRepository {
    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public ParticipantDbRepository(Properties props) {
        this.dbUtils =new JdbcUtils(props);
    }

    @Override
    public Iterable<Participant> findParticipantiByProba(Integer idProba) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Participant> all=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Participanti inner join Inscrieri on Participanti.ID=Inscrieri.IdParticipant where IdProba=?")) {
            preStmt.setInt(1,idProba);
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    Participant participant = getParticipant(result);
                    logger.traceExit(participant);
                    all.add(participant);
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

        logger.traceExit(null);
        return all;
    }


    @Override
    public Participant findOne(Integer integer) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        //List<concurs.model.Participant> all=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Participanti where ID=?")) {
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    Participant participant = getParticipant(result);
                    logger.traceExit(participant);
                    return participant;
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
        logger.traceExit(null);
        return null;
    }



    private Participant getParticipant(ResultSet result) throws SQLException {
        Integer id = result.getInt(1);
        String nume = result.getString(2);
        String prenume = result.getString(3);
        Integer varsta = result.getInt(4);
        return new Participant(id, nume, prenume, varsta);
    }

    @Override
    public Participant save(Participant participant) {
        logger.traceEntry("salvare participant {} ",participant);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Participanti(Nume,Prenume,Varsta) values (?,?,?)")){
            preStmt.setString(1,participant.getNume());
            preStmt.setString(2,participant.getPrenume());
            preStmt.setInt(3,participant.getVarsta());

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

        con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select Max(ID),Nume,Prenume,Varsta from Participanti")) {
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    Participant savedParticipant = getParticipant(result);
                    logger.traceExit(savedParticipant);
                    return savedParticipant;
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
        return null;

    }



    @Override
    public void delete(Integer integer) {
        logger.traceEntry("stergere participanti {}",integer);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Participanti where id=?")){
            preStmt.setInt(1,integer);
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
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) from Participanti")) {
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
    public Iterable<Participant> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Participant> all=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Participanti")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    Participant participant = getParticipant(result);
                    all.add(participant);
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
}
