package kr.lul.spring.examples.data.jpa.mysql.index.compare.service;

import kr.lul.spring.examples.data.jpa.mysql.index.compare.entity.Case1Root;
import kr.lul.spring.examples.data.jpa.mysql.index.compare.repository.Case1RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.stream.LongStream;

/**
 * @author justburrow
 * @since 2020/05/17
 */
@Service
@Transactional
public class Case1Service {
  @Autowired
  private Case1RootRepository repository;

  public CreateResult create(final long size, final int childSize) {
    Instant start = Instant.now();

    LongStream.range(0, size)
        .mapToObj(l -> new Case1Root())
        .forEach(root -> {
          for (int sequence = 0; sequence < childSize; sequence++) {
            root.add();
          }

          this.repository.save(root);
        });
    Instant end = Instant.now();

    return new CreateResult("case1 create test", childSize, start, end);
  }
}
