package club.runningcoin.repository;

import club.runningcoin.domain.RunningRecord;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the RunningRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RunningRecordRepository extends JpaRepository<RunningRecord, Long> {

}
