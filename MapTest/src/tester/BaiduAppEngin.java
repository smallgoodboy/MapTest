package tester;

import java.util.Arrays;

import javax.servlet.http.HttpServlet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
public class BaiduAppEngin extends HttpServlet { 
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 2525042511877670020L;
	DBCollection mongoCollection;
	public static void main(String args[]){
        try {
            /*****1. ��д���ݿ������Ϣ(��������ݿ�����ҳ)*****/
            String databaseName = "ZxeBymtkDfqipWNtyCoP"; 
            String host = "mongo.duapp.com";
            int port = 8908;
            String username = "tflXIGBYmmA9o3Xz0cNTEHfK";//�û���(api key);
            String password = "yCTggKOhg9g86ZcdcnYj6wqXoGIesQiQ";//����(secret key)
            String serverName = host + ":" + port;
 
            MongoClient mongoClient2 = new MongoClient(host, port);

    		DB	db = mongoClient2.getDB("admin");
    			db.authenticate(username, password.toCharArray());
	    /******2. �������Ӳ�ѡ�����ݿ���ΪdatabaseName�ķ�����******/
            MongoClient mongoClient = new MongoClient(
            		new ServerAddress(serverName),
            		Arrays.asList(MongoCredential.createMongoCRCredential(username, databaseName,password.toCharArray())),
            		new MongoClientOptions.Builder().cursorFinalizerEnabled(false).build());
	    DB mongoDB = mongoClient.getDB(databaseName);
//	    mongoClient.getDB("admin").authenticate(username, password.toCharArray());
	    mongoDB.authenticate(username, password.toCharArray());
            /*������������ȫ�������ͿɶԵ�ǰ���ݿ������Ӧ�Ĳ�����*/
	    /**
            * 3. �������Ϳ���ʹ��mongo���ݿ����������ݿ����,��ϸ����������ο�java-mongodb�ٷ��ĵ�
	    */
 
            //���ϲ�����ҪԤ�ȴ���
	    DBCollection mongoCollection = mongoDB.getCollection("test_mongo");
 
            //��������
            DBObject data1 = new BasicDBObject();
            data1.put("no", 2007);
            data1.put("name", "this is a test message");
	    mongoCollection.insert(data1);
	    mongoClient.close();
	} catch (Exception e) {
	   //e.printStackTrace(resp.getWriter());
	}
        
        
}
	public DBCollection getMongoCollection(){
    	return mongoCollection;
    }
 
}