// License: GPL. For details, see LICENSE file.
package tester;

import java.util.Scanner;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.LayerGroup;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapRectangleImpl;

public class ShowTest extends JFrame{
    public ShowTest(){
        setVisible(true);
        
        setSize(1500, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String args[]){
        JMapViewer map = new JMapViewer();
        ShowTest st = new ShowTest();
        st.add(map);
        LayerGroup germanyGroup = new LayerGroup("zaozhuang");
        Layer germanyEastLayer = germanyGroup.addLayer("dazaozhuang");
        MapMarkerDot eberstadt = new MapMarkerDot(germanyEastLayer, "hhhh", 30.66667, 104.06667);
        eberstadt.setRadius(5);
        //map.addMapMarker(eberstadt);
        map.setScrollWrapEnabled(true);
        
//        map.addMapMarker(new MapMarkerCircle(53.343, -6.267, 0.666));
//        map.addMapRectangle(new MapRectangleImpl(germanyEastLayer, "Wales", new Coordinate(53.35,-4.57), new Coordinate(53.35,-2.63)));
        Scanner cin=new Scanner(System.in);
        IPLocationInjector ili = new IPLocationInjector();
        String haha;
        for(;;){
        	//map.removeMapMarker(eberstadt);
			String abc = cin.next();
			haha = ili.get(abc);
			if(haha == null){continue;}
			System.out.println(haha);
			eberstadt = new MapMarkerDot(germanyEastLayer, haha, Double.valueOf(haha.split(",")[0]), Double.valueOf(haha.split(",")[1]));
			eberstadt.setRadius(3);
	        map.addMapMarker(eberstadt);
			}
    }

}
