package com.shyam.zenefits.ci.dao;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.mongodb.WriteResult;
import com.shyam.zenefits.ci.entities.AbstractEntity;

@Service
public class AbstractDAO extends AbstractMongoConfiguration {

	@Autowired
	private AbstractMongoConfiguration abstractMongoConfiguration;

	public AbstractDAO() {
		super();
	}

	protected void initAbstractMongoConfiguration() {
		this.abstractMongoConfiguration = new AbstractMongoConfiguration();
	}

	protected <E extends AbstractEntity> void updateEntity(E entity) {
		setEntityDefaultProperties(entity);
		abstractMongoConfiguration.getMongoOperations().save(entity, entity.getClass().getSimpleName());
	}

	public <T extends AbstractEntity> List<T> getEntities(Class<T> calzz) {
		return abstractMongoConfiguration.getMongoOperations().findAll(calzz, calzz.getSimpleName());
	}

	public <T extends AbstractEntity> T getEntityById(String id, Class<T> calzz) {
		// .andOperator(Criteria.where("state").is(VisibilityState.VISIBLE))
		Assert.hasText(id);
		Query query = new Query(Criteria.where("_id").is(id));
		return abstractMongoConfiguration.getMongoOperations().findOne(query, calzz, calzz.getSimpleName());
	}

	public <T extends AbstractEntity> List<T> runQuery(Query query, Class<T> calzz) {
		return abstractMongoConfiguration.getMongoOperations().find(query, calzz, calzz.getSimpleName());
	}

	public <T extends AbstractEntity> int queryCount(Query query, Class<T> calzz) {
		return abstractMongoConfiguration.getMongoOperations().find(query, calzz, calzz.getSimpleName()).size();
	}

	public <T> int deleteEntityById(String id, Class<T> calzz) {
		Query query = new Query(Criteria.where("_id").is(id));
		WriteResult result = abstractMongoConfiguration.getMongoOperations().remove(query, calzz,
				calzz.getSimpleName());
		return result.getN();
	}

	public static <E extends AbstractEntity> void setEntityDefaultProperties(Collection<E> entities) {
		if (entities != null && !entities.isEmpty()) {
			for (E entity : entities) {
				setEntityDefaultProperties(entity);
			}
		}
	}

	public static <E extends AbstractEntity> void setEntityDefaultProperties(E entity) {
		if (StringUtils.isEmpty(entity.getId())) {
			entity.setCreationTime(System.currentTimeMillis());
		}
		entity.setLastUpdatedTime(System.currentTimeMillis());
	}
}
