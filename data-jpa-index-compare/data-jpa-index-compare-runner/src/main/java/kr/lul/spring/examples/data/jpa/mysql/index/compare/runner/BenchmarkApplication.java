package kr.lul.spring.examples.data.jpa.mysql.index.compare.runner;

import kr.lul.spring.examples.data.jpa.mysql.index.compare.DataJpaIndexCompareAnchor;
import kr.lul.spring.examples.data.jpa.mysql.index.compare.service.Case1Service;
import kr.lul.spring.examples.data.jpa.mysql.index.compare.service.Case2Service;
import kr.lul.spring.examples.data.jpa.mysql.index.compare.service.Case3Service;
import kr.lul.spring.examples.data.jpa.mysql.index.compare.service.CreateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static java.lang.String.format;

/**
 * @author justburrow
 * @since 2020/05/17
 */
@SpringBootApplication(scanBasePackageClasses = DataJpaIndexCompareAnchor.class)
public class BenchmarkApplication implements ApplicationRunner {
  private static final Logger log = LoggerFactory.getLogger(BenchmarkApplication.class);

  public static final String ARG_SIZE = "size";
  public static final String ARG_CHILD_SIZE = "child";

  @Autowired
  private Case1Service case1Service;
  @Autowired
  private Case2Service case2Service;
  @Autowired
  private Case3Service case3Service;

  private long longArgument(String name, ApplicationArguments args) {
    List<String> values = args.getOptionValues(name);
    switch (values.size()) {
      case 0:
        throw new IllegalArgumentException(ARG_SIZE + " is required option.");
      case 1:
        return Long.parseLong(values.get(0));
      default:
        throw new IllegalArgumentException(format("too many %s values : %s", ARG_SIZE, values));
    }
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    long size = longArgument(ARG_SIZE, args);
    int childSize = (int) longArgument(ARG_CHILD_SIZE, args);
    log.info("#run size={}, childSize={}", size, childSize);

    List<CreateResult> results = List.of(
        this.case1Service.create(size, childSize),
        this.case2Service.create(size, childSize),
        this.case3Service.create(size, childSize));
    log.info("#run results={}", results);
  }

  public static void main(String[] args) {
    SpringApplication.run(BenchmarkApplication.class, args);
  }
}
