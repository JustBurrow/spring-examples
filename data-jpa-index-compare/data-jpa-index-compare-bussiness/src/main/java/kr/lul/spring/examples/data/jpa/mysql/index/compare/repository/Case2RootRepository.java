package kr.lul.spring.examples.data.jpa.mysql.index.compare.repository;

import kr.lul.spring.examples.data.jpa.mysql.index.compare.entity.Case2Root;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author justburrow
 * @since 2020/05/19
 */
@Repository
public interface Case2RootRepository extends JpaRepository<Case2Root, Long> {
}
