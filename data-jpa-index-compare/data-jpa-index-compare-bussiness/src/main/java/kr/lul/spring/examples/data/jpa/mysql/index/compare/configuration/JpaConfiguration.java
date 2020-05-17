package kr.lul.spring.examples.data.jpa.mysql.index.compare.configuration;

import kr.lul.spring.examples.data.jpa.mysql.index.compare.entity.EntityAnchor;
import kr.lul.spring.examples.data.jpa.mysql.index.compare.repository.RepositoryAnchor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import static java.util.Objects.requireNonNull;

/**
 * @author justburrow
 * @since 2020/05/17
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = RepositoryAnchor.class)
@EnableTransactionManagement
public class JpaConfiguration {
  @Bean
  @ConfigurationProperties("spring.datasource.hikari")
  public DataSource dataSource() {
    return DataSourceBuilder.create()
               .build();
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
    adapter.setDatabase(Database.MYSQL);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setDataSource(dataSource());
    factory.setPackagesToScan(EntityAnchor.PACKAGE_NAME);
    factory.setJpaVendorAdapter(adapter);

    return factory;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    return new JpaTransactionManager(requireNonNull(entityManagerFactory().getObject()));
  }

  @Bean
  public HibernateExceptionTranslator hibernateExceptionTranslator() {
    return new HibernateExceptionTranslator();
  }
}
