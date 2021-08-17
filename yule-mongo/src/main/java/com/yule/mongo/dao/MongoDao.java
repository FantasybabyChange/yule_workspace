//package com.yule.mongo.dao;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.mongodb.morphia.Datastore;
//import org.mongodb.morphia.DatastoreImpl;
//import org.mongodb.morphia.Key;
//import org.mongodb.morphia.query.Query;
//import org.mongodb.morphia.query.QueryResults;
//import org.mongodb.morphia.query.UpdateOperations;
//import org.mongodb.morphia.query.UpdateResults;
//
//import com.mongodb.DBCollection;
//import com.mongodb.WriteConcern;
//import com.mongodb.WriteResult;
//
//@SuppressWarnings({ "unchecked", "rawtypes" })
//public class MongoDao<T, K>  {
//	
//	protected Class<T> entityClazz;
//	protected DatastoreImpl ds;
//
//	protected MongoDao(Datastore ds) {
//		this.ds = (DatastoreImpl) ds;
//	}
//
//	protected void setEntityClazz(Class<T> entityClazz) {
//		this.entityClazz = entityClazz;
//	}
//
//	protected void setDs(DatastoreImpl ds) {
//		this.ds = ds;
//	}
//
//	protected List<?> keysToIds(List<Key<T>> keys) {
//		ArrayList ids = new ArrayList(keys.size() * 2);
//		for (Key<T> key : keys)
//			ids.add(key.getId());
//		return ids;
//	}
//
//	protected DBCollection getCollection() {
//		return ds.getCollection(entityClazz);
//	}
//
//	protected Query<T> createQuery() {
//		return ds.createQuery(entityClazz);
//	}
//
//	protected UpdateOperations<T> createUpdateOperations() {
//		return ds.createUpdateOperations(entityClazz);
//	}
//
//	protected Class<T> getEntityClass() {
//		return entityClazz;
//	}
//
//	protected Key<T> save(T entity) {
//		return ds.save(entity);
//	}
//
//	protected Key<T> save(T entity, WriteConcern wc) {
//		return ds.save(entity, wc);
//	}
//
//	protected UpdateResults<T> updateFirst(Query<T> q, UpdateOperations<T> ops) {
//		return ds.updateFirst(q, ops);
//	}
//
//	protected UpdateResults<T> update(Query<T> q, UpdateOperations<T> ops) {
//		return ds.update(q, ops);
//	}
//
//	protected WriteResult delete(T entity) {
//		return ds.delete(entity);
//	}
//
//	protected WriteResult delete(T entity, WriteConcern wc) {
//		return ds.delete(entity, wc);
//	}
//
//	protected WriteResult deleteById(K id) {
//		return ds.delete(entityClazz, id);
//	}
//
//	protected WriteResult deleteByQuery(Query q) {
//		return ds.delete(q);
//	}
//
//	protected T get(K id) {
//		return ds.get(entityClazz, id);
//	}
//
//	protected List<T> findIds(String key, Object value) {
//		return (List<T>) keysToIds(ds.find(entityClazz, key, value).asKeyList());
//	}
//
//	protected List<Key<T>> findIds() {
//		return (List<Key<T>>) keysToIds(ds.find(entityClazz).asKeyList());
//	}
//
//	protected List<Key<T>> findIds(Query<T> q) {
//		return (List<Key<T>>) keysToIds(q.asKeyList());
//	}
//
//	protected boolean exists(String key, Object value) {
//		return exists(ds.find(entityClazz, key, value));
//	}
//
//	protected boolean exists(Query<T> q) {
//		return ds.getCount(q) > 0;
//	}
//
//	protected long count() {
//		return ds.getCount(entityClazz);
//	}
//
//	protected long count(String key, Object value) {
//		return count(ds.find(entityClazz, key, value));
//	}
//
//	protected long count(Query<T> q) {
//		return ds.getCount(q);
//	}
//
//	protected T findOne(String key, Object value) {
//		return ds.find(entityClazz, key, value).get();
//	}
//
//	protected T findOne(Query<T> q) {
//		return q.get();
//	}
//
//	protected QueryResults<T> find() {
//		return createQuery();
//	}
//
//	protected QueryResults<T> find(Query<T> q) {
//		return q;
//	}
//
//	protected Datastore getDatastore() {
//		return ds;
//	}
//
//	protected void ensureIndexes() {
//		ds.ensureIndexes(entityClazz);
//	}
//
//}
