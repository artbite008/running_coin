package club.runningcoin.repository;

import club.runningcoin.domain.GroupTable;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the GroupTable entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GroupTableRepository extends JpaRepository<GroupTable, Long> {

}
