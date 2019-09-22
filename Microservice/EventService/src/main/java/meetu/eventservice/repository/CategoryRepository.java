/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.eventservice.repository;

import java.util.List;
import meetu.eventservice.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Test
 */
public interface CategoryRepository extends MongoRepository<Category, String> {

    public List<Category> findAllByOrderByCategoryLabelAsc();

}
