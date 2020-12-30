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

def holeParts = s.extrudeLayer(10,"holes")
def outsideParts = s.extrudeLayer(10,"outsides")
					.collect{it.difference(holeParts)}
def boarderParts = s.extrudeLayer(5,"boarder")

return [outsideParts,boarderParts]