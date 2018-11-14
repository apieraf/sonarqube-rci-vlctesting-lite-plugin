function makeRulesComplianceChart(aValue, bValue, cValue, dValue, rulesCompliance, title){
	AmCharts.makeChart("rulesComplianceChart", {
	  "theme": "light",
	  "type": "gauge",
	  "titles": [
			{
				"text": title,
				"size": 20
			}],
	  "axes": [{
	    "topTextFontSize": 20,
	    "topTextYOffset": 45,
	    "topText": rulesCompliance + "%",
	    "axisThickness": 1,
	    "endValue": 100,
	    "gridInside": true,
	    "inside": true,
	    "radius": "50%",
	    "valueInterval": 20,
	    "startAngle": -90,
	    "endAngle": 90,
	    "unit": "%",
	    "bandOutlineAlpha": 0,
	    "bands": [{
	      "color": "#e00",
	      "endValue": dValue,
	      "innerRadius": "105%",
	      "radius": "170%",
	      "gradientRatio": [0.5, 0, -0.5],
	      "startValue": 0
	    }, {
	      "color": "#ed7d20",
	      "endValue": cValue,
	      "innerRadius": "105%",
	      "radius": "170%",
	      "gradientRatio": [0.5, 0, -0.5],
	      "startValue": dValue
	    }, {
	      "color": "#eabe06",
	      "endValue": bValue,
	      "innerRadius": "105%",
	      "radius": "170%",
	      "gradientRatio": [0.5, 0, -0.5],
	      "startValue": cValue
	    }, {
	      "color": "#b0d513",
	      "endValue": aValue,
	      "innerRadius": "105%",
	      "radius": "170%",
	      "gradientRatio": [0.5, 0, -0.5],
	      "startValue": bValue
	    }, {
	      "color": "#0a0",
	      "endValue": 100,
	      "innerRadius": "105%",
	      "radius": "170%",
	      "gradientRatio": [0.5, 0, -0.5],
	      "startValue": aValue
	    }]
	  }],
	  "arrows": [{
	    "alpha": 1,
	    "innerRadius": "40%",
	    "nailRadius": 0,
	    "radius": "160%",
	    "value": rulesCompliance
	  }]
	});
}

