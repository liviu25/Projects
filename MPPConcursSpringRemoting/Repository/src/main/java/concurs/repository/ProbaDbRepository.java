package concurs.repository;

import concurs.model.Proba;
import concurs.model.TipProba;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class ProbaDbRepository implements IProbaRepository{


    private JdbcUtils dbUtils;

    private static final Logger logger= LogManager.getLogger();

    public ProbaDbRepository(Properties props) {
        this.dbUtils =new JdbcUtils(props);
    }

    @Override
    public Iterable<Proba> findProbeAndNrParticipanti() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Proba> all=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("SELECT Probe.ID,Probe.TipProba,Probe.VarstaMin,Probe.VarstaMax,count(Inscrieri.IdParticipant) as NrParticipanti " +
                "FROM Probe left join Inscrieri on Probe.ID=Inscrieri.IdProba " +
                "group by Probe.ID")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    Proba proba=getProba(result);
                    Integer nrParticipanti=result.getInt(5);
                    proba.setNrParticipanti(nrParticipanti);
                    all.add(proba);
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

    @Override
    public Proba findOne(Integer integer) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Probe where ID=?")) {
            preStmt.setInt(1,integer);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    Proba proba = getProba(result);
                    logger.traceExit(proba);
                    return proba;
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

    public Proba getProbaByTipAndVarsta(TipProba tipProba,Integer varstaParticipant) {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Probe where TipProba=?" +
                        "and VarstaMin<=? and VarstaMax>=?")) {
            preStmt.setString(1,tipProba.toString());
            preStmt.setInt(2,varstaParticipant);
            preStmt.setInt(3,varstaParticipant);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    Proba proba = getProba(result);
                    logger.traceExit(proba);
                    return proba;
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

    @Override
    public Proba save(Proba proba) {
        logger.traceEntry("salvare proba {} ",proba);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("insert into Probe(TipProba,VarstaMin,VarstaMax) values (?,?,?)")){

            preStmt.setString(1,proba.getTipProba().toString());
            preStmt.setInt(2,proba.getVarstaMin());
            preStmt.setInt(3,proba.getVarstaMax());

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
    public void delete(Integer integer) {
        logger.traceEntry("stergere proba {}",integer);
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("delete from Probe where id=?")){
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


    public void update(Proba proba) {
        logger.traceEntry("update proba {}",proba.getID());
        Connection con=dbUtils.getConnection();
        try(PreparedStatement preStmt=con.prepareStatement("update Probe set TipProba=?,VarstaMin=?,VarstaMax=? where ID=?")){
            preStmt.setString(1,proba.getTipProba().toString());
            preStmt.setInt(2,proba.getVarstaMin());
            preStmt.setInt(3,proba.getVarstaMax());
            preStmt.setInt(4,proba.getID());
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
        try(PreparedStatement preStmt=con.prepareStatement("select count(*) from Probe")) {
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
    public Iterable<Proba> findAll() {
        logger.traceEntry();
        Connection con=dbUtils.getConnection();
        List<Proba> all=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from Probe")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    Proba proba = getProba(result);
                    all.add(proba);
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
}
