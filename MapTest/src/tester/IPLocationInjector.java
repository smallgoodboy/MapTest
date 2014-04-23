package tester;

import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.csvreader.CsvReader;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.BasicDBObject;

public class IPLocationInjector {
	String IP2RegionCodeFile = "E:\\GeoLiteCity_20100401\\GeoLiteCity-Blocks.csv";
	String Region2LongAttFile = "E:\\GeoLiteCity_20100401\\GeoLiteCity-Location.csv";
	DBCollection IP2RegionCodeDBcollection, Region2LongAttDBcollection;
	public IPLocationInjector(){
		initMongoDB();
	}
	private void initMongoDB(){
		MongoClient mongoClient;
		try {
			mongoClient = new MongoClient("202.115.30.191", 12345);
			DB db = mongoClient.getDB("test");
			
			IP2RegionCodeDBcollection = db.getCollection("IP2Region");
			Region2LongAttDBcollection = db.getCollection("Region2LongAtt");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	@SuppressWarnings("unused")
	private void readIP2RegionCode(){
		try {
			List<DBObject> DBDataList = new ArrayList<DBObject>();
			CsvReader IP2RegionCodeReader = new CsvReader(IP2RegionCodeFile,',',Charset.forName("SJIS"));
			IP2RegionCodeReader.readHeaders();
			//int counter = 0;
			IP2RegionCodeReader.readRecord();
			BasicDBObject dbInputer;
			while(IP2RegionCodeReader.readRecord()){ //逐行读入除表头的数据      
	             //System.out.println(IP2RegionCodeReader.getValues()[2]);
				dbInputer = new BasicDBObject("StartIP",(int)Long.valueOf(IP2RegionCodeReader.getValues()[0]).longValue())
				.append("EndIP",(int) Long.valueOf(IP2RegionCodeReader.getValues()[1]).longValue())
				.append("Region", Long.valueOf(IP2RegionCodeReader.getValues()[2]).intValue());
				DBDataList.add(dbInputer);
				if(DBDataList.size()>=2000){
					IP2RegionCodeDBcollection.insert(DBDataList);
					DBDataList = new ArrayList<DBObject>();
				}
	         } 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private void readRegion2LongAtt(){
		List<DBObject> DBDataList = new ArrayList<DBObject>();
		CsvReader Region2LongAttReader;
		BasicDBObject dbInputer;
		int counter =0;
		try {
			Region2LongAttReader = new CsvReader(Region2LongAttFile,',',Charset.forName("SJIS"));
			Region2LongAttReader.readHeaders();
			Region2LongAttReader.readRecord();
			while(Region2LongAttReader.readRecord()){
				//dbInputer = new BasicDBObject();
				DBObject updatedValue=new BasicDBObject();
				DBObject updateCondition=new BasicDBObject();
//				if(Long.valueOf(Region2LongAttReader.getValues()[0]).intValue() == 341){
//					System.out.println("hah");
//				}
				updateCondition.put("Region", Long.valueOf(Region2LongAttReader.getValues()[0]).intValue());
				if(Region2LongAttReader.getValues()[5].length()>=10 || Region2LongAttReader.getValues()[6].length()>=10){
					continue;
				}
				updatedValue.put("latitude", Double.valueOf(Region2LongAttReader.getValues()[5]));
				updatedValue.put("longitude", Double.valueOf(Region2LongAttReader.getValues()[6]));
				dbInputer = new BasicDBObject("$set",updatedValue);
				IP2RegionCodeDBcollection.update(updateCondition, dbInputer, false, true);
				if(counter++%100 == 0){
					System.out.println(counter);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String get(String abc){
		//String abc = cin.next();
		String aa[];
		aa = abc.split("\\.");
		int ip = Integer.valueOf(aa[0].toString()).intValue()<<24;
			ip += Integer.valueOf(aa[1].toString()).intValue()<<16;
			ip += Integer.valueOf(aa[2].toString()).intValue()<<8;
			ip += Integer.valueOf(aa[3].toString()).intValue();
			//ip = 1964173312;
		BasicDBObject finder = new BasicDBObject("StartIP", new BasicDBObject("$lte", ip))
		.append("EndIP", new BasicDBObject("$gte", ip));
		DBCursor cursor = IP2RegionCodeDBcollection.find(finder);
		if(cursor.hasNext()){
			DBObject cursorDBObject = cursor.next();
			return (cursorDBObject.get("latitude") + "," + cursorDBObject.get("longitude"));
			}else{
				System.out.println("kaka");
				return null;
			}
	}
	
	@SuppressWarnings("resource")
	public static void main(String args[]){
		@SuppressWarnings("unused")
		BaiduAppEngin BAE = new BaiduAppEngin();
//		BAE.doGet(req, resp);
		IPLocationInjector ili = new IPLocationInjector();
		System.out.println(Long.valueOf("2147549184").intValue());
		ili.initMongoDB();
		@SuppressWarnings("unused")
		Scanner cin=new Scanner(System.in);
//		ili.readIP2RegionCode();
		//ili.readRegion2LongAtt();
//		for(;;){
//			String abc = cin.next();
//			String aa[];
//			aa = abc.split("\\.");
//			int ip = Integer.valueOf(aa[0].toString()).intValue()<<24;
//				ip += Integer.valueOf(aa[1].toString()).intValue()<<16;
//				ip += Integer.valueOf(aa[2].toString()).intValue()<<8;
//				ip += Integer.valueOf(aa[3].toString()).intValue();
//				//ip = 1964173312;
//			BasicDBObject finder = new BasicDBObject("StartIP", new BasicDBObject("$lte", ip))
//			.append("EndIP", new BasicDBObject("$gte", ip));
//			DBCursor cursor = ili.IP2RegionCodeDBcollection.find(finder);
//			if(cursor.hasNext()){
//				DBObject cursorDBObject = cursor.next();
//				System.out.println(cursorDBObject.get("latitude") + "," + cursorDBObject.get("longitude"));
//				}else{
//					System.out.println("kaka");
//				}
//		}
	}
}
