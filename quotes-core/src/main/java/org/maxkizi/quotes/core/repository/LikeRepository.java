package org.maxkizi.quotes.core.repository;

import org.maxkizi.quotes.core.model.Vote;
import org.maxkizi.quotes.core.model.VoteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Vote, VoteId> {
}
