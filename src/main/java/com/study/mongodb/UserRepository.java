package com.study.mongodb;

import java.util.List;
import java.util.Set;

import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
 
@Repository
public class UserRepository {
 
	@Autowired
    private MongoTemplate mongoTemplate;
 
	public void test() {
		Set<String> colls = this.mongoTemplate.getCollectionNames();
		for (String coll : colls) {
			System.out.println("CollectionName=" + coll);
		}
		MongoDatabase db = this.mongoTemplate.getDb();
		System.out.println("db=" + db.toString());
	}
 
	public void createCollection() {
		if (!this.mongoTemplate.collectionExists(MongodbUser.class)) {
			this.mongoTemplate.createCollection(MongodbUser.class);
		}
	}
 
	public List<MongodbUser> findList(int skip, int limit) {
		Query query = new Query();
		query.with(new Sort(new Order(Direction.ASC, "id")));
		query.skip(skip).limit(limit);
		return this.mongoTemplate.find(query, MongodbUser.class);
	}
 
	public List<MongodbUser> findListByName(String name) {
		Query query = new Query();
		query.addCriteria(new Criteria("name").is(name));
		return this.mongoTemplate.find(query, MongodbUser.class);
	}
 
	public MongodbUser findOne(String id) {
		Query query = new Query();
		query.addCriteria(new Criteria("_id").is(id));
		return this.mongoTemplate.findOne(query, MongodbUser.class);
	}
 
	public void insert(MongodbUser entity) {
		this.mongoTemplate.insert(entity);
 
	}
 
	public void update(MongodbUser entity) {
		Query query = new Query();
		query.addCriteria(new Criteria("_id").is(entity.getObjectId()));
		Update update = new Update();
		update.set("age", entity.getAge());
		update.set("name", entity.getName());
		this.mongoTemplate.updateFirst(query, update, MongodbUser.class);
	}
}
