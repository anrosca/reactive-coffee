package com.endava.reactive.coffee;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.connectionfactory.R2dbcTransactionManager;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.core.DefaultReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.core.ReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.dialect.PostgresDialect;
import org.springframework.transaction.ReactiveTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.reactive.GenericReactiveTransaction;
import org.springframework.transaction.reactive.TransactionSynchronizationManager;

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import reactor.core.publisher.Mono;

@Configuration
public class R2dbcConfig {

    @Bean
    public PostgresqlConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder()
                .host("localhost")
                .username("postgres")
                .password("welcome1")
                .database("r2dbc-postgres")
                .build());
    }

    @Bean
    @Primary
    public ReactiveTransactionManager reactiveTransactionManager() {
        return new R2dbcTransactionManager(connectionFactory()) {

            @Override
            protected Mono<Void> doBegin(final TransactionSynchronizationManager synchronizationManager,
                    final Object transaction,
                    final TransactionDefinition definition)
                    throws TransactionException {
                System.out.println("*****************************");
                System.out.println("**********begin*********");
                System.out.println("*****************************");
                return super.doBegin(synchronizationManager, transaction, definition);
            }

            @Override
            protected Mono<Void> doCommit(final TransactionSynchronizationManager TransactionSynchronizationManager, final GenericReactiveTransaction status)
                    throws TransactionException {
                System.out.println("*****************************");
                System.out.println("**********commit*********");
                System.out.println("*****************************");
                return super.doCommit(TransactionSynchronizationManager, status);
            }

            @Override
            protected Mono<Void> doRollback(final TransactionSynchronizationManager TransactionSynchronizationManager, final GenericReactiveTransaction status)
                    throws TransactionException {
                System.out.println("*****************************");
                System.out.println("**********rollback*********");
                System.out.println("*****************************");
                return super.doRollback(TransactionSynchronizationManager, status);
            }
        };
    }

    @Bean
    public ReactiveDataAccessStrategy reactiveDataAccessStrategy() {
        return new DefaultReactiveDataAccessStrategy(new PostgresDialect());
    }

    @Bean
    public DatabaseClient databaseClient() {
        return DatabaseClient.create(connectionFactory());
    }
}
