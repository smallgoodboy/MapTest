package org.openstreetmap.gui.jmapviewer.config;

public class JMapConfigure {

	public static String AbstractOsmTileSourceAttributionLinkURL = "http://openstreetmap.org/";
	public static String AbstractOsmTileSourceTermsOfUseURL = "http://www.openstreetmap.org/copyright";
	public static final String OsmTileSourceMAP_MAPNIK = "http://tile.openstreetmap.org";
	
	private static final String CycleMapPATTERN = "http://%s.tile.opencyclemap.org/cycle";
	private static final String[] CycleMapSERVER = { "a", "b", "c" };
	
//	public static String AbstractOsmTileSourceAttributionLinkURL = "http://openstreetmap.org/";
//	public static String AbstractOsmTileSourceTermsOfUseURL = "http://www.openstreetmap.org/copyright";
//	public static final String OsmTileSourceMAP_MAPNIK = "http://toolserver.org/tiles/hikebike";
//	
//	private static final String CycleMapPATTERN = "http://%s.tile.openstreetmap.org";
//	private static final String[] CycleMapSERVER = { "a", "b", "c" };
	
	public static String getAbstractOsmTileSourceAttributionLinkURL(){
		return AbstractOsmTileSourceAttributionLinkURL;
	}
	
	public static String getAbstractOsmTileSourceTermsOfUseURL(){
		return AbstractOsmTileSourceTermsOfUseURL;
	}
	
	public static String getOsmTileSourceMAP_MAPNIK(){
		return OsmTileSourceMAP_MAPNIK;
	}
	
	public static String getCycleMapPATTERN(){
		return CycleMapPATTERN;
	}
	
	public static String[] getCycleMapSERVER(){
		return CycleMapSERVER;
	}
}
