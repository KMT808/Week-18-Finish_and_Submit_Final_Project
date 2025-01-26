package art.prompt.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import art.prompt.controller.model.ContributorData;
import art.prompt.controller.model.PromptData;
import art.prompt.dao.ContributorDao;
import art.prompt.dao.PromptDao;
import art.prompt.dao.SupplyDao;
import art.prompt.entity.Contributor;
import art.prompt.entity.Prompt;
import art.prompt.entity.Supply;

@Service
public class PromptService {

  @Autowired
  private ContributorDao contributorDao;
  
  @Autowired
  private SupplyDao supplyDao;
  
  @Autowired
  private PromptDao promptDao;
  
  @Transactional(readOnly = false)
  public ContributorData saveContributor(ContributorData contributorData) {
    Long contributorId = contributorData.getContributorId();
    Contributor contributor = 
        findOrCreateContributor(contributorId, contributorData.getContributorEmail());
    
    setFieldsInContributor(contributor, contributorData);
    return new ContributorData(contributorDao.save(contributor));
  }

  private void setFieldsInContributor(Contributor contributor, ContributorData contributorData) {
    contributor.setContributorEmail(contributorData.getContributorEmail());
    contributor.setContributorName(contributorData.getContributorName());
    
  }

  private Contributor findOrCreateContributor(Long contributorId, String contributorEmail) {
    Contributor contributor;
    
    if (Objects.isNull(contributorId)) {
      Optional<Contributor> opContrib = contributorDao.findByContributorEmail(contributorEmail);
      
      if (opContrib.isPresent()) {
        throw new DuplicateKeyException(
            "Contributor with email " + contributorEmail + " already exists.");
      }
      
      contributor = new Contributor();
    } else {
      contributor = findContributorById(contributorId);
    }
    return contributor;
  }
  
  private Contributor findContributorById(Long contributorId) {
    return contributorDao.findById(contributorId).orElseThrow(() -> 
      new NoSuchElementException(
        "Contributor with ID=" + contributorId + " was not found."));
  }
  
  @Transactional(readOnly = true)
  public List<ContributorData> retrieveAllContributors() {
    List<Contributor> contributors = contributorDao.findAll();
    List<ContributorData> response = new LinkedList<>();
    
    for (Contributor contributor : contributors) {
      response.add(new ContributorData(contributor));
    }
    return response;
  }
  
  @Transactional(readOnly = true)
  public ContributorData retrieveContributorById(Long contributorId) {
    Contributor contributor = findContributorById(contributorId);
    return new ContributorData(contributor);
  }
  
  @Transactional(readOnly = false)
  public void deleteContributorById(Long contributorId) {
    Contributor contributor = findContributorById(contributorId);
    contributorDao.delete(contributor);
  }
  
  @Transactional(readOnly = false)
  public PromptData savePrompt(Long contributorId, PromptData promptData) {
    Contributor contributor = findContributorById(contributorId);
    
    Set<Supply> supplies = supplyDao.findAllBySupplyIn(promptData.getSupplies());
    
    Prompt prompt = findOrCreatePrompt(promptData.getPromptId());
    setPromptFields(prompt, promptData);
    
    prompt.setContributor(contributor);
    contributor.getPrompts().add(prompt);
    
    for (Supply supply : supplies) {
      supply.getPrompts().add(prompt);
      prompt.getSupplies().add(supply);
    }
    Prompt dbPrompt = promptDao.save(prompt);
    return new PromptData(dbPrompt);
  }
  
  private void setPromptFields(Prompt prompt, PromptData promptData) {
    prompt.setPromptId(promptData.getPromptId());
    prompt.setPromptName(promptData.getPromptName());
    prompt.setPromptDescription(promptData.getPromptDescription());
    prompt.setCompletionTime(promptData.getCompletionTime());
    prompt.setComplexity(promptData.getComplexity());
  }
  
  private Prompt findOrCreatePrompt(Long promptId) {
    Prompt prompt;
    
    if (Objects.isNull(promptId)) {
      prompt = new Prompt();
    } else {
      prompt = findPromptById(promptId);
    }
    return prompt;
  }

  private Prompt findPromptById(Long promptId) {
    return promptDao.findById(promptId).orElseThrow(
        () -> new NoSuchElementException("Prompt with ID=" + promptId + " does not exist"));
  }
  
  @Transactional(readOnly = true)
  public PromptData retrievePromptById(Long contributorId, Long promptId) {
    findContributorById(contributorId);
    Prompt prompt = findPromptById(promptId);
    
    if(prompt.getContributor().getContributorId() != contributorId) {
      throw new IllegalStateException("Prompt with ID=" + promptId + 
          " is not owned by contributor with ID=" + contributorId);
    }
    return new PromptData(prompt);
  }
 
}