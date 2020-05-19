package kr.lul.spring.examples.data.jpa.mysql.index.compare.service;

import kr.lul.spring.examples.data.jpa.mysql.index.compare.entity.Case3Root;
import kr.lul.spring.examples.data.jpa.mysql.index.compare.repository.Case3RootRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.LongStream;

import static kr.lul.spring.examples.data.jpa.mysql.index.compare.entity.Case3Root.ID_LENGTH;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author justburrow
 * @since 2020/05/19
 */
@Service
@Transactional
public class Case3Service {
  private static final Logger log = getLogger(Case3Service.class);

  @Autowired
  private Case3RootRepository repository;

  public CreateResult create(long size, int childSize) {
    Instant start = Instant.now();
    for (int i = 0; i < size; i++) {
      randomAlphanumeric(ID_LENGTH);
    }
    Duration keyGenDuration = Duration.between(start, Instant.now());

    start = Instant.now();
    LongStream.range(0, size)
        .mapToObj(l -> new Case3Root(randomAlphabetic(ID_LENGTH)))
        .forEach(root -> {
          for (int sequence = 0; sequence < childSize; sequence++) {
            root.add();
          }

          this.repository.save(root);
        });
    Instant end = Instant.now().minus(keyGenDuration);

    return new CreateResult("case 3 create. char(" + ID_LENGTH + ") pk - Composite PK", size, start, end);
  }
}
