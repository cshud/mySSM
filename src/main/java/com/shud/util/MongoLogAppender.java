package com.shud.util;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 * Created by shud on 2017/5/24.
 */
public class MongoLogAppender extends AppenderSkeleton {
    /**
     * 数据库的名称
     */
    private String databaseName = "ssm_logs";
    /**
     * 集合的名称
     */
    private String collectionName = "logs";


    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<BasicDBObject> logsCollection;


    @Override
    protected void append(LoggingEvent loggingEvent) {
        if(mongoDatabase == null) {
            mongoClient = new MongoClient("localhost",27017);
            mongoDatabase = mongoClient.getDatabase(databaseName);
            logsCollection = mongoDatabase.getCollection(collectionName, BasicDBObject.class);
        }
        //将日志插入到集合
        logsCollection.insertOne((BasicDBObject) loggingEvent.getMessage());
    }

    @Override
    public void close() {
        if(mongoClient!=null){
            mongoClient.close();
        }
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
