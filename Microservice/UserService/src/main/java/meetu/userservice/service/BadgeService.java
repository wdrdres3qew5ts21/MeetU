/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meetu.userservice.service;

import com.mongodb.BasicDBObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import meetu.userservice.model.Badge;
import meetu.userservice.model.UserBadge;
import meetu.userservice.repository.BadgeRepository;
import meetu.userservice.repository.UserBadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.limit;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.skip;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import static org.springframework.data.mongodb.core.query.UntypedExampleMatcher.matching;
import static org.springframework.data.mongodb.core.validation.Validator.criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Test
 */
@Service
public class BadgeService {

    @Autowired
    private BadgeRepository badgeRepository;

    @Autowired
    private UserBadgeRepository userBadgeRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public ResponseEntity createBadge(Badge badge) {
        Badge badgeInDatabase = badgeRepository.findByBadgeNameEquals(badge.getBadgeName());
        if (badgeInDatabase == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(badgeRepository.save(badge));
        }
        HashMap<String, String> response = new HashMap();
        response.put("response", "Badge Name Duplicate !!!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public ResponseEntity findBadgeByFilter(List<String> badgeTags, String badgeName, int page, int contentPerPage) {
        System.out.println("--------------");
        System.out.println("Test Bebug");
        System.out.println(badgeName);
        if (badgeTags == null & (badgeName.isEmpty() | badgeName == null)) {
            List<Badge> allBadge = badgeRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(allBadge);
        } else if (badgeTags != null & !badgeName.isEmpty()) {
            List<Badge> badgeFilterByTagsAndName = badgeRepository.findByBadgeTagsIsInAndBadgeNameLike(badgeTags, badgeName, PageRequest.of(page, contentPerPage));
            return ResponseEntity.status(HttpStatus.OK).body(badgeFilterByTagsAndName);
        } else if (!badgeName.isEmpty()) {
            System.out.println(badgeName);
            List<Badge> badgeFilterByTagsAndName = badgeRepository.findByBadgeNameLike(badgeName, PageRequest.of(page, contentPerPage));
            return ResponseEntity.status(HttpStatus.OK).body(badgeFilterByTagsAndName);
        }
        System.out.println("Filter some bade");
        System.out.println(badgeTags);
        List<Badge> matchingBadge = badgeRepository.findByBadgeTagsIsIn(badgeTags, PageRequest.of(page, contentPerPage));
        return ResponseEntity.status(HttpStatus.OK).body(matchingBadge);
    }

    public ResponseEntity findBadgeById(String badgeId) {
        Badge badgeInDatabase = badgeRepository.findById(badgeId).get();
        HashMap<String, String> response = new HashMap();
        if (badgeInDatabase != null) {
            return ResponseEntity.status(HttpStatus.OK).body(badgeInDatabase);

        }
        response.put("response", "Fail to create Event Because Badge ID Not found : ");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public ResponseEntity findRankingOfUserInBadge(String badgeId, int page, int contentPerPage) {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("users")
                .localField("uid")
                .foreignField("uid")
                .as("userDetail");
        AggregationOperation match = Aggregation.match(Criteria.where("badgeId").is(badgeId));
        SortOperation sortLevelAndExp = sort(new Sort(Sort.Direction.DESC, "level", "exp"));
        SkipOperation skip = skip(page);
        LimitOperation limitOperation = limit(contentPerPage);
        Aggregation aggregation = Aggregation.newAggregation(match, lookupOperation, sortLevelAndExp, skip, limitOperation);
        List<BasicDBObject> results = mongoTemplate.aggregate(aggregation, "userBadge", BasicDBObject.class).getMappedResults();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    public ResponseEntity findBadgeThatUserOwn(String uid, int page, int contentPerPage) {
        return ResponseEntity.status(HttpStatus.OK).body(userBadgeRepository.findByUid(uid, PageRequest.of(page, contentPerPage, Sort.Direction.DESC, "level", "exp")));
    }

}
