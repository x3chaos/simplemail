var defaultTags = [ 'Asdf!', 'w00t', '1337', 'Yay!', 'Shawn Lutch', 'Winner!' ];
var sources = {
	fb: [ 'Facebook!' ];
	twit: [ 'Twitter!' ];
	gh: [ 'GitHub!' ];
}

function getUrlVars() {
	var vars = {};
	var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
		vars[key] = value;
	});
	return vars;
}

function getTag() {
	var src = getUrlVars()["src"];
	var tags = (src == null || !sources.hasOwnProperty(src)) ? defaultTags : sources[src];
	return tags[Math.floor(Math.random() * tags.length)];
}

$(document).ready(function() {
	$("#tagline").text(getTag(getTags()));
});
