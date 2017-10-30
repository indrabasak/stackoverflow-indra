package com.basaki.data.repository;

import com.basaki.data.entity.Person;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * {@code RepositoryRestResource} acts a repository and REST controller for
 * {@code Person}.
 * <p>
 *
 * @author Indra Basak
 * @since 10/29/17
 */
@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    List<Person> findByLastName(@Param("name") String name);
}
