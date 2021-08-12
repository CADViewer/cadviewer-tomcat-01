var cvjs_stickyNotesRedlines_Base = {
					0: {
						node: "RED_1",
						name: "redline1",
						id: "1",
						layer: "RedLineLayer",
						group: "unassigned",
						color: "#FF0000",
						strokeWidth: "0.5",
						fill: "#FFF",
						username: "Bob Smith",
						userid: "user_1",
						currentPage: 2,
						triangle_design: "none",
						polypath_arrow: "none",
						redline_text: "%EC%95%88%EB%85%95%ED%95%98%EC%84%B8%EC%9A%94%20%EC%84%B8%EA%B3%84",
						redline_font_size: "10",
						fill_opacity: "0.01",
						transform: "T674.8842533302417,528.9593804481287S3.5",
						drawingRotation: 0
					}
						,
					1: {
						node: "RED_2",
						name: "redline2",
						id: "2",
						layer: "RedLineLayer",
						group: "unassigned",
						color: "#FF0000",
						strokeWidth: "0.5",
						fill: "#FF0000",
						username: "Bob Smith",
						userid: "user_1",
						currentPage: 2,
						triangle_design: "none",
						polypath_arrow: "none",
						redline_text: "none",
						redline_font_size: "undefined",
						fill_opacity: "0.1",
						transform: "none",
						drawingRotation: 0
					}
}

function cvjs_setUpStickyNotesRedlines(paper){

var cItemRed1= paper.path("M0,0")
.data("node","RED_1");
vqRedlines.push(cItemRed1);

cvjs_stickyNotesRedlines.push(cvjs_stickyNotesRedlines_Base[0]);

var cItemRed2= paper.path("M529.038762802785,486.68532522277894h382.5801997894155v76.0932994056296h-382.5801997894155v-76.0932994056296 Z ").attr({stroke: "#FF0000", "stroke-width": "0.5", "fill": "#FF0000", "fill-opacity": "0.1"})
.data("node","RED_2");
vqRedlines.push(cItemRed2);

cvjs_stickyNotesRedlines.push(cvjs_stickyNotesRedlines_Base[1]);

 cvjs_redline=2; 

 cvjs_stickynote=0; 

}

jQuery(document).ready(function() { 
	stickynotesRedlines_loaded = true; 
}); 
stickynotesRedlines_loaded = true; 
