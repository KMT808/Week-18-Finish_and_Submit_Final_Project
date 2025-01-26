package art.prompt.dao;

import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import art.prompt.entity.Supply;

public interface SupplyDao extends JpaRepository<Supply, Long> {
  Set<Supply> findAllBySupplyIn(java.util.Set<String> supplies);
}
