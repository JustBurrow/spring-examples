package kr.lul.spring.examples.data.jpa.mysql.index.compare.service;

import java.time.Duration;
import java.time.Instant;

/**
 * @author justburrow
 * @since 2020/05/17
 */
public class CreateResult {
  private String caseName;
  private long size;
  private Instant start;
  private Instant end;

  public CreateResult(String caseName, long size, Instant start, Instant end) {
    this.caseName = caseName;
    this.size = size;
    this.start = start;
    this.end = end;
  }

  public String getCaseName() {
    return this.caseName;
  }

  public long getSize() {
    return this.size;
  }

  public Instant getStart() {
    return this.start;
  }

  public Instant getEnd() {
    return this.end;
  }

  public Duration getDuration() {
    return Duration.between(this.start, this.end);
  }

  @Override
  public String toString() {
    return new StringBuilder(CreateResult.class.getSimpleName())
               .append("{caseName='").append(this.caseName).append('\'')
               .append(", size=").append(this.size)
               .append(", start=").append(this.start)
               .append(", end=").append(this.end)
               .append('}').toString();
  }
}
