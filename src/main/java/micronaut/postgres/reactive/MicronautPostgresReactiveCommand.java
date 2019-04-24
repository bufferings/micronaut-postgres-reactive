package micronaut.postgres.reactive;

import io.micronaut.configuration.picocli.PicocliRunner;
import io.micronaut.context.ApplicationContext;

import io.reactiverse.reactivex.pgclient.PgIterator;
import io.reactiverse.reactivex.pgclient.PgPool;
import io.reactiverse.reactivex.pgclient.Row;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Command(name = "micronaut-postgres-reactive", description = "...",
        mixinStandardHelpOptions = true)
public class MicronautPostgresReactiveCommand implements Runnable {

    @Option(names = {"-v", "--verbose"}, description = "...")
    boolean verbose;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(MicronautPostgresReactiveCommand.class, args);
    }

    public void run() {
        // business logic here
        if (verbose) {
            System.out.println("Hi!");
        }
        data();
    }

    @Inject
    PgPool client;
    public void data() {
        client.rxQuery("select * from users")
            .map(rowSet -> {
                List<String> result = new ArrayList<>();
                PgIterator ite = rowSet.iterator();
                while(ite.hasNext()) {
                    Row row = ite.next();
                    result.add(row.getString("first_name"));
                }
                return result;
            })
            .blockingGet()
            .stream()
            .forEach(System.out::println);
    }
}
