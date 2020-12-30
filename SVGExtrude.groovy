import eu.mihosoft.vrl.v3d.svg.*;
import eu.mihosoft.vrl.v3d.Extrude;

File f = ScriptingEngine
	.fileFromGit(
		"https://github.com/madhephaestus/SVGBowlerExtrude.git",//git repo URL
		"master",//branch
		"drawing.svg"// File from within the Git repo
	)
println "Extruding SVG "+f.getAbsolutePath()

SVGLoad s = new SVGLoad(f.toURI())
println "Layers= "+s.getLayers()
// A map of layers to polygons
HashMap<String,List<Polygon>> polygonsByLayer = s.toPolygons()
// extrude all layers to a map to 10mm thick
HashMap<String,ArrayList<CSG>> csgByLayers = s.extrudeLayers(10)
// extrude just one layer to 10mm
def holeParts = s.extrudeLayerToCSG(10,"holes")
// seperate holes and outsides using layers to differentiate
def outsideParts = s.extrudeLayerToCSG(10,"outsides")
					.difference(holeParts)
// layers can be extruded at different depths					
def boarderParts = s.extrudeLayerToCSG(5,"boarder")

return CSG.unionAll([boarderParts,outsideParts])