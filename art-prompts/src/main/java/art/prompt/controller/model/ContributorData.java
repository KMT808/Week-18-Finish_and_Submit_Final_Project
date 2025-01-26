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
public class ContributorData {
  private Long contributorId;
  private String contributorName;
  private String contributorEmail;
  private Set<PromptResponse> prompts = new HashSet<>();

  public ContributorData(Contributor contributor) {
    contributorId = contributor.getContributorId();
    contributorName = contributor.getContributorName();
    contributorEmail = contributor.getContributorEmail();
    
    for (Prompt prompt : contributor.getPrompts()) {
      prompts.add(new PromptResponse(prompt));
    }
  }
  
  @Data
  @NoArgsConstructor
  static class PromptResponse {
    private Long promptId;
    private String promptName;
    private String promptDescription;
    private String completionTime;
    private String complexity;
    private Set<String> supplies = new HashSet<>();
    
    PromptResponse(Prompt prompt) {
      promptId = prompt.getPromptId();
      promptName = prompt.getPromptName();
      promptDescription = prompt.getPromptDescription();
      completionTime = prompt.getCompletionTime();
      complexity = prompt.getComplexity();
      
      for (Supply supply : prompt.getSupplies()) {
        supplies.add(supply.getSupply());
      }
    }
  }
}

