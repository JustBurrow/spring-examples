package kr.lul.spring.examples.data.jpa.mysql.index.compare.repository;

import kr.lul.spring.examples.data.jpa.mysql.index.compare.entity.Case1Root;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author justburrow
 * @since 2020/05/17
 */
@Repository
public interface Case1RootRepository extends JpaRepository<Case1Root, Long> {
}
