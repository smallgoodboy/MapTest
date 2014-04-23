// License: GPL. For details, see Readme.txt file.
package org.openstreetmap.gui.jmapviewer.tilesources;

import org.openstreetmap.gui.jmapviewer.config.JMapConfigure;

/**
 * OSM Tile source.
 */
public class OsmTileSource {

    /**
     * The default "Mapnik" OSM tile source URL
     */
    //public static final String MAP_MAPNIK = "http://tile.openstreetmap.org";

    /**
     * The default "Mapnik" OSM tile source.
     */
    public static class Mapnik extends AbstractOsmTileSource {

        /**
         * Constructs a new {@code "Mapnik"} tile source.
         */
        public Mapnik() {
            super("Mapnik", JMapConfigure.getOsmTileSourceMAP_MAPNIK());
        }

        public TileUpdate getTileUpdate() {
            return TileUpdate.IfNoneMatch;
        }
    }

    /**
     * The "Cycle Map" OSM tile source.
     */
    public static class CycleMap extends AbstractOsmTileSource {

        //private static final String PATTERN = "http://%s.tile.opencyclemap.org/cycle";

        //private static final String[] SERVER = { "a", "b", "c" };

        private int SERVER_NUM = 0;

        /**
         * Constructs a new {@code CycleMap} tile source.
         */
        public CycleMap() {
            super("Cyclemap", JMapConfigure.getCycleMapPATTERN());
        }

        @Override
        public String getBaseUrl() {
            String url = String.format(this.baseUrl, new Object[] { JMapConfigure.getCycleMapSERVER()[SERVER_NUM] });
            SERVER_NUM = (SERVER_NUM + 1) %  JMapConfigure.getCycleMapSERVER().length;
            return url;
        }

        @Override
        public int getMaxZoom() {
            return 18;
        }

        public TileUpdate getTileUpdate() {
            return TileUpdate.LastModified;
        }
    }
}
