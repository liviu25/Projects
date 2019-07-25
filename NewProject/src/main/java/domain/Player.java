package domain;

import java.math.BigDecimal;
import java.sql.Date;

public class Player {
    private int id;
    private String name;
    private String position;
    private Date birthDate;
    private String natinality;
    private BigDecimal salary;
    private Date expires_date;
    private Team team;

    public Player(int id, String name, String position, Date birthDate, String natinality, BigDecimal salary, Date expires_date) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.birthDate = birthDate;
        this.natinality = natinality;
        this.salary = salary;
        this.expires_date = expires_date;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", birthDate=" + birthDate +
                ", natinality='" + natinality + '\'' +
                ", salary=" + salary +
                ", expires_date=" + expires_date +
                ", team=" + team +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNatinality() {
        return natinality;
    }

    public void setNatinality(String natinality) {
        this.natinality = natinality;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getExpires_date() {
        return expires_date;
    }

    public void setExpires_date(Date expires_date) {
        this.expires_date = expires_date;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
