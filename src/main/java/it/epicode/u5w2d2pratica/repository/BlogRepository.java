package it.epicode.u5w2d2pratica.repository;

import it.epicode.u5w2d2pratica.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
