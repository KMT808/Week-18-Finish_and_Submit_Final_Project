package art.prompt.controller.model;

import java.util.HashSet;
import java.util.Set;
import art.prompt.entity.Contributor;
import art.prompt.entity.Prompt;
import art.prompt.entity.Supply;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PromptData {
  
  private Long promptId;
  private String promptName;
  private String promptDescription;
  private String completionTime;
  private String complexity;
  private PromptContributor contributor;
  private Set<String> supplies = new HashSet<>();
  
  public PromptData(Prompt prompt) {
    promptId = prompt.getPromptId();
    promptName = prompt.getPromptName();
    promptDescription = prompt.getPromptDescription();
    completionTime = prompt.getCompletionTime();
    complexity = prompt.getComplexity();
    
    for (Supply supply : prompt.getSupplies()) {
      supplies.add(supply.getSupply());
    }
  }

  @Data
  @NoArgsConstructor
  public static class PromptContributor {
    private Long contributorId;
    private String contributorName;
    private String contributorEmail;
    
    public PromptContributor(Contributor contributor) {
      contributorId = contributor.getContributorId();
      contributorName = contributor.getContributorName();
      contributorEmail = contributor.getContributorEmail();
    }
  }

}


