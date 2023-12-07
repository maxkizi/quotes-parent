package org.maxkizi.quotes.core.repository;

import org.maxkizi.quotes.core.model.Quote;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
    @Query("select quote from Quote quote join fetch quote.user")
    List<Quote> findQuotes(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from quote order by RAND() limit 1")
    Quote findRandom();
}
