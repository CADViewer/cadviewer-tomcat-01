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
						redline_font_size: "undefined",
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
						,
					2: {
						node: "RED_3",
						name: "redline3",
						id: "3",
						layer: "RedLineLayer",
						group: "unassigned",
						color: "#FF0000",
						strokeWidth: "8",
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
						,
					3: {
						node: "SNOTE_1",
						name: "Bob Smith",
						id: "1",
						layer: "RedlineLayer",
						group: "unassigned",
						text: "This is a Test!",
						userid: "user_1",
						currentPage: 2,
						date: "Wed Aug 18 2021 14:15:09 GMT+0200 (centraleuropeisk sommartid)",
						linked: true,
						transform: "T1906.1140082644629,373.4789256198347S3.345",
						drawingRotation: 0
					}
}

function cvjs_setUpStickyNotesRedlines(paper){

var cItemRed1= paper.path("M0,0")
.data("node","RED_1");
vqRedlines.push(cItemRed1);

cvjs_stickyNotesRedlines.push(cvjs_stickyNotesRedlines_Base[0]);

var cItemRed2= paper.path("M529.038762802785,486.68532522277894h382.5801997894155v76.0932994056296h-382.5801997894155v-76.0932994056296 Z   ").attr({stroke: "#FF0000", "stroke-width": "0.5", "fill": "#FF0000", "fill-opacity": "0.1"})
.data("node","RED_2");
vqRedlines.push(cItemRed2);

cvjs_stickyNotesRedlines.push(cvjs_stickyNotesRedlines_Base[1]);

var cItemRed3= paper.path("M530.5173140495868,1050.495867768595h381.495867768595v196.2768595041322h-381.495867768595v-196.2768595041322 Z  ").attr({stroke: "#FF0000", "stroke-width": "8", "fill": "#FF0000", "fill-opacity": "0.1"})
.data("node","RED_3");
vqRedlines.push(cItemRed3);

cvjs_stickyNotesRedlines.push(cvjs_stickyNotesRedlines_Base[2]);

 cvjs_redline=3; 

var itemSNote1=paper.group(paper.path("M0,0h20v20h-20v-20Z").attr({stroke: "#000000", "stroke-width" : "1.0"}),paper.path("M7,12h1.3M9.5,12h3.5M11.25,12v5M9.75,12v1.75M12.75,12v1.75M10.5,17h1.5M7,13.5h1.3M7,15.25h3M7,17.0h2.2").attr({stroke: "#000000", "stroke-width" : "0.5"}),paper.text(6,9,"#1").attr({"font-size": "8px", "font-style": "italic", stroke: "#000000", "stroke-width" : "0.3", "stroke" : "0.3", "fill" : "#000000", "fill-opacity" : 1.0})).attr({'transform': "t0,0S0.01" , "opacity" : "0.1"}).attr({	fill: '#FFF', "fill-opacity": "0.8", stroke: '#000', 'stroke-opacity': "1" })
.data("node","SNOTE_1");
vqStickyNotes.push(itemSNote1);

cvjs_stickyNotesRedlines.push(cvjs_stickyNotesRedlines_Base[3]);

 cvjs_stickynote=1; 

}

jQuery(document).ready(function() { 
	stickynotesRedlines_loaded = true; 
}); 
stickynotesRedlines_loaded = true; 
