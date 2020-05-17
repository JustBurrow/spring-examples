package kr.lul.spring.examples.data.jpa.mysql.index.compare.repository;

import kr.lul.spring.examples.data.jpa.mysql.index.compare.DataIndexCompareTestConfiguration;
import kr.lul.spring.examples.data.jpa.mysql.index.compare.entity.Case1Root;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author justburrow
 * @since 2020/05/17
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DataIndexCompareTestConfiguration.class)
@Transactional
class Case1RootRepositoryTest {
  @Autowired
  private Case1RootRepository repository;

  @Test
  void test_findAll() {
    List<Case1Root> list = this.repository.findAll();

    assertThat(list)
        .isNotNull();
  }
}
