package art.prompt.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import art.prompt.controller.model.ContributorData;
import art.prompt.controller.model.PromptData;
import art.prompt.service.PromptService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/art_prompt")
@Slf4j
public class PromptController {
  @Autowired
  private PromptService promptService;
  
  // Method to post or insert a Contributor to the Contributor table. 
  //Uses the saveContributor method to save data
  @PostMapping("/contributor")
  @ResponseStatus(code = HttpStatus.CREATED)
  public ContributorData insertContributor(@RequestBody ContributorData contributorData) {
    log.info("Creating contributor()", contributorData);
    return promptService.saveContributor(contributorData);
  }
  
  // Method to put or update Contributor data for a contributor that is in the Contributor table.
  //Uses the save Contributor method to save the data
  @PutMapping("/contributor/{contributorId}")
  public ContributorData updateContributor(@PathVariable Long contributorId,
      @RequestBody ContributorData contributorData) {
    contributorData.setContributorId(contributorId);
    log.info("Updating contributor {}", contributorData);
    return promptService.saveContributor(contributorData);
  }
  
  // Method to get or retrieve all contributors in the Contributor table.
  @GetMapping("/contributor")
  public List<ContributorData> retrieveAllContributors() {
    log.info("Retrieve all contributors called.");
    return promptService.retrieveAllContributors();
  }
  
  // Method to get or retrieve a Contributor by Id from the Contributor table.
  @GetMapping("/contributor/{contributorId}")
  private ContributorData retrieveContributorById(@PathVariable Long contributorId) {
    log.info("Retrieving contributor with ID=", contributorId);
    return promptService.retrieveContributorById(contributorId);
  }
  
  // Method to display an error if a request is sent to to delete all contributors in the Contributor table.
  @DeleteMapping("/contributor")
  public void deleteAllContributors() {
    log.info("Attempting to delete all contributors");
    throw new UnsupportedOperationException("Deleting all contributors is not allowed.");
  }
  
  // Method to delete a contributor by contributor Id in the Contributor table.
  @DeleteMapping("/contributor/{contributorId}")
  public Map<String, String> deleteContributorById(@PathVariable Long contributorId) {
    log.info("Deleting contributor with ID={}", contributorId);
    
    promptService.deleteContributorById(contributorId);
    
    return Map.of("message", 
        "Deletion of contributor with ID=" + contributorId + " was successful.");
  }
  
  // Method that posts or inserts a prompt into the Prompt table
  @PostMapping("/contributor/{contributorId}/prompt")
  @ResponseStatus(code = HttpStatus.CREATED)
  public PromptData insertPrompt(@PathVariable Long contributorId,
      @RequestBody PromptData promptData) {
    
    log.info("Creating prompt {} for contributor with ID={}", promptData, contributorId);
    
    return promptService.savePrompt(contributorId, promptData);
  }
  
  // Method to put or update a prompt Id in the Prompt table
  @PutMapping("/contributor/{contributorId}/prompt/{promptId}")
  public PromptData updatePrompt(@PathVariable Long contributorId,
      @PathVariable Long promptId,
      @RequestBody PromptData promptData) {
    
    promptData.setPromptId(promptId);
    
    log.info("Updating prompt {} for contributor with ID={} ", contributorId, promptData);
    
    return promptService.savePrompt(contributorId, promptData);
  }
  
  // Method to get or retrieve a prompt in the Prompt table by Id
  @GetMapping("/contributor/{contributorId}/prompt/{promptId}")
  public PromptData retrievePromptById(@PathVariable Long contributorId,
      @PathVariable Long promptId) {
    
    log.info("Retrieving prompt with ID={} for contributor with ID={}",
        promptId, contributorId);
    
    return promptService.retrievePromptById(contributorId, promptId);
  }
  
}
