package org.maxkizi.quotes.core.repository;

import org.maxkizi.quotes.core.dto.projection.QuoteProjection;
import org.maxkizi.quotes.core.model.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.Entity;
import java.util.List;
import java.util.Optional;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query(nativeQuery = true,
            value = "with score as (select q.id, sum(v.int_value) as score\n" +
                    "               from quote q inner join votes v on q.id = v.quote_id\n" +
                    "               group by q.id)\n" +
                    "select q.id, q.content, s.score, u.login from quote q left join score s on q.id = s.id inner join users u on u.id = q.user_id")
    List<QuoteProjection> findQuotes(Pageable pageable);

    @EntityGraph(attributePaths = "user")
    Optional<Quote> findById(Long id);

    @Query(nativeQuery = true,
            value = "with score as (select q.id, sum(v.int_value) as score\n" +
                    "               from quote q inner join votes v on q.id = v.quote_id\n" +
                    "               group by q.id)\n" +
                    "select q.id, q.content, s.score, u.login from quote q left join score s on q.id = s.id inner join users u on u.id = q.user_id " +
                    "order by random() limit 1")
    QuoteProjection findRandom();
}
