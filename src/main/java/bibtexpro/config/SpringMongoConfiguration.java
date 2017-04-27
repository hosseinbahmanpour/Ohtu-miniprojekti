package bibtexpro.config;


import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("bibtexpro.repository")
public class SpringMongoConfiguration extends AbstractMongoConfiguration {

    @Value("${spring.profiles.active}")
    private String profileActive;

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private String mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDB;

    @Override
    public MongoMappingContext mongoMappingContext()
            throws ClassNotFoundException {
        return super.mongoMappingContext();
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        if ("production".equals(profileActive)) {
            return new MongoClient(mongoHost + ":" + mongoPort);
        }
        

        return new Fongo(getDatabaseName()).getMongo();
    }

    @Override
    protected String getDatabaseName() {
        return mongoDB;
    }
}
