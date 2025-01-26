package art.prompt.entity;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Prompt {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long promptId;
  
  private String promptName;
  private String promptDescription;
  private String completionTime;
  private String complexity;
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "contributor_id", nullable = false)
  private Contributor contributor;
  
  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "prompt_supply", 
      joinColumns = @JoinColumn(name = "prompt_id"),
      inverseJoinColumns = @JoinColumn(name = "supply_id"))
  private Set<Supply> supplies = new HashSet<>();
}
