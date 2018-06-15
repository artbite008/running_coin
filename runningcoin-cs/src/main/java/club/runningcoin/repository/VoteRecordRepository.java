package club.runningcoin.repository;

import club.runningcoin.domain.VoteRecord;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;

/**
 * Spring Data JPA repository for the VoteRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VoteRecordRepository extends JpaRepository<VoteRecord, Long> {

}
