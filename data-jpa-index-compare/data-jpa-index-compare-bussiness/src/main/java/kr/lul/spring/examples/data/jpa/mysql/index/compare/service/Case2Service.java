package kr.lul.spring.examples.data.jpa.mysql.index.compare.service;

import kr.lul.spring.examples.data.jpa.mysql.index.compare.entity.Case2Root;
import kr.lul.spring.examples.data.jpa.mysql.index.compare.repository.Case2RootRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.stream.LongStream;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author justburrow
 * @since 2020/05/19
 */
@Service
@Transactional
public class Case2Service {
  private static final Logger log = getLogger(Case2Service.class);

  @Autowired
  private Case2RootRepository repository;

  public CreateResult create(long size, int childSize) {
    Instant start = Instant.now();

    LongStream.range(0, size)
        .mapToObj(l -> new Case2Root())
        .forEach(root -> {
          for (int sequence = 0; sequence < childSize; sequence++) {
            root.add();
          }
          this.repository.save(root);
        });

    return new CreateResult("case2 create test. AI PK - Alternertive AI PK", childSize, start, Instant.now());
  }
}
