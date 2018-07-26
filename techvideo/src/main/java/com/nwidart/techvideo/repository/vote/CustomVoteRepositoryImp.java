package com.nwidart.techvideo.repository.vote;

import com.nwidart.techvideo.dto.VoteCountResult;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CustomVoteRepositoryImp implements CustomVoteRepository {

  private JdbcTemplate jdbcTemplate;

  public CustomVoteRepositoryImp(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public List<VoteCountResult> votesForSessionCount() {
    return jdbcTemplate.query("SELECT video_id, COUNT(votes.id) FROM votes WHERE session_id=2 GROUP BY video_id",
        new CustomRowMapper());
  }

  class CustomRowMapper implements RowMapper<VoteCountResult> {

    @Override
    public VoteCountResult mapRow(ResultSet resultSet, int i) throws SQLException {
      return null;
    }
  }
}
