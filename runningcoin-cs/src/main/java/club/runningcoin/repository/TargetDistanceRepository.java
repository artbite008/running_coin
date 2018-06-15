package club.runningcoin.repository;

import club.runningcoin.domain.TargetDistance;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the TargetDistance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TargetDistanceRepository extends JpaRepository<TargetDistance, Long> {

}
