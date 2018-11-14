window.registerExtension('rci/rci_page', function(options) {
	(function(){
		var key = options.component.key;
		var url = baseUrl + '/api/rci/rciPage?key=' + key;
		
		$.when(
			$.getScript(baseUrl + '/static/rci/js/amcharts/amcharts.js')
		).done(function(){
			$.getScript(baseUrl + '/static/rci/js/amcharts/gauge.js')
		}).done(function(){
			$.getScript(baseUrl + '/static/rci/js/amcharts/themes/light.js')
		}).done(function(){
			$.getScript(baseUrl + '/static/rci/js/MetricCharts/RulesComplianceChart.js')
		}).done(function(){
			jQuery.ajax({
				type: "GET",
				url: url,
				dataType: "text",
				success: function(data, textStatus, jqXHR){
				    if (document.getElementById('rci-div')) {
						options.el.removeChild(document.getElementById('rci-div'));
				    }
				    
				    var body = document.createElement('div');
				    body.setAttribute("id", "rci-div");
				    body.innerHTML = data;
				    options.el.appendChild(body);
				    
				    renderGauge("rulesCompliance-script", "rulesComplianceChartScript");
				    
		        	function renderGauge(idNewScript, idChartScript){
		        		var newScriptElement = document.createElement('script');
    		        	newScriptElement.setAttribute("id", idNewScript);
    		        	newScriptElement.type = 'text/javascript';
    		        	var chartScript = document.getElementById(idChartScript);
    		        	if ( chartScript ) {
	    		        	var chartScriptCode = chartScript.innerHTML;
	    		        	newScriptElement.appendChild(document.createTextNode(chartScriptCode));
	    		        	document.body.appendChild(newScriptElement);	
    		        	}
		        	}
				}
			})
		})
	})();
	
    return function() {
		options.el.textContent = '';
    };
    
});