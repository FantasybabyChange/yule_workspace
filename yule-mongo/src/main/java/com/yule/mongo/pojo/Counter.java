package com.yule.mongo.pojo;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.yule.mongo.constant.CollectionConst;

/**
 * 计数器
 */
@Document(collection=CollectionConst.COUNTER)
public class Counter implements Serializable{


	private static final long serialVersionUID = 5499065769135760016L;
	
	@Id
	private ObjectId id;
	
	private String collection_name;
	
	private long num;

	public String getCollection_name() {
		return collection_name;
	}

	public void setCollection_name(String collection_name) {
		this.collection_name = collection_name;
	}

	public long getNum() {
		return num;
	}

	public void setNum(long num) {
		this.num = num;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	
}
