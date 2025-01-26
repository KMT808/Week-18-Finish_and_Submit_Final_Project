package art.prompt.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import art.prompt.entity.Prompt;

public interface PromptDao extends JpaRepository<Prompt, Long> {

}
