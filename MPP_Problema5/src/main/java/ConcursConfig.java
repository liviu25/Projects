import Repository.InscriereDbRepository;
import Repository.ParticipantDbRepository;
import Repository.ProbaDbRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import sun.misc.Contended;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Contended
public class ConcursConfig {
    @Bean
    @Primary
    public Properties jdbsProps(){
        Properties serverProps=new Properties();
        try {
            serverProps.load(new FileInputStream("bd.config"));
            System.out.println("Properties set. ");
            serverProps.list(System.out);
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);

        }

        return  serverProps;

    }

    @Bean(name = "participantiRepo")
    public ParticipantDbRepository createParticipantRepository(Properties jdbcProps)
    {
        return new ParticipantDbRepository(jdbcProps);
    }

    @Bean(name = "probaRepo")
    public ProbaDbRepository createProbaRepository(Properties jdbcProps)
    {
        return new ProbaDbRepository(jdbcProps);
    }

    @Bean(name= "inscriereRepo")
    public InscriereDbRepository createInscriereRepository(Properties jdbcProps)
    {
        return new InscriereDbRepository(jdbcProps);
    }
}
