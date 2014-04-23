// License: GPL. For details, see LICENSE file.
package org.openstreetmap.gui.jmapviewer;

import javax.swing.JFrame;

public class ShowTest extends JFrame{
    public ShowTest(){
        setVisible(true);
    }
    public static void main(String args[]){
        JMapViewer map = new JMapViewer();
        ShowTest st = new ShowTest();
        st.add(map);
        LayerGroup germanyGroup = new LayerGroup("zaozhuang");
        Layer germanyEastLayer = germanyGroup.addLayer("dazaozhuang");
        MapMarkerDot eberstadt = new MapMarkerDot(germanyEastLayer, "我大枣庄", 34.48, 117.16);
        eberstadt.radius = 20;
        map.addMapMarker(eberstadt);

    }

}
